package com.back.nativemusic.service;

import com.back.nativemusic.activity.MainActivity;
import com.back.nativemusic.broadcastReceiver.MusicbReceiver;
import com.back.nativemusic.model.MusicHandle;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class PlayService extends Service {
	private MediaPlayer mPlayer;
	private String path;
	private int position;
	public final int play = 0, Pause = 1, Play_Next = 2,pro=3;
	public static int pos=3;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case play:
				if (!mPlayer.isPlaying()) {
					mPlayer.start();
				}
				UpdateSeekBar(1);
				break;
			case Pause:
				Pause();
				break;
			case Play_Next:
				Next();
			case pro:
				UpdateSeekBar(2);
				break;
			}
		};

	};

	@Override
	public void onCreate() {
		mPlayer = new MediaPlayer();
		MusicbReceiver musicbReceiver =new MusicbReceiver();
		IntentFilter intentFilter =new IntentFilter();
		intentFilter.addAction("pro");
		registerReceiver(musicbReceiver, intentFilter);
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		position = intent.getIntExtra("position", 0);
		path = MusicHandle.getMusicUtils().getMusicInfos().get(position)
				.getUrl();
		MusicHandle.setHandler2(handler);
		Play();
	}

	private void Play() {

		try {
			mPlayer.reset();
			mPlayer.setDataSource(path);
			mPlayer.prepare();
			mPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					Next();
				}
			});
			mPlayer.start();
			UpdateSeekBar(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void Pause() {
		try {
			mPlayer.pause();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void Stop() {
		try {
			mPlayer.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void Next() {
		try {
			position = position + 1;
			path = MusicHandle.getMusicUtils().getMusicInfos().get(position)
					.getUrl();
			MainActivity.updateBottomLay(position);
			Play();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void UpdateSeekBar(int index) {
		if (mPlayer != null) {
			if (index==1) {
				Intent intent = new Intent();
				intent.setAction("pos");
				intent.putExtra("pos", mPlayer.getCurrentPosition());
				sendBroadcast(intent);
				handler.sendEmptyMessageDelayed(0, 1000);
			}else {
				mPlayer.seekTo(pos*1000);
			}
			
		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

}
