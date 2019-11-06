package cache;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-11-18 22:02
 */
public class LinkedListLRUCache<K, V> implements LRUCache<K, V> {

    private int limit;

    private final LinkedList<K> keys = new LinkedList();

    private final Map<K, V> cache = new HashMap<>();

    public LinkedListLRUCache(int limit) {
        this.limit = limit;
    }

    @Override
    public void put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);

        if (keys.size() >= limit) {
            K first = keys.removeFirst();
            cache.remove(first);
        }

        keys.addLast(key);
        cache.put(key, value);
    }

    @Override
    public V get(K key) {

        boolean remove = keys.remove(key);
        if (!remove) {
            return null;
        }

        keys.addLast(key);
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        boolean remove = keys.remove(key);
        if (remove) {
            cache.remove(key);
        }
    }

    @Override
    public void clear() {
        keys.clear();
        cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    public static void main(String[] args) {

        LinkedListLRUCache<Integer, Integer> lruCache = new LinkedListLRUCache<>(3);
        lruCache.put(2, null);
        lruCache.put(1, 1);
        lruCache.put(3, 3);
        System.out.println(lruCache);
        lruCache.put(4, 4);
        System.out.println(lruCache);
        lruCache.get(1);
        System.out.println(lruCache);
    }
}
