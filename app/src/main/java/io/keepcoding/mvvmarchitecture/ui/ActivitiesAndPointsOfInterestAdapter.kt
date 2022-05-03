package io.keepcoding.mvvmarchitecture.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.utils.Constants
import kotlinx.android.synthetic.main.activity_recycler_view_item.view.*
import kotlinx.android.synthetic.main.point_of_interest_recycler_view_item.view.*
import java.lang.IllegalArgumentException

class ActivitiesAndPointsOfInterestAdapter(val context: Context, private val activityItemClickListener:  ((activityViewModel: ActivityViewModel) -> Unit)? = null, private val pointOfInterestItemClickListener : ((pointOfInterestViewModel: PointOfInterestViewModel) -> Unit)? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val activityViewTypeListener : ((View) -> Unit) = {
        if(it.tag is ActivityViewModel){
            activityItemClickListener?.invoke(it.tag as ActivityViewModel)
        } else {
            throw IllegalArgumentException("View tag has not been set to item view model")
        }
    }

    private val pointOfInterestViewTypeListener : ((View) -> Unit) = {
        if(it.tag is PointOfInterestViewModel) {
            pointOfInterestItemClickListener?.invoke(it.tag as PointOfInterestViewModel)
        } else {
            throw IllegalArgumentException("View tag has not been set to item view model")
        }
    }

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
                    itemView.activityName.text = it.name
                }
            }
    }

    inner class PointOfInterestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pointOfInterestViewModel: PointOfInterestViewModel? = null
            set(value) {
                field = value
                itemView.tag = pointOfInterestViewModel
                field?.let {
                    itemView.pointOfInterestName.text = it.name
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View
        return if(viewType == Constants.ACTIVITY_VIEW_TYPE){
            view = LayoutInflater.from(context).inflate(R.layout.activity_recycler_view_item, parent, false)
            ActivityViewHolder(view)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.point_of_interest_recycler_view_item, parent, false)
            PointOfInterestViewHolder(view)
        }
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
                activityViewHolder.activityViewModel = activity
                activityViewHolder.itemView.setOnClickListener(activityViewTypeListener)
            } else {
                val pointOfInterest: PointOfInterestViewModel = it as PointOfInterestViewModel
                val pointOfInterestViewHolder = (holder as PointOfInterestViewHolder)
                pointOfInterestViewHolder.pointOfInterestViewModel = pointOfInterest
                pointOfInterestViewHolder.itemView.setOnClickListener(pointOfInterestViewTypeListener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(!activitiesAndPointsOfInterestItems?.isEmpty()!!) {
            activitiesAndPointsOfInterestItems!![position]!!.viewType
        } else {
            return 0
        }
    }

}