package cn.lijiang;

/**
 * Created by lijiang on 2016/10/23.
 */
public class Roundness extends Shape{
    private double radius;

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double calculateArea(){
        return radius*radius* Math.PI;
    }
}
