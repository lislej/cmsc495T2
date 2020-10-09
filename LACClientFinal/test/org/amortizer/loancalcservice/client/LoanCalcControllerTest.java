package org.amortizer.loancalcservice.client;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.text.DecimalFormat;

import javax.swing.SwingUtilities;

import org.apache.axis2.AxisFault;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoanCalcControllerTest {

	static private LoanCalcController loanCalcCntlr;
	static DecimalFormat df = new DecimalFormat("0.##");
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
		      @Override
		      public void run() {
				try {
					loanCalcCntlr = new LoanCalcController();
					loanCalcCntlr.getLcModel().setLoanAmt(183579.78);
					loanCalcCntlr.getLcModel().setIntRate(5.5);
					loanCalcCntlr.getLcModel().setLoanPmt(1500);
					loanCalcCntlr.getLcModel().setTerm(15);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AxisFault e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		    });

	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	void testLoanCalcController() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(loanCalcCntlr.getLcsHndlr().calcLoanAmt(loanCalcCntlr.getLcModel()));
		System.out.println(loanCalcCntlr.getLcsHndlr().calcLoanPmt(loanCalcCntlr.getLcModel()));
		System.out.println(loanCalcCntlr.getLcsHndlr().calcLoanRate(loanCalcCntlr.getLcModel()));
		System.out.println(loanCalcCntlr.getLcsHndlr().calcLoanTerm(loanCalcCntlr.getLcModel()));
		assert(df.format(loanCalcCntlr.getLcsHndlr().calcLoanAmt(loanCalcCntlr.getLcModel())).equals("183579.78"));
		assert(df.format(loanCalcCntlr.getLcsHndlr().calcLoanPmt(loanCalcCntlr.getLcModel())).equals("1500"));
		assert(df.format(loanCalcCntlr.getLcsHndlr().calcLoanRate(loanCalcCntlr.getLcModel())).equals("5.5"));
		assert(df.format(loanCalcCntlr.getLcsHndlr().calcLoanTerm(loanCalcCntlr.getLcModel())).equals("180"));
	}

	@Test
	void testShowCalculator() {
		
		try {
			loanCalcCntlr.getLcView().getCalcFrame().setVisible(true);
			Thread.sleep(1000);
			loanCalcCntlr.getLcView().getCalcFrame().setVisible(false);
			
		} catch (InterruptedException e) {

			fail(e.getMessage());
		}
		assert(1==1);
	}


}
