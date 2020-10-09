## FlickrSearch ##

## About this app
* This app can be used to query photos from Flickrs API.
* Simply type a query that you are looking for inside the search field and press the search button.
* If you find an interesting photo you can click on it to inspect the photo more and see content information about it.

DISCLAIMER: Flickr API can sometimes be shutdown. This will be noticeable when you cannot make any queries from the search field.

## Technology choice and motivation ##

# Kotlin
Kotlin is a statically-typed language which is very easy to read and write. Write the same code as java, but shorter. Kotlin programs do not need semicolons in their program. This makes the programs easy to read and understand. They also have smart casts and string templates. Kotlin can easily exchange and use information from Java in a number of ways. One of the biggest advantages of Kotlin over Java is the null references.

## MVVM
Model-View-ViewModel (MVVM) is a client-side design pattern. It guides the structure and design of your code to help you achieve "Separation of Concerns". By following the MVVM pattern the code is easier to understand, maintain and troubleshoot. Easier to write tests inside the ViewModel. Removes unnecessary memory leaks and avoid app from crashing by removing logic inside the activity/fragment. 

## Retrofit2
Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. This app combines retrofit together with GsonConverterFactory and OkHttpClient for making it easier to fetch json data over an api and map it to a data layers (that are the blue prints for the received json object).

## Glide - for showing and caching images
* Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
* With Glide, the user will experience a smooth and fast scrolling experience while using the listView. 
* Glide also make it easy to show loading screen while retrieving the image and can show errors in the image if something went wrong.

## RecyclerView - and showing items in a grid
* This app uses ``onBindViewHolder()`` in order to recycle and reuse already created item views form the list view. This makes the app smoother when scrolling through e.g. 100 items, since onBindViewHolder() will take all the created views and just replace with a new binding. By binding the item views the app also creates a better "separation of concerns", where each item view can take use of their binding adapters to make a faster update.
* The RecyclerView uses DiffUtil to find differences between two lists and provides the updated list as an output. This class is used to notify updates to a RecyclerView Adapter.
* A clickListener class is also added in other to interact when a photo is clicked. This function is setup inside the OverViewFragment when setting upp the adapter. The onClick function is then allocated to a function inside the viewModel.

## Data Binding Everywhere
* Data Binding is used for storing and managing UI-related data in a lifecycle conscious way. This will make the app survive configuration changes and avoid memory leaks.
* This app uses binding adapters for populating the view layout. With Binding Adapters it is easier to handle data properties in your xml views, since the data (attached to corresponding property) is an observable. Any data changes will trigger and update the view. With a clean documentation this is very easy to follow and build on.
* ViewModelFactory is used in order to pass data between fragments. This is used since ViewModel can not pass data by itself since the ViewModelProvider utility does not provide arg constructors. However, by using ViewModelFactory the app makes sure that correct data is sent before creating the new fragment. It is a cleaner and safer way to pass data between activities/fragments. If the data would be too large, it would be enough to send e.g id-number and then fetch the data again from the api.

## LiveData and Observables
* The app uses LiveData in order to update changes for app components that is in an active lifecycle state.
* In OverviewFragment an observable is attached to a live data status in order to respond to changes for that property. Any changes will then navigate to PhotoDetailFragment with arguments to the new ViewModel. This is activated then interacting with a photo in the list  view.

## ConstraintLayout
This app uses only ConstraintLayout, since it allows us to lay out child views using ‘constraints’ to define position based relationships between different views found in our layout. The aim of the ConstraintLayout is to help reduce the number of nested views, which will improve the performance of our layout files.

## Design Choices
This app try to use material design as much as it can. There are material design implementations in most of the layouts and stylings. However, it is short on material design widgets since the app could work with simple widgets that the android studio offers.

## Error handling
* A ViewModelScope is used when fetching data from the OverviewViewModel. The ViewModelScope makes any coroutine launch (inside this scope) automatically canceled if the ViewModel is cleared. This will help the app to avoid consuming resources.
* ViewModelFactory is used to securely navigate to another activity/fragment. It will throw exception if arguments does not match early in the code (at build time). This makes the app more secure to avoid errors and crashes when running the app and interacting with it.
* Glide uses their own exception handling. However, they also provide the ability to insert an image/message if an error would occur. In this example the app will show a broken image if an error due happen.
* Memory Leaks and app crashes are minimal since the ass is using ViewModels and Binding Adapters for "Separation of Concerns".
* A try catch block is used when fetching data from the FLickr Api. If the service is down, the app wont crash.
