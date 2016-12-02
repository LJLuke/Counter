package cn.lijiang;

import java.io.*;

/**
 * Created by lijiang on 2016/11/15.
 */
public class TextSearch extends Thread {
    private String keyWords = "多";
    private String readResource = "test1";
    private BufferedReader bufferedReader;
    private CallBack callBack;

    int line = 0;
    String haveWords = null;

    public TextSearch(CallBack mCallBack) {
        callBack = mCallBack;
    }

    @Override
    public void run() {
        try {
            bufferedReader = new BufferedReader(new FileReader(readResource));
            while ((haveWords = bufferedReader.readLine()) != null) {
                line++;
                if (haveWords.contains(keyWords)) {
                    callBack.outPut(line);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

}
