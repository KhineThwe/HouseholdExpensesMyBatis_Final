package com.jp.household.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.household.entity.HouseholdExpenses;
import com.jp.household.entity.MonthlyExpense;
import com.jp.household.entity.MonthlyExpensesList;
import com.jp.household.form.HouseholdExpensesForm;
import com.jp.household.repository.HouseholdExpensesRepository;

@Service
public class HouseholdExpensesService {
	@Autowired
	HouseholdExpensesRepository householdExpensesRepository;

	public void save(HouseholdExpensesForm householdExpensesForm) {
		HouseholdExpenses entity = new HouseholdExpenses();
		String[] dateParts = householdExpensesForm.getDate().split("-");
		int day = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[2]);
		entity.setYear(year);
		entity.setMonth(month);
		entity.setDay(day);
		entity.setCost(householdExpensesForm.getCost());
		entity.setItem(householdExpensesForm.getItem());
		householdExpensesRepository.householdAdd(entity);
	}

	public HouseholdExpensesForm getHouseholdExpenses(int id) {
		Optional<HouseholdExpenses> p = householdExpensesRepository.findById(id);
		HouseholdExpenses e = p.get();
		HouseholdExpensesForm dto = new HouseholdExpensesForm();
		int year = e.getYear();
		int month = e.getMonth();
		int day = e.getDay();
		dto.setId(e.getId());
		String date = String.valueOf(day + '-' + month + '-' + year);
		dto.setDate(date);
		dto.setCost(e.getCost());
		dto.setItem(e.getItem());
		return dto;
	}

	public void updateExpenses(HouseholdExpensesForm householdExpensesDto) {
		HouseholdExpenses entity = new HouseholdExpenses();
		String[] dateParts = householdExpensesDto.getDate().split("-");
		int day = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]);
		int year = Integer.parseInt(dateParts[2]);
		entity.setId(householdExpensesDto.getId());
		entity.setYear(year);
		entity.setMonth(month);
		entity.setDay(day);
		entity.setCost(householdExpensesDto.getCost());
		entity.setItem(householdExpensesDto.getItem());
		householdExpensesRepository.update(entity);
	}

	public List<HouseholdExpenses> findAll() {
		return householdExpensesRepository.findAll();
	}

	public Integer getTotal(int month, int year) {
		return householdExpensesRepository.total(month, year);
	}

	public List<MonthlyExpense> getMonthlyExpenses() {
		return householdExpensesRepository.findMonthlyExpenses();
	}

	public List<MonthlyExpensesList> getMonthlyExpensesDetails(int month, int year) {
		return householdExpensesRepository.findTotalMonthlyCost(month, year);
	}

	public HouseholdExpenses findForValidate(String day, String month, String year, String item) {
		return householdExpensesRepository.findForValidate(Integer.parseInt(day), Integer.parseInt(month),
				Integer.parseInt(year), item);
	}

}
