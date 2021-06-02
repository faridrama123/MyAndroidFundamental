package com.faridrama123.app.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.app.R
import com.faridrama123.app.data.local.entity.SearchEntity
import com.faridrama123.app.databinding.SearchItemBinding
import com.faridrama123.app.ui.detail.DetailActivity
import com.faridrama123.app.ui.detail.DetailActivity.Companion.EXTRA


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val mData = ArrayList<SearchEntity>()

    fun clearData() {
        mData.clear()
        notifyDataSetChanged()
    }

    fun setData(items: ArrayList<SearchEntity>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = SearchItemBinding.bind(itemView)
        fun bind(items: SearchEntity) {
            with(itemView){
                binding.login.text = items.login.toString()
                binding.id.text = items.id.toString()
                Glide.with(itemView.context)
                    .load(items.avatarUrl.toString()).centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.avatar)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA,items)
                    itemView.context.startActivity(intent)
                }


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
       return mData.size
    }
}