package oliveiradev.com.github.simpledatabindingrecycleradapter;

import oliveiradev.com.github.lib.SimpleBaseViewHolder;
import oliveiradev.com.github.simpledatabindingrecycleradapter.databinding.ItemSampleBinding;

/**
 * Created by felipe on 30/05/17.
 */

public final class CustomViewHolder implements SimpleBaseViewHolder<ItemSampleBinding, String> {
    private ItemSampleBinding binding;

    @Override
    public void getBinding(ItemSampleBinding binding) {
        this.binding = binding;
    }

    @Override
    public void onBind(String item) {
        binding.setData(item);
    }
}
