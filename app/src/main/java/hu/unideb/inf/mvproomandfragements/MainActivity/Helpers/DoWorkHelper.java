package hu.unideb.inf.mvproomandfragements.MainActivity.Helpers;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.core.util.Consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import hu.unideb.inf.mvproomandfragements.Fragments.BaseFragment;

//TypeTemplates
public class DoWorkHelper {
    public static <R> void DoWork(R model, Consumer<R> function1) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        Runnable myRunnable = () -> function1.accept(model);

        FutureTask<String> futureTask = new FutureTask<>(myRunnable, "FutureTask is done!!!");
        executor.submit(futureTask);

        boolean isDone = true;

        while(isDone){
            if(futureTask.isDone()) {
                Log.e("l:", "FutureTask is complete!!!");
                isDone = false;
            }
            else if(futureTask.isCancelled()){
                Log.e("l:", "FutureTask is cancelled!!!");
                isDone = false;
            }
            else
                Log.e("k:", "FutureTask is not complete!!!");
        }
    }

    public static void DoWork(Runnable runnable) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        if(runnable != null){

            FutureTask<String >futureTask2 = new FutureTask<>(runnable, "FutureTask2 is done!!!");
            executor.submit(futureTask2);
            boolean isNotDone = true;

            while(isNotDone){
                if (futureTask2.isDone()) {
                    Log.e("l:", "FutureTask2 is complete!!!");

                    isNotDone = false;
                }
                else if (futureTask2.isCancelled()) {
                    Log.e("l:", "FutureTask2 is cancelled!!!");
                    isNotDone = false;
                }
                else Log.e("k:", "FutureTask2 is not complete!!!");
            }
        }
    }
}
