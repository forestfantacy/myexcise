package cachegua;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import net.jodah.expiringmap.ExpiringMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-08 16:19
 */
public class CacheLoaderTest {

    @Test
    public void testBasic() throws ExecutionException, InterruptedException {

        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().maximumSize(10)
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .build(createCacheLoader());

        Employee alex = cache.get("alex");
        assertThat(alex, notNullValue());
        TimeUnit.SECONDS.sleep(10);
        Employee alex2 = cache.get("alex");
        assertTrue(alex == alex2);
    }

    @Test
    public void testEvictionBySize() throws InterruptedException {

        CacheLoader cacheLoader = createCacheLoader();
        LoadingCache loadingCache = CacheBuilder.newBuilder().maximumSize(3).expireAfterWrite(3,TimeUnit.SECONDS).build(cacheLoader);
        loadingCache.getUnchecked("a");
        loadingCache.getUnchecked("b");
        loadingCache.getUnchecked("c");
        assertThat(loadingCache.size(), equalTo(3L));

        loadingCache.getUnchecked("d");
        assertThat(loadingCache.getIfPresent("a"), nullValue());
        assertThat(loadingCache.size(), equalTo(3L));

        TimeUnit.SECONDS.sleep(5);
        System.out.println(loadingCache.size());
    }

    @Test
    public void testEvictionByWeight() throws ExecutionException, InterruptedException {

        Weigher<String,Employee> weigher = (key,employ) ->
                employ.getName().length();

        CacheLoader cacheLoader = createCacheLoader();
        LoadingCache loadingCache = CacheBuilder.newBuilder().maximumWeight(6)//最大长度是6
                .concurrencyLevel(1).weigher(weigher).build(cacheLoader);

        loadingCache.getUnchecked("aa");
        loadingCache.getUnchecked("bb");
        loadingCache.getUnchecked("cc");
        assertThat(loadingCache.size(), equalTo(3L));

        loadingCache.getUnchecked("d");
        assertThat(loadingCache.getIfPresent("aa"), nullValue());
        assertThat(loadingCache.size(), equalTo(3L));

    }

    @Test
    public void testCachePreLoad(){
        CacheLoader<String, String> loader = CacheLoader.from(String::toUpperCase);//输入key，构造出value，而 put(key,value)没有这个强制限制
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().build(loader);

        Map<String,String> preData = new HashMap<String,String>(){
            {
                put("alex", "nan");
                put("hello", "nv");
            }
        };

        cache.putAll(preData);
        cache.put("zhangsan","nv");
        assertThat(cache.size(),equalTo(3L));
    }

    @Test
    public void testCachePut() throws InterruptedException, ExecutionException {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                //.maximumSize(3)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build();
        cache.put("a","aa");
        //cache.put("b","bb");
        //cache.put("c","cc");
        //cache.put("d","dd");
        //assertThat(cache.size(),equalTo(3L));
        //assertThat(cache.getIfPresent("a"), nullValue());
        TimeUnit.SECONDS.sleep(3);
        assertThat(cache.size(),equalTo(0L));

    }


    @Test
    public void testEvictionByWriteTime() throws InterruptedException {

        CacheLoader cacheLoader = createCacheLoader();
        LoadingCache cache = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS).build(cacheLoader);
        assertThat(cache.getUnchecked("alex"), notNullValue());
        assertThat(cache.size(), equalTo(1L));

        assertThat(cache.getUnchecked("guava"), notNullValue());

        TimeUnit.SECONDS.sleep(1);
        assertThat(cache.getIfPresent("guava"), notNullValue());

        TimeUnit.SECONDS.sleep(2);
        assertThat(cache.getIfPresent("guava"), nullValue());
    }

    @Test
    public void testLinkedHashMap(){

        Map<String, Object> cache = new LinkedHashMap<String, Object>(5, 0.75f, true) {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
                return size() > 3;
            }
        };

        cache.put("a", "aa");
        cache.put("b", "bb");
        cache.put("c", "cc");
        cache.put("d", "dd");

        System.out.println(cache);
        assertThat(cache.size(), equalTo(3));
        assertThat(cache.get("a"), nullValue());

    }

    @Test
    public void testConcurrentMapByExpireTime() throws InterruptedException {

        Map<String, Object> cache = ExpiringMap.builder()
                .maxSize(3)
                .expiration(3, TimeUnit.SECONDS)
                .build();

        cache.put("a", "aa");
        cache.put("b", "bb");
        cache.put("c", "cc");
        cache.put("d", "dd");

        System.out.println(cache);

        assertThat(cache.size(), equalTo(3));
        assertThat(cache.get("a"), nullValue());

        TimeUnit.SECONDS.sleep(4);
        assertThat(cache.size(), equalTo(0));
        System.out.println(cache);

    }

    private Employee getEmployeeByName(final String name) {
        System.out.println("the employee " + name + " is load from DB.");
        return new Employee(name,20);
    }

    private CacheLoader createCacheLoader() {
        return new CacheLoader<String, Employee>() {
            @Override
            public Employee load(String key) throws Exception {
                return getEmployeeByName(key);
            }
        };
    }
}
