package com.loanapp.services;

import java.text.ParseException;
import java.util.List;
import com.loanapp.exceptions.DataExistsException;
import com.loanapp.exceptions.DataNotFoundException;
import com.loanapp.exceptions.InvalidDateException;
import com.loanapp.loan.AggregateLoan;
import com.loanapp.loan.Loan;

public interface LoanServices {
	
	public abstract List<Loan> getAllLoans();
	public abstract String addLoan(Loan loan) throws DataExistsException, InvalidDateException, ParseException;
	public abstract Loan getLoanByLoanId(int loanId) throws DataNotFoundException;
	public abstract List<Loan> getLoanByCustomerId(String customerId) throws DataNotFoundException;
	public abstract List<Loan> getLoanByLenderId(String lenderId) throws DataNotFoundException;
	public abstract List<AggregateLoan> getAggregateLoanByLenderId(String lenderId) throws DataNotFoundException;
	public abstract List<AggregateLoan> getAggregateLoanByCustomerId(String customerId) throws DataNotFoundException;
	public abstract List<AggregateLoan> getAggregateLoanByInterest(double interest) throws DataNotFoundException;
	
	
	

}
