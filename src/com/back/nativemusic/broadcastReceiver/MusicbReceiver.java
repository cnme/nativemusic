package com.back.nativemusic.broadcastReceiver;

import com.back.nativemusic.activity.MainActivity;
import com.back.nativemusic.model.MusicHandle;
import com.back.nativemusic.service.PlayService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MusicbReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		switch (action) {
		case "pos":

			MainActivity.seebarpos	=intent.getIntExtra("pos", 0);
			MusicHandle.getHandle().sendEmptyMessageDelayed(3, 1000);
			break;

		case "pro":
			PlayService.pos=intent.getIntExtra("pro", 0);
			MusicHandle.getHandler2().sendEmptyMessage(3);
			break;
		}
		
		
		

	}

}
