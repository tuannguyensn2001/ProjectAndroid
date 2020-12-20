package com.example.deluxe.View.History;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.deluxe.Interface.Model.StatisticInterface;
import com.example.deluxe.Interface.PresenterView.History.HistoryInterface;
import com.example.deluxe.Presenter.History.HistoryPresenter;
import com.example.deluxe.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class HistoryFragment extends Fragment implements HistoryInterface.HistoryView {

	float barWidth = 0.3f;
	float barSpace = 0f;
	float groupSpace = 0.4f;
	private HistoryInterface.HistoryPresenter historyPresenter;
	private TextView seeMoreButton1, seeMoreButton2;
	private PieChart chart;
	private BarChart barChart;
	private StatisticInterface statisticInterface;

	public static Fragment newInstance() {
		HistoryFragment fragment = new HistoryFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private void setData() {
		ArrayList<PieEntry> entries = new ArrayList<>();

		entries.add(new PieEntry(5000000, "Chuyển tiền"));
		entries.add(new PieEntry(10000000, "Nhận tiền"));
		entries.add(new PieEntry(20000000, "Tiêu tiền"));


		PieDataSet dataSet = new PieDataSet(entries, "");

		ArrayList<Integer> colors = new ArrayList<>();

		for (int c : ColorTemplate.VORDIPLOM_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.JOYFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.COLORFUL_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.LIBERTY_COLORS)
			colors.add(c);

		for (int c : ColorTemplate.PASTEL_COLORS)
			colors.add(c);

		colors.add(ColorTemplate.getHoloBlue());

		dataSet.setColors(colors);
		dataSet.setSliceSpace(2f);
		dataSet.setSelectionShift(5f);

		dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);


		PieData data = new PieData(dataSet);
		data.setValueFormatter(new PercentFormatter());
		data.setValueTextSize(11f);
		data.setValueTextColor(Color.BLACK);

		chart.setData(data);
		chart.invalidate();
	}

	@Override
	public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
										  Bundle savedInstanceState) {
		android.view.View view = inflater.inflate(R.layout.fragment_history, container, false);

		init(view);
		seeMoreButton1.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				loadView(StatisticsActivity.class);
			}
		});
		seeMoreButton2.setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(android.view.View v) {
				loadView(LimitActivity.class);
			}
		});

		setUpPieChart();
		setData();
		setUpBarChart();

		return view;
	}

	private void setUpPieChart() {
		chart.setUsePercentValues(true);
		chart.getDescription().setText("Đơn vị: %");
		chart.getDescription().setTextSize(10);
		chart.getDescription().setPosition(670, 60);
		chart.setExtraOffsets(5, 10, 5, 5);
		chart.setDragDecelerationFrictionCoef(0.95f);
		chart.setDrawEntryLabels(false);


		chart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);

		//quay quay quay quayyy
		chart.setRotationAngle(0);
		chart.setRotationEnabled(true);
		chart.setHighlightPerTapEnabled(true);

		chart.animateY(1000, Easing.EaseInOutQuad);

		//chú thích.
		Legend l = chart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
		l.setXEntrySpace(7f);
		l.setYOffset(0f);
		l.setForm(Legend.LegendForm.SQUARE);
		l.setFormSize(10);
		l.setYEntrySpace(2);
		l.setFormToTextSpace(10);
		l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		l.setEnabled(true);

		chart.getLegend().setEnabled(true);


		//vòng tròn ở trong
		chart.setDrawHoleEnabled(true);
		chart.setRotationAngle(0);
		chart.setTransparentCircleColor(Color.WHITE);
		chart.setTransparentCircleAlpha(110);
		chart.setHoleRadius(50f);
		chart.setTransparentCircleRadius(55f);

		//text trong zòng tròn lè
		chart.setCenterText("Tỉ lệ chi tiêu");
		chart.setCenterTextRadiusPercent(70);
		chart.setCenterTextSize(15);
		chart.setDrawCenterText(true);

		chart.setEntryLabelTextSize(12);


	}

	private void setUpBarChart() {
		barChart.getDescription().setText("Thống kê thu, chi trong những tháng gần đây");
		barChart.getDescription().setPosition(550, 30);
		barChart.getDescription().setTextSize(10);
		barChart.setPinchZoom(false);
		barChart.setScaleEnabled(true);
		barChart.setDrawBarShadow(false);
		barChart.setDrawGridBackground(false);

		Legend l = barChart.getLegend();
		l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
		l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
		l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
		l.setDrawInside(true);
		l.setYOffset(20f);
		l.setXOffset(0f);
		l.setYEntrySpace(0f);
		l.setTextSize(10f);

		XAxis xAxis = barChart.getXAxis();
		xAxis.setGranularity(1f);
		xAxis.setGranularityEnabled(true);
		xAxis.setCenterAxisLabels(true);
		xAxis.setDrawLabels(true);
		xAxis.setLabelRotationAngle(45);
		xAxis.setDrawGridLines(false);
		xAxis.setAxisMaximum(12);
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		ArrayList<String> xVals = new ArrayList<>();
		xVals.add("Oct");
		xVals.add("Nov");
		xVals.add("Dec");
		xVals.add("Jan");
		xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));

		barChart.getAxisRight().setEnabled(false);
		YAxis leftAxis = barChart.getAxisLeft();
		leftAxis.setValueFormatter(new LargeValueFormatter());
		leftAxis.setDrawGridLines(true);
		leftAxis.setSpaceTop(35f);
		leftAxis.setAxisMinimum(0f);

		int groupCount = 5;


		ArrayList<BarEntry> yVals1 = new ArrayList<>();
		ArrayList<BarEntry> yVals2 = new ArrayList<>();


		yVals1.add(new BarEntry(1, (float) 7120000));
		yVals1.add(new BarEntry(2, (float) 3200000));
		yVals1.add(new BarEntry(3, (float) 6000000));
		yVals1.add(new BarEntry(4, (float) 0));

		yVals2.add(new BarEntry(1, (float) 1257000));
		yVals2.add(new BarEntry(2, (float) 4500000));
		yVals2.add(new BarEntry(3, (float) 5400000));
		yVals2.add(new BarEntry(4, (float) 0));

		BarDataSet set1, set2;
		set1 = new BarDataSet(yVals1, "Thu");
		set1.setColor(Color.rgb(2, 136, 209));
		set2 = new BarDataSet(yVals2, "Chi");
		set2.setColor(Color.rgb(211, 47, 47));

		BarData data = new BarData(set1, set2);
		data.setValueFormatter(new LargeValueFormatter());
		barChart.setData(data);
		barChart.getBarData().setBarWidth(barWidth);
		barChart.getXAxis().setAxisMinimum(0);
		barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
		barChart.groupBars(0, groupSpace, barSpace);
		barChart.getData().setHighlightEnabled(false);
		barChart.animateY(1000, Easing.EaseInOutQuad);
		barChart.invalidate();


	}

	private void init(android.view.View view) {
		this.historyPresenter = new HistoryPresenter(this);

		this.seeMoreButton1 = view.findViewById(R.id.see_more_text);
		this.seeMoreButton2 = view.findViewById(R.id.see_more_text_2);
		this.chart = view.findViewById(R.id.pie_chart);

		this.barChart = view.findViewById(R.id.bar_chart);
	}

	@Override
	public void loadView(Class<? extends com.example.deluxe.Core.View> view) {
		Intent intent = new Intent(getActivity(), view);
		startActivity(intent);
	}

	@Override
	public void setNotification(Enum e) {

	}
}



