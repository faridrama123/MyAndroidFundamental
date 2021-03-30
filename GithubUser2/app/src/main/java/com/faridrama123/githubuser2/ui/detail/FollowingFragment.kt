package com.faridrama123.githubuser2.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.githubuser2.R
import com.faridrama123.githubuser2.databinding.FollowerFragmentBinding
import com.faridrama123.githubuser2.databinding.FollowingFragmentBinding

class FollowingFragment : Fragment() {

    companion object {
        private val ARG_USERNAME =  "username"
        fun newInstance(username : String?): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }
    private lateinit var viewModel: FollowingViewModel
    private lateinit var binding: FollowingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FollowingFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val followingAdapter =  FollowingAdapter()
        viewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)
        arguments?.getString(ARG_USERNAME)?.let { viewModel.setFollowing(it) }

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getFollowing().observe(viewLifecycleOwner ,{ it->
             binding.progressBar.visibility = View.GONE
             followingAdapter.setData(it)
             followingAdapter.notifyDataSetChanged()

        })

        with(binding.rvFollowing) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = followingAdapter
        }

    }

}