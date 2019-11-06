package basic.annotation.test1;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
@MyAnnotation1(hello = "beijing",world = "tianjing")
public class MyService {

    @MyAnnotation1(hello = "tianjing",world = "shangdi")
    public void doo(){
        System.out.println("usage of annotation");
    }
}
