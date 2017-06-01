package oliveiradev.com.github.lib;

/**
 * Created by felipe on 30/05/17.
 */

public interface SimpleBaseViewHolder<T, R> {

    void getBinding(T binding);

    void onBind(R item);
}
