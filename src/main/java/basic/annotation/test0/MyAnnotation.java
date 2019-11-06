package basic.annotation.test0;


/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
public @interface MyAnnotation {

    String value();//value 是默认名称，使用的时候value = “hello” 可以写成 “hello”
}
