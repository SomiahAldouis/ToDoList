package somiah.jad.todolist.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import somiah.jad.todolist.Task

@Database (entities = [Task::class] , version = 1)
@TypeConverters(TaskTypeConverters :: class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

}
