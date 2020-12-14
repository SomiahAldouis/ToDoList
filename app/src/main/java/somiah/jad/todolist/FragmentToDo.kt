package somiah.jad.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentToDo: Fragment() , PopUpNewTask .Callbacks{
    private lateinit var addFab : FloatingActionButton
    private val toDoViewModel:TaskViewModel by lazy{
        ViewModelProviders.of(this).get(TaskViewModel::class.java)
    }
    private lateinit var toDoRecyclerView: RecyclerView
    private var adapter: ToDoAdapter?= ToDoAdapter(emptyList())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    // private var callBacks: NewTaskPopUp.Callbacks?=null

    override fun onTaskAdd(task: Task){

        toDoViewModel.addTask(task)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.todo_fragment, container, false)
        toDoRecyclerView =
            view.findViewById(R.id.todo_recyclerview_id) as RecyclerView
        toDoRecyclerView.layoutManager = LinearLayoutManager(context)
        toDoRecyclerView.adapter = adapter

        addFab = view.findViewById(R.id.fab) as FloatingActionButton

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toDoViewModel.allTodoTasks.observe(
            viewLifecycleOwner,
            Observer { tasks ->
                tasks?.let {
                    //Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(tasks)
                }
            })

        addFab.setOnClickListener {
            PopUpNewTask().apply {
                setTargetFragment(this@FragmentToDo, 0)
                show(this@FragmentToDo.requireFragmentManager(),"Input")
            }
        }
    }

    private fun updateUI(tasks: List<Task>) {
        adapter = ToDoAdapter(tasks)
        toDoRecyclerView.adapter = adapter
    }
    private inner class ToDoHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val taskTextTitle : TextView = itemView.findViewById(R.id.text_title_id)
        val taskTextDetails: TextView = itemView.findViewById(R.id.text_details_id)
        val taskTextDate: TextView = itemView.findViewById(R.id.text_date_id)
        val currentButton : ImageButton = itemView.findViewById(R.id.image_button1_id)
        val doneButton: ImageButton = itemView.findViewById(R.id.image_button2_id)

    }
    private inner class ToDoAdapter(var tasks: List<Task>)
        : RecyclerView.Adapter<ToDoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : ToDoHolder {
            val view = layoutInflater.inflate(R.layout.todo_item, parent, false)
            return ToDoHolder(view)
        }
        override fun getItemCount() : Int{
            return tasks.size
        }
        override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
            val task = tasks[position]
            holder.apply {
                taskTextTitle.text = task.taskTitle
                taskTextDetails.text = task.taskDetails
                taskTextDate.text = task.taskDate

                currentButton.setOnClickListener {
                    task.taskCategory = "Current"
                    toDoViewModel.changeCategory(task)
                }
                doneButton.setOnClickListener {
                    task.taskCategory="Done"
                    toDoViewModel.changeCategory(task)
                }
            }
        }
    }
    companion object {

        fun newInstance(param1: String, param2: String) =
            FragmentToDo().apply {
                arguments = Bundle().apply {

                }
            }
    }
}