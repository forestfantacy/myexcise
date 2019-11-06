package extended;

/**
 * @auther wei.wang09
 * @date 2019/6/27
 */
public class Main {


    public static void main(String[] args) {

        AbstractFather father = new Son();
        Son son = new Son();

        //father.doSth(); //son dosth
        son.doSth();  //son dosth
    }


}


