package org.amortizer.loancalcservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import org.amortizer.loancalcservice.client.LoanCalcModel;
import org.amortizer.loancalcservice.client.LoanCalcView;
import org.apache.axis2.AxisFault;
import org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanAmt;
import org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanPmt;
import org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanRate;
import org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanTerm;
import org.amortizer.loancalcservice.LoanCalcServiceStub.CheckEmail;
import org.amortizer.loancalcservice.LoanCalcServiceStub.CheckEmailResponse;
import org.amortizer.loancalcservice.LoanCalcServiceStub.VerifyEmailPIN;
import org.amortizer.loancalcservice.LoanCalcServiceStub.VerifyEmailPINResponse;

public class LoanCalcServiceHandler {

	private URL serviceUrl = null;
	private LoanCalcServiceStub lcsStub;

	public LoanCalcServiceHandler(String url) throws MalformedURLException {

		this.serviceUrl = new URL(url);

		try {
			lcsStub = new LoanCalcServiceStub(this.serviceUrl.toString());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double calcLoanAmt(LoanCalcModel lcModel) throws Exception {

		CalcLoanAmt calcLoanAmtReq = new CalcLoanAmt();

		calcLoanAmtReq.setLoanPmt(lcModel.getLoanPmt());
		calcLoanAmtReq.setLoanRate(lcModel.getIntRate());
		calcLoanAmtReq.setLoanTerm(lcModel.getTerm());
		calcLoanAmtReq.setEmailPIN(lcModel.getEmailPin());

		org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanAmtResponse clAmtRes = null;
		try {

			clAmtRes = lcsStub.calcLoanAmt(calcLoanAmtReq);

		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "LoanCalc Service Unavailable");
			throw new Exception("LoanCalc Service Unavailable");
		}

		return clAmtRes != null ? clAmtRes.getLoanAmt() : 0.0;

	}

	public double calcLoanPmt(LoanCalcModel lcModel) throws Exception {

		CalcLoanPmt calcLoanPmtReq = new CalcLoanPmt();

		calcLoanPmtReq.setLoanAmt(lcModel.getLoanAmt());
		calcLoanPmtReq.setLoanRate(lcModel.getIntRate());
		calcLoanPmtReq.setLoanTerm(lcModel.getTerm());
		calcLoanPmtReq.setEmailPIN(lcModel.getEmailPin());

		org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanPmtResponse clPmtRes = null;
		try {

			clPmtRes = lcsStub.calcLoanPmt(calcLoanPmtReq);

		} catch (RemoteException e) {

			JOptionPane.showMessageDialog(null, "LoanCalc Service Unavailable");
			throw new Exception("LoanCalc Service Unavailable");
		}

		return clPmtRes != null ? clPmtRes.getLoanPmt() : 0.0;

	}

	public double calcLoanRate(LoanCalcModel lcModel) throws Exception {
		
		CalcLoanRate calcLoanRateReq = new CalcLoanRate();

		calcLoanRateReq.setLoanAmt(lcModel.getLoanAmt());
		calcLoanRateReq.setLoanPmt(lcModel.getLoanPmt());
		calcLoanRateReq.setLoanTerm(lcModel.getTerm());
		calcLoanRateReq.setEmailPIN(lcModel.getEmailPin());

		org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanRateResponse clRateRes = null;
		try {
			clRateRes = lcsStub.calcLoanRate(calcLoanRateReq);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "LoanCalc Service Unavailable");
			throw new Exception("LoanCalc Service Unavailable");
		}
		
		return clRateRes != null ? clRateRes.getLoanRate() : 0.0;

	}

	public int calcLoanTerm(LoanCalcModel lcModel) throws Exception {

		CalcLoanTerm calcLoanTermReq = new CalcLoanTerm();

		calcLoanTermReq.setLoanAmt(lcModel.getLoanAmt());
		calcLoanTermReq.setLoanPmt(lcModel.getLoanPmt());
		calcLoanTermReq.setLoanRate(lcModel.getIntRate());
		calcLoanTermReq.setEmailPIN(lcModel.getEmailPin());

		org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanTermResponse clTermRes = null;
		try {
			clTermRes = lcsStub.calcLoanTerm(calcLoanTermReq);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "LoanCalc Service Unavailable");
			throw new Exception("LoanCalc Service Unavailable");
		}

		return clTermRes != null ? clTermRes.getLoanTerm() : 0;

	}

	public String chkEmail(String email) throws Exception {

		CheckEmailResponse chkEmailRsp = new CheckEmailResponse();
		CheckEmail chkEmail = new CheckEmail();
		chkEmail.setEmailAddress(email);

		try {
			chkEmailRsp = lcsStub.checkEmail(chkEmail);
		} catch (RemoteException e) {
			throw new Exception("LoanCalc Service Unavailable");
		}

		return chkEmailRsp.getResponse();

	}

	public String verfyPIN(String pin, String email) throws Exception {

		VerifyEmailPINResponse verfiyPINRsp = new VerifyEmailPINResponse();
		VerifyEmailPIN verifyPIN = new VerifyEmailPIN();
		verifyPIN.setEmailPIN(pin);
		verifyPIN.setEmailAddress(email);

		try {
			verfiyPINRsp = lcsStub.verifyEmailPIN(verifyPIN);
		} catch (RemoteException e) {
			throw new Exception("LoanCalc Service Unavailable");
		}

		return verfiyPINRsp.getResponse();

	}

	static public void main(String[] args) throws MalformedURLException {
		String CALC_SERVICE_URL = "https://loancalcservice.herokuapp.com/services/LoanCalcService";
		LoanCalcServiceHandler lcs = new LoanCalcServiceHandler(CALC_SERVICE_URL);

	}

}
