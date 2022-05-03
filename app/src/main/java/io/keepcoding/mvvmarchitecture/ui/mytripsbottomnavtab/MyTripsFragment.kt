package io.keepcoding.mvvmarchitecture.ui.mytripsbottomnavtab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.ui.ActivityViewModel
import io.keepcoding.mvvmarchitecture.ui.PointOfInterestViewModel
import io.keepcoding.mvvmarchitecture.ui.TripViewModel
import io.keepcoding.mvvmarchitecture.utils.CustomViewModelFactory
import io.keepcoding.mvvmarchitecture.utils.FragmentArguments
import io.keepcoding.mvvmarchitecture.utils.Status
import kotlinx.android.synthetic.main.fragment_my_trips.*
import kotlinx.android.synthetic.main.fragment_my_trips.loadingView


class MyTripsFragment : Fragment() {

    private var fromActivityDetail: Boolean = false // If we are opening the fragment from activity or points of interest details screen

    private var fromPointOfInterestDetail: Boolean = false

    private var myTripsAdapter: MyTripsAdapter? = null

    private var activityViewModel: ActivityViewModel? = null

    private var pointOfInterestViewModel: PointOfInterestViewModel? = null

    private var trips: MutableList<TripViewModel?> = mutableListOf()

    private val viewModel: MyTripsFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(application = requireActivity().application)
        ViewModelProvider(this, factory)[MyTripsFragmentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiveArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_my_trips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpRecyclerView(){
        tripsList.layoutManager = LinearLayoutManager(context)
    }

    private fun fetchData() {
        viewModel.fetchTripsFromLocal()
    }

    private fun setUpAdapter(){
        context?.let { context ->
            myTripsAdapter = MyTripsAdapter(context) {
                when {
                    fromActivityDetail -> {
                        activityViewModel?.let { activity ->
                            viewModel.saveActivity(it.id, activity)
                        }
                    }
                    fromPointOfInterestDetail -> {
                        pointOfInterestViewModel?.let { pointOfInterest ->
                            viewModel.savePointOfInterest(it.id, pointOfInterest)
                        }
                    }
                    else -> {
                        val navController = findNavController()
                        val bundle = Bundle()
                        bundle.putBoolean(FragmentArguments.FROM_SERVER, false)
                        bundle.putString(FragmentArguments.TRIP_ID, it.id)
                        navController.navigate(R.id.action_my_trips_to_local_activities_and_points_of_interest, bundle)
                    }
                }
            }
            Log.v("TRIPS", trips.toString())
            myTripsAdapter?.tripsItems = trips
        }
    }

    private fun observeTrips(){
        fetchData()
        viewModel.getTrips().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        if(data.isEmpty()){
                            noTripsAdded.visibility = View.VISIBLE
                            loadingView.visibility = View.INVISIBLE
                            tripsList.visibility = View.INVISIBLE
                        } else {
                            noTripsAdded.visibility = View.GONE
                            loadingView.visibility = View.GONE
                            tripsList.visibility = View.VISIBLE
                            trips = data as MutableList<TripViewModel?>
                            setUpAdapter()
                            tripsList.adapter = myTripsAdapter
                        }
                    }
                }
                Status.LOADING -> {
                    loadingView.visibility = View.VISIBLE
                    noTripsAdded.visibility = View.INVISIBLE
                    tripsList.visibility = View.INVISIBLE
                }
                Status.ERROR -> {
                    noTripsAdded.visibility = View.INVISIBLE
                    loadingView.visibility = View.INVISIBLE
                    tripsList.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun observeSnackbar(){
        viewModel.getSnackbar().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    Toast.makeText(context, it.data, Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        })
    }

    private fun setUpObservers() {
        observeTrips()
        observeSnackbar()
    }

    private fun receiveArguments() {
        arguments?.let {
            fromActivityDetail = it.getBoolean(FragmentArguments.FROM_ACTIVITY_DETAIL)
            fromPointOfInterestDetail = it.getBoolean(FragmentArguments.FROM_POINT_OF_INTEREST_DETAIL)
            if(fromActivityDetail){
                activityViewModel = it.getParcelable(FragmentArguments.ACTIVITY_PARCELABLE)
            } else if(fromPointOfInterestDetail){
                pointOfInterestViewModel = it.getParcelable(FragmentArguments.POINT_OF_INTEREST_PARCELABLE)
            }
        }
    }
}