package io.keepcoding.mvvmarchitecture.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActivitiesAndPointsOfInterestAdapter(val context: Context, val activityItemClickListener:  ((activityViewModel: ActivityViewModel) -> Unit), val pointOfInterestItemClickListener : ((pointOfInterestViewModel: PointOfInterestViewModel) -> Unit)) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var activityViewModel: ActivityViewModel? = null
            set(value) {
                field = value
                itemView.tag = activityViewModel
                field?.let {
                    itemView
                }
            }
    }

    inner class PointOfInterestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }


    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}