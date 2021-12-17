package com.ubaya.uts_160418058.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.databinding.DailyListItemBinding
import com.ubaya.uts_160418058.model.Todo
//import com.ubaya.uts_160418058.model.daily
import com.ubaya.uts_160418058.util.loadImage
import com.ubaya.uts_160418058.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.daily_list_item.view.*
import kotlinx.android.synthetic.main.fragment_daily_list.view.*
import kotlinx.android.synthetic.main.nav_header.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DailyListAdapter(val DailyList:ArrayList<Todo>):RecyclerView.Adapter<DailyListAdapter.DailyListViewHolder>(),TodoDetailClick {
        class DailyListViewHolder(var view: DailyListItemBinding) : RecyclerView.ViewHolder(view.root)


        fun updateDailyList(newDailyList: List<Todo>) {
            DailyList.clear()
            DailyList.addAll(newDailyList)
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyListViewHolder {

            val inflater = LayoutInflater.from(parent.context)
            val v = DataBindingUtil.inflate<DailyListItemBinding>(inflater, R.layout.daily_list_item, parent, false)

            return DailyListViewHolder(v)
        }

        override fun onBindViewHolder(holder: DailyListViewHolder, position: Int) {
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
            val netDate = Date(DailyList[position].time.toLong() * 1000)
            var time =  formatter.format(netDate);
            holder.view.todo = DailyList[position]
            holder.view.txtDate.text = time
            holder.view.detailListener = this
//            holder.view.txtTitle.text = DailyList[position].title
//            holder.view.txtCategory.text = DailyList[position].category
//            holder.view.txtDate.text = DailyList[position].time
//            holder.view.txtPriority.text = DailyList[position].priority
//            holder.view.imgProfile.loadImage(DailyList[position].photo_url.toString(), holder.view.progressBar)
//            holder.view.btnDetail.setOnClickListener{
//                val action = DailyListFragmentDirections.actionDailyDetail(position)
//                Navigation.findNavController(it).navigate(action)
//            }

        }

        override fun getItemCount(): Int {
            return DailyList.size

        }

        override fun onTodoDetailClick(v: View) {
            val uuid = v.tag.toString().toInt()
            val action = DailyListFragmentDirections.actionDailyDetail(uuid)

            Navigation.findNavController(v).navigate(action)

        }
    }

