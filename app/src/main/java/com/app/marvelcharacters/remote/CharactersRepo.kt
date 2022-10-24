//package com.app.marvelcharacters.remote
//
//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.work.OneTimeWorkRequest
//import androidx.work.WorkManager
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import com.app.marvelcharacters.models.*
//import com.app.marvelcharacters.remote.RestClient.client
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class CharactersRepo {
//    private var resultsList: ArrayList<Character?>? = ArrayList()
//    private var charactersList: List<Character?>? = ArrayList()
//    private var context: Context? = null
//    private var workRequest: OneTimeWorkRequest? = null
//    private var db: CharactersDb? = null
//    private var type = 0
//    private var count = 0
//    val liveData = MutableLiveData<List<Character?>?>()
//
//    constructor() {}
//        constructor(context: Context?) {
//        this.context = context
//        workRequest = OneTimeWorkRequest.Builder(CacheWorker::class.java).build()
//        db = getInstance(context!!)
//        if (instance == null) {
//            instance = CharactersRepo()
//        }
//    }
//
////    val data: MutableLiveData<List<Character?>?>
////        get() {
////            val timestamp = Timestamp(1).toString()
////            getCharacters(
////                timestamp, Constants().generateHashCode(
////                    timestamp,
////                    Constants.PRIVATE_KEY,
////                    Constants.PUBLIC_KEY
////                ),
////                Constants.PUBLIC_KEY, 100
////            )
////            val data = MutableLiveData<List<Character?>?>()
////            data.value = charactersList
////            Log.d("ResponseResult", "ArraySizeFinal " + data.value?.size)
////            return data
////        }
//
//
//    fun getList(): LiveData<List<Character?>?>
//    {
//        return liveData
//    }
//
//
//     fun getCharacters(ts: String, hash: String, apiKey: String, offset: Int) {
//        val call = client!!.create(
//            ApiListener::class.java
//        ).getCharacters(apiKey, ts, hash, offset)
//        call!!.enqueue(object : Callback<ResponseObject?> {
//            override fun onResponse(call: Call<ResponseObject?>, response: Response<ResponseObject?>) {
//
//                Log.d("ResponseResult", "Code " + response.code())
//                val `object` = response.body()
//                if (`object` != null) {
//                    type = 0
//
//                    for (result in response.body()!!.data!!.results!!) {
////                        resultsList?.add(
////                            Character(0,
////                                result.name,
////                                result.description,
////                                result.id!!.toInt())
////                        )
//                    }
//
//                    charactersList = resultsList
//                } else {
//                    type = 1
//                }
//
//                liveData.value = charactersList
//
//                Log.d("ResponseResult", "ArraySize " + charactersList?.size)
//                Log.d("ResponseResult", "ArraySize " + resultsList?.size)
//                WorkManager.getInstance(context!!).enqueue(workRequest!!)
////                call.cancel()
//            }
//            override fun onFailure(call: Call<ResponseObject?>, e: Throwable) {
//                println("ErrorMessage: " + e.message)
//                type = 1
//                WorkManager.getInstance(context!!).enqueue(workRequest!!)
//                liveData.value = null
////                call.cancel()
//            }
//        })
//    }
//
//    class CacheWorker(context: Context, workerParams: WorkerParameters) :
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
//}