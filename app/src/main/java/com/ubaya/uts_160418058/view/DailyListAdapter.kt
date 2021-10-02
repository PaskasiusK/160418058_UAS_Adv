package com.ubaya.uts_160418058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.model.daily
import com.ubaya.uts_160418058.util.loadImage
import kotlinx.android.synthetic.main.daily_list_item.view.*
import kotlinx.android.synthetic.main.fragment_daily_list.view.*
import kotlinx.android.synthetic.main.nav_header.view.*

    class DailyListAdapter(val DailyList:ArrayList<daily>):RecyclerView.Adapter<DailyListAdapter.DailyListViewHolder>() {
        class DailyListViewHolder(var view: View) : RecyclerView.ViewHolder(view)
        fun updateDailyList(newDailyList: List<daily>) {
            DailyList.clear()
            DailyList.addAll(newDailyList)
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val v = inflater.inflate(R.layout.daily_list_item, parent, false)
            return DailyListViewHolder(v)
        }

        override fun onBindViewHolder(holder: DailyListViewHolder, position: Int) {
            holder.view.txtTitle.text = DailyList[position].title
            holder.view.txtCategory.text = DailyList[position].category
            holder.view.txtDate.text = DailyList[position].time
            holder.view.txtPriority.text = DailyList[position].priority
            holder.view.imgProfile.loadImage(DailyList[position].photo_url.toString(), holder.view.progressBar)
            holder.view.btnDetail.setOnClickListener{
                val action = DailyListFragmentDirections.actionDailyDetail(position.toString(),"daily")
                Navigation.findNavController(it).navigate(action)
            }

        }

        override fun getItemCount(): Int {
            return DailyList.size

        }
    }

