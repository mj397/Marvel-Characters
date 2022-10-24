package com.app.marvelcharacters

//class TestClass : AppCompatActivity(), Serializable {
//    private var listView: ListView? = null
//    private var progressBar: ProgressBar? = null
//    private var mContext: Context? = null
//    private var tvLoading: TextView? = null
//    private val status: String? = null
//    private var viewModel: MainActivityViewModel? = null
//    private var adapter: ListViewAdapter? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        this.setContentView(R.layout.activity_main)
//        listView = findViewById(R.id.list_view)
//        progressBar = findViewById(R.id.progress_bar)
//        tvLoading = findViewById(R.id.text_status)
//        mContext = this
//
////        status = "Loading...";
//        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
//        viewModel!!.init(this)
//        adapter = ListViewAdapter(viewModel!!.results.value, this)
//        listView.setAdapter(adapter)
//        viewModel!!.results.observe(this) { results: ArrayList<Results?>? -> adapter!!.notifyDataSetChanged() }
//    }
//}