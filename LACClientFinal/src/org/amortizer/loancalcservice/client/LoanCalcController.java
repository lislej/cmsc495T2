package org.amortizer.loancalcservice.client;

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
	
		timer.schedule(pinger, 0, SERVICE_IS_ALIVE_INTERVAL);  //immediate ping followed by a ping every 15 minutes
		
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

		boolean calcErrrorNoAmortSched = false;

		Object object = e.getSource();

		// trap only for button presses
		if (object instanceof JButton) {

			// calculate button press
			if (((JButton) object).getText().equals(LoanCalcView.getCalcpay())) {

				// validate data
				if (this.validateData()) {

					// loan amount
					if (lcView.getLoanAmtRBtn().isSelected()) {

						lcView.getLoanCalcModel().setLoanAmt(lcsHndlr.calcLoanAmt(lcModel));

						lcView.getLoanAmtJTF().setText(lcView.getDf().format(lcView.getLoanCalcModel().getLoanAmt()));

						// interest rate
					} else if (lcView.getLoanRateRBtn().isSelected()) {

						lcView.getLoanCalcModel().setIntRate(lcsHndlr.calcLoanRate(lcModel));

						lcView.getLoanRateJTF().setText(lcView.getDf().format(lcView.getLoanCalcModel().getIntRate()));

						if (lcView.getLoanCalcModel().getIntRate() > 0) {

							lcView.getLoanRateJTF().setText(String.format("%.2f", lcView.getLoanCalcModel().getIntRate()));

							// set calc error so amortization schedule not produced
						} else {
							calcErrrorNoAmortSched = true;
						}

						// loan term
					} else if (lcView.getLoanTermRBtn().isSelected()) {

						lcView.getLoanCalcModel().setTerm(lcsHndlr.calcLoanTerm(lcModel));

						lcView.getLoanTermJTF().setText(lcView.getDf().format(lcView.getLoanCalcModel().getTerm()));

						// loan payment
					} else if (lcView.getLoanPmtRBtn().isSelected()) {

						lcView.getLoanCalcModel().setLoanPmt(lcsHndlr.calcLoanPmt(lcModel));

						lcView.getLoanPmtJTF().setText(lcView.getDf().format(lcView.getLoanCalcModel().getLoanPmt()));

					}

					// if checkbox is selected output amortization schedule
					if (lcView.getAmortSchedChkBox().isSelected() && !calcErrrorNoAmortSched) {
						createAmortizationSchedule();
					}

					calcErrrorNoAmortSched = false;

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
			} else if ( ( (JCheckBox) object).getText().equals("Email Amortization Schedule") )  { 
				
				if (lcView.getEmailAmortSched().isSelected()) {

					emailValidator = new EmailValidator(lcView, getLcsHndlr());
					
					emailValidator.setVisible(true);
					
				} else {
					
					this.lcView.setUserEmail("");
					
				}
			}
		}
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

		if (validateFieldData(LoanVariableComponent.LOANAMT) && !lcView.getLoanAmtRBtn().isSelected()) {

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
		double rate    = lcView.getLoanCalcModel().getIntRate();
	    int    term    = lcView.getLoanCalcModel().getTerm();
	    double totalInt = 0.0;
	    
	    
		double monthlyInterest = 0;
		double principal = 0;

		
		StringBuffer amortScheduleBody = new StringBuffer();
		
		amortScheduleBody.append("Month   Payment   Principal    Interest       Balance");
		amortScheduleBody.append("\n");

		for (int i = 0; i < lcView.getLoanCalcModel().getTerm(); i++) {

			monthlyInterest = calcMonthlyInterest(balance, lcView.getLoanCalcModel().getIntRate());
			totalInt+=monthlyInterest;
			principal = calculatePrincipal(payment, monthlyInterest);
			balance = calculateBalance(balance, lcView.getLoanCalcModel().getDownPmt(), principal);

			amortScheduleBody.append(String.format(" %3d    %7.2f     %7.2f     %7.2f     %8.2f", i + 1, payment, principal, monthlyInterest, balance > 0 ? balance:0.00));
			amortScheduleBody.append("\n");

		}

		//create header
		StringBuffer amortScheduleHdr = new StringBuffer();

		amortScheduleHdr.append("-----------------Loan Amortization Schedule-----------------");
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append("\n");

		amortScheduleHdr.append(String.format("Loan Amount ......: %10.2f", lcView.getLoanCalcModel().getLoanAmt()));
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append(String.format("Loan Payment .....: %10.2f", lcView.getLoanCalcModel().getLoanPmt()));
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append(String.format("Interest Rate ....: %10.2f", lcView.getLoanCalcModel().getIntRate()));
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append(String.format("Loan Term ........: %10d", lcView.getLoanCalcModel().getTerm()));
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append(String.format("Interest Paid ....: %10.2f", totalInt));
		
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append("\n");
		amortScheduleHdr.append("\n");

		
		//assemble final document
		StringBuffer amortSchedule = new StringBuffer();
		amortSchedule.append(amortScheduleHdr.toString());
		amortSchedule.append(amortScheduleBody.toString());
		
		
		Font font = new Font("Courier New", Font.PLAIN, 11);
		lcView.getEditorPane().setFont(font);
		lcView.getEditorPane().setText(amortSchedule.toString());

	}

	private String leftPad(String value, int len, String pad) {
		StringBuilder newStr = new StringBuilder();
		newStr.append(value);
		if (value!=null && value.length() < len) {
			for (int i=0; i<len-value.length(); i++) {
		       newStr.insert(0, pad);
			}
		}
		
		return newStr.toString();
	}
	
	private double calcMonthlyInterest(double balance, double intRate) {

		return balance * (intRate / (12 * 100));
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
		public void pingService() throws RemoteException, VersionExceptionException {

			//ping the service synchronously
			GetVersionResponse verRsp = vsHndlr.pingService();

			//set the calculate button accordingly
			lcView.getCalcPmtBtn().setEnabled(verRsp.get_return().contains("Axis2 version is".subSequence(0, 16)));
			
		}

		@Override
		public void run() {
	
			try {
				pingService();
			} catch (RemoteException | VersionExceptionException e) {

			}
			
		}
		
	}

}
