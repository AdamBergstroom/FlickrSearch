package se.knowit.flickrsearch.views.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import se.knowit.flickrsearch.entities.Photo
import se.knowit.flickrsearch.network.WebClient

class OverviewViewModel : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = mutablePhotosLiveData

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        viewModelScope.launch {
            val searchResponse = WebClient.client.fetchImages()
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mutablePhotosLiveData.postValue(photosList)
            _response.value = "Number of images found: ${photosList.size}"
        }
    }
}