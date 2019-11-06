package basic.annotation.test1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @auther wei.wang09
 * @date 2019/10/6
 */
@Retention(value = RetentionPolicy.RUNTIME)//运行时 还有source comply
//@Target(ElementType.METHOD) //方法 类型（类、接口） 字段等
public @interface MyAnnotation1 {

    String hello() default "wangwei";

    String world();
}
