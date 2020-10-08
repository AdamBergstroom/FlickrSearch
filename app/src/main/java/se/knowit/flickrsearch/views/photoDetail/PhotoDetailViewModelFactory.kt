package se.knowit.flickrsearch.views.photoDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PhotoDetailViewModelFactory (
    private val photoId: String,
    private val photoUrl: String,
    private val photoTitle: String) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoDetailViewModel::class.java)) {
            return PhotoDetailViewModel(photoId, photoUrl, photoTitle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}