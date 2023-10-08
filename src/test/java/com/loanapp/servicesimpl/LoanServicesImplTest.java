package com.loanapp.servicesimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.loanapp.exceptions.DataExistsException;
import com.loanapp.exceptions.DataNotFoundException;
import com.loanapp.exceptions.InvalidDateException;
import com.loanapp.loan.AggregateLoan;
import com.loanapp.loan.Loan;


@SpringBootTest(classes = com.loanapp.loanapplication.LoanApplication.class)
public class LoanServicesImplTest{
	
	@MockBean
	private LoanServicesImpl loanServicesImpl;
	
	List<Loan> mockItems;
	
	@BeforeEach
	void setUp() {
		mockItems = new ArrayList<>();
		mockItems.add(new Loan(1,"C1","LEN1",12345,234,"23-04-2023",1.2,"27-04-2023",3.4,false));
		mockItems.add(new Loan(2,"C2","LEN2",23451,2345,"28-04-2023",1.5,"05-05-2023",3.1,false));
	}

	@Test
	public void testGetAllLoans() {	
		Mockito.when(loanServicesImpl.getAllLoans()).thenReturn(mockItems);
		assertEquals(2, loanServicesImpl.getAllLoans().size());
	}

	@Test
	public void testAddLoan() throws DataExistsException, InvalidDateException, ParseException {
		Mockito.when(loanServicesImpl.addLoan(mockItems.get(0))).thenReturn("Successfully data inserted");
		assertEquals("Successfully data inserted", loanServicesImpl.addLoan(mockItems.get(0)));
	}

	@Test
	public void testGetLoanByLoanId() throws DataNotFoundException {
		Mockito.when(loanServicesImpl.getLoanByLoanId(1)).thenReturn(mockItems.get(0));
		assertEquals(mockItems.get(0), loanServicesImpl.getLoanByLoanId(1));
	}

	@Test
	public void testGetLoanByCustomerId() throws DataNotFoundException {
		Mockito.when(loanServicesImpl.getLoanByCustomerId("C1")).thenReturn(List.of(mockItems.get(0)));
		assertEquals(1, loanServicesImpl.getLoanByCustomerId("C1").size());
	}

	@Test
	public void testGetLoanByLenderId() throws DataNotFoundException {
		Mockito.when(loanServicesImpl.getLoanByLenderId("LEN1")).thenReturn(List.of(mockItems.get(0)));
		assertEquals(List.of(mockItems.get(0)), loanServicesImpl.getLoanByLenderId("LEN1"));
	}

	@Test
	public void testGetAggregateLoanByLenderId() throws DataNotFoundException {
		List<AggregateLoan> aggLoan = List.of(new AggregateLoan(234, 1, 3.2));
		Mockito.when(loanServicesImpl.getAggregateLoanByLenderId("LEN1")).thenReturn(aggLoan);
		assertEquals(aggLoan, loanServicesImpl.getAggregateLoanByLenderId("LEN1"));
	}

	@Test
	public void testGetAggregateLoanByCustomerId() throws DataNotFoundException {
		List<AggregateLoan> aggLoan = List.of(new AggregateLoan(234, 1, 3.2));
		Mockito.when(loanServicesImpl.getAggregateLoanByCustomerId("C1")).thenReturn(aggLoan);
		assertEquals(aggLoan.size(), loanServicesImpl.getAggregateLoanByCustomerId("C1").size());
	}

	@Test
	public void testGetAggregateLoanByInterest() throws DataNotFoundException {
		
	}
	
	

}
