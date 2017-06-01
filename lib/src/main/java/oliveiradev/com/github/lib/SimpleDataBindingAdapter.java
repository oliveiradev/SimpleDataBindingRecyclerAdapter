package oliveiradev.com.github.lib;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by felipe on 24/05/17.
 */

public class SimpleDataBindingAdapter extends RecyclerView.Adapter<SimpleDataBindingAdapter.ViewHolder> {

    private List items = Collections.EMPTY_LIST;
    private int itemId;
    private OnItemClick onItemClick;
    @Nullable
    private SimpleBaseViewHolder viewHolder;

    public SimpleDataBindingAdapter(List items, int itemId, OnItemClick onItemClick,
                                    @Nullable SimpleBaseViewHolder viewHolder) {
        this.items = items;
        this.itemId = itemId;
        this.onItemClick = onItemClick;
        this.viewHolder = viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return itemId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, itemId, parent, false);
        return new ViewHolder(binding, viewHolder);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(items.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null) onItemClick.call(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;
        final SimpleBaseViewHolder viewHolder;

        ViewHolder(ViewDataBinding binding, SimpleBaseViewHolder viewHolder) {
            super(binding.getRoot());
            this.binding = binding;
            this.viewHolder = viewHolder;
            if (viewHolder != null) viewHolder.getBinding(binding);
        }

        public <T> void  bind(T item) {
            if (viewHolder != null) {
                viewHolder.onBind(item);
            } else {
                binding.setVariable(BR.data, item);
            }

            binding.executePendingBindings();
        }

    }
}
