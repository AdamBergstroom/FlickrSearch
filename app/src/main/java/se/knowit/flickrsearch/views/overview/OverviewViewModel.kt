package se.knowit.flickrsearch.views.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import se.knowit.flickrsearch.models.Photo
import se.knowit.flickrsearch.network.WebClient
import java.lang.Exception

class OverviewViewModel : ViewModel() {
    private val _photos = MutableLiveData<List<Photo>>()
    val photos
        get() = _photos

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
     *  Use viewModelScope (CoroutineScope) to cancel request if viewModel is cleared out.
     *
     *  This method is called in OverViewViewModel init and when pressing the search button in fragment_overview.xml
     */
    fun getImages() {
        viewModelScope.launch {
            try {
                val searchResponse = WebClient.client.fetchImages(_query.value.toString())
                val photosList = searchResponse.photos.photo.map { photo ->
                    Photo(
                        id = photo.id,
                        /**
                         * Construct the source URL to a photo with its parameters.
                         * See documentation at: https://www.flickr.com/services/api/misc.urls.html
                         * Disclaimer: "live" is not working. Using farm{value} instead. See here: https://stackoverflow.com/questions/1803310/how-to-get-static-image-url-from-flickr-url
                         * */
                        url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                        title = photo.title
                    )
                }
                _photos.postValue(photosList)
            }
            catch (e:Exception){
                println(e.message)
            }
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