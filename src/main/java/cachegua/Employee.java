package cachegua;

/**
 * @author forest
 * @email wei.wang09@ucarinc.com
 * @create 2019-03-08 16:22
 */
public class Employee {

    private String name;

    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
