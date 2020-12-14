package somiah.jad.todolist

import android.app.Application

class TaskIntentApp: Application() {

    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)
    }

}