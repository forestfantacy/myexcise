package basic.enumExample;

/**
 * @auther wei.wang09
 * @date 2019/10/5
 */
public class EnumSwitch {


    public static void main(String[] args) {
        doOp(OpConstant.SHOOT);

        for (OpConstant opConstant : OpConstant.values()) {
            System.out.println(opConstant);
        }

        OpConstant turn_right = OpConstant.valueOf("TURN_RIGHT");
        System.out.println("TURN_RIGHT===>"+turn_right);
    }

    public static void doOp(OpConstant opConstant) {

        switch (opConstant) {//switch 后面可以接 byte short int enum 4种类型

            case TURN_LEFT:
                System.out.println("向左");
                break;
            case TURN_RIGHT:
                System.out.println("向右");
                break;
            case SHOOT:
                System.out.println("开枪");
                break;
        }
    }


    enum OpConstant{
        TURN_RIGHT,TURN_LEFT,SHOOT
    }
}
