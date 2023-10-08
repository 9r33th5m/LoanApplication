package com.loanapp.loan;

public class AggregateLoan {
	
	private double remainingAmount;
	private double interest;
	private double penalty;
	
	public AggregateLoan(double remainingAmount, double interest, double penalty) {
		this.remainingAmount = remainingAmount;
		this.interest = interest;
		this.penalty = penalty;
	}
	
	public double getRemainingAmount() {
		return remainingAmount;
	}
	
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	
	public double getInterest() {
		return interest;
	}
	
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	public double getPenalty() {
		return penalty;
	}
	
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}
	
	@Override
	public String toString() {
		return "AggregateLoan [remainingAmount=" + remainingAmount + ", interest=" + interest + ", penalty=" + penalty
				+ "]";
	}
	
	

}
