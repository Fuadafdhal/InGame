package com.afdhal_fa.ingame.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afdhal_fa.ingame.R
import com.afdhal_fa.ingame.core.data.Resource
import com.afdhal_fa.ingame.core.ui.GamesAdapter
import com.afdhal_fa.ingame.detail.DetailGameActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_error.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val gamesAdapter = GamesAdapter()
            gamesAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailGameActivity::class.java)
                intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.games.observe(viewLifecycleOwner, Observer { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading -> progress_shimmer.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progress_shimmer.visibility = View.GONE
                            gamesAdapter.setData(tourism.data)
                        }
                        is Resource.Error -> {
                            progress_shimmer.visibility = View.GONE
                            view_error.visibility = View.VISIBLE
                            tv_error.text = tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(rv_games) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = gamesAdapter
            }
        }
    }
}