import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * Created by lijiang on 2016/10/16.
 */
public class Test {
    public static void main(String[] args){
        ArrayList<Human> arrayList = new ArrayList();
        String[][] nameAndSex = {{"一","男"},{"二","男"},{"三","男"},{"四","女"},{"五","女"},{"六","女"}};
        final int age = 18;
        for (int i =0;i< 6;i++){
            Human person = new Human(nameAndSex[i][1]);
            person.setName(nameAndSex[i][0]);
            person.setAge(age);
            arrayList.add(person);
        }
        for (Human human : arrayList){
            System.out.println(human.introduce());
        }
    }
}
