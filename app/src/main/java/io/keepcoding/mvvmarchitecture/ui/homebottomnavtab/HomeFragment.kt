package io.keepcoding.mvvmarchitecture.ui.homebottomnavtab

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.utils.CustomViewModelFactory
import io.keepcoding.mvvmarchitecture.utils.FragmentArguments
import io.keepcoding.mvvmarchitecture.utils.SpacesItemDecoration
import io.keepcoding.mvvmarchitecture.utils.Status
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.try_again_view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var recommendedTrips: List<RecommendedTripViewModel?> = mutableListOf()

    private var recommendedTripsAdapter: RecommendedTripsAdapter? = null

    private val viewModel: HomeFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory)[HomeFragmentViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpRecyclerView()
        setUpObservers()
    }

    private fun setAdapter(){
        context?.let {
            recommendedTripsAdapter = RecommendedTripsAdapter(it) { recommendedTripViewModel -> // We pass the lambda function
                val navController = findNavController()
                val bundle: Bundle = Bundle() // Since longitude returns null from the api we will search coordinates by city name so we pass city name as parameter
                bundle.putString(FragmentArguments.CITY_NAME, recommendedTripViewModel.name)
                bundle.putBoolean(FragmentArguments.FROM_SERVER, true)
                navController.navigate(R.id.action_home_to_activities_and_points_of_interest, bundle)
            }
        }
        recommendedTripsAdapter?.recommendedTripItems = recommendedTrips
    }

    private fun setUpRecyclerView(){
        recommendedTripsList.layoutManager = GridLayoutManager(context, 4)
        recommendedTripsList.addItemDecoration(SpacesItemDecoration(10))
    }

    private fun setUpListeners() {
        buttonRetry.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData(){
        viewModel.fetchRecommendedTrips()
    }

    private fun setUpObservers(){
        fetchData()
        viewModel.getRecommendedTrips().observe(viewLifecycleOwner, Observer { recommendedTripsViewModels ->
            recommendedTripsViewModels.data?.let {
                when(recommendedTripsViewModels.status){
                    Status.SUCCESS -> {
                        recommendedTrips = it
                        loadingView.visibility = View.GONE
                        retry.visibility = View.GONE
                        recommendedTripsList.visibility = View.VISIBLE
                        setAdapter()
                        recommendedTripsList.adapter = recommendedTripsAdapter
                    }
                    Status.LOADING -> {
                        retry.visibility = View.INVISIBLE
                        loadingView.visibility = View.VISIBLE
                        recommendedTripsList.visibility = View.INVISIBLE
                    }
                    Status.ERROR -> {
                        Log.e("Error", "Error")
                        retry.visibility = View.VISIBLE
                        loadingView.visibility = View.INVISIBLE
                        recommendedTripsList.visibility = View.INVISIBLE
                    }
                }
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}