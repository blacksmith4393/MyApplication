package org.zwsmith.myapplication.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.zwsmith.myapplication.R

import java.util.ArrayList

class MyListAdapter internal constructor(private var titles: ArrayList<String>?) : RecyclerView.Adapter<MyListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val titleTextView = holder.itemView.findViewById(R.id.title_tv)
        titleTextView.setText(titles!![position])
    }

    override fun getItemCount(): Int {
        return titles!!.size
    }

    fun updateTitles(titles: ArrayList<String>) {
        this.titles = titles
        notifyDataSetChanged()
    }

    internal class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView)
}
