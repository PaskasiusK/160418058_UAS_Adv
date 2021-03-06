package com.ubaya.uts_160418058.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.uts_160418058.R
import com.ubaya.uts_160418058.model.Todo
import com.ubaya.uts_160418058.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_daily_list.*

class DailyListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val dailyListAdapter = DailyListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

//        removeDataDummy()
//        addDatadummy()
        viewModel.refreshDaily()
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = dailyListAdapter
        refreshLayout.setOnRefreshListener {
            recView.visibility = View.GONE
            txtError.visibility = View.GONE
            progressLoad.visibility = View.VISIBLE
            viewModel.refreshDaily()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.TodoLD.observe(viewLifecycleOwner, Observer {
            dailyListAdapter.updateDailyList(it)

        })
        viewModel.taskLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                txtError.visibility = View.VISIBLE
            } else {
                txtError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {

                recView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            } else {
                recView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            }
        })


    }
    fun addDatadummy()
    {
            var todo1 = Todo("Makan pagi","makan pagi jam 08.00 sampai 09.00",0,"kegiatan sehari-hari","penting","https://static.vecteezy.com/system/resources/previews/000/224/112/non_2x/vector-breakfast-illustration.jpg","Daily",1)
            var todo2 = Todo("Meeting","rapat membahas mengenai project",0,"Meeting Kerja","penting","https://static.vecteezy.com/system/resources/previews/002/158/141/non_2x/illustrations-flat-design-concept-video-conference-online-meeting-work-form-home-call-and-live-video-illustrate-free-vector.jpg","Daily",0)
            var todo3 = Todo("Weekly boss genshin Refresh","saatnya weekly",0,"Hobby","sedang","https://pbs.twimg.com/media/E_JA2BbVUAIP9FQ?format=jpg&name=large","Weekly",0)
            var todo4 = Todo("Audit","waktunya audit",0,"Kerja","penting","https://static.vecteezy.com/system/resources/previews/000/159/197/non_2x/bookkeeping-workspace-free-vector.jpg","Monthly",0)
            val add = listOf(todo1,todo2,todo3,todo4)
            viewModel.addDummyTodo(add)
    }
    fun removeDataDummy()
    {
        viewModel.deleteDummyTodo()
    }
}