package com.rizkyhamdana.movietvcatalogue.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizkyhamdana.movietvcatalogue.core.domain.model.TvShow
import com.rizkyhamdana.movietvcatalogue.core.ui.favorite.FavoriteTvShowAdapter
import com.rizkyhamdana.movietvcatalogue.di.favoriteModule
import com.rizkyhamdana.movietvcatalogue.favorite.databinding.FragmentFavoriteTvShowBinding
import com.rizkyhamdana.movietvcatalogue.favorite.tvshow.details.DetailsFavoriteTvShowActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteTvShowFragment : Fragment() {

    private val favoriteTvShowViewModel: FavoriteTvShowViewModel by viewModel()
    private lateinit var favoriteTvShowAdapter: FavoriteTvShowAdapter
    private var _binding: FragmentFavoriteTvShowBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)

        favoriteTvShowAdapter = FavoriteTvShowAdapter()

        favoriteTvShowViewModel.getFavoriteTv.observe(viewLifecycleOwner, {
            favoriteTvShowAdapter.seTvShow(it)
            binding.viewEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        })

        binding.rvFavoriteTv.adapter = favoriteTvShowAdapter
        binding.rvFavoriteTv.layoutManager = LinearLayoutManager(requireActivity())

        favoriteTvShowAdapter.setOnItemClickCallback(object : FavoriteTvShowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: TvShow) {
                val intent = Intent(activity, DetailsFavoriteTvShowActivity::class.java)
                intent.putExtra(DetailsFavoriteTvShowActivity.EXTRA_DATA, data)
                startActivity(intent)
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}