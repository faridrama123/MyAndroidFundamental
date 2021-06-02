package com.faridrama123.app.ui.detail.follower

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.faridrama123.app.R
import com.faridrama123.app.databinding.FollowerFragmentBinding
import com.faridrama123.app.factory.ViewModelFactory
import com.faridrama123.app.ui.detail.following.FollowingViewModel
import com.faridrama123.app.vo.Status

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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (activity != null) {

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this,factory)[FollowerViewModel::class.java]
        val followerAdapter = FollowerAdapter()
        binding.progressBar.visibility = View.VISIBLE


        arguments?.getString(ARG_USERNAME)?.let { arguments ->
            viewModel.getFollower(arguments).observe(viewLifecycleOwner, { it ->
                when(it.status){
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.ERROR   ->
                    {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> if (it.data != null) {
                        binding.progressBar.visibility = View.GONE
                        followerAdapter.setData(it.data)
                        followerAdapter.notifyDataSetChanged()
                    }
                }

            })

        }
        with(binding.rvFollower) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = followerAdapter
        }

    }
}

}