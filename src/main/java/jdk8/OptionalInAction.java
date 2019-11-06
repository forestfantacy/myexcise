package jdk8;

import jdk8.pojo.Car2;
import jdk8.pojo.Insurance;
import jdk8.pojo.Person2;

import java.util.Optional;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-06 12:48
 */
public class OptionalInAction {

    public static void main(String[] args) {
        getNameByOptional(new Person2());
    }

    private static String getNameByOptional(Person2 person) {
        Optional<Car2> car = Optional.ofNullable(person).flatMap(Person2::getCar);

        Optional<Insurance> insurance = car.flatMap(Car2::getInsurance);

        Optional<String> nameOptional = insurance.map(Insurance::getName);

        return nameOptional.orElse("empty name");
    }
}
