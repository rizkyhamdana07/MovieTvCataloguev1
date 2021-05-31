package com.rizkyhamdana.movietvcatalogue.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizkyhamdana.movietvcatalogue.core.domain.usecase.CatalogueUseCase

class FavoriteMovieViewModel(private val catalogueUseCase: CatalogueUseCase): ViewModel() {

    val getMovieFavorite = catalogueUseCase.getMovieFavorite().asLiveData()


}