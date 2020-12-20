package com.example.deluxe.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Object {

	public class ResponeOrderAPI {

		@SerializedName("id")
		@Expose
		private Integer id;
		@SerializedName("money")
		@Expose
		private Integer money;
		@SerializedName("updated_at")
		@Expose
		private String updatedAt;
		@SerializedName("update")
		@Expose
		private String update;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getMoney() {
			return money;
		}

		public void setMoney(Integer money) {
			this.money = money;
		}

		public String getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(String updatedAt) {
			this.updatedAt = updatedAt;
		}

		public String getUpdate() {
			return update;
		}

		public void setUpdate(String update) {
			this.update = update;
		}

	}
}
