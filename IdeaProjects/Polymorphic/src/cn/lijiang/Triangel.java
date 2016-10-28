package cn.lijiang;

/**
 * Created by lijiang on 2016/10/23.
 */
public class Triangel extends Shape{
    private double length;
    private double height;


    public void setLength(double length) {
        this.length = length;
    }


    public void setHeight(double height) {
        this.height = height;
    }

    public double calculateArea(){
        return height*length/2;
    }
}
