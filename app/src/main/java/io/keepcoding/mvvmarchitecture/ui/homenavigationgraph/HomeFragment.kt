package io.keepcoding.mvvmarchitecture.ui.homenavigationgraph

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.utils.Constants
import io.keepcoding.mvvmarchitecture.utils.CustomViewModelFactory
import io.keepcoding.mvvmarchitecture.utils.FragmentArguments
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private val recommendedTrips: List<RecommendedTripViewModel> = mutableListOf()

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
        view.setBackgroundColor(Color.BLUE)
    }

    private fun setAdapter(){
        context?.let {
            recommendedTripsAdapter = RecommendedTripsAdapter(it) { recommendedTripViewModel -> // We pass the lambda function
                val navController = findNavController()
                val bundle: Bundle = Bundle()
                recommendedTripViewModel.latitude?.let { latitude ->
                    bundle.putDouble(FragmentArguments.LATITUDE, latitude)
                }
                recommendedTripViewModel.longitude?.let { longitude ->
                    bundle.putDouble(FragmentArguments.LONGITUDE, longitude)
                }
                navController.navigate(R.id.action_home_to_activities_and_points_of_interest, bundle)
            }
            recommendedTripsAdapter?.recommendedTripItems = recommendedTrips
        }
    }

    private fun setUpRecyclerView(){
        list.layoutManager = GridLayoutManager(context, 4)
        list.addItemDecoration(DividerItemDecoration(context, GridLayoutManager.VERTICAL))
    }

    private fun setUpObservers(){

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