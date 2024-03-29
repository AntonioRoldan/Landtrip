package io.keepcoding.mvvmarchitecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.utils.*
import kotlinx.android.synthetic.main.fragment_activities_and_points_of_interest.*
import kotlinx.android.synthetic.main.trips_are_empty.*
import kotlinx.android.synthetic.main.try_again_view.*


/**
 * A simple [Fragment] subclass.
 * Use the [ActivitiesAndPointsOfInterestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ActivitiesAndPointsOfInterestFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var fromServer: Boolean = false
    var cityName: String = ""
    var tripId = ""

    private var activitiesAndPointsOfInterestAdapter: ActivitiesAndPointsOfInterestAdapter? = null

    var activitiesAndPointsOfInterest: List<ActivitiesAndPointOfInterestItemInterface?>? = mutableListOf()

    private val viewModel: ActivitiesAndPointsOfInterestFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory)[ActivitiesAndPointsOfInterestFragmentViewModel::class.java]
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
        return inflater.inflate(
            R.layout.fragment_activities_and_points_of_interest,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpRecyclerView()
        setUpObservers()
    }


    private fun fetchData() {
        if (fromServer){
            viewModel.fetchActivitiesAndPointsOfInterestFromServer(cityName = cityName)
        } else {
            viewModel.fetchActivitiesAndPointsOfInterestFromLocal(tripId = tripId)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun setAdapter(){
        context?.let {
            activitiesAndPointsOfInterestAdapter = ActivitiesAndPointsOfInterestAdapter(it, { activityViewModel ->
                val navController = findNavController()
                val bundle = Bundle()
                bundle.putString(FragmentArguments.ACTIVITY_ID, activityViewModel.id)
                bundle.putParcelable(FragmentArguments.ACTIVITY_PARCELABLE, activityViewModel)
                bundle.putBoolean(FragmentArguments.FROM_SERVER, fromServer)
                navController.navigate(R.id.local_activities_and_points_of_interest_to_local_activity_detail, bundle)
            }, { pointOfInterestViewModel ->
                val navController = findNavController()
                val bundle = Bundle()
                bundle.putString(FragmentArguments.POINT_OF_INTEREST_ID, pointOfInterestViewModel.id)
                bundle.putParcelable(FragmentArguments.POINT_OF_INTEREST_PARCELABLE, pointOfInterestViewModel)
                bundle.putBoolean(FragmentArguments.FROM_SERVER, fromServer)
                navController.navigate(R.id.local_activities_and_points_of_interest_to_local_point_of_interest_detail, bundle)
            })
            activitiesAndPointsOfInterestAdapter?.activitiesAndPointsOfInterestItems = activitiesAndPointsOfInterest
        }
    }

    private fun setUpListeners() {
        buttonRetry.setOnClickListener {
            fetchData()
        }
    }

    private fun setUpRecyclerView(){
        activitiesAndPointsOfInterestList.layoutManager = GridLayoutManager(context, 4)
        activitiesAndPointsOfInterestList.addItemDecoration(SpacesItemDecoration(10))
    }

    private fun setUpObservers() {
        fetchData()
        viewModel.getActivitiesAndPointsOfInterest().observe(viewLifecycleOwner, Observer { activitiesAndPointsOfInterestViewModels ->
            activitiesAndPointsOfInterestViewModels.data?.let {
                when(activitiesAndPointsOfInterestViewModels.status) {
                    Status.SUCCESS -> {
                        if(it[0] == null){
                            loadingView.visibility = View.INVISIBLE
                            retry.visibility = View.INVISIBLE
                            activitiesAndPointsOfInterestList.visibility = View.INVISIBLE
                            noActivitiesAndPointsOfInterestAdded.visibility = View.VISIBLE
                            emptyListLabel.text = "No activities or points of interest added"
                        } else {
                            activitiesAndPointsOfInterest = it
                            loadingView.visibility = View.GONE
                            retry.visibility = View.GONE
                            activitiesAndPointsOfInterestList.visibility = View.VISIBLE
                            noActivitiesAndPointsOfInterestAdded.visibility = View.INVISIBLE
                            setAdapter()
                            activitiesAndPointsOfInterestList.adapter = activitiesAndPointsOfInterestAdapter
                        }
                    }
                    Status.LOADING -> {
                        retry.visibility = View.INVISIBLE
                        loadingView.visibility = View.VISIBLE
                        activitiesAndPointsOfInterestList.visibility = View.INVISIBLE
                        noActivitiesAndPointsOfInterestAdded.visibility = View.INVISIBLE
                    }
                    Status.ERROR -> {
                        retry.visibility = View.VISIBLE
                        loadingView.visibility = View.INVISIBLE
                        activitiesAndPointsOfInterestList.visibility = View.INVISIBLE
                        noActivitiesAndPointsOfInterestAdded.visibility = View.INVISIBLE
                    }
                }
            }
        })
    }

    private fun receiveArguments() {
        arguments?.let {
            fromServer = it.getBoolean(FragmentArguments.FROM_SERVER)
            if(fromServer){
                it.getString(FragmentArguments.CITY_NAME)?.let { name ->
                    cityName = name
                }
            } else {
                it.getString(FragmentArguments.TRIP_ID)?.let { id ->
                    tripId = id
                }
            }
        }
    }
}