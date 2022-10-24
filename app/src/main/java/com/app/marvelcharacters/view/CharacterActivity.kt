package com.app.marvelcharacters.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.marvelcharacters.models.Constants
import com.app.marvelcharacters.R
import com.app.marvelcharacters.adapters.ResultListAdapter
import com.app.marvelcharacters.models.Results
import com.bumptech.glide.Glide

class CharacterActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private var tvName: TextView? = null
    private var tvId: TextView? = null
    private var tvHeader: TextView? = null
    private var tvHeader1: TextView? = null
    private var tvHeader2: TextView? = null
    private var tvHeader3: TextView? = null
    private var listView: ListView? = null
    private var listView1: ListView? = null
    private var listView2: ListView? = null
    private var listView3: ListView? = null
    private var result: Results? = null
    private var tvDesc: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        imageView = findViewById(R.id.image_view)
        tvName = findViewById(R.id.name_text)
        tvId = findViewById(R.id.id_text)
        tvHeader = findViewById(R.id.header_text)
        tvHeader1 = findViewById(R.id.header_text1)
        tvHeader2 = findViewById(R.id.header_text2)
        tvHeader3 = findViewById(R.id.header_text3)
        tvDesc = findViewById(R.id.desc_text)

        //comics
        listView = findViewById(R.id.list_view)
        //events
        listView1 = findViewById(R.id.list_view1)
        //stories
        listView2 = findViewById(R.id.list_view2)
        //series
        listView3 = findViewById(R.id.list_view3)
        pageInfo
    }

    private val pageInfo: Unit
        private get() {
            val intent = intent
            if (intent.extras != null) {
                result = intent.getSerializableExtra("ResultsItem") as Results?
            }
            Glide.with(this)
                .load(
                    result!!.thumbnail?.path?.let {
                        result!!.thumbnail?.extension?.let { it1 ->
                            Constants().getImagePath(
                                it,
                                it1
                            )
                        }
                    }
                )
                .into(imageView!!)
            tvName!!.text = result!!.name
            tvId!!.text = result!!.characterId
            listView!!.visibility = View.GONE
            listView1!!.visibility = View.GONE
            listView2!!.visibility = View.GONE
            listView3!!.visibility = View.GONE
            tvHeader!!.visibility = View.GONE
            tvHeader1!!.visibility = View.GONE
            tvHeader2!!.visibility = View.GONE
            tvHeader3!!.visibility = View.GONE
            if (!result!!.comics?.items?.isEmpty()!!) {
                listView!!.visibility = View.VISIBLE
                tvHeader!!.visibility = View.VISIBLE
                listView!!.adapter = result!!.comics?.items?.let { ResultListAdapter(it, this, "comics") }
            }
            if (!result!!.events?.items?.isEmpty()!!) {
                listView1!!.visibility = View.VISIBLE
                tvHeader1!!.visibility = View.VISIBLE
                listView1!!.adapter = result!!.events?.items?.let { ResultListAdapter(it, this, "events") }
            }
            if (!result!!.stories?.items?.isEmpty()!!) {
                listView2!!.visibility = View.VISIBLE
                tvHeader2!!.visibility = View.VISIBLE
                listView2!!.adapter = result!!.stories?.items?.let { ResultListAdapter(it, this, "stories") }
            }
            if (!result!!.series?.items?.isEmpty()!!) {
                listView3!!.visibility = View.VISIBLE
                tvHeader3!!.visibility = View.VISIBLE
                listView3!!.adapter = result!!.series?.items?.let { ResultListAdapter(it, this, "series") }
            }
            tvDesc?.text = result?.description
        }
}