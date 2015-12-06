package com.back.nativemusic.utils;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;

import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.back.nativemusic.MyApp;
import com.back.nativemusic.model.MusicInfo;

public class MusicUtils {
	private List<MusicInfo> list;
	private MusicInfo musicInfo;
	private Cursor cursor;
	private long id;
	private String MusicName;
	private String Aritst;
	private String album;
	private long albumId;
	private long Duration;
	private String Url;
	private int isMusic;
	private ContentResolver contentResolver;
	private static List<Bitmap> bList;
	private Bitmap bitmap;
	private Context context;

	public MusicUtils(ContentResolver contentResolver, Context context) {
		this.contentResolver = contentResolver;
		this.context = context;
	}

	/**
	 * 
	 * @return 音乐信息集合
	 */
	public List<MusicInfo> getMusicInfos() {
		if (list == null) {
			list = new ArrayList<MusicInfo>();
			bList = new ArrayList<Bitmap>();
			cursor = contentResolver.query(
					MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
					null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
			Log.i("counts", cursor.getCount() + "");
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				musicInfo = new MusicInfo();
				album = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.ALBUM));
				albumId = cursor.getInt(cursor
						.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
				id = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media._ID));
				Aritst = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.ARTIST));
				MusicName = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE));
				Duration = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media.DURATION));
				Url = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.DATA));
				isMusic = cursor.getInt(cursor
						.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
				if (isMusic != 0) {
					musicInfo.setId(id);
					musicInfo.setAritst(Aritst);
					musicInfo.setMusicName(MusicName);
					musicInfo.setDuration(formatTime(Duration));
					musicInfo.setUrl(Url);
					musicInfo.setAlbum(album);
					musicInfo.setAlbumid(albumId);
					if (Duration > 60000) {
						list.add(musicInfo);
						getbitmap(context, id, albumId, true, false);
					}

				}
			}
		}

		return list;

	}

	public void getbitmap(Context context, long song_id, long album_id,
			boolean allowdefalut, boolean small) {
		bitmap = MusicAlbum.getArtwork(context, song_id, album_id,
				allowdefalut, small);
		bList.add(bitmap);
	}

	public static List<Bitmap> getBitmaps() {
		Log.i("blist", bList.size()+"");
		return bList;
	}

	/**
	 * 格式化时间，将毫秒转换为分:秒格式
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime(long time) {
		String min = time / (1000 * 60) + "";
		String sec = time % (1000 * 60) + "";
		if (min.length() < 2) {
			min = "0" + time / (1000 * 60) + "";
		} else {
			min = time / (1000 * 60) + "";
		}
		if (sec.length() == 4) {
			sec = "0" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 3) {
			sec = "00" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 2) {
			sec = "000" + (time % (1000 * 60)) + "";
		} else if (sec.length() == 1) {
			sec = "0000" + (time % (1000 * 60)) + "";
		}
		return min + ":" + sec.trim().substring(0, 2);
	}
}
