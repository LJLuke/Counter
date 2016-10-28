package cn.lijiang;

import java.util.Scanner;

/**
 * Created by lijiang on 2016/10/23.
 */
public class ShapeDemo {
    public static void main(String[] args) {
        Shape triangle = new Triangel();
        Shape square = new Square();
        Shape roundness = new Roundness();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入三角形的长：");
        double length = scanner.nextDouble();
        System.out.println("请输入三角形的高：");
        double height = scanner.nextDouble();
        ((Triangel) triangle).setLength(length);
        ((Triangel) triangle).setHeight(height);
        System.out.println("请输入正方形的长：");
        double squareLength = scanner.nextDouble();
        System.out.println("请输入正方形的宽：");
        double width = scanner.nextDouble();
        ((Square) square).setLength(squareLength);
        ((Square) square).setWidth(width);
        System.out.println("请输入圆形的半径：");
        double radius = scanner.nextDouble();
        ((Roundness) roundness).setRadius(radius);
        System.out.println("三角形的面积是：" + triangle.calculateArea());
        System.out.println("正方形的面积是：" + square.calculateArea());
        System.out.println("圆形的面积是：" + roundness.calculateArea());
    }
}
