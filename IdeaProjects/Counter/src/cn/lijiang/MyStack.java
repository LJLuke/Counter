package cn.lijiang;

import java.util.Stack;

/**
 * Created by lijiang on 2016/10/31.
 */
public class MyStack {
    Stack<Character> infixStack = new Stack();
    Stack<Integer> postStack = new Stack();
    private int j = 0;
    private int level = -1;

    //比较运算符的优先权
    private int comparePriority(char operator1, char operator2) {
        if (operator1 == '+' || operator1 == '-') {
            if (operator2 == '*' || operator2 == '/') {
                level = -1;
            } else {
                level = 0;
            }
        }

        if (operator1 == '*' || operator1 == '/') {
            level = 0;
        }
        return level;
    }

    //将中缀表达式转换为后缀表达式
    public char[] infixToPostfix(char[] infix) {
        char[] postfix = new char[infix.length];
        for (int i = 0; i < infix.length; i++) {
            if (infix[i] == '(')
                infixStack.push(infix[i]);
            else if (infix[i] == ')') {
                while (infixStack.peek() != '(') {
                    postfix[j++] = infixStack.pop();
                }
                infixStack.pop();
            } else {
                if (infix[i] != '+' && infix[i] != '-' && infix[i] != '*' && infix[i] != '/' && infix[i] != '(' && infix[i] != ')') {
                    postfix[j++] = infix[i];
                } else {
                    while (infixStack.empty() == false && comparePriority(infixStack.peek(), infix[i]) == 0 && infixStack.peek() != '(') {
                        postfix[j++] = infixStack.pop();
                    }
                    infixStack.push(infix[i]);
                }
            }
        }
        while (infixStack.empty() == false) {
            postfix[j++] = infixStack.pop();
        }
        return postfix;
    }

    //计算表达式的值
    public int calculateValue(char[] postfix) {
        int value = 0, value1, value2;
        for (int i = 0; i < postfix.length; i++) {
            if (postfix[i] != '+' && postfix[i] != '-' && postfix[i] != '*' && postfix[i] != '/') {
                postStack.push(postfix[i] - '0');
            } else {
                value1 = postStack.pop();
                value2 = postStack.pop();
                switch (postfix[i]) {
                    case '*':
                        value = value1 * value2;
                        break;
                    case '/':
                        value = value1 / value2;
                        break;
                    case '+':
                        value = value1 + value2;
                        break;
                    case '-':
                        value = value2 - value1;
                }
                postStack.push(value);
            }
        }
        return value;
    }
}
