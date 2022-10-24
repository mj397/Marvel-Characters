package com.app.marvelcharacters.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.app.marvelcharacters.models.Items
import com.app.marvelcharacters.R
import java.io.Serializable

class ResultListAdapter(
    items: ArrayList<Items>,
    private val mContext: Context,
    private val type: String
) : BaseAdapter(), Serializable {
    private val items = ArrayList<Items>()
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(i: Int): Any {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        val view1 = view ?:
        LayoutInflater.from(mContext)
            .inflate(R.layout.list_item_layout, viewGroup, false)
        val tvItem = view1.findViewById<TextView>(R.id.item_text)
        tvItem.text = items[i].name
        return view1
    }

    init {
        var count = 0
        for (items1 in items) {
            if (count == 3) break
            this.items.add(items1)
            count++
        }
    }
}