package hu.unideb.inf.mvproomandfragements.LoggerElements;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class LogObject {

    private final LogLevel loggingLevel;
    private final StackTraceElement stackTraceElement;
    private final String zonedDateTime;
    private final String message;

    public LogObject(LogLevel loggingLevel, StackTraceElement stackTraceElement, String zonedDateTime, String message) {
        this.loggingLevel = loggingLevel;
        this.stackTraceElement = stackTraceElement;
        this.zonedDateTime = zonedDateTime;
        this.message = message;
    }

    public LogLevel getLoggingLevel() {
        return loggingLevel;
    }

    public String getZonedDateTime() {
        return zonedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTraceClassName(){
        return this.stackTraceElement.getClassName();
    }

    public String getStackTraceFileName(){
        return this.stackTraceElement.getFileName();
    }

    public int getStackTraceLineNumber(){
        return this.stackTraceElement.getLineNumber();
    }

    public String getStackTraceMethodName(){
        return this.stackTraceElement.getMethodName();
    }

    //----------------------------------------------------------------------------------------------

    public static LogObject info(String errorMeesage){
        MethodCallCounter.add();

        /*
         - az aktuális Thread két függvényhívása miatt
         - a Stack Trace e két fügvényhívás után áll össze, ezért a Stack Trace szintje ezek miatt nő 2-vel
         */
        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];

        // idő lekédezése
        String zonedDateTime = getUTCDateTimeString();

        LogObject logObject = new LogObject(LogLevel.INFORMATION, stackTraceElement, zonedDateTime, errorMeesage);

        Log.i("my", logObject.toString());

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

        //Log.e("ERROR:", logObject.toString());

        Log.e("my", logObject.toString());

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

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        if(this.loggingLevel.equals(LogLevel.ERROR) || this.loggingLevel.equals(LogLevel.FATAL)){
            return String.format("[%s] -> (%s) - Üzenet: %s\n  Hiba helye: %s (%d. sor) | Osztálynév: %s | Függvény: %s",
                    this.getLoggingLevel(),
                    this.getZonedDateTime(),
                    this.getMessage(),
                    this.getStackTraceFileName(),
                    this.getStackTraceLineNumber(),
                    this.getStackTraceClassName(),
                    this.getStackTraceMethodName());
        }

        return String.format("[%s] -> (%s) - Üzenet: %s\n  Keletkezési hely: %s (%d. sor) | Osztálynév: %s | Függvény: %s",
                this.getLoggingLevel(),
                this.getZonedDateTime(),
                this.getMessage(),
                this.getStackTraceFileName(),
                this.getStackTraceLineNumber(),
                this.getStackTraceClassName(),
                this.getStackTraceMethodName());
    }
}
