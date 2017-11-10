package com.example.selectmenu2;

import java.util.List;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<MyFriendsSDetails>{
	
	Context context;
	LayoutInflater inflater;
	List<MyFriendsSDetails> list;
	private SparseBooleanArray mSelectedItemsIds;
	
	public MyListAdapter(Context context, List<MyFriendsSDetails> list) {
		super(context, 0, list);
		mSelectedItemsIds = new SparseBooleanArray();
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			holder.name = (TextView) convertView.findViewById(R.id.title);
			holder.nickname = (TextView) convertView.findViewById(R.id.subtitle);
			holder.photo = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(list.get(position).getMyfriendname());
		holder.nickname.setText(list.get(position).getMyfriendnickname());
		holder.photo.setImageResource(list.get(position).getPhoto());
		return convertView;
	}
	
	public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));
	}

	public void selectView(int position, boolean value) {
		if (value)
			mSelectedItemsIds.put(position, value);
		else
			mSelectedItemsIds.delete(position);
		notifyDataSetChanged();
	}
	
	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}
	
	public SparseBooleanArray getSelectedIds() {
		return mSelectedItemsIds;
	}
	private class ViewHolder {
		TextView name;
		TextView nickname;
		ImageView photo;
	}
}
