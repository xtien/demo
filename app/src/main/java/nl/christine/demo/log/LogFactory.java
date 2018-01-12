package nl.christine.demo.log;

/**
 * Created by christine on 11-1-18.
 */

public class LogFactory {

    private static MyLog log;

    public static MyLog get(){
        if(log == null){
            log = new MyLog();
        }
        return log;
    }
}
