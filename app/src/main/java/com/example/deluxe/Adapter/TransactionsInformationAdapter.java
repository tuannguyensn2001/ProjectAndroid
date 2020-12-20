package com.example.deluxe.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Entity.ListViewInfo;
import com.example.deluxe.R;

import java.util.List;

public class TransactionsInformationAdapter extends BaseAdapter {
	private List<ListViewInfo> listViewInfos;
	private LayoutInflater layoutInflater;
	private AppCompatActivity context;


	public TransactionsInformationAdapter(AppCompatActivity context, List<ListViewInfo> listViewInfos) {
		this.context = context;
		this.listViewInfos = listViewInfos;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listViewInfos.size();
	}

	@Override
	public Object getItem(int position) {
		return listViewInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.layout_transaction_information, parent);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.info_title);
			holder.subtitle = (TextView) convertView.findViewById(R.id.info_subtitle);
			holder.frameLayout = (FrameLayout) convertView.findViewById(R.id.info_frame);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ListViewInfo listviewInfo = this.listViewInfos.get(position);
		holder.title.setText(listviewInfo.getTitle());
		holder.subtitle.setText(listviewInfo.getSubtitle());
		holder.frameLayout.setForeground(null);

		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView subtitle;
		FrameLayout frameLayout;
	}
}
