package com.loanapp.cache;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.loanapp.loan.Loan;

@Component
public class LoanAppCache {
	
	private static List<Loan> loanStore;
	
	private LoanAppCache() {
		
	}
	
	public static List<Loan> getLoanStoreInstance() {
		
		if(loanStore == null)
			loanStore = new ArrayList<>();
		
		return loanStore;
	}

}
