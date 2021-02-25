package com.faridrama123.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faridrama123.githubuser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA = "extra_datauser"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        val dataUser = intent.getParcelableExtra<DataUser>(EXTRA) as DataUser

        activityDetailBinding.username.text = dataUser.username
        activityDetailBinding.name.text = dataUser.name
        activityDetailBinding.img1.setImageResource(dataUser.avatar)
        activityDetailBinding.company.text = dataUser.company
        activityDetailBinding.location.text = dataUser.location
        activityDetailBinding.repository.text =  resources.getString(R.string.repository,dataUser.repository)
        activityDetailBinding.followers.text = resources.getString(R.string.followers,dataUser.follower)
        activityDetailBinding.following.text = resources.getString(R.string.following,dataUser.following)


    }
}