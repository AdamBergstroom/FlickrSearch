package se.knowit.flickrsearch.views.overview

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import se.knowit.flickrsearch.entities.Photo
import se.knowit.flickrsearch.network.WebClient

class OverviewViewModel : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = mutablePhotosLiveData

    private val searchTerm = "Animals";

    init {
        getImages()
    }

    private fun getImages() {
        if (searchTerm.isBlank()) {
            mutablePhotosLiveData.postValue(emptyList())
            return
        }
        viewModelScope.launch {
            val searchResponse = WebClient.client.fetchImages(searchTerm)
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mutablePhotosLiveData.postValue(photosList)
        }
    }
}