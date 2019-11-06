package basic.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestClassLoad06 extends ClassLoader {

    private String classloadName;
    private String path;

    private final static String fileextend = ".class";

    public TestClassLoad06(String classloadName){
        super();
        this.classloadName = classloadName;
    }

    public TestClassLoad06(ClassLoader parent, String classloadName) {
        super(parent);
        this.classloadName = classloadName;
    }

    @Override
    protected Class<?> findClass(String className){

        System.out.println("findClass " + className);
        //获取class的byte数据
        byte[] classData = readClass(className);
        //
        return super.defineClass(className, classData, 0, classData.length);
    }

    private byte[] readClass(String className)  {

        InputStream inputStream = null;
        byte[] buffer = null;
        ByteArrayOutputStream bis = null;

        try {
            className = className.replace(".", "/");


            //byte[] allBytes = Files.readAllBytes(new File(path + className + fileextend).toPath());
            //return allBytes;
            inputStream = new FileInputStream(new File(path + className + fileextend));
            bis = new ByteArrayOutputStream();
            buffer = new byte[200];
            int ch = -1;
            while ((ch = inputStream.read(buffer, 0, buffer.length)) != -1 ) {
                bis.write(buffer,0,ch);
            }
            return bis.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        TestClassLoad06 ccc = new TestClassLoad06("CCC");
        ccc.setPath("/Users/forest/Desktop/");
        Class<?> aClass0 = ccc.loadClass("atomic.AtomicTest");
        System.out.println("class:" + aClass0.hashCode());
        Object o0 = aClass0.newInstance();
        System.out.println(o0);

        System.out.println("----------------------------");

        TestClassLoad06 ddd = new TestClassLoad06("CCC");
        ddd.setPath("/Users/forest/Desktop/");
        Class<?> aClass1 = ddd.loadClass("atomic.AtomicTest");//aClass0 == aClass1 false
        System.out.println("class:" + aClass1.hashCode());
        Object o1 = aClass1.newInstance();
        System.out.println(o1);


    }
}
