
import java.util.Scanner;

/**
 * Created by lijiang on 2016/10/17.
 */
public class Factorial {
    public static void main(String[] args) {
        System.out.println("请输入你需要求阶乘的数：");
        Scanner scanner = new Scanner(System.in);
        int factorialNumber1 = scanner.nextInt();
        String result = Calculate.calculateFactorial(factorialNumber1);
        System.out.println(factorialNumber1 + "的阶乘是：" + result);
        System.out.println();
        System.out.println("请输入你需要求的阶乘数，截取位的开始和截止数：");
        int factorialNumber2 = scanner.nextInt();
        int startNumber = scanner.nextInt();
        int endNumber = scanner.nextInt();
        String subResult = Calculate.cutOutBigInteger(factorialNumber2, startNumber, endNumber);
        System.out.println("你所截取的数字是：" + subResult);
    }
}
