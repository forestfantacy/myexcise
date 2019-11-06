package basic.classload;

/**
 * @auther wei.wang09
 * @date 2019/10/10
 */
public class TestContextClassLoad01 implements Runnable{

    private Thread thread;

    public TestContextClassLoad01() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        ClassLoader contextClassLoader = this.thread.getContextClassLoader();

        this.thread.setContextClassLoader(contextClassLoader);

        System.out.println("class : " + contextClassLoader.getClass());
        System.out.println("parent : " + contextClassLoader.getParent());
    }

    public static void main(String[] args) {
        new TestContextClassLoad01();
    }
}
