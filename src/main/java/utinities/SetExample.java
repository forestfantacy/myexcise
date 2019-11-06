package utinities;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-11-18 20:01
 */
public class SetExample {

    @Test
    public void testQuChong() {
        ArrayList<Integer> arrayList = Lists.newArrayList(1, 1, 2, 3);
        HashSet<Integer> newHashSet = Sets.newHashSet(arrayList);
        System.out.println(newHashSet);
        assertThat(newHashSet.size(), equalTo(3));
    }

    @Test
    public void testCartesian() {
        Set<List<Integer>> cartesianProduct = Sets.cartesianProduct(Sets.newHashSet(1, 2), Sets.newHashSet(3, 4));
        System.out.println(cartesianProduct);
    }

    @Test
    public void testDiff() {
        HashSet<Integer> hashSet1 = Sets.newHashSet(1, 2, 3, 5);
        HashSet<Integer> hashSet2 = Sets.newHashSet(1, 2, 4);
        Sets.SetView<Integer> difference = Sets.difference(hashSet1, hashSet2);
        System.out.println(difference);
    }

    @Test
    public void testIntersection() {
        HashSet<Integer> hashSet1 = Sets.newHashSet(1, 2, 3, 5);
        HashSet<Integer> hashSet2 = Sets.newHashSet(1, 2, 4);
        Sets.SetView<Integer> intersection = Sets.intersection(hashSet1, hashSet2);
        System.out.println(intersection);
    }

    @Test
    public void testUnion() {
        HashSet<Integer> hashSet1 = Sets.newHashSet(1, 2, 3, 5);
        HashSet<Integer> hashSet2 = Sets.newHashSet(1, 2, 4);
        Sets.SetView<Integer> union = Sets.union(hashSet1, hashSet2);
        System.out.println(union);
    }
}
