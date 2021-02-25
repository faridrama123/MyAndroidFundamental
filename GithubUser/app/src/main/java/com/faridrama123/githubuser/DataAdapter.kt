package com.faridrama123.githubuser

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.faridrama123.githubuser.databinding.ItemsDatauserBinding
import java.util.ArrayList

class DataAdapter : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var dataset = ArrayList<DataUser>()

    fun set(data: List<DataUser>?) {
        if (data == null) return
        this.dataset.clear()
        this.dataset.addAll(data)
    }

    class ViewHolder(private val binding: ItemsDatauserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (datauser: DataUser){
            with(binding){
                text1.text = datauser.name
                text2.text = itemView.resources.getString(R.string.followers,datauser.follower)
                img1.setImageResource(datauser.avatar)
                itemView.setOnClickListener {
                    val datauser = DataUser (
                        datauser.username,
                        datauser.name,
                        datauser.avatar,
                        datauser.company,
                        datauser.location,
                        datauser.repository,
                        datauser.follower,
                        datauser.following)

                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA, datauser)
                    itemView.context.startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsDataUserBinding = ItemsDatauserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsDataUserBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datauser = dataset[position]
        holder.bind(datauser)
    }

    override fun getItemCount(): Int {
       return dataset.size
    }
}