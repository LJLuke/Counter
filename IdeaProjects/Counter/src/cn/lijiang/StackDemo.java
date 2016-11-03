package cn.lijiang;

import java.util.Scanner;

/**
 * Created by lijiang on 2016/10/31.
 */
//只能计算个位数的运算

public class StackDemo {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入正确的表达式");
        String expression = scanner.next();
        char[] strings = expression.toCharArray();
        char[] postfix = myStack.infixToPostfix(strings);
        int value = myStack.calculateValue(postfix);
        System.out.println("最终的值是" + value);
    }
}

