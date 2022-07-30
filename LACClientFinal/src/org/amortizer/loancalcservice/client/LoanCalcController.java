package org.amortizer.loancalcservice.client;

import java.lang.Math;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.amortizer.loancalcservice.LoanCalcServiceHandler;
import org.amortizer.loancalcservice.client.LoanCalcView.LoanVariableComponent;
import org.amortizer.loancalcservice.utils.EmailValidator;
import org.apache.axis2.AxisFault;
import org.apache.axis2.version.VersionExceptionException;
import org.apache.axis2.version.VersionServiceHandler;
import org.apache.axis2.version.VersionStub.GetVersionResponse;

public class LoanCalcController {

	private final long SERVICE_IS_ALIVE_INTERVAL = 15000;
	private final String VERSION_SERVICE_URL = "https://loancalcservice.herokuapp.com/services/Version";
	private final String CALC_SERVICE_URL = "https://loancalcservice.herokuapp.com/services/LoanCalcService";

//	private final String VERSION_SERVICE_URL = "http://localhost:8080/LoanCalcService/services/Version";
//	private final String CALC_SERVICE_URL = "http://localhost:8080/LoanCalcService/services/LoanCalcService";
//	private final String VERSION_SERVICE_URL = "http://localhost:8080/LACServiceFinal/services/Version";
//	private final String CALC_SERVICE_URL = "http://localhost:8080/LACServiceFinal/services/LoanCalcService";

	private LoanCalcView lcView;
	private LoanCalcModel lcModel;
	private LoanCalcServiceHandler lcsHndlr;
	private VersionServiceHandler vsHndlr;
	private Timer timer;
	private ServicePinger pinger;
	private EmailValidator emailValidator;

	public LoanCalcController() throws MalformedURLException, AxisFault {

		initServiceStubs();
		lcModel = new LoanCalcModel();
		lcView = new LoanCalcView(this, lcModel);
		pinger = new ServicePinger(vsHndlr, lcView);

		timer = new Timer("service-alive");
		startServiceIsAlive();
		showCalculator();
	}

	public void showCalculator() {

		lcView.showCalculator(true);

	}

	private void startServiceIsAlive() {

		timer.schedule(pinger, 0, SERVICE_IS_ALIVE_INTERVAL); // immediate ping followed by a ping every 15 seconds

	}

	private void initServiceStubs() throws AxisFault, MalformedURLException {

		lcsHndlr = new LoanCalcServiceHandler(CALC_SERVICE_URL);
		vsHndlr = new VersionServiceHandler(VERSION_SERVICE_URL);

	}

	public LoanCalcView getLcView() {
		return lcView;
	}

	public LoanCalcModel getLcModel() {
		return lcModel;
	}

	public LoanCalcServiceHandler getLcsHndlr() {
		return lcsHndlr;
	}

	protected void guiEventController(ActionEvent e) {

		boolean calcErrorNoAmortSched = false;

		Object object = e.getSource();

		// trap only for button presses
		if (object instanceof JButton) {

			// calculate button press
			if (((JButton) object).getText().equals(LoanCalcView.getCalcpay())) {

				// validate data
				if (this.validateData()) {

					// loan amount
					if (lcView.getLoanAmtRBtn().isSelected()) {

						try {
							lcView.getLoanCalcModel().setLoanAmt(lcsHndlr.calcLoanAmt(lcModel));
							lcView.getLoanAmtJTF()
									.setText(lcView.getDf().format(lcView.getLoanCalcModel().getLoanAmt()));
						} catch (Exception e1) {

							calcErrorNoAmortSched = true;

						}

						// interest rate
					} else if (lcView.getLoanRateRBtn().isSelected()) {

						try {
							double rate = lcsHndlr.calcLoanRate(lcModel);
							lcView.getLoanCalcModel().setIntRate(new Double(String.format("%.3f", rate)));

							if (lcView.getLoanCalcModel().getIntRate() >= 0) {
								lcView.getLoanRateJTF()
										.setText(lcView.getDf3().format(lcView.getLoanCalcModel().getIntRate()));

								// set calc error so amortization schedule not produced
							} else {
								calcErrorNoAmortSched = true;
							}

						} catch (Exception e1) {
							calcErrorNoAmortSched = true;
						}
						// loan term
					} else if (lcView.getLoanTermRBtn().isSelected()) {

						try {
							lcView.getLoanCalcModel().setTerm(lcsHndlr.calcLoanTerm(lcModel));
							lcView.getLoanTermJTF().setText(lcView.getDf().format(lcView.getLoanCalcModel().getTerm()));
						} catch (Exception e1) {
							calcErrorNoAmortSched = true;
						}
						// loan payment
					} else if (lcView.getLoanPmtRBtn().isSelected()) {

						try {
							lcView.getLoanCalcModel().setLoanPmt(lcsHndlr.calcLoanPmt(lcModel));
							lcView.getLoanPmtJTF()
									.setText(lcView.getDf().format(lcView.getLoanCalcModel().getLoanPmt()));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					// if checkbox is selected output amortization schedule
					if (lcView.getAmortSchedChkBox().isSelected() && !calcErrorNoAmortSched) {
						createAmortizationSchedule();
					}

					calcErrorNoAmortSched = false;

				}
				// clear button press
			} else if (((JButton) object).getText().equals(LoanCalcView.getCalcclr())) {

				lcView.getLoanCalcModel().resetAmounts();
				lcView.clearView();

				// set focus to loan amount
				lcView.getLoanAmtJTF().requestFocus();

			}

		} else if (object instanceof JRadioButton) {

			if (lcView.getLoanAmtRBtn().isSelected()) {

				lcView.getLoanAmtJTF().setText("");

				lcView.resetAllTextFields(LoanCalcView.LOANAMT);

				lcView.getLoanRateJTF().requestFocus();

			} else if (lcView.getLoanRateRBtn().isSelected()) {

				lcView.getLoanRateJTF().setText("");

				lcView.resetAllTextFields(LoanCalcView.INTRATE);

				lcView.getLoanAmtJTF().requestFocus();

			} else if (lcView.getLoanTermRBtn().isSelected()) {

				lcView.getLoanTermJTF().setText("");

				lcView.resetAllTextFields(LoanCalcView.TERM);

				lcView.getLoanAmtJTF().requestFocus();

			} else if (lcView.getLoanPmtRBtn().isSelected()) {

				lcView.getLoanPmtJTF().setText("");

				lcView.resetAllTextFields(LoanCalcView.PAYMENT);

				lcView.getLoanAmtJTF().requestFocus();

			}
		} else if (object instanceof JCheckBox) {

			if (((JCheckBox) object).getText().equals("Amortization Schedule")) {
				if (lcView.getAmortSchedChkBox().isSelected()) {

				} else {
					lcView.getEditorPane().setText("");
				}
			} else if (((JCheckBox) object).getText().equals("Email Amortization Schedule")) {

				if (lcView.getEmailAmortSched().isSelected()) {

					emailValidator = new EmailValidator(lcView, getLcsHndlr());

					emailValidator.setVisible(true);

				} else {

					this.lcView.setUserEmail("");
					this.lcView.getLoanCalcModel().setEmailPin("");

				}
			}
		}
	}

	private boolean isUnReasonableInputs(LoanCalcView lcView) {

		int lnTerm = new Integer(lcView.getLoanTermJTF().getText());
		double lnPmt = new Double(lcView.getLoanPmtJTF().getText());
		return (lnTerm * lnPmt < lcModel.getLoanAmt());

	}

	// validate data
	private boolean validateFieldData(LoanVariableComponent loanPmtComp) {

		boolean errorOccurred = true;

		try {

			switch (loanPmtComp) {

			case LOANAMT:

				lcView.getLoanCalcModel().setLoanAmt(new Double(lcView.getLoanAmtJTF().getText()).doubleValue());
				if (lcView.getLoanCalcModel().getLoanAmt() > 0) {
					errorOccurred = false;
				}
				break;

			case INTRATE:

				lcView.getLoanCalcModel().setIntRate(new Double(lcView.getLoanRateJTF().getText()).doubleValue());

				if (lcView.getLoanCalcModel().getIntRate() > 0) {

					errorOccurred = false;
				}
				break;

			case TERM:

				lcView.getLoanCalcModel().setTerm(new Integer(lcView.getLoanTermJTF().getText()).intValue());

				if (lcView.getLoanCalcModel().getTerm() > 0) {
					errorOccurred = false;
				}
				break;

			case PAYMENT:

				lcView.getLoanCalcModel().setLoanPmt(new Double(lcView.getLoanPmtJTF().getText()).doubleValue());

				if (lcView.getLoanCalcModel().getLoanPmt() > 0) {
					errorOccurred = false;
				}
				break;

			default:
				break;
			}
		} catch (NumberFormatException nfe) {

			// nothing to do here

		}

		return errorOccurred;
	}

	// give field level error messages if validation fails
	private boolean validateData() {

		boolean retCode = false;

		if (lcView.getLoanRateRBtn().isSelected() && this.isUnReasonableInputs(lcView)) {

			JOptionPane.showMessageDialog(lcView.getCalcFrame(), LoanCalcView.getRsnbleInputs());

			lcView.getLoanTermJTF().requestFocus();
		} else if (validateFieldData(LoanVariableComponent.LOANAMT) && !lcView.getLoanAmtRBtn().isSelected()) {

			JOptionPane.showMessageDialog(lcView.getCalcFrame(), LoanCalcView.getLoanamterror());

			lcView.getLoanAmtJTF().requestFocus();

		} else if (validateFieldData(LoanVariableComponent.INTRATE) && !lcView.getLoanRateRBtn().isSelected()) {

			JOptionPane.showMessageDialog(lcView.getCalcFrame(), LoanCalcView.getIntrateerror());

			lcView.getLoanRateJTF().requestFocus();

		} else if (validateFieldData(LoanVariableComponent.TERM) && !lcView.getLoanTermRBtn().isSelected()) {

			JOptionPane.showMessageDialog(lcView.getCalcFrame(), LoanCalcView.getTermerror());

			lcView.getLoanTermJTF().requestFocus();

		} else if (validateFieldData(LoanVariableComponent.PAYMENT) && !lcView.getLoanPmtRBtn().isSelected()) {

			JOptionPane.showMessageDialog(lcView.getCalcFrame(), LoanCalcView.getPmterror());

			lcView.getLoanPmtJTF().requestFocus();

		} else {

			lcView.getErrorMsgLabel().setText("");

			retCode = true;
		}

		return retCode;
	}

	private void createAmortizationSchedule() {

		double balance = lcView.getLoanCalcModel().getLoanAmt();
		double payment = lcView.getLoanCalcModel().getLoanPmt();
		double rate = lcView.getLoanCalcModel().getIntRate();
		int term = lcView.getLoanCalcModel().getTerm();
		double totalInt = 0.0;

		double monthlyInterest = 0;
		double principal = 0;

		StringBuffer amortScheduleBody = new StringBuffer();

		amortScheduleBody.append("Month    Payment   Principal    Interest       Balance");
		amortScheduleBody.append("\n");

		for (int i = 0; i < lcView.getLoanCalcModel().getTerm(); i++) {

			monthlyInterest = round(calcMonthlyInterest(balance, rate), 3);
			totalInt += monthlyInterest;
			principal = round(calculatePrincipal(payment, monthlyInterest),3);
			balance = round(calculateBalance(balance, lcView.getLoanCalcModel().getDownPmt(), principal),3);

			amortScheduleBody.append(String.format(" %4d    %7.2f     %7.2f     %7.2f     %9.2f", i + 1, payment,
					principal, monthlyInterest, balance > 0 ? balance : 0.00));
			amortScheduleBody.append("\n");

		}

		// create header
		StringBuffer amortScheduleHdr = new StringBuffer();

		amortScheduleHdr.append("-----------------Loan Amortization Schedule-----------------");
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append("\n");

		amortScheduleHdr.append(String.format("Loan Amount ......: %10.2f", lcView.getLoanCalcModel().getLoanAmt()));
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append(String.format("Loan Payment .....: %10.2f", lcView.getLoanCalcModel().getLoanPmt()));
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append(String.format("Interest Rate ....: %10.3f", lcView.getLoanCalcModel().getIntRate()));
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append(String.format("Loan Term ........: %10d", lcView.getLoanCalcModel().getTerm()));
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append(String.format("Interest Paid ....: %10.2f", totalInt));

		amortScheduleHdr.append("\n");
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append("\n");

		// assemble final document
		StringBuffer amortSchedule = new StringBuffer();
		amortSchedule.append(amortScheduleHdr.toString());
		amortSchedule.append(amortScheduleBody.toString());

		Font font = new Font("Courier New", Font.PLAIN, 11);
		lcView.getEditorPane().setFont(font);
		lcView.getEditorPane().setText(amortSchedule.toString());

	}

	public float round(float data, int decplace)	{

		Math.pow(10, decplace);
		
		int ibase = (int) (data * Math.pow(10,decplace-1));
	    int diff = (int) (data * Math.pow(10,decplace) - (ibase * 10));

	    double tbase = ibase/Math.pow(10,decplace-1);

	    return (float)(diff < 5 ? tbase : tbase + 1/Math.pow(10,decplace-1));
	}
	
	public double round(double data, int decplace)	{

		Math.pow(10, decplace);
		
		int ibase = (int) (data * Math.pow(10,decplace-1));
	    int diff = (int) (data * Math.pow(10,decplace) - (ibase * 10));

	    double tbase = ibase/Math.pow(10,decplace-1);

	    return diff < 5 ? tbase : tbase + 1/Math.pow(10,decplace-1);
	}
	
	public BigDecimal round(BigDecimal data, int decplace)	{

		Math.pow(10, decplace);
		
		int ibase = (int) (data.doubleValue() * Math.pow(10,decplace-1));
	    int diff = (int) (data.doubleValue() * Math.pow(10,decplace) - (ibase * 10));

	    double tbase = ibase/Math.pow(10,decplace-1);

	    return new BigDecimal(diff < 5 ? tbase : tbase + 1/Math.pow(10,decplace-1));
	}


	public <T> void genericsMethod(T data) {
	    System.out.println("Generics Method:");
	    System.out.println("Data Passed: " + data);
	  }

//	private void createAmortizationSchedule() {
//
//		BigDecimal balance = new BigDecimal(lcView.getLoanCalcModel().getLoanAmt());
//		BigDecimal payment = new BigDecimal(lcView.getLoanCalcModel().getLoanPmt());
//		BigDecimal rate = new BigDecimal(lcView.getLoanCalcModel().getIntRate());
//		BigDecimal term = new BigDecimal(lcView.getLoanCalcModel().getTerm());
//		BigDecimal totalInt = new BigDecimal(0.0);
//
//		BigDecimal monthlyInterest = new BigDecimal(0.0);
//		BigDecimal principal = new BigDecimal(0.0);
//		
//		BigDecimal downPymnt = new BigDecimal(lcView.getLoanCalcModel().getDownPmt());
//		BigDecimal months = new BigDecimal(12);
//		BigDecimal divisor = new BigDecimal(100);
//	
//
//		StringBuffer amortScheduleBody = new StringBuffer();
//
//		amortScheduleBody.append("Month    Payment   Principal    Interest       Balance");
//		amortScheduleBody.append("\n");
//
//		for (int i = 0; i < term.toBigInteger().intValue(); i++) {
//				
//			monthlyInterest = balance.multiply(rate.divide(months).divide(divisor));
//			monthlyInterest = round(monthlyInterest,3);
//			totalInt = totalInt.add(monthlyInterest);
//			principal = payment.subtract(monthlyInterest);
//			balance = (balance.subtract(downPymnt)).subtract(principal);
//			
//			amortScheduleBody.append(String.format(" %4d    %7.3f     %7.3f     %7.3f     %9.3f", i + 1, payment,
//					principal.doubleValue(), monthlyInterest.doubleValue(), balance.doubleValue() > 0 ? balance.doubleValue() : 0.00));
//			amortScheduleBody.append("\n");
//
//		}
//
//		// create header
//		StringBuffer amortScheduleHdr = new StringBuffer();
//
//		amortScheduleHdr.append("-----------------Loan Amortization Schedule-----------------");
//		amortScheduleHdr.append("\n");
//		amortScheduleHdr.append("\n");
//
//		amortScheduleHdr.append(String.format("Loan Amount ......: %10.3f", lcView.getLoanCalcModel().getLoanAmt()));
//		amortScheduleHdr.append("\n");
//		amortScheduleHdr.append(String.format("Loan Payment .....: %10.3f", lcView.getLoanCalcModel().getLoanPmt()));
//		amortScheduleHdr.append("\n");
//		amortScheduleHdr.append(String.format("Interest Rate ....: %10.3f", lcView.getLoanCalcModel().getIntRate()));
//		amortScheduleHdr.append("\n");
//		amortScheduleHdr.append(String.format("Loan Term ........: %10d", lcView.getLoanCalcModel().getTerm()));
//		amortScheduleHdr.append("\n");
//		amortScheduleHdr.append(String.format("Interest Paid ....: %10.3f", totalInt.doubleValue()));
//
//		amortScheduleHdr.append("\n");
//		amortScheduleHdr.append("\n");
//		amortScheduleHdr.append("\n");
//
//		// assemble final document
//		StringBuffer amortSchedule = new StringBuffer();
//		amortSchedule.append(amortScheduleHdr.toString());
//		amortSchedule.append(amortScheduleBody.toString());
//
//		Font font = new Font("Courier New", Font.PLAIN, 11);
//		lcView.getEditorPane().setFont(font);
//		lcView.getEditorPane().setText(amortSchedule.toString());
//
//	}

	
	private String leftPad(String value, int len, String pad) {
		StringBuilder newStr = new StringBuilder();
		newStr.append(value);
		if (value != null && value.length() < len) {
			for (int i = 0; i < len - value.length(); i++) {
				newStr.insert(0, pad);
			}
		}

		return newStr.toString();
	}

	private BigDecimal calcMonthlyInterestBD(BigDecimal balance, BigDecimal intRate) {
		
		BigDecimal months = new BigDecimal(12);
		BigDecimal divisor = new BigDecimal(100);
		
		return balance.multiply(intRate.divide(months, RoundingMode.HALF_UP).multiply(divisor));
	}

	private void calculatePrincipalBD(BigDecimal loanPmt, BigDecimal monthlyInterest, BigDecimal principal) {
		
		 
	}

	private void calculateBalanceBD(BigDecimal balance, BigDecimal downPmt, BigDecimal principal) {
		
		balance = (balance.subtract(downPmt)).subtract(principal);
		
	}
	
	private double calcMonthlyInterest(double balance, double intRate) {
		double monthlyInt = intRate / (12 * 100);
		return balance * monthlyInt ;
	}
	
	private double calculatePrincipal(double loanPmt, double monthlyInterest) {
		return loanPmt - monthlyInterest;
	}

	private double calculateBalance(double loanAmt, double downPmt, double principal) {
		return (loanAmt - downPmt - principal);
	}

	private interface Ping {
		void pingService() throws RemoteException, VersionExceptionException;
	}

	class ServicePinger extends TimerTask implements Ping {

		VersionServiceHandler vsHndlr;
		LoanCalcView lcView;

		ServicePinger(VersionServiceHandler vsHndlr, LoanCalcView lcView) {
			super();
			this.vsHndlr = vsHndlr;
			this.lcView = lcView;
		}

		@Override
		public void pingService() {

			// ping the service synchronously
			GetVersionResponse verRsp = null;
			try {
				verRsp = vsHndlr.pingService();
				lcView.getCalcPmtBtn().setEnabled(verRsp.get_return().contains("Axis2 version is".subSequence(0, 16)));
			} catch (RemoteException | VersionExceptionException e) {
				// set the calculate button accordingly
				lcView.getCalcPmtBtn().setEnabled(false);
			}
		}

		@Override
		public void run() {

			pingService();

		}

	}

}
