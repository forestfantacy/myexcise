package char_byte;

import com.google.common.collect.ImmutableList;
import com.google.common.io.ByteSink;
import com.google.common.io.CharSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-27 09:04
 *
 * 相当于 reader
 */
public class CharsourceTest {

    @Test
    public void testCharSource() throws IOException {

        CharSource charSource = CharSource.wrap("i am wangwei26");

        String read = charSource.read();

        assertThat(read, equalTo("i am wangwei26"));

        ImmutableList<String> strings = charSource.readLines();

        long length = charSource.length();

        assertThat(length, equalTo(14L));

    }

    public void testCharSink() throws IOException {

        ByteSink byteSink = Files.asByteSink(new File(""), FileWriteMode.APPEND);
        byteSink.write(new byte[]{'a', 'b', 'c'});

    }
}
