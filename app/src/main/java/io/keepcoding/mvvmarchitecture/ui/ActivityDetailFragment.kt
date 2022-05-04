package io.keepcoding.mvvmarchitecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.utils.CustomViewModelFactory
import io.keepcoding.mvvmarchitecture.utils.FragmentArguments
import io.keepcoding.mvvmarchitecture.utils.Status
import kotlinx.android.synthetic.main.fragment_activity_detail.*




class ActivityDetailFragment : Fragment(), OnMapReadyCallback {

    private var activityViewModel: ActivityViewModel? = null

    private lateinit var id: String

    private var fromServer: Boolean = false

    private val viewModel: ActivityDetailFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory)[ActivityDetailFragmentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiveArguments()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMapFragment()
        setUpUI()
        setUpListeners()
        if(fromServer){
            setUpObservers()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_detail, container, false)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        if(fromServer){
            viewModel.getActivityDetailViewModel().observe(viewLifecycleOwner, Observer { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.latitude?.let { latitude ->
                            resource.data.longitude?.let { longitude ->
                                val activityPosition = LatLng(latitude, longitude)
                                googleMap.addMarker(
                                    MarkerOptions()
                                        .position(activityPosition)
                                        .title("Point of interest location")
                                )
                            }
                        }
                    }
                    else -> {

                    }
                }
            })
        } else {
            activityViewModel?.latitude?.let { latitude ->
                activityViewModel?.longitude?.let { longitude ->
                    val activityPosition = LatLng(latitude, longitude)
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(activityPosition)
                            .title("Point of interest location")
                    )
                }
            }
        }
    }


    private fun fetchData() {
        if(fromServer){
           viewModel.fetchActivityFromServer(id = id)
        } else {
            // We fetch the data from room
        }
    }

    private fun showViews(){
        activityMap.visibility = View.VISIBLE
        activityName.visibility = View.VISIBLE
        activityImage.visibility = View.VISIBLE
        description.visibility = View.VISIBLE
        saveOrDeleteActivityButton.visibility = View.VISIBLE
        if(!fromServer){
            // Change fab's icon
            visitedCheckbox.visibility = View.VISIBLE
        } else {
            visitedCheckbox.visibility = View.INVISIBLE
        }
        loadingView.visibility = View.GONE
        retry.visibility = View.GONE
    }

    private fun showLoading(){
        activityMap.visibility = View.INVISIBLE
        activityName.visibility = View.INVISIBLE
        activityImage.visibility = View.INVISIBLE
        description.visibility = View.INVISIBLE
        visitedCheckbox.visibility = View.INVISIBLE
        saveOrDeleteActivityButton.visibility = View.INVISIBLE

        loadingView.visibility = View.VISIBLE
        retry.visibility = View.INVISIBLE
    }

    private fun showRetry(){
        activityMap.visibility = View.INVISIBLE
        activityName.visibility = View.INVISIBLE
        activityImage.visibility = View.INVISIBLE
        description.visibility = View.INVISIBLE
        visitedCheckbox.visibility = View.INVISIBLE
        saveOrDeleteActivityButton.visibility = View.INVISIBLE
        loadingView.visibility = View.INVISIBLE
        retry.visibility = View.VISIBLE
    }

    private fun setUpListeners(){
        visitedCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.updateVisitedFieldOfActivityEntityFromLocal(isChecked)
        }
        saveOrDeleteActivityButton.setOnClickListener {
            if(fromServer){
                val navController = findNavController()
                val bundle = Bundle()
                bundle.putParcelable(FragmentArguments.ACTIVITY_PARCELABLE, activityViewModel)
                bundle.putBoolean(FragmentArguments.FROM_ACTIVITY_DETAIL, true)
                navController.navigate(R.id.action_activity_detail_to_my_trips, bundle)
            } else {
                viewModel.deleteActivity(id = id)
            }
        }
    }

    private fun setUpUI(){
        if(!fromServer){
            showViews()
            bindDataFromLocalToViews()
        }
    }

    private fun bindDataFromLocalToViews() {
        activityName.text = activityViewModel?.name
        context?.let {
            Glide.with(it)
                .load(activityViewModel?.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(activityImage)
        }
        description.text = activityViewModel?.shortDescription
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun bindDataFromServerToViews(viewModel: ActivityViewModel?) {
        activityName.text = viewModel?.name
        context?.let {
            Glide.with(it)
                .load(viewModel?.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(activityImage)
        }
        description.text = viewModel?.shortDescription
    }

    private fun observeSnackbar() {
        viewModel.getSnackbar().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.ERROR -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, it.data, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun observeActivityViewModels(){
        fetchData()
        viewModel.getActivityDetailViewModel().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    activityViewModel = it.data
                    showViews()
                    bindDataFromServerToViews(it.data) // The data property from the resource class is our view model object that we pass
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.ERROR -> {
                    showRetry()
                }
            }
        })
    }

    private fun setUpObservers(){
        observeActivityViewModels()
        observeSnackbar()
    }

    private fun setUpMapFragment(){
        val mapFragment: SupportMapFragment? = childFragmentManager.findFragmentById(R.id.activityMap) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    private fun receiveArguments() {
        arguments?.let {
            fromServer = it.getBoolean(FragmentArguments.FROM_SERVER)
            if(!fromServer){ //If we are fetching data from local we must have a parcelable as argument
                it.getParcelable<ActivityViewModel>(FragmentArguments.ACTIVITY_PARCELABLE)?.let { parcelable ->
                    activityViewModel = parcelable
                }
            }
            it.getString(FragmentArguments.ACTIVITY_ID)?.let { activityId ->
                id = activityId
            }
        }
    }
}