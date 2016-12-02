package cn.lijiang;


/**
 * Created by lijiang on 2016/11/15.
 */
public class TextSearchDome implements CallBack{
    public static void main(String[] args){
        TextSearchDome textSearchDome = new TextSearchDome();
        TextSearch textSearch = new TextSearch(textSearchDome);
        textSearch.setKeyWords("线程");
        textSearch.start();
}
    @Override
    public void outPut(int line) {
        System.out.println(line);
    }
}
