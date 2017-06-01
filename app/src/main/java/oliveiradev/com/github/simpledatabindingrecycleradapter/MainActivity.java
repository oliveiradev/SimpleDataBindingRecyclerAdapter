package oliveiradev.com.github.simpledatabindingrecycleradapter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Arrays;

import oliveiradev.com.github.lib.OnItemClick;
import oliveiradev.com.github.simpledatabindingrecycleradapter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewHolder(new CustomViewHolder());
        binding.setItems(Arrays.asList(getResources().getStringArray(R.array.series)));
        binding.setOnItemClick(new OnItemClick<String>() {
            @Override
            public void call(String item) {
                Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();
            }
        });
    }
}
