package com.example.retrofitplayground.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.retrofitplayground.databinding.FragmentMainBinding
import com.example.retrofitplayground.viewmodel.GithubViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val TAG: String? = MainFragment::class.simpleName
    private lateinit var binding: FragmentMainBinding
    private val githubViewModel: GithubViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        githubViewModel.requestRepos()
        setupDataObservation()
    }

    private fun setupDataObservation() {
        githubViewModel.githubRepos.observe(viewLifecycleOwner, { snapshots ->
            snapshots?.let {
                Log.d(TAG, "Snapshot received from viewModel")
                binding.resultsTv.text = it.first().name
            }
        })
    }
}