import java.math.BigInteger;

/**
 * Created by lijiang on 2016/10/17.
 */
public class Calculate {
    public static String calculateFactorial(int input) {
        BigInteger sum = new BigInteger("1");
        for (int i = input; i > 0; i--) {
            sum = sum.multiply(new BigInteger(String.valueOf(i)));
        }
        return sum.toString();
    }

    public static String cutOutBigInteger(int input, int start, int end) {
        BigInteger sum = new BigInteger("1");
        if (start > end)
            throw new IllegalArgumentException("开始截取数不能大于截止截取数");
        for (int i = input; i > 0; i--) {
            sum = sum.multiply(new BigInteger(String.valueOf(i)));
        }
        return sum.toString().substring(start, end);
    }
}
