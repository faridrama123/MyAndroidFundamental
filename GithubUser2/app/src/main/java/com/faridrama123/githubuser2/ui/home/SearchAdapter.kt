package com.faridrama123.githubuser2.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.githubuser2.R
import com.faridrama123.githubuser2.databinding.SearchItemBinding
import com.faridrama123.githubuser2.response.ItemsItem
import com.faridrama123.githubuser2.ui.detail.DetailActivity


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val mData = ArrayList<ItemsItem>()

    fun setData(items: ArrayList<ItemsItem>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = SearchItemBinding.bind(itemView)
        fun bind(items: ItemsItem) {
            with(itemView){
                binding.login.text = items.login.toString()
                binding.id.text = items.id.toString()
                Glide.with(itemView.context)
                    .load(items.avatarUrl.toString()).centerCrop()
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                    .into(binding.avatar)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_USERNAME,items.login)
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