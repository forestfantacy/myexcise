package utinities.datastruct.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-29 09:37
 */
public class ConcurrentSkipListVsConcurrentHashmap {

    private final static Map<Class<?>, List<Entry>> summary = new HashMap<Class<?>, List<Entry>>(){
        {
            put(ConcurrentHashMap.class, new ArrayList<>());
            put(ConcurrentSkipListMap.class, new ArrayList<>());
        }
    };

    public static void main(String[] args) throws Exception {
        for (int i = 10; i < 100; ) {
            pressureTest(new ConcurrentHashMap<>(), i, true);
            pressureTest(new ConcurrentSkipListMap<>(), i, true);
            i += 10;
        }

        summary.forEach((k,v)->{
            System.out.println(k.getSimpleName());
            v.forEach(System.out::println);
            System.out.println("======================================");
        });
    }

    private static void pressureTest(final Map<String,Integer> map,int threshold,final boolean retrive) throws Exception {
        System.out.println("start pressure testing the map [" + map.getClass() + "] use the threshold [" + threshold + "],retrive [" + retrive + "]");
        long totalTime = 0L;
        final int MAX_THRESHOLD = 5000000;
        for (int i = 0; i < 5; i++) {
            final AtomicInteger counter = new AtomicInteger(0);
            map.clear();
            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(threshold);
            for (int j = 0; j < threshold; j++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int k = 0; k < MAX_THRESHOLD && counter.getAndIncrement() < MAX_THRESHOLD; k++) {

                            Integer randomNumber = (int) Math.ceil(Math.random() * 600000);
                            if (retrive) {
                                map.get(String.valueOf(randomNumber));
                            }
                            map.put(String.valueOf(randomNumber), randomNumber);
                        }
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endTime = System.nanoTime();
            long period = (endTime - startTime) / 1000000L;
            System.out.println(threshold + " element inserted/retrived in " + period + " ms");
            totalTime += period;
            System.out.println();
        }

        summary.get(map.getClass()).add(new Entry(threshold, (totalTime / 5)));
        System.out.println("for the map [" + map.getClass() + "] the average time is " + (totalTime / 5) + " ms");
        System.out.println("");
    }

    static class Entry{
        int count;
        long ms;

        public Entry(int count, long ms) {
            this.count = count;
            this.ms = ms;
        }

        @Override
        public String toString() {
            return "count=" + count +", ms=" + ms;
        }
    }

}
