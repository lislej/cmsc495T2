package org.amortizer.loancalcservice.utils;


import java.io.IOException;
import java.text.DecimalFormat;

import org.amortizer.loancalcservice.email.EmailManager;
import com.sendgrid.Response;

public class AmortizationSchedule {
	
	public AmortizationSchedule() {
		
	}
	
	
	
	public String createAmortizationSchedule(double loanAmt, double loanPmt, double intRate, int lnTerm, double downPmt) {

		double balance = loanAmt;
		double payment = loanPmt;
		DecimalFormat df3 = new DecimalFormat("0.###");
		double rate = new Double(df3.format(intRate));
		
		
		
	    int    term    = lnTerm;
	    double totalInt = 0.0;
	    
	    
		double monthlyInterest = 0;
		double principal = 0;

		
		StringBuffer amortScheduleBody = new StringBuffer();
		
		amortScheduleBody.append("<b><pre>Month    Payment   Principal    Interest       Balance</b></pre><br>");

		for (int i = 0; i < term; i++) {

			monthlyInterest = calcMonthlyInterest(balance, rate);
			totalInt+=monthlyInterest;
			principal = calculatePrincipal(payment, monthlyInterest);
			balance = calculateBalance(balance, 0, principal);

			amortScheduleBody.append(String.format("<b><pre> %4d    %7.2f     %7.2f     %7.2f     %9.2f</b></pre>", i + 1, payment, principal, monthlyInterest, balance > 0 ? balance:0.00));
//			amortScheduleBody.append("\n");

		}

		//create header
		StringBuffer amortScheduleHdr = new StringBuffer();

		amortScheduleHdr.append("<b>-----------------Loan Amortization Schedule-----------------</b>");

		amortScheduleHdr.append(String.format("<b><pre>Loan Amount ......: %10.2f</b></pre>", loanAmt));
		amortScheduleHdr.append(String.format("<b><pre>Loan Payment .....: %10.2f</b></pre>", loanPmt));
		amortScheduleHdr.append(String.format("<b><pre>Interest Rate ....: %10.3f</b></pre>", rate));
		amortScheduleHdr.append(String.format("<b><pre>Loan Term ........: %10d</b></pre>", lnTerm));
		amortScheduleHdr.append(String.format("<b><pre>Interest Paid ....: %10.2f</b></pre>", totalInt));
		
		amortScheduleHdr.append("<br>");

		
		//assemble final document
		StringBuffer amortSchedule = new StringBuffer();
		amortSchedule.append("<html>");
		amortSchedule.append("<font face=courier new size=2px >");
		amortSchedule.append("<body>");
		amortSchedule.append(amortScheduleHdr.toString());
		amortSchedule.append(amortScheduleBody.toString());
		amortSchedule.append("</body>");
		amortSchedule.append("</font>");		
		amortSchedule.append("</html>");
		
		return amortSchedule.toString();

	}


	private double calculatePrincipal(double loanPmt, double monthlyInterest) {
	
		return loanPmt - monthlyInterest;
	
	}

	private double calculateBalance(double loanAmt, double downPmt, double principal) {
	
		return (loanAmt - downPmt - principal);
	}

	private double calcMonthlyInterest(double balance, double intRate) {
	
		return balance * (intRate / (12 * 100));
	}
	
	public static void main (String[] args) throws IOException {
		
		AmortizationSchedule am = new AmortizationSchedule();
		String schedule = am.createAmortizationSchedule(223225.75, 1250, 5.3754, 360, 0);
		
		System.out.println(schedule);
		
        EmailManager emailMgr = new EmailManager("Loan Amortization Schedule", "lislej@gmail.com", "info@loancalc.com", schedule);
		
		Response response = emailMgr.sendEmailWithoutAttachement();
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		System.out.println(response.getHeaders());

		
	}

}
