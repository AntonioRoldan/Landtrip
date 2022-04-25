package io.keepcoding.mvvmarchitecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.utils.FragmentArguments
import kotlinx.android.synthetic.main.fragment_point_of_interest_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [PointOfInterestDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PointOfInterestDetailFragment : Fragment(), OnMapReadyCallback {
    // TODO: Add view model
    private lateinit var pointOfInterestViewModel: PointOfInterestViewModel
    private var fromServer: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiveArguments()
        setUpMapFragment()
        setUpUI()
        setUpObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_point_of_interest_detail, container, false)
    }

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }

    private fun fetchData() {
        if(fromServer){
            // We fetch the data from the server
        } else {
            // We fetch the data from room
        }
    }

    private fun setUpListeners(){
        visitedCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            // TODO: Set visited variable on room object

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
            }
        }
    }
}