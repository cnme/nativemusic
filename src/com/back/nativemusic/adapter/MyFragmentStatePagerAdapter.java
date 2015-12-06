package com.back.nativemusic.adapter;

import java.util.List;

import android.content.IntentSender.OnFinished;
import android.net.VpnService;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

public class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> list;
	public final static int Single = 0, Artist = 1, Special = 2;
	private Handler handler;
	private ViewPager vp;

	public MyFragmentStatePagerAdapter(FragmentManager fm, List<Fragment> list,
			Handler handler, ViewPager vp) {
		super(fm);
		this.list = list;
		this.handler = handler;
		this.vp = vp;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.finishUpdate(container);
		Message message = new Message();
		switch (vp.getCurrentItem()) {
		case Single:
			message.what = Single;
			break;
		case Artist:
			message.what = Artist;
			break;
		case Special:
			message.what = Special;
			break;
		}

		handler.sendMessage(message);

	}
}
