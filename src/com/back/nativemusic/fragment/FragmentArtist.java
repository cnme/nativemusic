package com.back.nativemusic.fragment;

import com.back.nativemusic.MyApp;
import com.back.nativemusic.R;
import com.back.nativemusic.activity.MainActivity;
import com.back.nativemusic.adapter.ListViewAdapter;
import com.back.nativemusic.model.MusicHandle;
import com.back.nativemusic.model.MusicInfo;
import com.back.nativemusic.service.PlayService;
import com.back.nativemusic.utils.MusicUtils;

import android.R.menu;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FragmentArtist extends Fragment {
	private View rootview;
	private ListView lv;
	private MusicUtils musicUtils;
	public final static int isScroll = 3;

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

		lv.setAdapter(new ListViewAdapter(getActivity(), MusicHandle.getMusicUtils()
				.getMusicInfos()));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), PlayService.class);
				intent.putExtra("position", position);
				getActivity().startService(intent);
				MainActivity.updateBottomLay(position);

			}
		});
	}
}
