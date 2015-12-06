package com.back.nativemusic.model;

import android.os.Handler;

import com.back.nativemusic.MyApp;
import com.back.nativemusic.utils.MusicUtils;

public class MusicHandle {
	public static Handler handler, handler2;
	public static MusicUtils musicUtils;
	public static void SetHandle(Handler hand) {
		handler = hand;
	}

	public static Handler getHandle() {
		return handler;
	}
	
	public static Handler getHandler2() {
		return handler2;
	}

	public static void setHandler2(Handler handl2) {
		handler2 = handl2;
	}

	public static void setMusicUtils(MusicUtils musicUtil) {
		musicUtils = musicUtil;
	}

	public static MusicUtils getMusicUtils() {
		return musicUtils;
	}

}
