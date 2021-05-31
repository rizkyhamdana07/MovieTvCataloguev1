package com.rizkyhamdana.movietvcatalogue.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.rizkyhamdana.movietvcatalogue.core.data.Resource
import com.rizkyhamdana.movietvcatalogue.core.domain.model.Movies
import com.rizkyhamdana.movietvcatalogue.core.ui.MovieAdapter
import com.rizkyhamdana.movietvcatalogue.databinding.FragmentMovieBinding
import com.rizkyhamdana.movietvcatalogue.movie.details.DetailsMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter
    private var _binding: FragmentMovieBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            movieAdapter = MovieAdapter()

            movieViewModel.popularMovie.observe(viewLifecycleOwner, { movies ->
                if (movies != null){
                    when(movies){
                        is Resource.Loading -> {
                            showLoading(true)
                        }
                        is Resource.Success -> {
                            showLoading(false)
                            movieAdapter.setMovies(movies.data)
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            binding.viewError.root.visibility = View.VISIBLE
                        }
                    }
                }
            })

            val pageTransformer = pageTransformer()
            binding.vpMovies.apply {
                adapter = movieAdapter
                clipChildren = false
                clipToPadding = false
                offscreenPageLimit = 3
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                setPageTransformer(pageTransformer)
            }


            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: Movies) {
                    val intent = Intent(activity, DetailsMovieActivity::class.java)
                    intent.putExtra(DetailsMovieActivity.EXTRA_ID, data.id)
                    startActivity(intent)
                }
            })

        }

    }

    private fun pageTransformer(): CompositePageTransformer {
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r: Float = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        return compositePageTransformer
    }


    private fun showLoading(state: Boolean){
        if (state) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}