package org.amortizer.loancalcservice.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoanCalcView implements ActionListener, Closeable {

	// enum for loan payment component validation
	public enum LoanVariableComponent {
		LOANAMT, INTRATE, TERM, PAYMENT
	};

	private LoanCalcController lcCntrlr = null;
	private LoanCalcModel lcModel = null;

	// currency format
	DecimalFormat df = new DecimalFormat("0.##");
	DecimalFormat df3 = new DecimalFormat("0.###");

	// button text
	private static final String CALCPAY = "Calculate";
	private static final String CALCCLR = "   Clear   ";

	// label text
	public static final String LOANAMT = "Loan Amount ";
	public static final String INTRATE = "Interest Rate";
	public static final String TERM = "Loan Term";
	public static final String PAYMENT = "Monthly Payment";

	// error messages
	private static final String LOANAMTERROR = "Loan Amount must be > 0";
	private static final String INTRATEERROR = "Interest Rate must be > 0";
	private static final String TERMERROR = "Number of payments must be > 0";
	private static final String PMTERROR = "Monthly Payment must be > 0";
	private static final String RSNBLEINPUTS = "Make sure loan payment and term are large enough to cover the loan amount.";

	// windows and layout constants
	private static final int CALC_WIDTH = 500;
	private static final int CALC_HEIGHT = 500;
	private static final int FLOW_LAYOUT_GAP = 15;

	// custom tab order class
	MyOwnFocusTraversalPolicy newPolicy = null;

	// frame
	private JFrame calcFrame = new JFrame("Loan Amortization Calculator");

	// panels
	private JPanel calcPanel = new JPanel();
	private JPanel calcFieldsAndLabelsPanel = new JPanel(new GridLayout(5, 2));
	private JPanel calcErrorMsgPanel = new JPanel();
	private JPanel calcButtonPanel = new JPanel();
	private JPanel calcRadioButtonPanel = new JPanel();
	private JPanel calcChkBoxPanel = new JPanel(new GridLayout(1, 2));
	private JPanel calcEditorPanel = new JPanel();

	// JEditor Pane for amortization schedule
	JEditorPane editorPane = new JEditorPane();

	// fields
	private JTextField loanAmtJTF = new JTextField();
	private JTextField loanPmtJTF = new JTextField();
	private JTextField loanTermJTF = new JTextField();
	private JTextField loanRateJTF = new JTextField();
	private JTextField userEmail = new JTextField();
	// radio buttons
	private JRadioButton loanAmtRBtn = new JRadioButton(LOANAMT);
	private JRadioButton loanPmtRBtn = new JRadioButton(PAYMENT, true);
	private JRadioButton loanTermRBtn = new JRadioButton(TERM);
	private JRadioButton loanRateRBtn = new JRadioButton(INTRATE);

	// radio button group
	private ButtonGroup btnGrp = new ButtonGroup();

	// checkboxes
	private JCheckBox emailAmortSched = new JCheckBox("Email Amortization Schedule");
	private JCheckBox amortSchedChkBox = new JCheckBox("Amortization Schedule", true);

	// labels
	private JLabel errorMsgLabel = new JLabel();

	// buttons
	private JButton calcPmtBtn = new JButton(LoanCalcView.CALCPAY);
	private JButton calcClrBtn = new JButton(LoanCalcView.CALCCLR);

	@SuppressWarnings("unused")
	private LoanCalcView() {
	}

	public LoanCalcView(LoanCalcController lcController, LoanCalcModel lcModel) {
		this.lcCntrlr = lcController;
		this.lcModel = lcModel;
		createPaymentCalculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.lcCntrlr.guiEventController(e);

	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	private void createPaymentCalculator() {

		// layout for radio button panel

		calcRadioButtonPanel.setLayout(new BoxLayout(calcRadioButtonPanel, BoxLayout.X_AXIS));

		// layout for button panel
		calcButtonPanel.setLayout(new BoxLayout(calcButtonPanel, BoxLayout.X_AXIS));

		// layout for error msg panel
		calcErrorMsgPanel.setLayout(new BoxLayout(calcErrorMsgPanel, BoxLayout.X_AXIS));

		// layout for check box panel
//		FlowLayout flowLayout3 = new FlowLayout(FlowLayout.CENTER, FLOW_LAYOUT_GAP, FLOW_LAYOUT_GAP);
//		calcChkBoxPanel.setLayout(flowLayout3);

		// layout for JEditorPanel
		FlowLayout flowLayout2 = new FlowLayout(FlowLayout.CENTER, FLOW_LAYOUT_GAP, FLOW_LAYOUT_GAP);
		calcEditorPanel.setLayout(flowLayout2);

		// layout for main panel
		calcPanel.setLayout(new BoxLayout(calcPanel, BoxLayout.Y_AXIS));
		calcPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

		// sizing of TextFields
		loanAmtJTF.setMaximumSize(new Dimension(Integer.MAX_VALUE, loanAmtJTF.getPreferredSize().height));
		loanPmtJTF.setMaximumSize(new Dimension(Integer.MAX_VALUE, loanPmtJTF.getPreferredSize().height));
		loanTermJTF.setMaximumSize(new Dimension(Integer.MAX_VALUE, loanTermJTF.getPreferredSize().height));
		loanRateJTF.setMaximumSize(new Dimension(Integer.MAX_VALUE, loanRateJTF.getPreferredSize().height));
		userEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, userEmail.getPreferredSize().height));
		userEmail.setMinimumSize(new Dimension(Integer.MAX_VALUE, userEmail.getPreferredSize().height));

		// tie radio button selection to frame action listener
		loanAmtRBtn.addActionListener(this);
		loanRateRBtn.addActionListener(this);
		loanTermRBtn.addActionListener(this);
		loanPmtRBtn.addActionListener(this);

		// add radio buttons to group
		btnGrp.add(loanAmtRBtn);
		btnGrp.add(loanRateRBtn);
		btnGrp.add(loanTermRBtn);
		btnGrp.add(loanPmtRBtn);

		// set CalcFrame values
		calcFrame.setSize(CALC_HEIGHT, CALC_WIDTH);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, FLOW_LAYOUT_GAP, FLOW_LAYOUT_GAP);
		calcFrame.getContentPane().setLayout(flowLayout);
		calcFrame.setBackground(Color.white);
		calcFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// put labels and text fields in the same panel
		calcFieldsAndLabelsPanel.add(loanAmtRBtn);
		calcFieldsAndLabelsPanel.add(loanAmtJTF);
		calcFieldsAndLabelsPanel.add(loanRateRBtn);
		calcFieldsAndLabelsPanel.add(loanRateJTF);
		calcFieldsAndLabelsPanel.add(loanTermRBtn);
		calcFieldsAndLabelsPanel.add(loanTermJTF);
		calcFieldsAndLabelsPanel.add(loanPmtRBtn);
		calcFieldsAndLabelsPanel.add(loanPmtJTF);
//		calcFieldsAndLabelsPanel.add(emailAmortSched);
//		calcFieldsAndLabelsPanel.add(userEmail);

		calcFieldsAndLabelsPanel.setBorder(BorderFactory.createTitledBorder("Select an Option to Calculate"));

		// add error msg label to error label
		errorMsgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		calcErrorMsgPanel.add(errorMsgLabel);

		// tie check box selection to frame
		emailAmortSched.addActionListener(this);

		// tie check box selection to frame
		amortSchedChkBox.addActionListener(this);

		// editor pane is read only
		editorPane.setEditable(false);

		// Put the editor pane in a scroll pane.
		JScrollPane editorScrollPane = new JScrollPane(editorPane);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(450, 175));
		editorScrollPane.setMinimumSize(new Dimension(10, 10));

		// add editor pane to panel
		calcEditorPanel.add(editorScrollPane);

		// add checkbox to panel
		calcChkBoxPanel.add(emailAmortSched);
		calcChkBoxPanel.add(userEmail);

		// tie button presses to frame
		calcClrBtn.addActionListener(this);
		calcPmtBtn.addActionListener(this);

		// put button in its own panel
		calcButtonPanel.add(calcPmtBtn);
		calcButtonPanel.add(calcClrBtn);

		// put sub panels in main panel
		calcPanel.add(calcFieldsAndLabelsPanel, BorderLayout.NORTH);
		calcPanel.add(calcChkBoxPanel);
		calcPanel.add(calcEditorPanel);
		calcErrorMsgPanel.add(Box.createRigidArea(new Dimension(15, 15)));
		calcPanel.add(calcErrorMsgPanel, BorderLayout.CENTER);
		calcPanel.add(calcButtonPanel, BorderLayout.SOUTH);

		// add main panel to window frame
		calcFrame.add(calcPanel);
		// size window
		// calcFrame.pack();
		// disallow resizing
		calcFrame.setResizable(false);

		// set tab order
		Vector<Component> order = new Vector<Component>(8);
		calcFieldsAndLabelsPanel.add(loanAmtRBtn);
		calcFieldsAndLabelsPanel.add(loanAmtJTF);
		calcFieldsAndLabelsPanel.add(loanRateRBtn);
		calcFieldsAndLabelsPanel.add(loanRateJTF);
		calcFieldsAndLabelsPanel.add(loanTermRBtn);
		calcFieldsAndLabelsPanel.add(loanTermJTF);
		calcFieldsAndLabelsPanel.add(loanPmtRBtn);
		calcFieldsAndLabelsPanel.add(loanPmtJTF);
//		calcFieldsAndLabelsPanel.add(emailAmortSched);
//		calcFieldsAndLabelsPanel.add(userEmail);

		calcButtonPanel.add(calcPmtBtn);
		calcButtonPanel.add(calcClrBtn);

		order.add(loanAmtJTF);
		order.add(loanRateJTF);
		order.add(loanTermJTF);
		order.add(loanPmtJTF);
		order.add(emailAmortSched);
		order.add(userEmail);
		order.add(amortSchedChkBox);
		order.add(calcPmtBtn);
		order.add(calcClrBtn);
		order.add(loanAmtRBtn);
		order.add(loanRateRBtn);
		order.add(loanTermRBtn);
		order.add(loanPmtRBtn);

		newPolicy = new MyOwnFocusTraversalPolicy(order);

		calcFrame.setFocusTraversalPolicyProvider(true);
		calcFrame.setFocusTraversalPolicy(newPolicy);

		userEmail.setEditable(false);
		// disable loan payment field
		loanPmtJTF.setEditable(false);

		// assume service is unavailable - will be checked on first ping
		calcPmtBtn.setEnabled(false);

		// set focus to first field
		loanAmtJTF.requestFocus();
		// center window
		calcFrame.setLocationRelativeTo(null);

	}

	public void showCalculator(boolean visible) {
		calcFrame.setVisible(visible);
	}

	public void clearView() {

		loanAmtJTF.setText("");
		loanPmtJTF.setText("");
		loanTermJTF.setText("");
		loanRateJTF.setText("");
		errorMsgLabel.setText("");
		emailAmortSched.setSelected(false);
		userEmail.setText("");
		editorPane.setText("");
	}

	public void resetAllTextFields(String selectedRadioBtn) {

		loanAmtJTF.setEditable(selectedRadioBtn.equals(LOANAMT) ? false : true);
		loanPmtJTF.setEditable(selectedRadioBtn.equals(PAYMENT) ? false : true);
		loanTermJTF.setEditable(selectedRadioBtn.equals(TERM) ? false : true);
		loanRateJTF.setEditable(selectedRadioBtn.equals(INTRATE) ? false : true);

	}

	public DecimalFormat getDf() {
		return df;
	}

	public DecimalFormat getDf3() {
		return df3;
	}

	public LoanCalcModel getLoanCalcModel() {
		return lcModel;
	}

	public JEditorPane getEditorPane() {
		return editorPane;
	}

	public JTextField getLoanAmtJTF() {
		return loanAmtJTF;
	}

	public void setLoanAmtJTF(JTextField loanAmtJTF) {
		this.loanAmtJTF = loanAmtJTF;
	}

	public JTextField getLoanPmtJTF() {
		return loanPmtJTF;
	}

	public void setLoanPmtJTF(JTextField loanPmtJTF) {
		this.loanPmtJTF = loanPmtJTF;
	}

	public JTextField getLoanTermJTF() {
		return loanTermJTF;
	}

	public void setLoanTermJTF(JTextField loanTermJTF) {
		this.loanTermJTF = loanTermJTF;
	}

	public JTextField getLoanRateJTF() {
		return loanRateJTF;
	}

	public void setLoanRateJTF(JTextField loanRateJTF) {
		this.loanRateJTF = loanRateJTF;
	}

	public JRadioButton getLoanAmtRBtn() {
		return loanAmtRBtn;
	}

	public void setLoanAmtRBtn(JRadioButton loanAmtRBtn) {
		this.loanAmtRBtn = loanAmtRBtn;
	}

	public JRadioButton getLoanPmtRBtn() {
		return loanPmtRBtn;
	}

	public void setLoanPmtRBtn(JRadioButton loanPmtRBtn) {
		this.loanPmtRBtn = loanPmtRBtn;
	}

	public JRadioButton getLoanTermRBtn() {
		return loanTermRBtn;
	}

	public void setLoanTermRBtn(JRadioButton loanTermRBtn) {
		this.loanTermRBtn = loanTermRBtn;
	}

	public JRadioButton getLoanRateRBtn() {
		return loanRateRBtn;
	}

	public void setLoanRateRBtn(JRadioButton loanRateRBtn) {
		this.loanRateRBtn = loanRateRBtn;
	}

	public static String getCalcpay() {
		return CALCPAY;
	}

	public static String getCalcclr() {
		return CALCCLR;
	}

	public JFrame getCalcFrame() {
		return calcFrame;
	}

	public static String getLoanamterror() {
		return LOANAMTERROR;
	}

	public static String getIntrateerror() {
		return INTRATEERROR;
	}

	public static String getTermerror() {
		return TERMERROR;
	}

	public static String getPmterror() {
		return PMTERROR;
	}

	public static String getRsnbleInputs() {
		return RSNBLEINPUTS;
	}
	public JLabel getErrorMsgLabel() {
		return errorMsgLabel;
	}

	public JButton getCalcPmtBtn() {
		return calcPmtBtn;
	}

	public void setCalcPmtBtn(boolean enable) {
		calcPmtBtn.setEnabled(enable);
	}

	public void setUserEmail(String email) {
		userEmail.setText(email);
	}

	public String getUserEmail() {
		return userEmail.getText();
	}

//	public JTextField getUserEmail() {
//		return userEmail;
//	}

	public JCheckBox getEmailAmortSched() {
		return emailAmortSched;
	}

	public JCheckBox getAmortSchedChkBox() {
		return amortSchedChkBox;
	}

	public void setEmailAmortSched(boolean enabled) {
		emailAmortSched.setEnabled(enabled);
	}

	public void setEmailAmortSchedSelected(boolean selected) {
		emailAmortSched.setSelected(selected);
	}

	public void doClickEmailAmortSched() {
		emailAmortSched.doClick();
	}

	public static class MyOwnFocusTraversalPolicy extends FocusTraversalPolicy {
		Vector<Component> order;

		public MyOwnFocusTraversalPolicy(Vector<Component> order) {
			this.order = new Vector<Component>(order.size());
			this.order.addAll(order);
		}

		public MyOwnFocusTraversalPolicy() {
			// TODO Auto-generated constructor stub
		}

		public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
			int idx = (order.indexOf(aComponent) + 1) % order.size();
			return order.get(idx);
		}

		public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
			int idx = order.indexOf(aComponent) - 1;
			if (idx < 0) {
				idx = order.size() - 1;
			}
			return order.get(idx);
		}

		public Component getDefaultComponent(Container focusCycleRoot) {
			return order.get(0);
		}

		public Component getLastComponent(Container focusCycleRoot) {
			return order.lastElement();
		}

		public Component getFirstComponent(Container focusCycleRoot) {
			return order.get(0);
		}

	}

}
