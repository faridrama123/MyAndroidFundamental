package com.faridrama123.githubuser2.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.githubuser2.databinding.FollowerFragmentBinding

class FollowerFragment : Fragment() {
    private lateinit var binding: FollowerFragmentBinding
    private lateinit var viewModel: FollowerViewModel



    companion object {
        private val ARG_USERNAME =  "username"
        fun newInstance(username : String?): FollowerFragment {
            val fragment = FollowerFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FollowerFragmentBinding.inflate(inflater, container, false)
        return binding.root    }



@SuppressLint("FragmentLiveDataObserve")
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (activity != null) {

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowerViewModel::class.java)
        val followerAdapter = FollowerAdapter()
        binding.progressBar.visibility = View.VISIBLE


        arguments?.getString(ARG_USERNAME)?.let { viewModel.setFollower(it) }
        viewModel.getFollower().observe(this,{ it ->
            binding.progressBar.visibility = View.GONE
            followerAdapter.setData(it)
            followerAdapter.notifyDataSetChanged()

        })
        with(binding.rvFollower) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = followerAdapter
        }

    }
}

}