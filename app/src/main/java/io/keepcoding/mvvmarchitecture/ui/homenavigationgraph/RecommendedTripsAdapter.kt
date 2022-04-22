package io.keepcoding.mvvmarchitecture.ui.homenavigationgraph

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.mvvmarchitecture.R
import java.lang.IllegalArgumentException

class RecommendedTripsAdapter(val context: Context, val itemClickListener: ((RecommendedTripViewModel) -> Unit)? = null) : RecyclerView.Adapter<RecommendedTripsAdapter.RecommendedTripViewHolder>() {

    private val listener : ((View) -> Unit) = {
        if(it.tag is RecommendedTripViewModel){
            itemClickListener?.invoke(it.tag as RecommendedTripViewModel)
        } else {
            throw IllegalArgumentException("View tag has not been set to item view model")
        }
    }

    var recommendedTripItems: List<RecommendedTripViewModel?>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class RecommendedTripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recommendedTripViewModel: RecommendedTripViewModel? = null
            set(value) {
                field = value
                itemView.tag = recommendedTripViewModel
                field?.let {
                    //TODO: Fill item view with recommended trip view model's data here
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedTripsAdapter.RecommendedTripViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recommended_trips_recycler_view_item, parent, false)
        return RecommendedTripViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecommendedTripsAdapter.RecommendedTripViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount(): Int {
        return if(recommendedTripItems != null) {
            recommendedTripItems!!.size
        } else {
            0
        }
    }
}
