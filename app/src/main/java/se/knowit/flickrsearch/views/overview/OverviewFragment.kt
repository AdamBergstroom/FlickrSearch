package se.knowit.flickrsearch.views.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import se.knowit.flickrsearch.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.navigateToPhotoDetail.observe(viewLifecycleOwner, Observer { photo ->
            photo?.let {
                this.findNavController().navigate(
                    OverviewFragmentDirections
                        .actionFragmentOverviewToFragmentPhotoDetail(photo.id, photo.url, photo.title))
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                viewModel.doneNavigated()
            }
        })

        val adapter = PhotoGridAdapter(PhotoListener { photo ->
            viewModel.onPhotoClicked(photo)
        })

        // Connect the adapter to binding.
        binding.photosGrid.adapter = adapter

        setHasOptionsMenu(true)
        return binding.root
    }

}