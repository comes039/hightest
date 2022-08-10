package com.example.highcharttest.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.highcharttest.R;
import com.example.highcharttest.chart.data.SampleData;

import java.util.ArrayList;
import java.util.List;

public class PieAllListAdapter extends BaseAdapter {

	private List<SampleData> sampleDataList;
	public Context context;

	public PieAllListAdapter(@NonNull List<SampleData> sampleDataList, Context context) {
		this.sampleDataList = new ArrayList<>(sampleDataList);
		this.context = context;
	}

	static class ViewHolder {
		TextView name;
		TextView percent;
		View color;
	}

	@Override
	public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
		SampleData sampleData = (SampleData) getItem(position);
		ViewHolder vh;

		vh = new ViewHolder();
		convertView = LayoutInflater.from(context).inflate(R.layout.aura_item, parent, false);
		vh.name = (TextView) convertView.findViewById(R.id.pie_name);
		vh.percent = (TextView) convertView.findViewById(R.id.pie_percent);
		vh.color = (View) convertView.findViewById(R.id.pie_box);
		vh.name.setText(sampleData.getName());
		vh.percent.setText(sampleData.getPercent());
		Number color = sampleData.getColor();
		switch (color.intValue()) {
			case 1:
				vh.color.setBackgroundResource(R.drawable.box_aura);
				break;
			case 2:
				vh.color.setBackgroundResource(R.drawable.box_aura_unknown);
				break;
			case 3:
				vh.color.setBackgroundResource(R.drawable.box_no_aura);
				break;
			case 4:
				vh.color.setBackgroundResource(R.drawable.box_no_record);
				break;
			default:
				vh.color.setBackgroundResource(R.drawable.box_no_record);
		}


		return convertView;
	}

	@Override
	public int getCount() {
		return sampleDataList.size();
	}

	@Override
	public Object getItem(int position) {
		return sampleDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


}
