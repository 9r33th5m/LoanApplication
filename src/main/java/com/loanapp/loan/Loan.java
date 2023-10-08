package com.loanapp.loan;

import java.util.Date;

import org.springframework.stereotype.Component;

public class Loan{
	
	private int loanId;
	private String customerId; 
	private String lenderId;
	private double amount;
	private double remainingAmount;
	private String paymentDate;
	private double ipd;
	private String dueDate;
	private double penalty;
	private boolean cancel;
	
	public Loan(int loanId, String customerId, String lenderId, double amount, double remainingAmount, String paymentDate, double ipd, String dueDate,
			double penalty, boolean cancel) {
		
		this.loanId = loanId;
		this.customerId = customerId;
		this.lenderId = lenderId;
		this.amount = amount;
		this.remainingAmount = remainingAmount;
		this.paymentDate = paymentDate;
		this.ipd = ipd;
		this.dueDate = dueDate;
		this.penalty = penalty;
		this.cancel = cancel;
		
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getLenderId() {
		return lenderId;
	}

	public void setLenderId(String lenderId) {
		this.lenderId = lenderId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getIpd() {
		return ipd;
	}

	public void setIpd(double ipd) {
		this.ipd = ipd;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", customerId=" + customerId + ", lenderId=" + lenderId + ", amount=" + amount
				+ ", remainingAmount=" + remainingAmount + ", paymentDate=" + paymentDate + ", ipd=" + ipd
				+ ", dueDate=" + dueDate + ", penalty=" + penalty + ", cancel=" + cancel + "]";
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	public boolean isCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	
	
		

}
