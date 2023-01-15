package hu.unideb.inf.mvproomandfragements.LoggerElements;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class ApplicationLogger {

    private static List<LogObject> myLogObjects = new ArrayList<>();

    public static void addLogToList(LogLevel logLevel, String message){
        MethodCallCounter.add();

        switch (logLevel){
            case DEBUG:;
            case ERROR: myLogObjects.add(ApplicationLogger.error(message));
            case FATAL:;
            case TRACE:;
            case WARNING:;
            case INFORMATION: myLogObjects.add(ApplicationLogger.info(message));
            default:
        }

        for (int i = 0; i < myLogObjects.size(); i++) {
            Log.d("d", myLogObjects.get(i).toString());
        }
        Log.d("mérete", String.valueOf(myLogObjects.size()));
    }

    public static LogObject info(String errorMeesage){
        MethodCallCounter.add();

        /*
         - az aktuális Thread két függvényhívása miatt
         - a Stack Trace e két fügvényhívás után áll össze, ezért a Stack Trace szintje ezek miatt nő 2-vel
         */
        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];
        String zonedDateTime = getUTCDateTimeString();
        LogObject logObject = new LogObject(LogLevel.INFORMATION, stackTraceElement, zonedDateTime, errorMeesage);

        Log.i(null, logObject.toString());

        MethodCallCounter.clear();

        return logObject;
    }

    public static LogObject error(String errorMeesage){
        MethodCallCounter.add();

        /*
         - az aktuális Thread két függvényhívása miatt
         - a Stack Trace e két fügvényhívás után áll össze, ezért a Stack Trace szintje ezek miatt nő 2-vel
         */
        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];

        // idő lekédezése
        String zonedDateTime = getUTCDateTimeString();

        LogObject logObject = new LogObject(LogLevel.ERROR, stackTraceElement, zonedDateTime, errorMeesage);

        Log.e(null, logObject.toString());

        MethodCallCounter.clear();

        return logObject;
    }

    public static LogObject error(Exception exceptionObject){

        StackTraceElement stackTraceElement = exceptionObject.getStackTrace()[0];

        // idő lekédezése
        String zonedDateTime = getUTCDateTimeString();

        /*LogObject logObject = new LogObject(LogLevel.ERROR, stackTraceElement, zonedDateTime, errorMeesage);*/

        //Log.e("k:", log);

        StackTraceElement[] s = exceptionObject.getStackTrace();
        for (int i = 0; i < s.length; i++) {
            Log.i("j", s[i].toString());
        }

        return null;
    }

    public static String getUTCDateTimeString(){
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        return df.format(date.getTimeInMillis()) + " " + date.getTimeZone().getID();
    }

    public static void addExceptionLogToList(LogLevel logLevel, Exception exception){

    }

}
