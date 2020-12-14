package somiah.jad.todolist

import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

        private val taskRepository = TaskRepository.get()
        val allTodoTasks = taskRepository.getAllTasks("ToDo")
        val allInProcessTasks = taskRepository.getAllTasks("Current")
        val allDoneTasks = taskRepository.getAllTasks("Done")

        fun addTask(task: Task){
            taskRepository.addTask(task)
        }

        fun changeCategory(task: Task){
                taskRepository.changeCategory(task)
        }
}