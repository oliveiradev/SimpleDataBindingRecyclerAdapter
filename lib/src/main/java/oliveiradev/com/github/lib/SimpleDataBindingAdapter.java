package oliveiradev.com.github.lib;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by felipe on 24/05/17.
 */

public class SimpleDataBindingAdapter<T>
        extends RecyclerView.Adapter<SimpleDataBindingAdapter.ViewHolder> implements ListOperation<T> {

    private List<T> items;
    private int itemId;
    private OnItemClick onItemClick;
    @Nullable
    private SimpleBaseViewHolder viewHolder;

    public SimpleDataBindingAdapter(List<T> items, int itemId, OnItemClick onItemClick,
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

    @Override
    public void add(T item) {
        items.add(item);
        update(makeNewList(items));
    }

    @Override
    public void add(T item, int index) {
        items.add(index, item);
        update(makeNewList(items));
    }

    @Override
    public void addAll(Collection<T> items) {
        this.items.addAll(items);
        update(makeNewList(this.items));
    }

    @Override
    public void remove(T item) {
        items.remove(item);
        update(makeNewList(items));
    }

    private List<T> makeNewList(List<T> newList) {
        return new ArrayList<>(newList);
    }

    private void update(List newList) {
        final SimpleDiffCallback<T> diffCallback =
                new SimpleDiffCallback(items, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        items.clear();
        items.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;
        @Nullable
        final SimpleBaseViewHolder viewHolder;

        ViewHolder(ViewDataBinding binding, @Nullable SimpleBaseViewHolder viewHolder) {
            super(binding.getRoot());
            this.binding = binding;
            this.viewHolder = viewHolder;
            if (this.viewHolder != null) {
                this.viewHolder.getBinding(binding);
            }
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
