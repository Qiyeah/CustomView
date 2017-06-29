package com.person.sl;

import android.app.Application;

/**
 * Created by sl on 2017/6/28.
 */

public class App extends Application {
    private static App instance = null;
    public synchronized static App getInstance(){
        if (null == instance){
            synchronized (App.class){
                if (null == instance){
                    instance = new App();
                }
            }
        }
        return instance;
    }
    private App(){}
}
