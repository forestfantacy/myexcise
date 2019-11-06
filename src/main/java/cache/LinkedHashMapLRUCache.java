package cache;

import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-11-18 21:34
 */
public class LinkedHashMapLRUCache implements LRUCache {

    private static class InternalLRUCache extends LinkedHashMap {

        private int limit;

        public InternalLRUCache(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > limit;
        }
    }

    private int limit;

    private InternalLRUCache internalLRUCache;

    public LinkedHashMapLRUCache(int limit) {
        Preconditions.checkArgument(limit > 0, "limit must more than zero");
        this.limit = limit;
        this.internalLRUCache = new InternalLRUCache(limit);
    }

    @Override
    public void put(Object key, Object value) {
        internalLRUCache.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return internalLRUCache.get(key);
    }

    @Override
    public void remove(Object key) {
        internalLRUCache.remove(key);
    }

    @Override
    public void clear() {
        internalLRUCache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        return "LinkedHashMapLRUCache{" +
                "internalLRUCache=" + internalLRUCache +
                '}';
    }

    public static void main(String[] args) {
        LinkedHashMapLRUCache lruCache = new LinkedHashMapLRUCache(3);
        lruCache.put(2, 2);
        lruCache.put(1, 1);
        lruCache.put(3, 3);
        System.out.println(lruCache);
        lruCache.put(4, 4);
        System.out.println(lruCache);
        lruCache.get(1);
        System.out.println(lruCache);
    }
}
