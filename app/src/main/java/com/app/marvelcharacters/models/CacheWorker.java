package com.app.marvelcharacters.models;

//public class CacheWorker extends Worker {
//
//    private static final String TAG = CacheWorker.class.getName();
//    private ArrayList<Results> results;
//    private Context context;
//    private CharactersDb db;
//
//    public CacheWorker(@NonNull Context context, @NonNull WorkerParameters workerParams, ArrayList<Results> results) {
//        super(context, workerParams);
//        this.results = results;
//        this.context = context;
//        this.db = CharactersDb.getInstance(context);
//    }
//
//    /**
//     * Do your background processing here. doWork() is called on a
//     * background thread - you are required to synchronously do your work and return the
//     * Result from this method. Once you return from the
//     * method, the Worker is considered to have finished what it's doing and will be destroyed. If
//     * you need to do your work asynchronously on a thread of your own choice, see ListenableWorker.
//     *
//     * A Worker is given a maximum of ten minutes to finish its execution and return a
//     * Result. After this time has expired, the Worker will be signaled to stop.
//     */
//    @NonNull
//    @Override
//    public Result doWork() {
//
//        Log.d(TAG, "BackGround Cache Worker");
//
//        for (Results result: results)
//        {
//            if (db.charactersDb().getCharacter(result.getId()) == null)
//            {
//                Log.d(TAG, "Added Character " + result.getName());
//                db.charactersDb().insert(result);
//            }
//
//        }
//
//
//        return Result.success();
//    }
//}