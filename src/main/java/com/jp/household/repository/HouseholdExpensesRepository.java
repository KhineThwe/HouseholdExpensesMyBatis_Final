package com.jp.household.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.jp.household.entity.HouseholdExpenses;
import com.jp.household.entity.MonthlyExpense;
import com.jp.household.entity.MonthlyExpensesList;

@Mapper
public interface HouseholdExpensesRepository {
	List<HouseholdExpenses> findAll();

	public Integer total(int year, int month);

	List<MonthlyExpense> findMonthlyExpenses();

	List<MonthlyExpensesList> findTotalMonthlyCost(int month, int year);

	HouseholdExpenses findForValidate(int day, int month, int year, String item);

	public void householdAdd(HouseholdExpenses exp);

	public Optional<HouseholdExpenses> findById(int id);

	public void update(HouseholdExpenses exp);
}
