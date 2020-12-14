package somiah.jad.todolist
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    @PrimaryKey
    val taskId: UUID = UUID.randomUUID(),
    var taskTitle: String = "",
    var taskDetails: String = "",
    var taskDate: String = "",
    var taskCategory: String = "")
