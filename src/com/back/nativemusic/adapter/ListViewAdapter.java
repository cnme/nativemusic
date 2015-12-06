package com.back.nativemusic.adapter;

import java.util.List;

import com.back.nativemusic.MyApp;
import com.back.nativemusic.R;
import com.back.nativemusic.model.MusicInfo;
import com.back.nativemusic.utils.MusicAlbum;
import com.back.nativemusic.utils.MusicUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private List<MusicInfo> list;
	private ViewHold viewHold;
	private LayoutInflater inflater;
	private MusicInfo musicInfo;
	private Context context;
	private List<Bitmap> bList;
	private Bitmap bitmap;

	public ListViewAdapter(Context context, List<MusicInfo> list) {
		this.list = list;
		this.context = context;
		inflater = LayoutInflater.from(context);
		bList = MusicUtils.getBitmaps();
		Log.i("bitmaps", bList.size()+"");

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			viewHold = new ViewHold();
			convertView = inflater.inflate(R.layout.listview_item, null);
			viewHold.iv_avater = (ImageView) convertView
					.findViewById(R.id.iv_avater);
			viewHold.tv_artist = (TextView) convertView
					.findViewById(R.id.tv_artist);
			viewHold.tv_musicname = (TextView) convertView
					.findViewById(R.id.tv_musicname);
			viewHold.tv_duration = (TextView) convertView
					.findViewById(R.id.tv_duration);
			convertView.setTag(viewHold);
		} else {
			viewHold = (ViewHold) convertView.getTag();
		}
		musicInfo = (MusicInfo) getItem(position);
		viewHold.tv_artist.setText(musicInfo.getAritst());
		viewHold.tv_musicname.setText(musicInfo.getMusicName());
		viewHold.tv_duration.setText(musicInfo.getDuration() + "");
		if (!list.isEmpty()) {
			bitmap = bList.get(position);
		}
		if (bitmap != null) {
			viewHold.iv_avater.setImageBitmap(bitmap);
		}
		
		return convertView;
	}

	class ViewHold {
		public TextView tv_musicname, tv_artist, tv_duration;
		public ImageView iv_avater;
	}

}
