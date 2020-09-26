package com.afdhal_fa.ingame.favorite

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afdhal_fa.core.ui.GamesAdapter
import com.afdhal_fa.ingame.R
import com.afdhal_fa.ingame.detail.DetailGameActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    private fun clearFavorite () {
        val alertDialogBuilder = MaterialAlertDialogBuilder(this.requireContext())
        alertDialogBuilder.setTitle("Clear All Favorite")
        alertDialogBuilder.setMessage("Are you sure want to clear all favorite data?")
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setPositiveButton("Yes, Clear all") { _: DialogInterface, _: Int ->
            favoriteViewModel.clearFavoriteGames()
        }
        alertDialogBuilder.show()
    }
}