package com.ubaya.uts_160418058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.model.daily
import com.ubaya.uts_160418058.model.weekly
import com.ubaya.uts_160418058.util.loadImage
import kotlinx.android.synthetic.main.daily_list_item.view.*

class WeeklyListAdapter(val WeeklyList:ArrayList<weekly>): RecyclerView.Adapter<WeeklyListAdapter.weeklyListViewHolder>() {
    class weeklyListViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    fun updateWeeklyList(newWeeklyList: List<weekly>) {
        WeeklyList.clear()
        WeeklyList.addAll(newWeeklyList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): weeklyListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.daily_list_item, parent, false)
        return WeeklyListAdapter.weeklyListViewHolder(v)
    }

    override fun onBindViewHolder(holder: weeklyListViewHolder, position: Int) {
        holder.view.txtTitle.text = WeeklyList[position].title
        holder.view.txtCategory.text = WeeklyList[position].category
        holder.view.txtDate.text = WeeklyList[position].date
        holder.view.txtPriority.text = WeeklyList[position].priority
        holder.view.imgProfile.loadImage(WeeklyList[position].photo_url.toString(), holder.view.progressBar)
        holder.view.btnDetail.setOnClickListener{
            val action = WeeklyListFragmentDirections.actionWeeklyDetail(position.toString(),"weekly")
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {

        return WeeklyList.size
    }
}