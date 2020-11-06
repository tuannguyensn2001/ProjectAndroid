package com.example.deluxe.Entity;

public class Wallet {
//<<<<<<< HEAD
//
//    private double amount;
//    private String created_at;
//    private String updated_at;
//
//     public Wallet(){
//
//     }
//=======
	private double amount;
	private String created_at;
	private String updated_at;

	public Wallet() {

	}
//>>>>>>> 90beacaf579479388b35c4001f3ad09a29db41f5
//
//	public Wallet(double amount) {
//		this.amount = amount;
//	}
//
//<<<<<<< HEAD

        public Double getAmount() {
            return amount;
        }

        public void increaseAmount(double amount)
        {
            this.amount+= amount;
        }

        public void decreaseAmount(double amount)
        {
            this.amount-=amount;
        }


            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getCreated_at() {
                return created_at;
            }
            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
            public String getUpdated_at() {
                return updated_at;
            }
            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }

=======
	public Double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public void increaseAmount(double amount) {
		this.amount += amount;
	}

	public void decreaseAmount(double amount) {
		this.amount -= amount;
	}

}
>>>>>>> 90beacaf579479388b35c4001f3ad09a29db41f5
