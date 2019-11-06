package classloader.v2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-01-28 09:49
 */
public class MyTomatClassLoader extends ClassLoader{

    private static String DEFAULT_DIR = "/Users/forest/logs/0130/";

    private String dir = DEFAULT_DIR;

    private String classloadName;

    public MyTomatClassLoader() {
        super();
    }

    public MyTomatClassLoader(String classloaderName) {
        super();
        this.classloadName = classloaderName;
    }

    public MyTomatClassLoader(String classloaderName, ClassLoader parent) {
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

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException{

        Class<?> clazz = null;

        if (name.startsWith("java.")) {
            try {
                ClassLoader system = ClassLoader.getSystemClassLoader();
                clazz = system.loadClass(name);
                if (clazz != null) {
                    if (resolve) {
                        resolveClass(clazz);
                    }
                    return clazz;
                }
            } catch (Exception e) {
                //Ignore
            }
        }

        try {//自己找
            clazz = this.findClass(name);
        } catch (Exception e) {

        }

        //自己没找到，就交给父亲找
        if (clazz == null && getParent() != null) {
            getParent().loadClass(name);
        }
        return clazz;
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

    }
}
