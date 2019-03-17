package cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class HashCache<K, V> implements Cache<K, V> {
    private Map<K, V> cache;
    private double clearFactor;
    private int capacity;

    public HashCache(int capacity, double clearFactor) {
        checkDigitParameter(clearFactor);
        checkDigitParameter(capacity);
        cache = new LinkedHashMap<>(capacity);
        this.clearFactor = clearFactor;
        this.capacity = capacity;
    }

    @Override
    public void cache(K key, V value) {
        checkObjectParameter(key, value);

        cache.put(key, value);

        if (cache.size() > capacity) {
            int countDeleteElement = (int) Math.ceil(cache.size() * clearFactor);
            clear(countDeleteElement);
        }
    }

    @Override
    public V get(K element) {
        checkObjectParameter(element);

        V repeat = cache.get(element);

        if (repeat != null) {
            cache.remove(element);
            return cache.put(element, repeat);
        }

        return null;
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<K, V>> entries = cache.entrySet();

        int i = 1;
        for (Map.Entry<K, V> entry : entries) {
            builder.append(i)
                    .append(". ")
                    .append(entry.getKey())
                    .append("\n");
            i++;
        }

        return builder.toString();
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public void clear(int count) {
        Iterator<Map.Entry<K, V>> removeIterator = cache.entrySet().iterator();

        for (int i = 0; i < count; i++) {
            removeIterator.next();
            removeIterator.remove();
        }
    }

    private void checkObjectParameter(Object... params) {
        for (Object param : params) {
            if (param == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkDigitParameter(long... params) {
        for (long param : params) {
            if (param <= 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void checkDigitParameter(double... params) {
        for (double param : params) {
            if (param <= 0) {
                throw new IllegalArgumentException();
            }
        }
    }
}
