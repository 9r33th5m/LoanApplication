package com.loanapp.restcontroller;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.loanapp.exceptions.DataExistsException;
import com.loanapp.exceptions.DataNotFoundException;
import com.loanapp.exceptions.InvalidDateException;
import com.loanapp.loan.AggregateLoan;
import com.loanapp.loan.Loan;
import com.loanapp.servicesimpl.LoanServicesImpl;

@RestController
@RequestMapping("/api")
public class RestControllerClass {
	
	@Autowired
	LoanServicesImpl loanServicesImpl;
	
	@GetMapping("getMessage")
	public String getMessage() {
		return "Hey! How are you doing today!";
	}
	
	@GetMapping("loans")
	public List<Loan> getAllLoans(){
		return loanServicesImpl.getAllLoans();
	}
	
	@PostMapping("loans/add")
	public String addLoan(@RequestBody Loan loan) throws InvalidDateException, ParseException, DataExistsException {
		return loanServicesImpl.addLoan(loan);
	}
	
	@GetMapping("/loans/{loanId}")
	public Loan getLoanByLoanId(@PathVariable int loanId) throws DataNotFoundException{
		return loanServicesImpl.getLoanByLoanId(loanId);
	}
	
	@GetMapping("/loans/customer/{customerId}")
    public List<Loan> getLoanByCustomerId(@PathVariable String customerId ) throws DataNotFoundException{
		return loanServicesImpl.getLoanByCustomerId(customerId);
	}

	@GetMapping("/loans/lender/{lenderId}")
    public List<Loan> getLoanByLenderId(@PathVariable String lenderId ) throws DataNotFoundException{
	    return loanServicesImpl.getLoanByLenderId(lenderId);
    }
    
	@GetMapping("/loans/aggregate/lender/{lenderId}")
    public List<AggregateLoan> getAggregateLoanByLenderId(@PathVariable String lenderId) throws DataNotFoundException{
    	return loanServicesImpl.getAggregateLoanByLenderId(lenderId);
    }
    
	@GetMapping("/loans/aggregate/customer/{customerId}")
    public List<AggregateLoan> getAggregateLoanByCustomerId(@PathVariable String customerId) throws DataNotFoundException{
    	return loanServicesImpl.getAggregateLoanByCustomerId(customerId);
    }
    
	@GetMapping("/loans/aggregate/interest/{interest}")
    public List<AggregateLoan> getAggregateLoanByInterest(@PathVariable double interest) throws DataNotFoundException{
    	return loanServicesImpl.getAggregateLoanByInterest(interest);
    }
	
	

}
