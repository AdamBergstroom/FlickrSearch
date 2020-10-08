package se.knowit.flickrsearch.views.photoDetail

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import se.knowit.flickrsearch.models.Photo

@SuppressLint("SetTextI18n")
@BindingAdapter("numberOfPhotosFound")
fun TextView.setNumberOfPhotosFound(data: List<Photo>?) {
    data.let {
        text = "Found ${data?.size.toString()} photos"
    }
}

@BindingAdapter("photoTitle")
fun TextView.setPhotoTitle(title: String) {
    title.let {
        text = title
    }
}