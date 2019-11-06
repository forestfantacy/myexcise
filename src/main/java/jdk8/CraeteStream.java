package jdk8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-02 23:13
 */
public class CraeteStream {

    public static void main(String[] args) {

    }

    private static Stream<String> createStreamFromColl() {
        List<String> es = Arrays.asList("hello", "world");
        return es.stream();
    }

    private static Stream<String> createStreamByFactory() {
        return Stream.of("hello", "world");
    }

    private static Stream<String> createStreamByArr() {
        String[] arr = new String[]{"hello", "world"};
        return Arrays.stream(arr);
    }

    private static Stream<String> createStreamFromFile() {
        Path path = Paths.get("/Users/forest/test.txt");
        try (Stream<String> lines = Files.lines(path)) {
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Stream<Obj> createStreamFromGenerate() {
        return Stream.generate(new ObjSupplier());
    }

    static class ObjSupplier implements Supplier<Obj> {

        private int index = 0;
        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            return new Obj(random.nextInt(100),"name->"+index);
        }
    }

    static class Obj{
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

