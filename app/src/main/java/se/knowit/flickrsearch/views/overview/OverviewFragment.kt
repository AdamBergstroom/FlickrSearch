package se.knowit.flickrsearch.views.overview

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
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

        /**
         * Observe if navigateToPhotoDetail value is changed.
         * Navigate to the detail fragment if clicked.
         */
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

        /***
         * Observe if showSnackBarMessage is changed.
         * Show a snackBar if fetching data failed.
         */
        viewModel.showSnackBarMessage.observe(viewLifecycleOwner, Observer { message ->
            message?.let{
                val snackBar = Snackbar.make(
                    view!!, message,
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null)
                snackBar.setActionTextColor(Color.WHITE)
                val snackBarView = snackBar.view
                snackBarView.setBackgroundColor(Color.BLACK)
                val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.WHITE)
                snackBar.show()
                viewModel.turnOfSnackBarMessage()
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