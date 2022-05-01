package io.keepcoding.mvvmarchitecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.utils.CustomViewModelFactory
import io.keepcoding.mvvmarchitecture.utils.FragmentArguments
import io.keepcoding.mvvmarchitecture.utils.Status
import kotlinx.android.synthetic.main.fragment_point_of_interest_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [PointOfInterestDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PointOfInterestDetailFragment : Fragment(), OnMapReadyCallback {
    // TODO: Add view model

    private val viewModel: PointOfInterestDetailFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory)[PointOfInterestDetailFragmentViewModel::class.java]
    }

    private lateinit var pointOfInterestViewModel: PointOfInterestViewModel

    private var fromServer: Boolean = false

    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiveArguments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMapFragment()
        setUpUI()
        setUpObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_point_of_interest_detail, container, false)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        viewModel.getPointOfInterestDetailViewModel().observe(viewLifecycleOwner, Observer { resource ->
            when(resource.status) {
                Status.SUCCESS -> {
                    resource.data?.latitude?.let { latitude ->
                        resource.data.longitude?.let { longitude ->
                            val pointOfInterestPosition = LatLng(latitude, longitude)
                            googleMap.addMarker(
                                MarkerOptions()
                                    .position(pointOfInterestPosition)
                                    .title("Point of interest location")
                            )
                        }
                    }
                }
                else -> {

                }
            }
        })
    }

    private fun fetchData() {
        if(fromServer){
            viewModel.fetchPointOfInterestFromServer(id)
        } // We don't need to fetch from local since we have the parcelable
    }

    private fun setUpListeners(){
        visitedCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.updateVisitedFieldOfPointOfInterestEntityFromLocal(isChecked)
        }
    }

    private fun setUpUI(){
        if(fromServer){
            visitedCheckbox.visibility = View.INVISIBLE
        }
    }
    private fun setUpObservers(){

    }

    private fun setUpMapFragment(){
        val mapFragment: SupportMapFragment = childFragmentManager.findFragmentById(R.id.activityMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun receiveArguments() {
        arguments?.let {
            fromServer = it.getBoolean(FragmentArguments.FROM_SERVER)
            if(!fromServer){ //If we are fetching data from local we must have a parcelable as argument
                it.getParcelable<PointOfInterestViewModel>(FragmentArguments.POINT_OF_INTEREST_PARCELABLE)?.let { parcelable ->
                    pointOfInterestViewModel = parcelable
                }
                it.getString(FragmentArguments.POINT_OF_INTEREST_ID)?.let { pointOfInterestId ->
                    id = pointOfInterestId
                }
            }
        }
    }
}