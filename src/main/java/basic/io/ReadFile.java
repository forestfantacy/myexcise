package basic.io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public class ReadFile {

    public static void main(String[] args) throws IOException {

        //从文件流到程序  5
        FileInputStream fileInputStream = new FileInputStream("/Users/forest/ping.sh");

        byte[] buffer = new byte[200];

        int readLength;
        while ((readLength = fileInputStream.read(buffer, 0, buffer.length)) != -1) {
            System.out.println(new String(buffer,0,buffer.length));
        }

    }

}
