package com.afdhal_fa.favorite

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
import com.afdhal_fa.core.ui.GamesAdapter
import com.afdhal_fa.favorite.di.favoriteModule
import com.afdhal_fa.ingame.detail.DetailGameActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)

        getFavoriteData()
    }

    private fun getFavoriteData () {
        val gamesAdapter = GamesAdapter()
        gamesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this.context, DetailGameActivity::class.java)
            intent.putExtra(DetailGameActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteGames.observe(viewLifecycleOwner, Observer {
            gamesAdapter.setData(it)
            view_empty.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(rv_games) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gamesAdapter
        }
    }
}