package com.afdhal_fa.ingame.home

/**
 * Copyright 2020 Muh Fuad Afdhal

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afdhal_fa.core.data.Resource
import com.afdhal_fa.core.ui.GamesAdapter
import com.afdhal_fa.ingame.R
import com.afdhal_fa.ingame.detail.DetailGameActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

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

            homeViewModel.games(1, 30).observe(viewLifecycleOwner, Observer { tourism ->
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