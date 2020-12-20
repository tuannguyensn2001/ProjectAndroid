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

	public class getPerMonthAPI {
		@SerializedName("id")
		@Expose
		private String id;
		@SerializedName("money")
		@Expose
		private Integer money;
		@SerializedName("created_at")
		@Expose
		private String createdAt;
		@SerializedName("email")
		@Expose
		private String email;
		@SerializedName("type")
		@Expose
		private Integer type;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getMoney() {
			return money;
		}

		public void setMoney(Integer money) {
			this.money = money;
		}

		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
	}


}
