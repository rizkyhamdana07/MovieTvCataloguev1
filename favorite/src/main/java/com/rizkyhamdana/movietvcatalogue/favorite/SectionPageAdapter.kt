package com.rizkyhamdana.movietvcatalogue.favorite

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rizkyhamdana.movietvcatalogue.favorite.movie.FavoriteMovieFragment
import com.rizkyhamdana.movietvcatalogue.favorite.tvshow.FavoriteTvShowFragment

class SectionPageAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = FavoriteMovieFragment()
            }
            1 -> {
                fragment = FavoriteTvShowFragment()
            }
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }
}