package io.keepcoding.mvvmarchitecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.mvvmarchitecture.utils.CustomViewModelFactory
import io.keepcoding.mvvmarchitecture.R
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel: FragmentOrActivityViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory).get(FragmentOrActivityViewModel::class.java)
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //With kotlinx we replace by button_first.setOnClickListener {
        // }
        //view.findViewById<Button>(R.id.button_first).setOnClickListener {
         //   findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        //}
    }
    private fun fetchData() {
        //viewModel.fetchPOJORetrofitResponses
    }
    private fun setUpObservers() {
        fetchData()
        //viewModel.getPOJORetrofitResponses().observe(viewLifecycleOwner, Observer {
        //   when(it.status) {
        //      Status.SUCCESS -> {
        //          we fill our POJORetrofitResponseItemViewModel array by setting it to it.data then we modify the ui
        //          then we set the elements of our recyclerview adapter
        //          and set the recyclerview adapter here
        //      }
        //      Status.LOADING -> {
        //
        //      }
        //      Status.ERROR -> {
        //
        //      }
        //   }
        //
        // })
    }
}