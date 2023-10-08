package com.loanapp.restcontroller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loanapp.exceptions.DataExistsException;
import com.loanapp.exceptions.InvalidDateException;
import com.loanapp.loan.AggregateLoan;
import com.loanapp.loan.Loan;
import com.loanapp.servicesimpl.LoanServicesImpl;

@SpringBootTest(classes = com.loanapp.loanapplication.LoanApplication.class)
@AutoConfigureMockMvc
public class RestControllerClassTest {
	
	@Autowired
	private MockMvc mockMvc;
	
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
	public void testGetAllLoans() throws Exception {
		
		Mockito.when(loanServicesImpl.getAllLoans()).thenReturn(mockItems);
		
		mockMvc.perform(MockMvcRequestBuilders
                .get("/api/loans"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(mockItems.size()));
	}
	
	@Test
	public void testAddLoan() throws Exception {
		Loan loan = new Loan(1,"C1","LEN1",12345,234,"23-04-2023",1.2,"27-04-2023",3.4,false);
		
		Mockito.when(loanServicesImpl.addLoan(loan)).thenReturn("Successfully data inserted");
		
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/loans/add")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(new ObjectMapper().writeValueAsString(loan)))
		        .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetLoanByLoanId() throws Exception {
		Loan loan = new Loan(1,"C1","LEN1",12345,234,"23-04-2023",1.2,"27-04-2023",3.4,false);
		
		Mockito.when(loanServicesImpl.getLoanByLoanId(loan.getLoanId())).thenReturn(loan);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/loans/1")
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(loan)));
		
	}
	
	@Test
	public void testGetLoanByCustomerId() throws JsonProcessingException, Exception {
        
		List<Loan> loan = List.of(new Loan(1,"C1","LEN1",12345,234,"23-04-2023",1.2,"27-04-2023",3.4,false),new Loan(2,"C1","LEN2",2345,23,"23-04-2023",1.2,"27-04-2023",3.4,false));
		
		Mockito.when(loanServicesImpl.getLoanByCustomerId("C1")).thenReturn(loan);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/loans/customer/C1"))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(loan)));
	}
	
	@Test
	public void testGetLoanByLenderId() throws JsonProcessingException, Exception {
        List<Loan> loan = List.of(new Loan(1,"C1","LEN1",12345,234,"23-04-2023",1.2,"27-04-2023",3.4,false),new Loan(2,"C2","LEN1",2345,23,"23-04-2023",1.2,"27-04-2023",3.4,false));
		
		Mockito.when(loanServicesImpl.getLoanByLenderId("LEN1")).thenReturn(loan);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/loans/lender/LEN1"))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(loan)));
	}
	
	@Test
	public void testGetAggregateLoanByCustomerId() throws JsonProcessingException, Exception {
		
		List<AggregateLoan> loan = List.of(new AggregateLoan(100, 1, 3), new AggregateLoan(350, 2, 4));
		
		Mockito.when(loanServicesImpl.getAggregateLoanByCustomerId("C1")).thenReturn(loan);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/loans/aggregate/customer/C1"))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(loan)));
		
	}
	
	@Test
	public void testGetAggregateLoanByLenderId() throws JsonProcessingException, Exception {
		
       List<AggregateLoan> loan = List.of(new AggregateLoan(100, 1, 3), new AggregateLoan(350, 2, 4));
		
	   Mockito.when(loanServicesImpl.getAggregateLoanByLenderId("LEN1")).thenReturn(loan);
		
	   mockMvc.perform(MockMvcRequestBuilders
				.get("/api/loans/aggregate/lender/LEN1"))
		        .andExpect(MockMvcResultMatchers.status().isOk())
		        .andExpect(MockMvcResultMatchers.content().string(new ObjectMapper().writeValueAsString(loan)));
		
	}
	
	@Test
	public void testGetAggregateLoanByInterest() {
		
	}

}
