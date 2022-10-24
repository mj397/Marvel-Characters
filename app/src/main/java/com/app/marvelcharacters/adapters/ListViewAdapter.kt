package com.app.marvelcharacters.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.app.marvelcharacters.models.Results
import com.app.marvelcharacters.R
import com.app.marvelcharacters.models.Constants
import com.app.marvelcharacters.view.CharacterActivity
import com.bumptech.glide.Glide


class ListViewAdapter(

    private val results: ArrayList<Results>,
    private val mContext: Context?
) : BaseAdapter() {

    override fun getCount(): Int {
        return results!!.size
    }

    override fun getItem(i: Int): Results? {
        return results?.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val view1 = view ?:
        LayoutInflater.from(mContext)
            .inflate(R.layout.character_list_item, viewGroup, false)

        val imageView = view1?.findViewById<ImageView>(R.id.image_view)
        val tvName = view1?.findViewById<TextView>(R.id.character_name)
        val tvId = view1?.findViewById<TextView>(R.id.character_id)
        val result = results?.get(i)
        val path = Constants().getImagePath(result?.thumbnail?.path.orEmpty(), result?.thumbnail?.extension.orEmpty())
        println("NameValue: " + result?.name)
        println("IdValue: " + result?.id)
        println("ImagePath: $path")
        mContext?.applicationContext?.let {
            if (imageView != null) {
                Glide.with(it)
                    .load(path)
                    .into(imageView)
            }
        }
        if (tvName != null) {
            tvName.text = result?.name
        }
        if (tvId != null) {
            tvId.text = result?.id.toString()
        }
        view1?.setOnClickListener {
            val results = results?.get(i)
            val intent = Intent(mContext, CharacterActivity::class.java)
            println("ResultsItem: " + result?.name)
            intent.putExtra("ResultsItem", results)
            mContext?.startActivity(intent)
        }
        return view1
    }


}