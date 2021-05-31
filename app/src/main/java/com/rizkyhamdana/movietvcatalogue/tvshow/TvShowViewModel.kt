package com.rizkyhamdana.movietvcatalogue.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizkyhamdana.movietvcatalogue.core.domain.usecase.CatalogueUseCase

class TvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {

    val popularTv = catalogueUseCase.getTvPopular().asLiveData()

}