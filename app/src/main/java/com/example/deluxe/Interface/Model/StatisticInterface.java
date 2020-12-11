package com.example.deluxe.Interface.Model;

import com.example.deluxe.Entity.Income;
import com.example.deluxe.Entity.Pay;

import java.util.List;

public interface StatisticInterface {

	void dataIsLoaded(List<Income> listIncome, List<Pay> ListPay);
}