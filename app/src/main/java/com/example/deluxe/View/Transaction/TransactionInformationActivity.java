package com.example.deluxe.View.Transaction;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deluxe.Adapter.TransactionsInformationAdapter;
import com.example.deluxe.Core.View;
import com.example.deluxe.Entity.ListViewInfo;
import com.example.deluxe.Interface.PresenterView.Transaction.TransactionInformationInterface;
import com.example.deluxe.Presenter.Transaction.TransactionInformationPresenter;
import com.example.deluxe.R;

import java.util.ArrayList;
import java.util.List;

public class TransactionInformationActivity extends AppCompatActivity implements TransactionInformationInterface.TransactionInformationView {
	TransactionInformationInterface.TransactionInformationPresenter transactionInformationPresenter;


	ListView listView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_information);
//		listView = (ListView) findViewById(R.id.list_info);
		List<ListViewInfo> listViewInfos = getListData();
		listView.setAdapter(new TransactionsInformationAdapter(this, listViewInfos));


		init();
	}

	private List<ListViewInfo> getListData() {
		List<ListViewInfo> listViewInfos = new ArrayList<>();
		ListViewInfo giaodich = new ListViewInfo("Loai giao dich", "Deo biet", null);
		ListViewInfo sotien = new ListViewInfo("So tien", "1,000,,000", null);
		ListViewInfo magiaodich = new ListViewInfo("Ma giao dich", "01234567", null);
		ListViewInfo tinhtrang = new ListViewInfo("tinh trang", "deo biet", null);
		ListViewInfo thoigian = new ListViewInfo("Thoi gian", "20/11/2020", null);
		ListViewInfo nguoidung = new ListViewInfo("Nguoi dung", "Deo biet", null);
		ListViewInfo loinhan = new ListViewInfo("Loi nhan", "Deo biet", null);
		ListViewInfo giohang = new ListViewInfo("Gio hang", "deo biet", null);


		listViewInfos.add(giaodich);
		listViewInfos.add(sotien);
		listViewInfos.add(magiaodich);
		listViewInfos.add(tinhtrang);
		listViewInfos.add(thoigian);
		listViewInfos.add(nguoidung);
		listViewInfos.add(loinhan);
		listViewInfos.add(giohang);

		return listViewInfos;
	}

	private void init() {
		this.transactionInformationPresenter = new TransactionInformationPresenter(this);

		((TextView) findViewById(R.id.action_bar_title)).setText(getString(R.string.transaction_info_action_bar_title));
	}

	@Override
	public void loadView(Class<? extends View> view) {

	}

	@Override
	public void setNotification(Enum e) {

	}
}