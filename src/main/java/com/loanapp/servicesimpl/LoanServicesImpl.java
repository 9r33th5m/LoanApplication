package com.loanapp.servicesimpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.loanapp.cache.LoanAppCache;
import com.loanapp.exceptions.DataExistsException;
import com.loanapp.exceptions.DataNotFoundException;
import com.loanapp.exceptions.InvalidDateException;
import com.loanapp.loan.AggregateLoan;
import com.loanapp.loan.Loan;
import com.loanapp.services.LoanServices;
import com.loanapp.verification.DataVerification;

@Service
public class LoanServicesImpl implements LoanServices {
	
	List<Loan> loan;
	List<AggregateLoan> aggLoan;
	
	private LoanServicesImpl() {
		loan = new ArrayList<>();
	}

	@Override
	public List<Loan> getAllLoans() {
		return LoanAppCache.getLoanStoreInstance();
	}

	@Override
	public String addLoan(Loan newLoan) throws InvalidDateException, ParseException, DataExistsException {
		
		try {
	        if(DataVerification.dataVerification(newLoan))
	    	LoanAppCache.getLoanStoreInstance().add(newLoan);
	        return "Successfully data inserted";
		}
		catch(InvalidDateException | ParseException | DataExistsException e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}

	@Override
	public Loan getLoanByLoanId(int loanId) throws DataNotFoundException {
		
		loan = new ArrayList<>();
        for(Loan eachLoan : LoanAppCache.getLoanStoreInstance()) {
        	if(eachLoan.getLoanId() == loanId)
        		return eachLoan;
	}
        throw new DataNotFoundException("Invalid Load Id, Please try again");
	}

	@Override
	public List<Loan> getLoanByCustomerId(String customerId) throws DataNotFoundException {
        
		loan = new ArrayList<>();
		for(Loan eachLoan : LoanAppCache.getLoanStoreInstance()) {
        	if(eachLoan.getCustomerId().equals(customerId.toUpperCase()))
        		loan.add(eachLoan);
        }
		if(loan.size() == 0)
        	throw new DataNotFoundException("Incorrect Customer Id, Please check again");
        else
		return loan;
		
	}

	@Override
	public List<Loan> getLoanByLenderId(String lenderId) throws DataNotFoundException {
		loan = new ArrayList<>();
		for(Loan eachLoan : LoanAppCache.getLoanStoreInstance()) {
        	if(eachLoan.getLenderId().equals(lenderId.toUpperCase()))
        		loan.add(eachLoan);
        }
		if(loan.size() == 0)
        	throw new DataNotFoundException("Incorrect Lender Id, Please check again");
        else
		return loan;
	
	}

	@Override
	public List<AggregateLoan> getAggregateLoanByLenderId(String lenderId) throws DataNotFoundException {
		aggLoan = new ArrayList<>();
		for(Loan loan : LoanAppCache.getLoanStoreInstance()) {
		if(loan.getLenderId().equals(lenderId.toUpperCase()))
		aggLoan.add(new AggregateLoan(loan.getRemainingAmount(), loan.getIpd(), loan.getPenalty()));
		}
		if(aggLoan.size() == 0)
        	throw new DataNotFoundException("Incorrect Lender Id, Please check again");
        else
		return aggLoan;
	}

	@Override
	public List<AggregateLoan> getAggregateLoanByCustomerId(String customerId) throws DataNotFoundException {
		aggLoan = new ArrayList<>();
		for(Loan loan : LoanAppCache.getLoanStoreInstance()) {
		if(loan.getCustomerId().equals(customerId.toUpperCase()))
		aggLoan.add(new AggregateLoan(loan.getRemainingAmount(), loan.getIpd(), loan.getPenalty()));
		}
		if(aggLoan.size() == 0)
        	throw new DataNotFoundException("Incorrect Customer Id, Please check again");
        else
		return aggLoan;
		}

	@Override
	public List<AggregateLoan> getAggregateLoanByInterest(double interest) throws DataNotFoundException {
		aggLoan = new ArrayList<>();
		for(Loan loan : LoanAppCache.getLoanStoreInstance()) {
		if(loan.getIpd() == interest)
		aggLoan.add(new AggregateLoan(loan.getRemainingAmount(), loan.getIpd(), loan.getPenalty()));
		}
		if(aggLoan.size() == 0)
        	throw new DataNotFoundException("Loan application with provided interest rate not found, please try again");
        else
		    return aggLoan;
		}

	}

