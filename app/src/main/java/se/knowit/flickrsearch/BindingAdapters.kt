package se.knowit.flickrsearch

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import se.knowit.flickrsearch.entities.Photo
import se.knowit.flickrsearch.views.overview.PhotoGridAdapter

/**
 * BindingAdapter for populating fragment_overview.xml in app:listData property.
 *
 * This adapter will help to show/hide [RecyclerView] when there is data/no data.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Photo>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

/**
 * BindingAdapter for populating an image in grid_view_item.xml in app:imageUrl property.
 *
 * Uses the Glide library to load and cache an image.
 * Shows a loading indicator when loading the image.
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

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