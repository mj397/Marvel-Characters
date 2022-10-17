package com.app.marvelcharacters

import android.content.Context
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PackageManagerCompat.LOG_TAG
import org.apache.commons.lang3.SerializationUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.sql.DriverManager.println
import java.sql.Timestamp
import java.util.*

class MainActivity : AppCompatActivity(), Serializable {
    private var listView: ListView? = null
    private var progressBar: ProgressBar? = null
    private var mContext: Context = this
    private var dataBaseHelper: DatabaseHelper? = null
    private var tvLoading: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.list_view)
        progressBar = findViewById(R.id.progress_bar)
        tvLoading = findViewById(R.id.text_status)
        mContext = this
        dataBaseHelper = DatabaseHelper(mContext)
        CheckConnection().execute()
    }

    private fun getCharacters(ts: String, hash: String, apiKey: String, offset: String) {
        println("TimeStamp: $ts")
        println("HashValue: $hash")
        println("APIKEY: $apiKey")
        val call = RestClient.client?.create(ApiListener::class.java)
            ?.getCharacters(apiKey, ts, hash, 100)
        call!!.enqueue(object : Callback<ResponseObject?> {
            override fun onResponse(
                call: Call<ResponseObject?>,
                response: Response<ResponseObject?>
            ) {
                val `object` = response.body()
                Log.d("ObjectValue", response.code().toString())
                Log.d("ObjectValue", response.errorBody().toString())
                println("ObjectValue: " + response.code())
                println("ObjectValue: " + response.errorBody())
                tvLoading!!.text = "Status: Online"
                if (`object` != null) {

                    Log.d("ResultsSize", `object`.data?.results?.size.toString())
                    listView?.adapter = ListViewAdapter(`object`.data?.results, mContext)

                    for (result in `object`.data?.results!!) {
                        if (!dataBaseHelper!!.ifCharacterExits(result)) {
                            if (dataBaseHelper!!.writeResult(result)) {
                                Log.d("AddedCharacter", result.name.orEmpty())
                                println("AddedCharacter: " + result.name)
                            }
                        }
                    }
                }
                progressBar!!.visibility = View.GONE
            }

            override fun onFailure(call: Call<ResponseObject?>, t: Throwable) {
                Toast.makeText(mContext, "Error Getting Characters", Toast.LENGTH_SHORT).show()
                println("ErrorMessage: " + t.message)
                tvLoading!!.text = "Status: Error"
                fetchCache()
                progressBar!!.visibility = View.GONE
            }
        })
    }

    private inner class CheckConnection : AsyncTask<Void?, Void?, Void?>() {
        private var isConnected = false
        override fun onPreExecute() {
            super.onPreExecute()
            progressBar!!.visibility = View.VISIBLE
            tvLoading!!.text = "Status: Loading..."
            Toast.makeText(mContext, "Checking Internet Connection", Toast.LENGTH_SHORT).show()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            val connMgr = mContext!!.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connMgr != null) {
                val activeNetworkInfo = connMgr.activeNetworkInfo
                if (activeNetworkInfo != null) { // connected to the internet
                    // connected to the mobile provider's data plan
                    if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                        // connected to wifi
                        isConnected = true
                    } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                        isConnected = true
                    }
                }
            }
            return null
        }

        override fun onPostExecute(unused: Void?) {
            super.onPostExecute(unused)
            println("IsConnectedValue: $isConnected")
            if (isConnected) {
                val timestamp = Timestamp(1).toString()
                getCharacters(
                    timestamp,
                    Constants().generateHashCode(
                        timestamp,
                        Constants.PRIVATE_KEY,
                        Constants.PUBLIC_KEY
                    ),
                    Constants.PUBLIC_KEY, "0"
                )
                return
            }
            tvLoading!!.text = "Status: Offline"
            fetchCache()
        }
    }

    private fun fetchCache() {
        //fetching characters
        val cursor = dataBaseHelper!!.readResults()
        val arrayList = ArrayList<ByteArray>()
        while (cursor.moveToNext()) {
            if (cursor.getBlob(0) != null) {
                val result = SerializationUtils.deserialize<Results>(cursor.getBlob(0))
                println("CharacterName: " + result.name)
                arrayList.add(cursor.getBlob(0))
            }
        }
        if (!arrayList.isEmpty()) {
            val resultsList = ArrayList<Results>()
            for (array in arrayList) {
                val result = SerializationUtils.deserialize<Results>(array)
                resultsList.add(result)
            }
            listView!!.adapter = ListViewAdapter(resultsList, mContext)
            progressBar!!.visibility = View.GONE
        } else {
            Toast.makeText(mContext, "No data to present", Toast.LENGTH_SHORT).show()
        }
    }
}