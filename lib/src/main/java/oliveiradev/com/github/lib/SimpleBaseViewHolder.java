package oliveiradev.com.github.lib;

import android.databinding.ViewDataBinding;

/**
 * Created by felipe on 30/05/17.
 */

public interface SimpleBaseViewHolder<T extends ViewDataBinding, R> {

    void getBinding(T binding);

    void onBind(R item);
}
