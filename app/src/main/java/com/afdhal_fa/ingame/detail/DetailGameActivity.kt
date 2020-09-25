package com.afdhal_fa.ingame.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.afdhal_fa.ingame.R
import com.afdhal_fa.ingame.core.data.Resource
import com.afdhal_fa.ingame.core.domain.model.Game
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail_game.*
import kotlinx.android.synthetic.main.content_detail_game.*
import kotlinx.android.synthetic.main.view_error.*

@AndroidEntryPoint
class DetailGameActivity : AppCompatActivity() {

    private val detailGameViewModel: DetailGameViewModel by viewModels()

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

//        inject toolbar to ActionBar after title was assign
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
                .into(text_detail_image)

            var statusFavorite = detailGame.isFavorite
            setStatusFavorite(statusFavorite)
            fab.visibility = View.VISIBLE
            fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailGameViewModel.setFavoriteGame(detailGame, statusFavorite)
                setStatusFavorite(statusFavorite)
                val toastMessage =
                    if (statusFavorite) "${detailGame.name} added to Favorite" else "${detailGame.name} deleted from Favorite"
                showToast(toastMessage)
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

    private fun showToast(text: String = "", duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, text, duration).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return false
    }
}