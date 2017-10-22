package oliveiradev.com.github.simpledatabindingrecycleradapter

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import oliveiradev.com.github.lib.OnItemClick
import oliveiradev.com.github.simpledatabindingrecycleradapter.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            viewHolder = CustomViewHolder()
            items = Arrays.asList(*resources.getStringArray(R.array.series))
            onItemClick = OnItemClick<String> { showToast(it) }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
