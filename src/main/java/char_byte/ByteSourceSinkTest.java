package char_byte;

import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-27 09:28
 */
public class ByteSourceSinkTest {

    private final String path = "/Users/forest/Downloads/IMG_1511.JPG";
    private final String path2 = "/Users/forest/Downloads/IMG_9999.JPG";

    @Test
    public void testAsByteSource() throws IOException {

        File file = new File(path);

        ByteSource byteSource = Files.asByteSource(file);

        byte[] read = byteSource.read();

        assertThat(read, is(Files.toByteArray(file)));
    }

    @Test
    public void testSliceByByteSource() throws IOException {

        ByteSource byteSource = ByteSource.wrap(new byte[]{0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x10});

        ByteSource slice = byteSource.slice(5, 5);

        byte[] read = slice.read();

        for (int i = 0; i < read.length; i++) {

            System.out.println(read[i]);
        }
    }

    @Test
    public void testByteSink() throws IOException {

        File file = new File(path2);

        ByteSink byteSink = Files.asByteSink(file);

        byte[] bytes1 = {0x01, 0x02, 0x03, 0x04};

        byteSink.write(bytes1);

        byte[] bytes = Files.toByteArray(file);

        assertThat(bytes1,is(bytes));

    }
}
