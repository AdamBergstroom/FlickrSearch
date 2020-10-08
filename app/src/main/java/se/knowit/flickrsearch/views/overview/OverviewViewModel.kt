package se.knowit.flickrsearch.views.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import se.knowit.flickrsearch.entities.Photo
import se.knowit.flickrsearch.network.WebClient

@Suppress("UNUSED_PARAMETER")
class OverviewViewModel : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = mutablePhotosLiveData

    private val _navigateToPhotoDetail = MutableLiveData<Photo>()
    val navigateToPhotoDetail
        get() = _navigateToPhotoDetail

    private val _query = MutableLiveData<String>()
    private val query
        get() = _query

    init {
        query.value = "inspiration"
        getImages()
    }

    /**
     *  Fetch images from the Flickr api.
     *
     *  This method is called in OverViewViewModel init and when pressing the search button in fragment_overview.xml
     */
    fun getImages() {
        viewModelScope.launch {
            val searchResponse = WebClient.client.fetchImages(_query.value.toString())
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

    fun onPasswordTextChanged(s: CharSequence,start: Int,before : Int,
                              count :Int){
        _query.value = s.toString()
    }

    fun onPhotoClicked(photo: Photo) {
        _navigateToPhotoDetail.value = photo
    }

    fun doneNavigated() {
        _navigateToPhotoDetail.value = null
    }
}