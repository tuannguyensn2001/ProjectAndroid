package com.example.deluxe.Adapter.StatisticsCard;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deluxe.Entity.Transaction;

public class StatisticsCardChild implements Parcelable {
	public static final Creator<StatisticsCardChild> CREATOR = new Creator<StatisticsCardChild>() {
		@Override
		public StatisticsCardChild createFromParcel(Parcel in) {
			return new StatisticsCardChild(in);
		}

		@Override
		public StatisticsCardChild[] newArray(int size) {
			return new StatisticsCardChild[size];
		}
	};
	private Transaction transaction;

	protected StatisticsCardChild(Parcel in) {
	}

	public StatisticsCardChild(Transaction transaction) {
		this.transaction = transaction;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}
}
