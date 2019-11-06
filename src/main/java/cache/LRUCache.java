package cache;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-11-18 21:30
 */
public interface LRUCache<K, V> {

    void put(K key, V value);

    V get(K key);

    void remove(K key);

    void clear();

    int limit();
}
