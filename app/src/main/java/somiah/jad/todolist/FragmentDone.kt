package somiah.jad.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer


    class FragmentDone : Fragment() {

        private val doneViewModel:TaskViewModel by lazy{
            ViewModelProviders.of(this).get(TaskViewModel::class.java)
        }
        private lateinit var doneRecyclerView: RecyclerView
        private var adapter: FragmentDone.DoneAdapter?= DoneAdapter(emptyList())

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {

            }
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.done_fragment, container, false)
            doneRecyclerView =
                view.findViewById(R.id.done_recyclerview_id) as RecyclerView
            doneRecyclerView.layoutManager = LinearLayoutManager(context)
            doneRecyclerView.adapter = adapter

            return view
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            doneViewModel.allDoneTasks.observe(
                viewLifecycleOwner,
                Observer { tasks ->
                    tasks?.let {
                        //Log.i(TAG, "Got crimes ${crimes.size}")
                        updateUI(tasks)
                    }
                })
        }




        private fun updateUI(tasks: List<Task>) {

            adapter = DoneAdapter(tasks)
            doneRecyclerView.adapter = adapter
        }

        private inner class DoneHolder(view: View)
            : RecyclerView.ViewHolder(view) {

            val taskTextTitle : TextView = itemView.findViewById(R.id.text_title_id)
            val taskTextDetails: TextView = itemView.findViewById(R.id.text_details_id)
            val taskTextDate: TextView = itemView.findViewById(R.id.text_date_id)
            val todoButton : ImageButton = itemView.findViewById(R.id.image_button1_id)
            val currentButton: ImageButton = itemView.findViewById(R.id.image_button2_id)

        }
        private inner class DoneAdapter(var tasks: List<Task>)
            : RecyclerView.Adapter<DoneHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                    : DoneHolder {
                val view = layoutInflater.inflate(R.layout.done_item, parent, false)
                return DoneHolder(view)
            }
            override fun getItemCount() : Int{

                return tasks.size
            }
            override fun onBindViewHolder(holder: DoneHolder, position: Int) {
                val task = tasks[position]
                holder.apply {
                    taskTextTitle.text = task.taskTitle
                    taskTextDetails.text = task.taskDetails
                    taskTextDate.text = task.taskDate

                    currentButton.setOnClickListener {
                        task.taskCategory = "Current"
                        doneViewModel.changeCategory(task)
                    }
                    todoButton.setOnClickListener {
                        task.taskCategory="ToDo"
                        doneViewModel.changeCategory(task)
                    }
                }
            }
        }

        companion object {

            fun newInstance(param1: String, param2: String) =
                FragmentDone().apply {
                    arguments = Bundle().apply {

                    }
                }
        }

}