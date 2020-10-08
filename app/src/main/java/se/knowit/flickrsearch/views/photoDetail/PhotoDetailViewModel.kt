package se.knowit.flickrsearch.views.photoDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import se.knowit.flickrsearch.models.Photo

class PhotoDetailViewModel(photoId: String, photoUrl: String, photoTitle: String) : ViewModel() {

    private val _photoDetail = MutableLiveData<Photo>()
    val photoDetail
        get() = _photoDetail

    init {
        _photoDetail.value = Photo (
            id = photoId,
            url = photoUrl,
            title = photoTitle
        )
    }
}