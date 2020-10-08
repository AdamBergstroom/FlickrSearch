package se.knowit.flickrsearch.views.photoDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.solver.widgets.analyzer.Dependency
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import se.knowit.flickrsearch.R
import se.knowit.flickrsearch.databinding.FragmentPhotoDetailBinding

class PhotoDetailFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentPhotoDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_photo_detail, container, false)

        //val application = requireNotNull(this.activity).application
        val arguments = PhotoDetailFragmentArgs.fromBundle(arguments!!)

        val viewModelFactory = PhotoDetailViewModelFactory(arguments.photoId, arguments.photoUrl, arguments.photoTitle)

        // Get a reference to the ViewModel associated with this fragment.
        val photoDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(PhotoDetailViewModel::class.java)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.photoDetailViewModel = photoDetailViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}
