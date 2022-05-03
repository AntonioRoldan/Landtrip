package io.keepcoding.mvvmarchitecture.ui.mytripsbottomnavtab

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.mvvmarchitecture.R
import io.keepcoding.mvvmarchitecture.ui.TripViewModel
import kotlinx.android.synthetic.main.trip_recycler_view_item.view.*
import java.lang.IllegalArgumentException

class MyTripsAdapter(val context: Context, private val itemClickListener: ((TripViewModel) -> Unit)?): RecyclerView.Adapter<MyTripsAdapter.TripViewHolder>() {

    var tripsItems: MutableList<TripViewModel?>? = null
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    private val tripItemClickListener : ((View) -> Unit) = {
        if(it.tag is TripViewModel){
            itemClickListener?.invoke(it.tag as TripViewModel)
        } else {
            throw IllegalArgumentException("View tag has not been set to item view model")
        }
    }

    inner class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tripViewModel: TripViewModel? = null
        set(value) {
            field = value
            itemView.tag = tripViewModel
            field?.let {
                itemView.tripName.text = it.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTripsAdapter.TripViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.trip_recycler_view_item, parent, false)
        return TripViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyTripsAdapter.TripViewHolder, position: Int) {
        val trip = tripsItems?.get(position)
        holder.tripViewModel = trip
        holder.itemView.setOnClickListener(tripItemClickListener)
    }

    override fun getItemCount(): Int {
        return if (tripsItems != null) {
            tripsItems!!.size
        } else {
            0
        }
    }


}