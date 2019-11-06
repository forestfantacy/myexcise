package exception;

import com.google.common.io.ByteSource;
import com.google.common.io.Closer;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-28 08:48
 */
public class CloserTest {

    @Test
    public void testCloser() throws IOException {

        ByteSource byteSource = Files.asByteSource(new File(""));
        Closer closer = Closer.create();

        try {
            InputStream register = closer.register(byteSource.openStream());
        } catch (IOException e) {
            throw closer.rethrow(e);
        }finally {
            closer.close();
        }
    }

    @Test
    public void testFinallyException() {
        try {
            System.out.println("work area≈");
            throw new IllegalArgumentException();
        } catch (Exception e) {
            System.out.println("exception area");
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally area");
            NullPointerException nullPointerException = new NullPointerException("");
            //nullPointerException.addSuppressed(t);
            throw nullPointerException;
        }
    }
}
