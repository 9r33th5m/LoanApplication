package com.loanapp.verification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.loanapp.cache.LoanAppCache;
import com.loanapp.exceptions.DataExistsException;
import com.loanapp.exceptions.InvalidDateException;
import com.loanapp.loan.Loan;

public class DataVerification {
	
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public static boolean dataVerification(Loan loan) throws InvalidDateException, ParseException, DataExistsException {
		if(dateVerification(loan) && duplicateValidation(loan)) {
			return true;
		}
		return false;
	}
	
	private static boolean dateVerification(Loan loan) throws InvalidDateException, ParseException {
		Date paymentDate = simpleDateFormat.parse(loan.getPaymentDate());
		Date dueDate = simpleDateFormat.parse(loan.getDueDate());
		if(paymentDate.after(dueDate)) 
		throw new InvalidDateException("Invalid Date, Please enter the correct date in required format");
		return true;
	}
	
	private static boolean duplicateValidation(Loan loan) throws DataExistsException {
		for(Loan loane:LoanAppCache.getLoanStoreInstance()) {
			if(loane.getLoanId() == loan.getLoanId())
				throw new DataExistsException("Loan application already exists for the given loan id");
		}
		return true;
	}
}
