package basic.annotation.test0;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */

@MyMarkerAnnotation
public class AnnotationUsage {

    @MyAnnotation(value = "hello")
    public void doSth(){
        System.out.println("usage of annotation");
    }

    public static void main(String[] args) {

    }
}
