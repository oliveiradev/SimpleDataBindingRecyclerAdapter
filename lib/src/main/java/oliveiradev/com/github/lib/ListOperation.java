package oliveiradev.com.github.lib;

import java.util.Collection;

/**
 * Created by felipe on 29/06/17.
 */

public interface ListOperation<T> {
    void add(T item);
    void add(T item, int index);
    void addAll(Collection<T> items);
    void remove(T item);
}
