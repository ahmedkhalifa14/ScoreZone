package com.example.scorezone.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scorezone.adapters.MatchAdapter
import com.example.scorezone.databinding.FragmentHomeBinding
import com.example.scorezone.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var matchRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObservers()
        setupMatchRecyclerView()
        homeViewModel.getAllMatches()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun subscribeToObservers() {
        lifecycleScope.launchWhenStarted {
            launch {
                homeViewModel.allMatchState.collect(
                    EventObserver(
                        onLoading = {
                        },
                        onSuccess = {matches ->
                           matchAdapter.differ.submitList(matches.matches)
                        },
                        onError = {

                        }
                    )
                )

            }


        }

    }
    private fun setupMatchRecyclerView() {
        matchRecyclerView = binding!!.matchRecyclerView
        matchAdapter = MatchAdapter()
        matchRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        matchRecyclerView.adapter = matchAdapter

    }
}