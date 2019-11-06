package jdk8;

import jdk8.pojo.Insurance;
import jdk8.pojo.Person;

import java.util.Optional;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-06 08:07
 */
public class OptionalUsage {


    public static void main(String[] args) {
        //createBasic();

        //testfilter();

        //testIfpresent();

        returnTest();

    }

    private static String returnTest() {
        Person person = null;

        Optional.ofNullable(person).ifPresent(pp->{
            System.out.println("处理无返回值的业务逻辑");
        });
        System.out.println("unknown");

        return "unknown";
    }

    private static void testIfpresent() {
        Optional<Insurance> insurance = Optional.of(new Insurance());

        //传入insurance，返回name
        Optional<String> nameOptional = insurance.map(i -> i.getName());

        System.out.println(nameOptional.orElse("empty value"));

        System.out.println(nameOptional.isPresent());

        //consumer 把当前对象当做参数，无返回值，用来定义无返回值的回调逻辑
        nameOptional.ifPresent(System.out::println);
    }

    private static Optional<Insurance> testfilter() {
        Optional<Insurance> insurance = Optional.of(new Insurance());

        //获取到name为空的保险对象
        Insurance insurance1 = insurance.filter(i -> i.getName() == null).get();

        //因为没有name不空的对象，get的时候会抛出异常
        insurance.filter(i -> i.getName() != null).get();

        return insurance;
    }

    private static void createBasic() {
        //Optional<Insurance> empty = Optional.<Insurance>empty();
        //Insurance insurance = empty.get();//
        //
        Optional<Insurance> insurance3 = Optional.of(new Insurance());
        //insurance1.get();

        //可以是nullable
        Optional<Insurance> nullableOpt = Optional.ofNullable(null);
        //Insurance insurance2 = nullableOpt.get();//直接get还是会报错的

        //没有显示用if，还是做了if检查，好处是代码更加流畅
        Insurance insurance = nullableOpt.orElseGet(Insurance::new);

        Insurance insurance1 = nullableOpt.orElse(new Insurance());

        nullableOpt.orElseThrow(() -> new RuntimeException("sth wrong"));
    }

    private static String getName(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("empty value");
    }
}
