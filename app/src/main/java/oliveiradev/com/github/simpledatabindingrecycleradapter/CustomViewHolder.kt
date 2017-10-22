package oliveiradev.com.github.simpledatabindingrecycleradapter

import oliveiradev.com.github.lib.SimpleBaseViewHolder
import oliveiradev.com.github.simpledatabindingrecycleradapter.databinding.ItemSampleBinding

/**
 * Created by felipe on 30/05/17.
 */

class CustomViewHolder : SimpleBaseViewHolder<ItemSampleBinding, String> {
    private var binding: ItemSampleBinding? = null

    override fun getBinding(binding: ItemSampleBinding) {
        this.binding = binding
    }

    override fun onBind(item: String) {
        binding?.let {
            it.data = item
        }
    }
}
