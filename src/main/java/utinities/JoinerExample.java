package utinities;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-11-17 18:55
 */
public class JoinerExample {

    private final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java", "Scale", "Kafka"
    );

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Scale", null
    );

    private final Map<String, String> stringMap = of("Hello", "Guava", "Java", "Scale");

    private final String TARGET = "/Users/forest/workbench/myrepo/myguava/test.txt";

    @Test
    public void TestJoinOnJoin() {
        String result = Joiner.on("#").join(stringList);
        assertThat(result, equalTo("Google#Guava#Java#Scale#Kafka"));
    }

    @Test(expected = NullPointerException.class)
    public void TestJoinOnJoin_With_null_value() {
        String result = Joiner.on("#").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scale"));
    }

    @Test
    public void TestJoinOnJoin_With_skip_null_value() {
        String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scale"));
    }

    @Test
    public void TestJoinOnJoin_With_null_value_but_use_default() {
        String result = Joiner.on("#").useForNull("Default").join(stringListWithNullValue);
        assertThat(result, equalTo("Google#Guava#Java#Scale#Default"));
    }

    @Test
    public void TestJoinOnJoin_append_to_string() {
        StringBuilder resultSb = Joiner.on("#").useForNull("Default").appendTo(new StringBuilder(),stringListWithNullValue);
        assertThat(resultSb.toString(),equalTo("Google#Guava#Java#Scale#Default"));
    }

    @Test
    public void TestJoinOnJoin_append_to_writer() {
        try (FileWriter fileWriter = new FileWriter(new File(TARGET))) {
            Joiner.on("#").useForNull("Default").appendTo(fileWriter, stringListWithNullValue);
            assertThat(Files.isFile().test(new File(TARGET)), equalTo(true));
        }catch (IOException e) {
            fail();
        }
    }

    @Test
    public void TestJoinByStream_skip_null() {
        String result = stringList.stream()
                .filter(iterm -> iterm != null && !iterm.isEmpty())
                .collect(joining("#"));
        assertThat(result,equalTo("Google#Guava#Java#Scale#Kafka"));
    }

    @Test
    public void TestJoinByStream_default_value() {
        String result = stringListWithNullValue.stream()
                .map(iterm -> iterm == null ? "Default" : iterm).collect(joining("#"));
        assertThat(result,equalTo("Google#Guava#Java#Scale#Default"));

    }

    @Test
    public void TestJoinByStream_default_value2() {
        String result = stringListWithNullValue.stream()
                .map(this::defaultValue).collect(joining("#"));
        assertThat(result,equalTo("Google#Guava#Java#Scale#Default"));

    }

    private String defaultValue(String iterm){
        return iterm == null ? "Default" : iterm;
    }

    @Test
    public void TestJoinByMap() {
        assertThat(Joiner.on("#").withKeyValueSeparator("=").join(stringMap),equalTo("Hello=Guava#Java=Scale"));
    }

    public static void main(String[] args) throws IOException {

        Process ping = Runtime.getRuntime().exec("ping");
    }


}
