package com.example.deluxe.Interface.Model;

import com.example.deluxe.Entity.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public interface ListTransactionInterface {

	void dataIsLoaded(HashMap<Date, ArrayList<Transaction>> transactions);
}
