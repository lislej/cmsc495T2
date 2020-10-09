
package org.amortizer.loancalcservice;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.amortizer.loancalcservice.email.EmailManager;
import org.amortizer.loancalcservice.loancalcs.LoanCalculator;

/**
 * LoanCalcServiceSkeleton java skeleton for the axisService
 */

public class LoanCalcServiceSkeletonImpl implements LoanCalcServiceSkeletonInterface {

	static private ConcurrentHashMap<String, Integer> emailStore = new ConcurrentHashMap<String, Integer>();
	


	private int getPIN() {
		Double randomNo = Math.random() * Math.random() * Math.pow(10, 8);

		while (!(randomNo < 100000000 && randomNo >= 10000000)) {
			randomNo = Math.random() * Math.pow(10, 9);
		}
		return randomNo.intValue();
	}

	static public void main(String args[]) {

		//System.out.println(new LoanCalcServiceSkeleton.getPIN());

	}

	
	@Override
	public CheckEmailResponse checkEmail(CheckEmail checkEmail) {
		Integer pin = getPIN();
		EmailManager emailMgr = null;
		try {
			emailMgr = new EmailManager("Loan Amortization Calculator: 2-Step Verification", checkEmail.getEmailAddress(),
					"info@LoanCalc.com", String.format("Here is your pin: %d", pin));
			emailMgr.sendEmailWithoutAttachement();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// store email/pin combo for final verification
		emailStore.put(checkEmail.getEmailAddress(), pin);
		
		CheckEmailResponse response = new CheckEmailResponse();
		response.setResponse("Check the email address you entered for a verification pin number.");

		return response;
	}


	@Override
	public VerifyEmailPINResponse verifyEmailPIN(VerifyEmailPIN verifyEmailPIN) {
		String email = verifyEmailPIN.getEmailAddress();
		VerifyEmailPINResponse response = new VerifyEmailPINResponse();
		
		if (emailStore.get(email) != null) {
			int pinNoC = new Integer(verifyEmailPIN.getEmailPIN());
			int pinNoS = new Integer(emailStore.get(email));
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

		LoanCalculator lc = new LoanCalculator(loanPmt, loanAmt, loanRate);

		lc.calcLoanTerm();

		CalcLoanTermResponse calcLoanTermResponseDoc = new CalcLoanTermResponse();

		calcLoanTermResponseDoc.setLoanTerm(lc.getTerm());

		return calcLoanTermResponseDoc;
	
	}

	@Override
	public CalcLoanRateResponse calcLoanRate(CalcLoanRate calcLoanRate) {
		double loanPmt = calcLoanRate.getLoanPmt();
		double loanAmt = calcLoanRate.getLoanAmt();
		int loanTerm = calcLoanRate.getLoanTerm();

		LoanCalculator lc = new LoanCalculator(loanTerm, loanPmt, loanAmt);

		lc.calcLoanIntRt();

		CalcLoanRateResponse calcLoanRateResponseDoc = new CalcLoanRateResponse();

		calcLoanRateResponseDoc.setLoanRate(lc.getRate());

		return calcLoanRateResponseDoc;
	}

	
	@Override
	public CalcLoanPmtResponse calcLoanPmt(CalcLoanPmt calcLoanPmt) {
		double loanAmt = calcLoanPmt.getLoanAmt();
		double loanRate = calcLoanPmt.getLoanRate();
		int loanTerm = calcLoanPmt.getLoanTerm();

		LoanCalculator lc = new LoanCalculator(loanAmt, loanRate, loanTerm);

		lc.calcLoanPmt();

		CalcLoanPmtResponse calcLoanPmtResponseDoc = new CalcLoanPmtResponse();

		calcLoanPmtResponseDoc.setLoanPmt(lc.getLoanPmt());

		return calcLoanPmtResponseDoc;
	}

	@Override
	public CalcLoanAmtResponse calcLoanAmt(CalcLoanAmt calcLoanAmt) {
		double loanPmt = calcLoanAmt.getLoanPmt();
		double loanRate = calcLoanAmt.getLoanRate();
		int loanTerm = calcLoanAmt.getLoanTerm();

		LoanCalculator lc = new LoanCalculator(loanPmt, loanTerm, loanRate);

		lc.calcLoanAmt();

		CalcLoanAmtResponse calcLoanAmtResponseDoc = new CalcLoanAmtResponse();

		calcLoanAmtResponseDoc.setLoanAmt(lc.getLoanAmt());

		return calcLoanAmtResponseDoc;

	}


}
