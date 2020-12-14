package somiah.jad.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var tabViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout_id)
        tabViewPager = findViewById(R.id.view_pager_id)

        tabViewPager.adapter = object : FragmentStateAdapter(this){
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when(position){
                    0 -> FragmentToDo.newInstance("","")
                    1 -> FragmentCurrent.newInstance("","")
                    2 -> FragmentDone.newInstance("","")
                    else -> FragmentToDo.newInstance("","")
                }
            }

        }
        TabLayoutMediator(tabLayout,tabViewPager){ tab, position ->
            when(position){
                0 -> { tab.text = "ToDo" }
                1 -> { tab.text = "InProcess"}
                2 -> { tab.text = "Done"}
                else -> null
            }
        }.attach()


    }
}

