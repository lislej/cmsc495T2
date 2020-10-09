
/*
 * Calculations for Amt, Pmt, Term, and Rate are based on this equation
 * 
 * A = (P * r(1+r)^n)/((1+r)^n -1)
 * 
 * Interest is calculated using a binary search. Reference - http://www.hughcalc.org/formula.php 
 * 
 */

package org.amortizer.loancalcservice.loancalcs;



public class LoanCalculator {
  
  //constants
  final private static double DELTA = 0.0000000000001d;

  //instance variables
  private double loanAmt;
  private double loanPmt;
  private int term;
  private double rate;
  

  
  // calculate loan payment constructor
  public LoanCalculator(double loanAmt, double rate, int term) {

    this.loanAmt = loanAmt;
    this.rate = rate / (12 * 100);
    this.term = term;

  }

  // calculate loan amount constructor
  public LoanCalculator(double loanPmt, int term, double rate) {

    this.loanPmt = loanPmt;
    this.rate = rate / (12 * 100);
    this.term = term;

  }

  // calculate term constructor
  public LoanCalculator(double loanPmt, double loanAmt, double rate) {

    this.loanPmt = loanPmt;
    this.loanAmt = loanAmt;
    this.rate = rate / (12 * 100);

  }

//calculate rate constructor
 public LoanCalculator(int term, double loanPmt, double loanAmt) {

   this.loanPmt = loanPmt;
   this.loanAmt = loanAmt;
   this.term = term;

 }


 
 /*
  * lnPmt  - payment amount
  * lnRate - monthly rate ( interest rate / ( 12 * 100) )
  * lnTerm - total months 
  */
 
 public double calcLoanAmt(double lnPmt, double lnRate, double lnTerm) {

   double lnAmt = (lnPmt * (1 - Math.pow((1 + lnRate), -1 * lnTerm))) / lnRate;
   
   return lnAmt;
 
 }

  

  public void calcLoanAmt() {

    this.loanAmt = (loanPmt * (1 - Math.pow((1 + rate), -1 * term))) / rate;

  }

  public void calcLoanPmt() {

    this.loanPmt = (this.rate * this.loanAmt) / (1 - Math.pow((1 + this.rate), -1 * this.term));

  }
  
  public double calcLoanPmt(double rate, double loanAmt, int term) {

	    return (rate * loanAmt) / (1 - Math.pow((1 + rate), -1 * term));

	  }
  
  public void calcLoanTerm() {

    double numerator   = 0.0d;
    double denomerator = 0.0d;
    
    numerator =  (1 - (rate * loanAmt)/loanPmt);
    denomerator = 1 + rate;
    
    this.term = (int) Math.round(-1 * ( Math.log(numerator) / Math.log(denomerator) ));
    
  }
   
  
  public void calcLoanIntRate() {
        
    double maxRate = 100;
    double minRate = 0;
    double midRate = 0;
    double guessLoanAmt = 0;
    double monthlyRate = 0;

    
    while (minRate < maxRate - DELTA) {
      
      midRate = (minRate + maxRate) / 2;
      
      monthlyRate = midRate / (12*100);
 
      guessLoanAmt = calcLoanAmt(this.loanPmt, monthlyRate, this.term);
      
      if ( guessLoanAmt < this.loanAmt) {
        
        maxRate = midRate;
        
      } else {
        
        minRate = midRate;
        
      }
      
    }

    this.rate = midRate;
    
  }

  public void calcLoanIntRt() {
      
	    double maxRate = 10000;
	    double minRate = 0;
	    double midRate = 0;
	    double guessLoanPmt = 0;
	    double monthlyRate = 0;

	    
	    while (minRate < maxRate - 0.0001) {
//	    while (!(Math.abs(guessLoanPmt - this.loanPmt) < 0.00001)) {	      
	      midRate = (minRate + maxRate) / 2;
	      
	      monthlyRate = midRate / (12*100);
	 
	      guessLoanPmt = calcLoanPmt(monthlyRate, this.loanAmt, this.term);
	      
	      if ( guessLoanPmt > this.loanPmt) {
	        maxRate = midRate;
	      } else {
	        minRate = midRate;
	      }
	    }

	    this.rate = midRate;
	    
	  }

  

  public double getLoanAmt() {
    return loanAmt;
  }

  public void setLoanAmt(double loanAmt) {
    this.loanAmt = loanAmt;
  }

  public double getLoanPmt() {
    return loanPmt;
  }

  public void setLoanPmt(double loanPmt) {
    this.loanPmt = loanPmt;
  }

  public int getTerm() {
    return term;
  }

  public void setTerm(int term) {
    this.term = term;
  }

  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  
  public static void main(String[] args) {

    
    
//    LoanCalculator lc = new LoanCalculator(166127.21, 3.375, 360);
//    lc.calcLoanPmt();
//    
//    System.out.println(lc.getLoanPmt());
//    
//    LoanCalculator lc2 = new LoanCalculator(734.44, 360, 3.375);
//    lc2.calcLoanAmt();
//    
//    System.out.println(lc2.getLoanAmt());
//        
//    LoanCalculator lc3 = new LoanCalculator(734.44, 166127.21, 3.375);    
//    lc3.calcLoanTerm();
//
//    System.out.println(lc3.getTerm());    
    
    LoanCalculator lc4 = new LoanCalculator(360, 734.44, 166127.21);    
    lc4.calcLoanIntRate();    
    System.out.println(lc4.getRate());
  
    LoanCalculator lc5 = new LoanCalculator(360, 750.00, 276000.00);    //360 * 750 = 270000
    lc5.calcLoanIntRate();
    System.out.println(lc5.getRate());
    
    LoanCalculator lc6 = new LoanCalculator(12, 600.00, 6000.00);       //12 * 600 = 7200
    lc6.calcLoanIntRate();
    System.out.println(lc6.getRate());
    
    
    
  }

  

}
