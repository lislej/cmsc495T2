/**
 * LoanCalcServiceSkeletonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package org.amortizer.loancalcservice;

/**
 *  LoanCalcServiceSkeletonInterface java skeleton interface for the axisService
 */
public interface LoanCalcServiceSkeletonInterface {
    /**
     * Auto generated method signature
     *
     * @param verifyEmailPIN
     */
    public org.amortizer.loancalcservice.VerifyEmailPINResponse verifyEmailPIN(
        org.amortizer.loancalcservice.VerifyEmailPIN verifyEmailPIN);

    /**
     * Auto generated method signature
     *
     * @param deleteEmailPIN
     */
    public org.amortizer.loancalcservice.DeleteEmailPINResponse deleteEmailPIN(
        org.amortizer.loancalcservice.DeleteEmailPIN deleteEmailPIN);

    /**
     * Auto generated method signature
     *
     * @param calcLoanTerm
     */
    public org.amortizer.loancalcservice.CalcLoanTermResponse calcLoanTerm(
        org.amortizer.loancalcservice.CalcLoanTerm calcLoanTerm);

    /**
     * Auto generated method signature
     *
     * @param calcLoanRate
     */
    public org.amortizer.loancalcservice.CalcLoanRateResponse calcLoanRate(
        org.amortizer.loancalcservice.CalcLoanRate calcLoanRate);

    /**
     * Auto generated method signature
     *
     * @param checkEmail
     */
    public org.amortizer.loancalcservice.CheckEmailResponse checkEmail(
        org.amortizer.loancalcservice.CheckEmail checkEmail);

    /**
     * Auto generated method signature
     *
     * @param calcLoanPmt
     */
    public org.amortizer.loancalcservice.CalcLoanPmtResponse calcLoanPmt(
        org.amortizer.loancalcservice.CalcLoanPmt calcLoanPmt);

    /**
     * Auto generated method signature
     *
     * @param calcLoanAmt
     */
    public org.amortizer.loancalcservice.CalcLoanAmtResponse calcLoanAmt(
        org.amortizer.loancalcservice.CalcLoanAmt calcLoanAmt);
}
