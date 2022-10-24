package com.app.marvelcharacters.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.marvelcharacters.R
import com.app.marvelcharacters.adapters.ListViewAdapter
import com.app.marvelcharacters.models.ResponseObject
import com.app.marvelcharacters.models.Results
import com.app.marvelcharacters.remote.ApiListener
import com.app.marvelcharacters.remote.RestClient
import com.app.marvelcharacters.viewmodels.MainActivityViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainActivity : AppCompatActivity(), Serializable {
    private var listView: ListView? = null
    private var progressBar: ProgressBar? = null
    private var mContext: Context? = null
    private var tvLoading: TextView? = null
    private val status: String? = null
    private var viewModel: MainActivityViewModel? = null
    private var adapter: ListViewAdapter? = null
    private var TAG: String = "MainActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        listView = findViewById(R.id.list_view)
        progressBar = findViewById(R.id.progress_bar)
        tvLoading = findViewById(R.id.text_status)
        mContext = this

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        viewModel?.searchCharacterApi()
        observeChange()


//        viewModel!!.results?.observe(this) {
//                results: List<Character?>? -> adapter!!.notifyDataSetChanged()
//            Log.d("MainActivityClass", "ArraySize" + results?.size)
//        }


//        getCharacters()




    }




    private fun getCharacters(ts: String, hash: String, apiKey: String, offset: Int) {

        var list: ArrayList<Results>

        val call = RestClient.client!!.create(
            ApiListener::class.java
        ).getCharacters(apiKey, ts, hash, offset)
        call!!.enqueue(object : Callback<ResponseObject?> {
            override fun onResponse(call: Call<ResponseObject?>, response: Response<ResponseObject?>) {

                Log.d("ResponseResult", "Code " + response.code())
                val `object` = response.body()
                if (`object` != null) {
                    //type = 0
                    list = response.body()!!.data!!.results!!

                    adapter = ListViewAdapter(list, mContext)
                    listView?.adapter = adapter
                }


//                WorkManager.getInstance(context!!).enqueue(workRequest!!)
//                call.cancel()


            }
            override fun onFailure(call: Call<ResponseObject?>, e: Throwable) {
                println("ErrorMessage: " + e.message)
            }
        })
    }


    private fun observeChange() {
        viewModel?.result?.observe(this,
            Observer { results: java.util.ArrayList<Results>? ->
                Log.d(TAG, "IsValid " + true)
                if (results != null) {
                    listView?.adapter = ListViewAdapter(results, mContext)
                    for (result in results) {
                        Log.d(TAG,  "ResultName "+ result.name!!)
                    }
                }
            })
    }

}