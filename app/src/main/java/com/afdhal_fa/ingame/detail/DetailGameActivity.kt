package com.afdhal_fa.ingame.detail

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

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.afdhal_fa.core.data.Resource
import com.afdhal_fa.core.domain.model.Game
import com.afdhal_fa.ingame.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_game.*
import kotlinx.android.synthetic.main.content_detail_game.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailGameActivity : AppCompatActivity() {

    private val detailGameViewModel: DetailGameViewModel by viewModel()

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_game)

        val detailGame = intent.getParcelableExtra<Game>(EXTRA_DATA)
        if (detailGame != null) {
            toolbar.title = detailGame.name
            getDetailGame(detailGame.gameId)
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    private fun getDetailGame(id: String) {
        detailGameViewModel.getDetailGame(id).observe(this, Observer {
            when (it) {
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                    container_detail_content.visibility = View.GONE
                }
                is Resource.Success -> {
                    showDetailGame(it.data)
                    progress_bar.visibility = View.GONE
                    container_detail_content.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    progress_bar.visibility = View.GONE
                    view_error.visibility = View.VISIBLE
                    tv_error.text = it.message ?: getString(R.string.something_wrong)
                }
            }
        })
    }

    private fun showDetailGame(detailGame: Game?) {
        detailGame?.let {
            tv_release_date.text = detailGame.released
            tv_detail_description.text = detailGame.description

            Glide.with(this@DetailGameActivity)
                .load(detailGame.imageUrl)
                .placeholder(R.mipmap.image_placeholder)
                .into(text_detail_image)

            var statusFavorite = detailGame.isFavorite
            setStatusFavorite(statusFavorite)
            fab.visibility = View.VISIBLE
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailGameViewModel.setFavoriteGame(detailGame, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_active))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_unactive))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}