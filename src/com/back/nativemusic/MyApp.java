package com.back.nativemusic;
import android.app.Application;
import android.content.Context;

public class MyApp extends Application {

	public static Context context;
	

	@Override
	public void onCreate() {
		context = getApplicationContext();
		super.onCreate();
	}

	public static Context getContext() {

		return context;

	}

	
}
