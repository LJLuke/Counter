package cn.lijiang;

/**
 * Created by lijiang on 2016/10/23.
 */
public class Square extends Shape{
    private double length;
    private double width;


    public void setLength(double length) {
        this.length = length;
    }


    public void setWidth(double width) {
        this.width = width;
    }

    public double calculateArea(){
        return length*width;
    }
}
