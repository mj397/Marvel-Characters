package com.app.marvelcharacters.remote

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.app.marvelcharacters.models.AppExecutors
import com.app.marvelcharacters.models.Constants
import com.app.marvelcharacters.models.ResponseObject
import com.app.marvelcharacters.models.Results
import com.app.marvelcharacters.remote.RestClient.client
import com.app.marvelcharacters.viewmodels.MainActivityViewModel
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.sql.Timestamp
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

class CharacterAPIClient {
    val charactersList: MutableLiveData<ArrayList<Results>>
    val TAG: String = "CharacterAPIClient"


    private var retrieveCharactersRunnable: RetrieveCharactersRunnable? = null
    fun searchCharacters() {
        Log.d(TAG, "SearchValue " +1)
        if (retrieveCharactersRunnable != null) {
            retrieveCharactersRunnable = null
        }
        retrieveCharactersRunnable = RetrieveCharactersRunnable()
        val handler: Future<*> =
            AppExecutors.instance?.networkIO()!!.submit(retrieveCharactersRunnable)
        AppExecutors.instance!!.networkIO()
            .schedule(Runnable { handler.cancel(true) }, 15000000, TimeUnit.MICROSECONDS)
    }

    private inner class RetrieveCharactersRunnable : Runnable {
        private var cancelRequest = false
        override fun run() {
            Log.d(TAG, "SearchValue " +2)
            val timestamp = Timestamp(1).toString()
            try {
                Log.d(TAG, "SearchValue " +3)
                val response: Response<*> = getCharacters(
                    timestamp, Constants().generateHashCode(
                        timestamp, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY
                    ), Constants.PUBLIC_KEY, 100
                )!!.execute()
                if (cancelRequest) {
                    return
                }
                if (response.code() == 200) {
                    //list available
                    val list = ArrayList((response.body() as ResponseObject?)!!.data!!.results)
                    Log.d(TAG,  "ResultSize "+ list.size)
                    charactersList.postValue(list)
                } else {
                    val error = response.errorBody().toString()
                    Log.d(TAG, "ErrorMessage $error")
                    charactersList.postValue(null)
                }
            } catch (e: IOException) {
                Log.d(TAG, "ErrorException " + e.printStackTrace())
                Log.d(TAG, "ErrorException " + e.message)
                Log.d(TAG, "ErrorException " + e.stackTraceToString())
                Log.d(TAG, "ErrorException " + e.cause.toString())
                e.printStackTrace()
                charactersList.postValue(null)
            }
            if (cancelRequest) {
                return
            }
        }

        private fun getCharacters(
            ts: String,
            hash: String,
            apiKey: String,
            offset: Int
        ): Call<ResponseObject?>? {
            return client!!.create(ApiListener::class.java)
                .getCharacters(apiKey, ts, hash, offset)
        }

        private fun cancelRequest() {
            Log.d(TAG, "Cancelling Search Request")
            cancelRequest = true
        }
    }

    companion object {
        var instance: CharacterAPIClient? = null
            get() {
                if (field == null) {
                    field = CharacterAPIClient()
                }
                return field
            }
            private set
        private const val TAG = "CharacterAPIClient"
    }

    init {
        charactersList = MutableLiveData()
    }

//
//        class CacheWorker(context: Context, workerParams: WorkerParameters) :
//        Worker(context, workerParams) {
//        /**
//         * Do your background processing here. doWork() is called on a
//         * background thread - you are required to synchronously do your work and return the
//         * Result from this method. Once you return from the
//         * method, the Worker is considered to have finished what it's doing and will be destroyed. If
//         * you need to do your work asynchronously on a thread of your own choice, see ListenableWorker.
//         *
//         *
//         * A Worker is given a maximum of ten minutes to finish its execution and return a
//         * Result. After this time has expired, the Worker will be signaled to stop.
//         */
//        override fun doWork(): Result {
//            Log.d(TAG, "BackGround Worker Running")
//            when (instance!!.type) {
//                0 -> cacheCharacters()
//                1 -> geCharacters()
//            }
//            return Result.success()
//        }
//
//        private fun geCharacters() {
//            instance!!.charactersList = instance!!.db!!.charactersDb()
//                .charactersList()
//            println("ArraySizeData: " + instance!!.db!!.charactersDb()
//                .charactersList()?.size)
//        }
//
//        private fun cacheCharacters() {
//            Log.d(TAG, "AccessedValue " + true)
//            for (result in instance?.charactersList!!) {
//                if (instance?.db?.charactersDb()!!
//                        .charactersList() == null) {
////                    Log.d(TAG, "Added Character " + result?.name)
//                    instance!!.db!!.charactersDb().insert(result)
//                }
//                else{Log.d(TAG, "AccessedValue " + 1)}
//            }
//        }
//
//        companion object {
//            private const val TAG = "CacheWorkerClass"
//        }
//    }
//
//    companion object {
//        private var instance: CharactersRepo? = null
//    }

}