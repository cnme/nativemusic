package com.back.nativemusic.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.back.nativemusic.MyApp;
import com.back.nativemusic.R;
import com.back.nativemusic.adapter.MyFragmentStatePagerAdapter;
import com.back.nativemusic.broadcastReceiver.MusicbReceiver;
import com.back.nativemusic.fragment.FragmentArtist;
import com.back.nativemusic.fragment.FragmentNative;
import com.back.nativemusic.fragment.FragmentSpecial;
import com.back.nativemusic.model.MusicHandle;
import com.back.nativemusic.utils.MusicUtils;

import android.support.v4.app.Fragment;
import android.R.integer;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * @Back
 * @主类，管理ViewPage页面
 * @2015/11
 */
public class MainActivity extends FragmentActivity implements OnClickListener {
	private List<Fragment> views;// ViewPager item 集合
	private ViewPager vp;
	private TextView tv_title;
	private FragmentArtist fragmentArtist;
	private FragmentNative fragmentNative;
	private FragmentSpecial fragmentSpecial;
	private Handler handler;
	private static ImageView imageView;
	private static Bitmap bitmap;
	private SeekBar seekBar;
	private static ImageButton btn_playNext2, btn_pause2, btn_play2;
	public final static int Single = 0, Artist = 1, Special = 2, seebar = 3;
	public static int seebarpos = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MusicHandle.setMusicUtils(new MusicUtils(getContentResolver(),
				MainActivity.this));
		InitView();
		InitDate();
	}

	private void InitDate() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("pos");
		MusicbReceiver musicbReceiver = new MusicbReceiver();
		registerReceiver(musicbReceiver, intentFilter);

	}

	/**
	 * 初始化视图控件
	 */
	private void InitView() {
		vp = (ViewPager) findViewById(R.id.vp);
		tv_title = (TextView) findViewById(R.id.tv_title);
		imageView = (ImageView) findViewById(R.id.headicon_iv);
		btn_pause2 = (ImageButton) findViewById(R.id.btn_pause2);
		btn_play2 = (ImageButton) findViewById(R.id.btn_play2);
		btn_playNext2 = (ImageButton) findViewById(R.id.btn_playNext2);
		seekBar = (SeekBar) findViewById(R.id.pbDuration);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					Intent intent = new Intent();
					intent.setAction("pro");
					intent.putExtra("pro", progress);
					sendBroadcast(intent);
				}
				

			}
		});

		btn_pause2.setOnClickListener(this);
		btn_play2.setOnClickListener(this);
		btn_playNext2.setOnClickListener(this);
		fragmentArtist = new FragmentArtist();
		fragmentNative = new FragmentNative();
		fragmentSpecial = new FragmentSpecial();
		handler = new Handler() {
			public void handleMessage(Message msg) {

				switch (msg.what) {

				case Single:
					tv_title.setText("单  曲");
					break;
				case Artist:
					tv_title.setText("歌  手");
					break;
				case Special:
					tv_title.setText("专  辑");
					break;
				case seebar:
					seekBar.setProgress(seebarpos / 1000);
					break;
				}
			};
		};
		MusicHandle.SetHandle(handler);
		InitViewPage();
	}

	/**
	 * 初始化ViewPage
	 */
	private void InitViewPage() {
		views = new ArrayList<Fragment>();
		views.add(fragmentArtist);
		views.add(fragmentNative);
		views.add(fragmentSpecial);
		vp.setAdapter(new MyFragmentStatePagerAdapter(
				getSupportFragmentManager(), views, handler, vp));
		vp.setCurrentItem(0);

	}

	@SuppressWarnings("static-access")
	public static void updateBottomLay(int poistion) {
		bitmap = MusicHandle.getMusicUtils().getBitmaps().get(poistion);
		imageView.setImageBitmap(bitmap);
		btn_pause2.setVisibility(View.VISIBLE);
		btn_play2.setVisibility(View.GONE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_play2:
			btn_pause2.setVisibility(View.VISIBLE);
			btn_play2.setVisibility(View.GONE);
			new Thread(new Runnable() {

				@Override
				public void run() {
					Message message = new Message();
					message.what = 0;
					MusicHandle.getHandler2().sendMessage(message);

				}
			}).start();
			break;
		case R.id.btn_pause2:
			btn_pause2.setVisibility(View.GONE);
			btn_play2.setVisibility(View.VISIBLE);
			new Thread(new Runnable() {

				@Override
				public void run() {
					Message message = new Message();
					message.what = 1;
					MusicHandle.getHandler2().sendMessage(message);

				}
			}).start();

			break;

		case R.id.btn_playNext2:
			btn_pause2.setVisibility(View.GONE);
			btn_play2.setVisibility(View.VISIBLE);
			new Thread(new Runnable() {

				@Override
				public void run() {
					Message message = new Message();
					message.what = 2;
					MusicHandle.getHandler2().sendMessage(message);

				}
			}).start();
			break;

		}

	}

}
