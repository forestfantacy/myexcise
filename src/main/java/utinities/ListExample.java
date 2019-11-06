package utinities;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-11-18 20:35
 */
public class ListExample {

    @Test
    public void testTransform(){
        ArrayList<String> list = Lists.newArrayList("Java", "Scale", "Lists");
        List<String> transform = Lists.transform(list, e -> e.toLowerCase());
        //System.out.println(transform);
        transform.forEach(System.out::println);
    }

    @Test
    public void testPartition(){
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        List<List<Integer>> partition = Lists.partition(list, 3);
        System.out.println(partition.get(0));
        System.out.println(partition.get(1));
    }
}
