package se.knowit.flickrsearch.network

/**
 *  Data classes for API response from Flickr
 *
 * */
data class SearchResponseData(
    val photos: PhotosMetaData
)

data class PhotosMetaData(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: String,
    val photo: List<PhotoResponse>
)

data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)