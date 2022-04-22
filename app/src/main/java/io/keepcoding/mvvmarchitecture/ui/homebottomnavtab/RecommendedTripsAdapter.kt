package io.keepcoding.mvvmarchitecture.ui.homebottomnavtab

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.keepcoding.mvvmarchitecture.R
import kotlinx.android.synthetic.main.recommended_trips_recycler_view_item.view.*
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
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class RecommendedTripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recommendedTripViewModel: RecommendedTripViewModel? = null
            @SuppressLint("CheckResult")
            set(value) {
                field = value
                itemView.tag = recommendedTripViewModel
                field?.let {
                    //TODO: Fill item view with recommended trip view model's data here
                    itemView.cityName.text = it.name
                    Glide.with(context)
                        .load(it.image)
                        .apply {
                            RequestOptions()
                                .placeholder(R.drawable.ic_launcher_background)
                        }.into(itemView.cityImage)
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
        val recommendedTrip = recommendedTripItems?.get(position)
        holder.recommendedTripViewModel = recommendedTrip
        holder.itemView.setOnClickListener(listener)
    }

    override fun getItemCount(): Int {
        return if(recommendedTripItems != null) {
            recommendedTripItems!!.size
        } else {
            0
        }
    }
}
