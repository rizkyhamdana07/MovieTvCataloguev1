package com.rizkyhamdana.movietvcatalogue.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizkyhamdana.movietvcatalogue.core.domain.usecase.CatalogueUseCase

class MovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val popularMovie = catalogueUseCase.getMoviePopular().asLiveData()

}