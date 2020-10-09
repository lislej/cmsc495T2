package org.amortizer.loancalcservice.client;

import java.io.Serializable;

public class LoanCalcModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3159911703114726650L;

	private double loanAmt = 0.0D;
	private double downPmt = 0.0D;
	private double intRate = 0.0D;
	private int term = 0;
	private double loanPmt = 0.0D;
	private double balance = 0.0D;
	private boolean amortSchedule = false;
	private String emailPin = "";

	public double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public double getDownPmt() {
		return downPmt;
	}

	public void setDownPmt(double downPmt) {
		this.downPmt = downPmt;
	}

	public double getIntRate() {
		return intRate;
	}

	public void setIntRate(double intRate) {
		this.intRate = intRate;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public double getLoanPmt() {
		return loanPmt;
	}

	public void setLoanPmt(double loanPmt) {
		this.loanPmt = loanPmt;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void setEmailPin(String emailPin) {
		this.emailPin = emailPin;
	}
	
	public String getEmailPin() {
		return emailPin;
	}
	
	// reset numeric amounts
	public void resetAmounts() {

		loanAmt = 0.0D;
		downPmt = 0.0D;
		intRate = 0.0D;
		term = 0;
		loanPmt = 0.0;
		emailPin = "";

	}


}
