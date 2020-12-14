package somiah.jad.todolist

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.util.*

class PopUpNewTask: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity?.layoutInflater?.inflate(R.layout.new_task_pop_up,null)
        val titleText = view?.findViewById(R.id.insert_title_id) as EditText
        val detailText = view?.findViewById(R.id.insert_detail_id) as EditText
        val dateText = view?.findViewById(R.id.insert_date_id) as EditText

        return AlertDialog.Builder(requireContext(),R.style.ThemeOverlay_AppCompat_Dialog_Alert)
            .setView(view)
            .setPositiveButton("Add"){ dialog, _ ->
                val newTask= Task(
                    UUID.randomUUID(),
                    titleText.text.toString(),
                    detailText.text.toString(),
                    dateText.text.toString(),
                    "ToDo")
                targetFragment?.let {
                    (it as Callbacks).onTaskAdd(newTask)
                }
            }.setNegativeButton("Cancel"){dialog, _ ->
                dialog.cancel()
            }.create()
    }

    interface Callbacks{
        fun onTaskAdd(task: Task)
    }
}