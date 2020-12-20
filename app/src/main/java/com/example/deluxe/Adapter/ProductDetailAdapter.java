package com.example.deluxe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.deluxe.Entity.Attribute;
import com.example.deluxe.R;

import java.util.List;


public class ProductDetailAdapter extends BaseAdapter {
	private Context context;
	private List<Attribute> listInfos;


	public ProductDetailAdapter(Context context, List<Attribute> listInfos) {
		this.context = context;
		this.listInfos = listInfos;
	}

	@Override
	public int getCount() {
		return listInfos.size();
	}

	@Override
	public Object getItem(int position) {
		return listInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.component_product_detail, parent, false);
			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.info_description_title);
			holder.description = (TextView) convertView.findViewById(R.id.info_description);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Attribute productDetailListInfo = this.listInfos.get(position);
		holder.title.setText(productDetailListInfo.getName());
		holder.description.setText(productDetailListInfo.getValue());

		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView description;
	}
}
