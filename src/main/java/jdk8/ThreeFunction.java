package jdk8;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-01 18:11
 */
@FunctionalInterface
public interface ThreeFunction<T,U,K, R> {

    R apply(T t, U u, K k);
}
