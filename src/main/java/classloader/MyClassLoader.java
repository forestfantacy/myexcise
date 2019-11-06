package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-28 09:49
 */
public class MyClassLoader extends ClassLoader{

    private static String DEFAULT_DIR = "/Users/forest/logs/0128/";

    private String dir = DEFAULT_DIR;

    private String classloadName;

    public MyClassLoader() {
        super();
    }

    public MyClassLoader(String classloaderName) {
        super();
        this.classloadName = classloaderName;
    }

    public MyClassLoader(String classloaderName, ClassLoader parent) {
        super(parent);
        this.classloadName = classloaderName;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassloadName() {
        return classloadName;
    }

    public void setClassloadName(String classloadName) {
        this.classloadName = classloadName;
    }

    /**
     * xxx.xxx.xxx.AAA
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String classpath = name.replace(".", "/");
        File classFile = new File(dir, classpath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("the class " + name + " not found under " + classpath);
        }

        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0) {
            throw new ClassNotFoundException("fail to load class " + classFile);
        }

        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] loadClassBytes(File classFile) {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            FileInputStream fis = new FileInputStream(classFile);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myFirstClassLoader = new MyClassLoader("My first ClassLoader");

        //加载这个类的时候，注意不要存在
        Class<?> aClass = myFirstClassLoader.loadClass("classloader.pojo.MyTestObj");

        //loadClass 并不会初始化类，没有打印static部分
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

        //初始化实例
        Object obj = aClass.newInstance();
        Method hello = aClass.getMethod("hello", new Class[]{});

        //调用方法
        Object invoke = hello.invoke(obj, new Object[]{});
        System.out.println(invoke);
    }
}
