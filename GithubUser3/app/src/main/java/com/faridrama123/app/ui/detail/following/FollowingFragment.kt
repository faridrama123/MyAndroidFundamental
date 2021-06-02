package com.faridrama123.app.ui.detail.following

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridrama123.app.databinding.FollowingFragmentBinding
import com.faridrama123.app.factory.ViewModelFactory
import com.faridrama123.app.ui.detail.follower.FollowerFragment
import com.faridrama123.app.vo.Status


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

        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(this,factory)[FollowingViewModel::class.java]

        arguments?.getString(FollowingFragment.ARG_USERNAME)?.let { arguments ->
            viewModel.getFollowing(arguments).observe(viewLifecycleOwner, { it ->
                when(it.status){
                    Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    Status.ERROR   ->
                    {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> if (it.data != null) {
                        binding.progressBar.visibility = View.GONE
                        followingAdapter.setData(it.data)
                        followingAdapter.notifyDataSetChanged()
                    }
                }

            })

        }
        with(binding.rvFollowing) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = followingAdapter
        }

    }

}