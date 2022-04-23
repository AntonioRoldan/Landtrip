package io.keepcoding.mvvmarchitecture.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.mvvmarchitecture.utils.Constants

class ActivitiesAndPointsOfInterestAdapter(val context: Context, val activityItemClickListener:  ((activityViewModel: ActivityViewModel) -> Unit), val pointOfInterestItemClickListener : ((pointOfInterestViewModel: PointOfInterestViewModel) -> Unit)) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var activitiesAndPointsOfInterestItems: List<ActivitiesAndPointOfInterestItemInterface?>? = null
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var activityViewModel: ActivityViewModel? = null
            set(value) {
                field = value
                itemView.tag = activityViewModel
                field?.let {
                    itemView //TODO: Write this
                }
            }
    }

    inner class PointOfInterestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pointOfInterestViewModel: PointOfInterestViewModel? = null
            set(value) {
                field = value
                itemView.tag = pointOfInterestViewModel
                field?.let {
                    itemView //TODO: Write this
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        return if(activitiesAndPointsOfInterestItems != null){
            activitiesAndPointsOfInterestItems!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        activitiesAndPointsOfInterestItems?.get(position)?.let {
            if(it.viewType == Constants.ACTIVITY_VIEW_TYPE) {
                val activity: ActivityViewModel = it as ActivityViewModel
                val activityViewHolder = (holder as ActivityViewHolder)
                holder.activityViewModel = activity
                //TODO: Set click listener
            } else {
                val pointOfInterest: PointOfInterestViewModel = it as PointOfInterestViewModel
                val pointOfInterestViewHolder = (holder as PointOfInterestViewHolder)
                pointOfInterestViewHolder.pointOfInterestViewModel = pointOfInterest
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return activitiesAndPointsOfInterestItems!![position]!!.viewType
    }

}