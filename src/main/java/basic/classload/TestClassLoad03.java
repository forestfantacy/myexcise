package basic.classload;

/**
 * @auther wei.wang09
 * @date 2019/10/9
 */
public class TestClassLoad03 {


    public static void main(String[] args) {

        /**
         * getSystemClassLoader 和 继承关系
         */
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        while (systemClassLoader != null) {
            systemClassLoader = systemClassLoader.getParent();
            System.out.println(systemClassLoader);
        }

    }
}
