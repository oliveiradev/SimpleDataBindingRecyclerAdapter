package oliveiradev.com.github.lib.binding_adapter;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import oliveiradev.com.github.lib.OnItemClick;
import oliveiradev.com.github.lib.SimpleBaseViewHolder;
import oliveiradev.com.github.lib.SimpleDataBindingAdapter;

/**
 * Created by felipe on 24/05/17.
 */
public final class RecyclerBindingAdapter {

    @BindingAdapter(value = {"entries", "layout", "onItemClick", "customViewHolder"}, requireAll = false)
    public static <T> void setEntries(RecyclerView recyclerView, List<T> entries, int layout,
                                      OnItemClick<T> onItemClick, SimpleBaseViewHolder viewHolder) {
        final SimpleDataBindingAdapter adapter =
                new SimpleDataBindingAdapter(entries, layout, onItemClick, viewHolder);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
