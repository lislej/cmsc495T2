package org.amortizer.loancalcservice;

import java.text.DecimalFormat;

import org.amortizer.loancalcservice.client.LoanCalcModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoanCalcServiceHandlerTest {

	static private LoanCalcServiceHandler lcsHndlr;
	static private final String CALC_SERVICE_URL = "https://loancalcservice.herokuapp.com/services/LoanCalcService";
	static LoanCalcModel lcModel;
	static DecimalFormat df = new DecimalFormat("0.##");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		lcsHndlr = new LoanCalcServiceHandler(CALC_SERVICE_URL);
	}

	@BeforeEach
	void setUp() throws Exception {
		lcModel = new LoanCalcModel();
		lcModel.setIntRate(5.5);
		lcModel.setLoanAmt(183579.78);
		lcModel.setTerm(15);
		lcModel.setLoanPmt(1500);
	}

	@Test
	void testLoanCalcServiceHandler() {
		assert (lcsHndlr != null);
	}

	@Test
	void testCalcLoanAmt() {
		
		try {
			assert (df.format(lcsHndlr.calcLoanAmt(lcModel)).equals("183579.78"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCalcLoanPmt() {
		
		try {
			assert (df.format(lcsHndlr.calcLoanPmt(lcModel)).equals("1500"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCalcLoanRate() {

		try {
			assert (df.format(lcsHndlr.calcLoanRate(lcModel)).equals("5.5"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testCalcLoanTerm() {

		try {
			assert (df.format(lcsHndlr.calcLoanTerm(lcModel)).equals("180"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
