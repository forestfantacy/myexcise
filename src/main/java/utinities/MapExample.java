package utinities;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-11-17 22:48
 */
public class MapExample {

    @Test
    public void testCreate() {
        ArrayList<String> valueList = Lists.newArrayList("1", "2", "3");
        ImmutableMap<String, String> map = Maps.uniqueIndex(valueList, v -> v + "_key");
        //System.out.println(map);

        Map<String, String> map1 = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        System.out.println(map1);

        ImmutableMap<String, String> of = ImmutableMap.of("k1", "v1");

        Map<String,String> test = ImmutableMap.<String, String>builder()
                .put("k1","v1")
                .put("k2","v2").build();
    }

    @Test
    public void testTransform() {
        Map<String, String> map1 = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        Map<String, String> newMap = Maps.transformValues(map1, v -> v + "_transform");
        System.out.println(newMap);
        assertThat(newMap.containsValue("1_value_transform"),is(true));
    }

    @Test
    public void testFilter() {
        Map<String, String> map1 = Maps.asMap(Sets.newHashSet("1", "2", "3"), k -> k + "_value");
        Map<String, String> newMap = Maps.filterKeys(map1, k -> Lists.newArrayList("1", "2").contains(k));
        System.out.println(newMap);
        assertThat(newMap.containsKey("3"),is(false));

        Map<String, String> newMap2 = Maps.filterValues(map1, v -> Lists.newArrayList("1_value").contains(v));
        assertThat(newMap2.containsValue("2_value"),is(false));
        System.out.println(newMap2);

        Map<String, String> newMap3 = Maps.filterEntries(map1, entry -> Lists.newArrayList("1_value").contains(entry.getValue()));
        System.out.println(newMap3);
        assertThat(newMap3.containsValue("1_value"), is(true));
    }

    @Test
    public void testLinkedListMultiMap() {
        LinkedListMultimap<String, String> multimap = LinkedListMultimap.create();
        multimap.put("1", "2");
        multimap.put("1", "2");
        System.out.println(multimap); //{1=[2, 2]}
    }

    @Test
    public void testBiMap() {
        HashBiMap<Object, Object> hashBiMap = HashBiMap.create();
        hashBiMap.put("1", "2");
        hashBiMap.put("1", "3");
        hashBiMap.put("2", "2");//value already present: 2
        System.out.println(hashBiMap);
    }
}
