package com.faridrama123.githubuser

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var datausername: Array<String>
    private lateinit var dataname: Array<String>
    private lateinit var dataavatar: TypedArray
    private lateinit var datacompany: Array<String>
    private lateinit var datalocation: Array<String>
    private lateinit var datarepository: Array<String>
    private lateinit var datafollower: Array<String>
    private lateinit var datafollowing: Array<String>
    private var listDataUser = arrayListOf<DataUser>()
    private lateinit var datauserAdapter : DataAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        prepare()
        addItem()

        datauserAdapter = DataAdapter()
        datauserAdapter.set(listDataUser)
        with(activityMainBinding.rv1) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = datauserAdapter
        }


    }


    private fun prepare() {
        datausername = resources.getStringArray(R.array.username)
        dataname = resources.getStringArray(R.array.name)
        dataavatar = resources.obtainTypedArray(R.array.avatar)
        datacompany= resources.getStringArray(R.array.company)
        datalocation= resources.getStringArray(R.array.location)
        datarepository= resources.getStringArray(R.array.repository)
        datafollower= resources.getStringArray(R.array.followers)
        datafollowing= resources.getStringArray(R.array.following)
    }

    private fun addItem() {
        for (position in datausername.indices) {
            val data = DataUser(
                        datausername [position],
                        dataname  [position],
                        dataavatar.getResourceId(position, -1),
                        datacompany [position],
                        datalocation [position],
                        datarepository [position],
                        datafollower [position],
                        datafollowing [position],
            )
            listDataUser.add(data)
        }

    }
}