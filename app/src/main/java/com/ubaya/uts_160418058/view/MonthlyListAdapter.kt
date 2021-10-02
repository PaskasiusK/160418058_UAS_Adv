package com.ubaya.uts_160418058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.model.monthly
import com.ubaya.uts_160418058.util.loadImage
import kotlinx.android.synthetic.main.daily_list_item.view.*


class MonthlyListAdapter(val MonthlyList:ArrayList<monthly>): RecyclerView.Adapter<MonthlyListAdapter.monthlyListViewHolder>()
{
    class monthlyListViewHolder(var view: View) : RecyclerView.ViewHolder(view)
    fun updateMonthlyList(newMonthlyList: List<monthly>) {
        MonthlyList.clear()
        MonthlyList.addAll(newMonthlyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): monthlyListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.daily_list_item, parent, false)
        return MonthlyListAdapter.monthlyListViewHolder(v)
    }

    override fun onBindViewHolder(holder: monthlyListViewHolder, position: Int) {
        holder.view.txtTitle.text = MonthlyList[position].title
        holder.view.txtCategory.text = MonthlyList[position].category
        holder.view.txtDate.text = MonthlyList[position].month
        holder.view.txtPriority.text = MonthlyList[position].priority
        holder.view.imgProfile.loadImage(MonthlyList[position].photo_url.toString(), holder.view.progressBar)
        holder.view.btnDetail.setOnClickListener{
            val action = MonthlyListFragmentDirections.actionMovieDetail(position.toString(),"monthly")
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return MonthlyList.size
    }
}