/**
 * LoanCalcServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package org.amortizer.loancalcservice;


/**
 *  LoanCalcServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class LoanCalcServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public LoanCalcServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public LoanCalcServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for verifyEmailPIN method
     * override this method for handling normal response from verifyEmailPIN operation
     */
    public void receiveResultverifyEmailPIN(
        org.amortizer.loancalcservice.VerifyEmailPINResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from verifyEmailPIN operation
     */
    public void receiveErrorverifyEmailPIN(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for deleteEmailPIN method
     * override this method for handling normal response from deleteEmailPIN operation
     */
    public void receiveResultdeleteEmailPIN(
        org.amortizer.loancalcservice.DeleteEmailPINResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from deleteEmailPIN operation
     */
    public void receiveErrordeleteEmailPIN(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for calcLoanTerm method
     * override this method for handling normal response from calcLoanTerm operation
     */
    public void receiveResultcalcLoanTerm(
        org.amortizer.loancalcservice.CalcLoanTermResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from calcLoanTerm operation
     */
    public void receiveErrorcalcLoanTerm(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for calcLoanRate method
     * override this method for handling normal response from calcLoanRate operation
     */
    public void receiveResultcalcLoanRate(
        org.amortizer.loancalcservice.CalcLoanRateResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from calcLoanRate operation
     */
    public void receiveErrorcalcLoanRate(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for checkEmail method
     * override this method for handling normal response from checkEmail operation
     */
    public void receiveResultcheckEmail(
        org.amortizer.loancalcservice.CheckEmailResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from checkEmail operation
     */
    public void receiveErrorcheckEmail(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for calcLoanPmt method
     * override this method for handling normal response from calcLoanPmt operation
     */
    public void receiveResultcalcLoanPmt(
        org.amortizer.loancalcservice.CalcLoanPmtResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from calcLoanPmt operation
     */
    public void receiveErrorcalcLoanPmt(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for calcLoanAmt method
     * override this method for handling normal response from calcLoanAmt operation
     */
    public void receiveResultcalcLoanAmt(
        org.amortizer.loancalcservice.CalcLoanAmtResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from calcLoanAmt operation
     */
    public void receiveErrorcalcLoanAmt(java.lang.Exception e) {
    }
}
