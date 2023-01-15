package hu.unideb.inf.mvproomandfragements.LoggerElements;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ApplicationLogger {

    private static List<LogObject> myLogObjects = new ArrayList<>();

    public static void addLogToList(LogLevel logLevel, String message){
        MethodCallCounter.add();

        switch (logLevel){
            case DEBUG:;
            case ERROR: myLogObjects.add(LogObject.error(message));
            case FATAL:;
            case TRACE:;
            case WARNING:;
            case INFORMATION: myLogObjects.add(LogObject.info(message));
            default:
        }

        for (int i = 0; i < myLogObjects.size(); i++) {
            Log.d("d", myLogObjects.get(i).toString());
        }
        Log.d("mérete", String.valueOf(myLogObjects.size()));
    }

    public static void addExceptionLogToList(LogLevel logLevel, Exception exception){

    }

}
