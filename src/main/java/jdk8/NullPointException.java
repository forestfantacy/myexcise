package jdk8;

import jdk8.pojo.Car;
import jdk8.pojo.Insurance;
import jdk8.pojo.Person;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2018-12-06 07:58
 */
public class NullPointException {

    public static void main(String[] args) {

        String insuranceName = getInsuranceName(new Person());

    }

    private static String getInsuranceNameByDeepDoult(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "UNKOWN";
    }

    private static String getInsuranceNameByMultiExit(Person person) {
        final String defaultValue = "UNKOWN";
        if (person == null) {
            return defaultValue;
        }
        if (person.getCar() == null) {
            return defaultValue;
        }
        if (person.getCar().getInsurance() == null) {
            return defaultValue;
        }
        return person.getCar().getInsurance().getName();
    }

    private static String getInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }
}
