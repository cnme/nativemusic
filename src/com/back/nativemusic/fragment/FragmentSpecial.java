package com.back.nativemusic.fragment;

import com.back.nativemusic.MyApp;
import com.back.nativemusic.R;
import com.back.nativemusic.adapter.ListViewAdapter;
import com.back.nativemusic.model.MusicHandle;
import com.back.nativemusic.model.MusicInfo;
import com.back.nativemusic.utils.MusicUtils;

import android.R.menu;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentSpecial extends Fragment {
	private View rootview;
	private ListView lv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootview = inflater.inflate(R.layout.fragment_pages, container, false);
		lv = (ListView) rootview.findViewById(R.id.lv);
		Init();
		return rootview;

	}

	/**
	 * 初始化listview
	 */
	private void Init() {
		lv.setAdapter(new ListViewAdapter(getActivity(), MusicHandle.getMusicUtils().getMusicInfos()));
	}
}
