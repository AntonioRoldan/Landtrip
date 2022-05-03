package io.keepcoding.mvvmarchitecture.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.utils.CustomViewModelFactory
import io.keepcoding.mvvmarchitecture.utils.Status
import kotlinx.android.synthetic.main.fragment_add_trip.*


class AddTripFragment : Fragment() {

    private val viewModel: AddTripFragmentViewModel by lazy {
        val factory = CustomViewModelFactory(requireActivity().application)
        ViewModelProvider(this, factory)[AddTripFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_trip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpObservers()
    }

    private fun setUpObservers() {
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

    private fun setUpListeners(){
        addTripButton.setOnClickListener {
            setTripNameEditText.text?.let {
                viewModel.saveTrip(it.toString())
            }
        }
    }
}