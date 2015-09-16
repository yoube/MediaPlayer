package com.example.videoplayer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class VideoAdapter extends BaseAdapter {

	private List<VideoBean> data = new ArrayList<VideoBean>();
	private LayoutInflater inflater ;
	public VideoAdapter(Context context){
		inflater = LayoutInflater.from(context);
		
	}
	
	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = inflater.inflate(R.layout.view_video_list_item, null);
		view.findViewById(R.id.video_surface);
		
		return view;
	}
	
}
