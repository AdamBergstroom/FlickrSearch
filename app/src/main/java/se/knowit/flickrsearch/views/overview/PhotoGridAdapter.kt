package se.knowit.flickrsearch.views.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import se.knowit.flickrsearch.databinding.GridViewItemBinding
import se.knowit.flickrsearch.models.Photo

/**
 * PhotoGridAdapter implements a [RecyclerView] and [ListAdapter] which uses Data Binding to
 * present a [List] of data.
 * */
class PhotoGridAdapter(private val photoListener: PhotoListener) : ListAdapter<Photo, PhotoGridAdapter.PhotoViewHolder>(DiffCallback) {

    /**
     * PhotoViewHolder that uses a binding to populate the images with every information needed.
     *
     * executePendingBindings() is important for data binding to execute immediately.
     * This will secure correct view size measurements for each image.
     * */
    class PhotoViewHolder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: PhotoListener, photo: Photo){
            binding.photo = photo
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    /**
     * Allows RecyclerView to filter changed items from the list.
     * */
    companion object DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

    }

    /**
     * Create a new RecyclerView item views.
     * */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        return PhotoViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces contents of a view.
     *
     * List arrives from the app:listData property from fragment_overview.xml, where each item
     * is sent to their viewHolder.
     * */
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photoListener,photo)
    }

}

class PhotoListener(val clickListener: (photo: Photo) -> Unit) {
    fun onClick(photo: Photo) = clickListener(photo)
}