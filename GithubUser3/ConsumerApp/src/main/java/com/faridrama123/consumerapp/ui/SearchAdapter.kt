package com.faridrama123.consumerapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.consumerapp.R
import com.faridrama123.consumerapp.databinding.SearchItemBinding
import com.faridrama123.consumerapp.entity.SearchEntity


class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private val mData = ArrayList<SearchEntity>()

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
                    Toast.makeText(
                        context,
                        context.getString(R.string.user_detail_information),
                        Toast.LENGTH_SHORT
                    ).show()
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