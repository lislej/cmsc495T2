/**
 * LoanCalcServiceTest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package org.amortizer.loancalcservice;


/*
 *  LoanCalcServiceTest Junit test case
 */
public class LoanCalcServiceTest extends junit.framework.TestCase {
    /**
     * Auto generated test method
     */
    public void testverifyEmailPIN() throws java.lang.Exception {
        org.amortizer.loancalcservice.LoanCalcServiceStub stub = new org.amortizer.loancalcservice.LoanCalcServiceStub(); //the default implementation should point to the right endpoint

        org.amortizer.loancalcservice.LoanCalcServiceStub.VerifyEmailPIN verifyEmailPIN14 =
            (org.amortizer.loancalcservice.LoanCalcServiceStub.VerifyEmailPIN) getTestObject(org.amortizer.loancalcservice.LoanCalcServiceStub.VerifyEmailPIN.class);
        // TODO : Fill in the verifyEmailPIN14 here
        assertNotNull(stub.verifyEmailPIN(verifyEmailPIN14));
    }

    /**
     * Auto generated test method
     */
    public void testdeleteEmailPIN() throws java.lang.Exception {
        org.amortizer.loancalcservice.LoanCalcServiceStub stub = new org.amortizer.loancalcservice.LoanCalcServiceStub(); //the default implementation should point to the right endpoint

        org.amortizer.loancalcservice.LoanCalcServiceStub.DeleteEmailPIN deleteEmailPIN16 =
            (org.amortizer.loancalcservice.LoanCalcServiceStub.DeleteEmailPIN) getTestObject(org.amortizer.loancalcservice.LoanCalcServiceStub.DeleteEmailPIN.class);
        // TODO : Fill in the deleteEmailPIN16 here
        assertNotNull(stub.deleteEmailPIN(deleteEmailPIN16));
    }

    /**
     * Auto generated test method
     */
    public void testcalcLoanTerm() throws java.lang.Exception {
        org.amortizer.loancalcservice.LoanCalcServiceStub stub = new org.amortizer.loancalcservice.LoanCalcServiceStub(); //the default implementation should point to the right endpoint

        org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanTerm calcLoanTerm18 =
            (org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanTerm) getTestObject(org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanTerm.class);
        // TODO : Fill in the calcLoanTerm18 here
        assertNotNull(stub.calcLoanTerm(calcLoanTerm18));
    }

    /**
     * Auto generated test method
     */
    public void testcalcLoanRate() throws java.lang.Exception {
        org.amortizer.loancalcservice.LoanCalcServiceStub stub = new org.amortizer.loancalcservice.LoanCalcServiceStub(); //the default implementation should point to the right endpoint

        org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanRate calcLoanRate20 =
            (org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanRate) getTestObject(org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanRate.class);
        // TODO : Fill in the calcLoanRate20 here
        assertNotNull(stub.calcLoanRate(calcLoanRate20));
    }

    /**
     * Auto generated test method
     */
    public void testcheckEmail() throws java.lang.Exception {
        org.amortizer.loancalcservice.LoanCalcServiceStub stub = new org.amortizer.loancalcservice.LoanCalcServiceStub(); //the default implementation should point to the right endpoint

        org.amortizer.loancalcservice.LoanCalcServiceStub.CheckEmail checkEmail22 =
            (org.amortizer.loancalcservice.LoanCalcServiceStub.CheckEmail) getTestObject(org.amortizer.loancalcservice.LoanCalcServiceStub.CheckEmail.class);
        // TODO : Fill in the checkEmail22 here
        assertNotNull(stub.checkEmail(checkEmail22));
    }

    /**
     * Auto generated test method
     */
    public void testcalcLoanPmt() throws java.lang.Exception {
        org.amortizer.loancalcservice.LoanCalcServiceStub stub = new org.amortizer.loancalcservice.LoanCalcServiceStub(); //the default implementation should point to the right endpoint

        org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanPmt calcLoanPmt24 =
            (org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanPmt) getTestObject(org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanPmt.class);
        // TODO : Fill in the calcLoanPmt24 here
        assertNotNull(stub.calcLoanPmt(calcLoanPmt24));
    }

    /**
     * Auto generated test method
     */
    public void testcalcLoanAmt() throws java.lang.Exception {
        org.amortizer.loancalcservice.LoanCalcServiceStub stub = new org.amortizer.loancalcservice.LoanCalcServiceStub(); //the default implementation should point to the right endpoint

        org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanAmt calcLoanAmt26 =
            (org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanAmt) getTestObject(org.amortizer.loancalcservice.LoanCalcServiceStub.CalcLoanAmt.class);
        // TODO : Fill in the calcLoanAmt26 here
        assertNotNull(stub.calcLoanAmt(calcLoanAmt26));
    }

    //Create an ADBBean and provide it as the test object
    public org.apache.axis2.databinding.ADBBean getTestObject(
        java.lang.Class type) throws java.lang.Exception {
        return (org.apache.axis2.databinding.ADBBean) type.newInstance();
    }
}
