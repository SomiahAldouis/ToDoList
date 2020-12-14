package somiah.jad.todolist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import somiah.jad.todolist.database.TaskDatabase
import java.util.*
import java.util.concurrent.Executors


private const val DATABASE_NAME = "task-database"

class TaskRepository private constructor(context: Context) {

    private val database : TaskDatabase = Room.databaseBuilder(
        context.applicationContext,
        TaskDatabase::class.java,
        DATABASE_NAME
    ).build()
    private val taskDao = database.taskDao()
    private val executor = Executors.newSingleThreadExecutor();

    fun getAllTasks(category : String): LiveData<List<Task>> = taskDao.getAllTasks(category)
    fun getTask(id: UUID): LiveData<Task?> = taskDao.getTask(id)



    fun addTask(task: Task) {
        executor.execute {
            taskDao.addTask(task)
        }
    }

    fun changeCategory(task: Task){
        executor.execute {
            taskDao.changeCategory(task)
        }
    }

    companion object {
        private var INSTANCE: TaskRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = TaskRepository(context)
            }
        }
        fun get(): TaskRepository {
            return INSTANCE ?:
            throw IllegalStateException("TaskRepository must be initialized")
        }
    }
}


