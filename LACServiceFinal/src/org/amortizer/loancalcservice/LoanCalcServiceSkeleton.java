/**
 * LoanCalcServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package org.amortizer.loancalcservice;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.amortizer.loancalcservice.email.EmailManager;
import org.amortizer.loancalcservice.loancalcs.LoanCalculator;
import org.amortizer.loancalcservice.utils.AmortizationSchedule;

/**
 * LoanCalcServiceSkeleton java skeleton for the axisService
 */
public class LoanCalcServiceSkeleton implements LoanCalcServiceSkeletonInterface {
	static private ConcurrentHashMap<String, Integer> emailStoreByEmail = new ConcurrentHashMap<String, Integer>();
	static private ConcurrentHashMap<Integer, String> emailStoreByPIN = new ConcurrentHashMap<Integer, String>();
	private AmortizationSchedule am;

	public LoanCalcServiceSkeleton() {

		am = new AmortizationSchedule();
	}

	private int getPIN() {
		Double randomNo = Math.random() * Math.random() * Math.pow(10, 8);

		while (!(randomNo < 100000000 && randomNo >= 10000000)) {
			randomNo = Math.random() * Math.pow(10, 9);
		}
		return randomNo.intValue();
	}

	static public void main(String args[]) {

		// System.out.println(new LoanCalcServiceSkeleton.getPIN());

	}

	@Override
	public CheckEmailResponse checkEmail(CheckEmail checkEmail) {
		Integer pin = getPIN();
		EmailManager emailMgr = null;
		try {
			emailMgr = new EmailManager("Loan Amortization Calculator: 2-Step Verification",
					checkEmail.getEmailAddress(), "info@LoanCalc.com", String.format("Here is your pin: %d", pin));
			emailMgr.sendEmailWithoutAttachement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// store email/pin combo for final verification
		emailStoreByEmail.put(checkEmail.getEmailAddress(), pin);
		// store pin/email combo for amortization schedule mailing
		emailStoreByPIN.put(pin, checkEmail.getEmailAddress());

		CheckEmailResponse response = new CheckEmailResponse();
		response.setResponse("Check the email address you entered for a verification pin number.");

		return response;
	}

	@Override
	public VerifyEmailPINResponse verifyEmailPIN(VerifyEmailPIN verifyEmailPIN) {
		String email = verifyEmailPIN.getEmailAddress();
		VerifyEmailPINResponse response = new VerifyEmailPINResponse();

		if (emailStoreByEmail.get(email) != null) {
			int pinNoC = new Integer(verifyEmailPIN.getEmailPIN());
			int pinNoS = new Integer(emailStoreByEmail.get(email));
			if (pinNoC == pinNoS) {
				response.setResponse("SUCCESS");
			}
		} else {
			response.setResponse("Check the email and pin submitted.");
		}

		return response;
	}

	@Override
	public DeleteEmailPINResponse deleteEmailPIN(DeleteEmailPIN deleteEmailPIN) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CalcLoanTermResponse calcLoanTerm(CalcLoanTerm calcLoanTerm) {

		double loanPmt = calcLoanTerm.getLoanPmt();
		double loanRate = calcLoanTerm.getLoanRate();
		double loanAmt = calcLoanTerm.getLoanAmt();
		String emailPin = calcLoanTerm.getEmailPIN();

		LoanCalculator lc = new LoanCalculator(loanPmt, loanAmt, loanRate);
		lc.calcLoanTerm();

		String userEmail = "";
		// set amortization schedule if a emailPin is present
		if (emailPin != null && emailPin.length() == 8) {
			int PIN = new Integer(emailPin);
			userEmail = emailStoreByPIN.get(PIN);
			// create amortization schedule
			String amortSched = am.createAmortizationSchedule(loanAmt, loanPmt, loanRate, lc.getTerm(), 0);

			try {
				EmailManager em = new EmailManager("Loan Amortization Schedule", userEmail, "info@LoanCalc.edu",
						amortSched);
				em.sendEmailWithoutAttachement();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		CalcLoanTermResponse calcLoanTermResponseDoc = new CalcLoanTermResponse();

		calcLoanTermResponseDoc.setLoanTerm(lc.getTerm());

		return calcLoanTermResponseDoc;

	}

	@Override
	public CalcLoanRateResponse calcLoanRate(CalcLoanRate calcLoanRate) {
		double loanPmt = calcLoanRate.getLoanPmt();
		double loanAmt = calcLoanRate.getLoanAmt();
		int loanTerm = calcLoanRate.getLoanTerm();
		String emailPin = calcLoanRate.getEmailPIN();

		LoanCalculator lc = new LoanCalculator(loanTerm, loanPmt, loanAmt);

		lc.calcLoanIntRt();

		String userEmail = "";
		// set amortization schedule if a emailPin is present
		if (emailPin != null && emailPin.length() == 8) {
			int PIN = new Integer(emailPin);
			userEmail = emailStoreByPIN.get(PIN);
			// create amortization schedule
			String amortSched = am.createAmortizationSchedule(loanAmt, loanPmt, lc.getRate(), loanTerm, 0);

			try {
				EmailManager em = new EmailManager("Loan Amortization Schedule", userEmail, "info@LoanCalc.edu",
						amortSched);
				em.sendEmailWithoutAttachement();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		CalcLoanRateResponse calcLoanRateResponseDoc = new CalcLoanRateResponse();

		calcLoanRateResponseDoc.setLoanRate(lc.getRate());

		return calcLoanRateResponseDoc;
	}

	@Override
	public CalcLoanPmtResponse calcLoanPmt(CalcLoanPmt calcLoanPmt) {
		double loanAmt = calcLoanPmt.getLoanAmt();
		double loanRate = calcLoanPmt.getLoanRate();
		int loanTerm = calcLoanPmt.getLoanTerm();
		String emailPin = calcLoanPmt.getEmailPIN();

		LoanCalculator lc = new LoanCalculator(loanAmt, loanRate, loanTerm);

		lc.calcLoanPmt();

		String userEmail = "";
		// set amortization schedule if a emailPin is present
		if (emailPin != null && emailPin.length() == 8) {
			int PIN = new Integer(emailPin);
			userEmail = emailStoreByPIN.get(PIN);
			// create amortization schedule
			String amortSched = am.createAmortizationSchedule(loanAmt, lc.getLoanPmt(), loanRate, loanTerm, 0);

			try {
				EmailManager em = new EmailManager("Loan Amortization Schedule", userEmail, "info@LoanCalc.edu",
						amortSched);
				em.sendEmailWithoutAttachement();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		CalcLoanPmtResponse calcLoanPmtResponseDoc = new CalcLoanPmtResponse();

		calcLoanPmtResponseDoc.setLoanPmt(lc.getLoanPmt());

		return calcLoanPmtResponseDoc;
	}

	@Override
	public CalcLoanAmtResponse calcLoanAmt(CalcLoanAmt calcLoanAmt) {
		double loanPmt = calcLoanAmt.getLoanPmt();
		double loanRate = calcLoanAmt.getLoanRate();
		int loanTerm = calcLoanAmt.getLoanTerm();
		String emailPin = calcLoanAmt.getEmailPIN();

		LoanCalculator lc = new LoanCalculator(loanPmt, loanTerm, loanRate);

		lc.calcLoanAmt();

		String userEmail = "";
		// set amortization schedule if a emailPin is present
		if (emailPin != null && emailPin.length() == 8) {
			int PIN = new Integer(emailPin);
			userEmail = emailStoreByPIN.get(PIN);
			// create amortization schedule
			String amortSched = am.createAmortizationSchedule(lc.getLoanAmt(), loanPmt, loanRate, loanTerm, 0);

			try {
				EmailManager em = new EmailManager("Loan Amortization Schedule", userEmail, "info@LoanCalc.edu",
						amortSched);
				em.sendEmailWithoutAttachement();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		CalcLoanAmtResponse calcLoanAmtResponseDoc = new CalcLoanAmtResponse();

		calcLoanAmtResponseDoc.setLoanAmt(lc.getLoanAmt());

		return calcLoanAmtResponseDoc;

	}

}
