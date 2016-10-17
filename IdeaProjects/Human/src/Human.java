import java.util.ArrayList;

/**
 * Created by lijiang on 2016/10/16.
 */
public class Human {
    private String name;
    private int age;
    private String sex;

    public String getSex() {
        return sex;
    }

    public Human(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String introduce() {
        return "姓名：" + getName() + " " + "年龄：" + getAge() + " " + "性别：" + getSex();
    }
}
