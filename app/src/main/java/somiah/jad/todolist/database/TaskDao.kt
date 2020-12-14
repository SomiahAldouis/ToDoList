package somiah.jad.todolist.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import somiah.jad.todolist.Task
import java.util.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE taskCategory=(:category)")
    fun getAllTasks(category: String): LiveData<List<Task>>


    @Query("SELECT * FROM task WHERE taskId=(:id)")
    fun getTask(id: UUID): LiveData<Task?>

    @Insert
    fun addTask(task : Task)

    @Update
    fun changeCategory(task: Task)

}