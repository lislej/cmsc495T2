package org.amortizer.loancalcservice.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.amortizer.loancalcservice.LoanCalcServiceHandler;
import org.amortizer.loancalcservice.utils.RegexValidator;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;

public class EmailValidator extends JDialog implements ActionListener, Closeable {

	private final JPanel contentPanel = new JPanel();
	private LoanCalcServiceHandler lcsHdlr;
	private LoanCalcView lcView;
	private JTextField emailAddress;
	private JTextField emailPIN;
	private JButton okButton;
	private JButton cancelButton;	
	private JButton btnVerifyPin;
	private JButton btnValidateEmail;	
	private String emailRegexString = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private String emailPinRegex = "^[0-9]{8}$";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmailValidator dialog = new EmailValidator(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmailValidator(LoanCalcView lcView, LoanCalcServiceHandler lcsHdlr) {

		this.lcView = lcView;
		this.lcsHdlr = lcsHdlr;
		setTitle("2-Step Email Verification");
		setBounds(100, 100, 450, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		btnValidateEmail = new JButton("Verify Email");
		btnValidateEmail.addActionListener(this);
		btnValidateEmail.setToolTipText("Press to verify email address.");
		contentPanel.add(btnValidateEmail, "10, 4");

		emailAddress = new JTextField();
		emailAddress.setToolTipText("Enter email address for verification.");
		contentPanel.add(emailAddress, "14, 4, fill, default");
		emailAddress.setColumns(10);

		emailAddress.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				validateInput(emailAddress, emailRegexString, btnValidateEmail);
				System.out.println("Validation-insertUpate");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateInput(emailAddress, emailRegexString, btnValidateEmail);
				System.out.println("Validation-removeUpate");
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateInput(emailAddress, emailRegexString, btnValidateEmail);
				System.out.println("Validation-changedUpate");

			}
		});

		btnVerifyPin = new JButton("Verify PIN");
		btnVerifyPin.setToolTipText("Press to verify PIN number.");
		btnVerifyPin.addActionListener(this);

		contentPanel.add(btnVerifyPin, "10, 6");

		emailPIN = new JTextField();
		emailPIN.setToolTipText("Enter PIN received in email.");
		contentPanel.add(emailPIN, "14, 6, fill, default");
		emailPIN.setColumns(10);

		emailPIN.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				validateInput(emailPIN, emailPinRegex, btnVerifyPin);
				System.out.println("Validation-insertUpate");
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateInput(emailPIN, emailPinRegex, btnVerifyPin);
				System.out.println("Validation-removeUpate");
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateInput(emailPIN, emailPinRegex, btnVerifyPin);
				System.out.println("Validation-changedUpate");

			}
		});

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("   OK   ");
		okButton.setHorizontalAlignment(SwingConstants.LEFT);
		okButton.addActionListener(this);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		buttonPane.add(horizontalStrut);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		Component horizontalStrut1 = Box.createHorizontalStrut(100);
		buttonPane.add(horizontalStrut1);


		//initially disables for email validation
		this.btnValidateEmail.setEnabled(false);
		this.btnVerifyPin.setEnabled(false);
		this.emailPIN.setEnabled(false);
		this.okButton.setEnabled(false);
		
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.emailAddress.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object object = e.getSource();

		// trap only for button presses
		if (object instanceof JButton) {

			// calculate button press
			if (((JButton) object).getText().trim().equalsIgnoreCase("Ok")) {

				lcView.setUserEmail(this.emailAddress.getText());
				try {
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (((JButton) object).getText().trim().equalsIgnoreCase("Cancel")) {

				lcView.setUserEmail("");
				lcView.doClickEmailAmortSched();
				
				//ensure email pin is cleared from model
				this.lcView.getLoanCalcModel().setEmailPin("");

				
				try {
					this.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (((JButton) object).getText().trim().equalsIgnoreCase("Verify Email")) {

				String msg = null;
				try {
					msg = lcsHdlr.chkEmail(this.emailAddress.getText());
					if (msg != null && msg.equalsIgnoreCase("Check the email address you entered for a verification pin number.")) {
						JOptionPane.showMessageDialog(this, String.format("Your PIN has been emailed to: %s", this.emailAddress.getText()));
						
						this.emailPIN.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(this, "Check your email address and retry.");					
						this.emailPIN.setEnabled(true);
						this.okButton.setEnabled(false);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "The email verification call generated an exception. Please retry. \n\n " + e1.getMessage());
				}
				

			} else if (((JButton) object).getText().trim().equalsIgnoreCase("Verify PIN")) {

				String msg = null;
				try {
				msg = lcsHdlr.verfyPIN(this.emailPIN.getText(), this.emailAddress.getText());
				if (msg != null && msg.equalsIgnoreCase("success")) {
					JOptionPane.showMessageDialog(this, String.format("Your PIN has been verified.", this.emailAddress.getText()));
					this.okButton.setEnabled(true);
					this.okButton.requestFocus();

					//add valid email pin to model
					this.lcView.getLoanCalcModel().setEmailPin(this.emailPIN.getText());
					
				} else {
					JOptionPane.showMessageDialog(this, "Ensure you have entered the PIN received.");
				}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, "The email pin validation call generated an exception. Please retry. \n\n " + e1.getMessage());					
				}
			}
		}
	}

	public JTextField getEmailAddress() {
		return emailAddress;
	}

	public JTextField getEmailPIN() {
		return emailPIN;
	}

	private void validateInput(JTextField jtxtFld, String pattern) {
		String text = jtxtFld.getText();
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(text);
		if (m.matches()) {
			jtxtFld.setForeground(Color.GREEN);
		} else {
			jtxtFld.setForeground(Color.RED);
		}
	}
	
	private void validateInput(JTextField jtxtFld, String pattern, JButton jbtn) {
		String text = jtxtFld.getText();
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(text);
		if (m.matches()) {
			jtxtFld.setForeground(Color.GREEN);
			jbtn.setEnabled(true);
		} else {
			jtxtFld.setForeground(Color.RED);
			jbtn.setEnabled(false);
		}
	}

	@Override
	public void close() throws IOException {

		this.dispose();
		
	}

}
