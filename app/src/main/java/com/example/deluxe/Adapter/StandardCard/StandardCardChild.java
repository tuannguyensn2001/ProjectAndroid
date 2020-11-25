package com.example.deluxe.Adapter.StandardCard;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.deluxe.Entity.StandardCard;

public class StandardCardChild implements Parcelable {
	private StandardCard standardCard;

	public StandardCard getStandardCard() {
		return standardCard;
	}

	public void setStandardCard(StandardCard standardCard) {
		this.standardCard = standardCard;
	}

	public StandardCardChild(StandardCard standardCard) {
		this.standardCard = standardCard;
	}

	protected StandardCardChild(Parcel in) {
	}

	public static final Creator<StandardCardChild> CREATOR = new Creator<StandardCardChild>() {
		@Override
		public StandardCardChild createFromParcel(Parcel in) {
			return new StandardCardChild(in);
		}

		@Override
		public StandardCardChild[] newArray(int size) {
			return new StandardCardChild[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
	}
}
