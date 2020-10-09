/**
 * LoanCalcServiceMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */
package org.amortizer.loancalcservice;


/**
 *  LoanCalcServiceMessageReceiverInOut message receiver
 */
public class LoanCalcServiceMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            LoanCalcServiceSkeletonInterface skel = (LoanCalcServiceSkeletonInterface) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("verifyEmailPIN".equals(methodName)) {
                    org.amortizer.loancalcservice.VerifyEmailPINResponse verifyEmailPINResponse43 =
                        null;
                    org.amortizer.loancalcservice.VerifyEmailPIN wrappedParam = (org.amortizer.loancalcservice.VerifyEmailPIN) fromOM(msgContext.getEnvelope()
                                                                                                                                                .getBody()
                                                                                                                                                .getFirstElement(),
                            org.amortizer.loancalcservice.VerifyEmailPIN.class);

                    verifyEmailPINResponse43 = skel.verifyEmailPIN(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            verifyEmailPINResponse43, false,
                            new javax.xml.namespace.QName(
                                "http://www.amortizer.org/LoanCalcService/",
                                "VerifyEmailPINResponse"));
                } else
                 if ("deleteEmailPIN".equals(methodName)) {
                    org.amortizer.loancalcservice.DeleteEmailPINResponse deleteEmailPINResponse45 =
                        null;
                    org.amortizer.loancalcservice.DeleteEmailPIN wrappedParam = (org.amortizer.loancalcservice.DeleteEmailPIN) fromOM(msgContext.getEnvelope()
                                                                                                                                                .getBody()
                                                                                                                                                .getFirstElement(),
                            org.amortizer.loancalcservice.DeleteEmailPIN.class);

                    deleteEmailPINResponse45 = skel.deleteEmailPIN(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            deleteEmailPINResponse45, false,
                            new javax.xml.namespace.QName(
                                "http://www.amortizer.org/LoanCalcService/",
                                "DeleteEmailPINResponse"));
                } else
                 if ("calcLoanTerm".equals(methodName)) {
                    org.amortizer.loancalcservice.CalcLoanTermResponse calcLoanTermResponse47 =
                        null;
                    org.amortizer.loancalcservice.CalcLoanTerm wrappedParam = (org.amortizer.loancalcservice.CalcLoanTerm) fromOM(msgContext.getEnvelope()
                                                                                                                                            .getBody()
                                                                                                                                            .getFirstElement(),
                            org.amortizer.loancalcservice.CalcLoanTerm.class);

                    calcLoanTermResponse47 = skel.calcLoanTerm(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            calcLoanTermResponse47, false,
                            new javax.xml.namespace.QName(
                                "http://www.amortizer.org/LoanCalcService/",
                                "CalcLoanTermResponse"));
                } else
                 if ("calcLoanRate".equals(methodName)) {
                    org.amortizer.loancalcservice.CalcLoanRateResponse calcLoanRateResponse49 =
                        null;
                    org.amortizer.loancalcservice.CalcLoanRate wrappedParam = (org.amortizer.loancalcservice.CalcLoanRate) fromOM(msgContext.getEnvelope()
                                                                                                                                            .getBody()
                                                                                                                                            .getFirstElement(),
                            org.amortizer.loancalcservice.CalcLoanRate.class);

                    calcLoanRateResponse49 = skel.calcLoanRate(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            calcLoanRateResponse49, false,
                            new javax.xml.namespace.QName(
                                "http://www.amortizer.org/LoanCalcService/",
                                "CalcLoanRateResponse"));
                } else
                 if ("checkEmail".equals(methodName)) {
                    org.amortizer.loancalcservice.CheckEmailResponse checkEmailResponse51 =
                        null;
                    org.amortizer.loancalcservice.CheckEmail wrappedParam = (org.amortizer.loancalcservice.CheckEmail) fromOM(msgContext.getEnvelope()
                                                                                                                                        .getBody()
                                                                                                                                        .getFirstElement(),
                            org.amortizer.loancalcservice.CheckEmail.class);

                    checkEmailResponse51 = skel.checkEmail(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            checkEmailResponse51, false,
                            new javax.xml.namespace.QName(
                                "http://www.amortizer.org/LoanCalcService/",
                                "CheckEmailResponse"));
                } else
                 if ("calcLoanPmt".equals(methodName)) {
                    org.amortizer.loancalcservice.CalcLoanPmtResponse calcLoanPmtResponse53 =
                        null;
                    org.amortizer.loancalcservice.CalcLoanPmt wrappedParam = (org.amortizer.loancalcservice.CalcLoanPmt) fromOM(msgContext.getEnvelope()
                                                                                                                                          .getBody()
                                                                                                                                          .getFirstElement(),
                            org.amortizer.loancalcservice.CalcLoanPmt.class);

                    calcLoanPmtResponse53 = skel.calcLoanPmt(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            calcLoanPmtResponse53, false,
                            new javax.xml.namespace.QName(
                                "http://www.amortizer.org/LoanCalcService/",
                                "CalcLoanPmtResponse"));
                } else
                 if ("calcLoanAmt".equals(methodName)) {
                    org.amortizer.loancalcservice.CalcLoanAmtResponse calcLoanAmtResponse55 =
                        null;
                    org.amortizer.loancalcservice.CalcLoanAmt wrappedParam = (org.amortizer.loancalcservice.CalcLoanAmt) fromOM(msgContext.getEnvelope()
                                                                                                                                          .getBody()
                                                                                                                                          .getFirstElement(),
                            org.amortizer.loancalcservice.CalcLoanAmt.class);

                    calcLoanAmtResponse55 = skel.calcLoanAmt(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            calcLoanAmtResponse55, false,
                            new javax.xml.namespace.QName(
                                "http://www.amortizer.org/LoanCalcService/",
                                "CalcLoanAmtResponse"));
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.VerifyEmailPIN param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.VerifyEmailPIN.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.VerifyEmailPINResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.VerifyEmailPINResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.DeleteEmailPIN param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.DeleteEmailPIN.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.DeleteEmailPINResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.DeleteEmailPINResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CalcLoanTerm param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CalcLoanTerm.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CalcLoanTermResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CalcLoanTermResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CalcLoanRate param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CalcLoanRate.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CalcLoanRateResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CalcLoanRateResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CheckEmail param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CheckEmail.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CheckEmailResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CheckEmailResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CalcLoanPmt param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CalcLoanPmt.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CalcLoanPmtResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CalcLoanPmtResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CalcLoanAmt param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CalcLoanAmt.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.amortizer.loancalcservice.CalcLoanAmtResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.amortizer.loancalcservice.CalcLoanAmtResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.amortizer.loancalcservice.VerifyEmailPINResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.amortizer.loancalcservice.VerifyEmailPINResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.amortizer.loancalcservice.VerifyEmailPINResponse wrapVerifyEmailPIN() {
        org.amortizer.loancalcservice.VerifyEmailPINResponse wrappedElement = new org.amortizer.loancalcservice.VerifyEmailPINResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.amortizer.loancalcservice.DeleteEmailPINResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.amortizer.loancalcservice.DeleteEmailPINResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.amortizer.loancalcservice.DeleteEmailPINResponse wrapDeleteEmailPIN() {
        org.amortizer.loancalcservice.DeleteEmailPINResponse wrappedElement = new org.amortizer.loancalcservice.DeleteEmailPINResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.amortizer.loancalcservice.CalcLoanTermResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.amortizer.loancalcservice.CalcLoanTermResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.amortizer.loancalcservice.CalcLoanTermResponse wrapCalcLoanTerm() {
        org.amortizer.loancalcservice.CalcLoanTermResponse wrappedElement = new org.amortizer.loancalcservice.CalcLoanTermResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.amortizer.loancalcservice.CalcLoanRateResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.amortizer.loancalcservice.CalcLoanRateResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.amortizer.loancalcservice.CalcLoanRateResponse wrapCalcLoanRate() {
        org.amortizer.loancalcservice.CalcLoanRateResponse wrappedElement = new org.amortizer.loancalcservice.CalcLoanRateResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.amortizer.loancalcservice.CheckEmailResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.amortizer.loancalcservice.CheckEmailResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.amortizer.loancalcservice.CheckEmailResponse wrapCheckEmail() {
        org.amortizer.loancalcservice.CheckEmailResponse wrappedElement = new org.amortizer.loancalcservice.CheckEmailResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.amortizer.loancalcservice.CalcLoanPmtResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.amortizer.loancalcservice.CalcLoanPmtResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.amortizer.loancalcservice.CalcLoanPmtResponse wrapCalcLoanPmt() {
        org.amortizer.loancalcservice.CalcLoanPmtResponse wrappedElement = new org.amortizer.loancalcservice.CalcLoanPmtResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.amortizer.loancalcservice.CalcLoanAmtResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.amortizer.loancalcservice.CalcLoanAmtResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.amortizer.loancalcservice.CalcLoanAmtResponse wrapCalcLoanAmt() {
        org.amortizer.loancalcservice.CalcLoanAmtResponse wrappedElement = new org.amortizer.loancalcservice.CalcLoanAmtResponse();

        return wrappedElement;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (org.amortizer.loancalcservice.CalcLoanAmt.class.equals(type)) {
                return org.amortizer.loancalcservice.CalcLoanAmt.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CalcLoanAmtResponse.class.equals(
                        type)) {
                return org.amortizer.loancalcservice.CalcLoanAmtResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CalcLoanPmt.class.equals(type)) {
                return org.amortizer.loancalcservice.CalcLoanPmt.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CalcLoanPmtResponse.class.equals(
                        type)) {
                return org.amortizer.loancalcservice.CalcLoanPmtResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CalcLoanRate.class.equals(type)) {
                return org.amortizer.loancalcservice.CalcLoanRate.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CalcLoanRateResponse.class.equals(
                        type)) {
                return org.amortizer.loancalcservice.CalcLoanRateResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CalcLoanTerm.class.equals(type)) {
                return org.amortizer.loancalcservice.CalcLoanTerm.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CalcLoanTermResponse.class.equals(
                        type)) {
                return org.amortizer.loancalcservice.CalcLoanTermResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CheckEmail.class.equals(type)) {
                return org.amortizer.loancalcservice.CheckEmail.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.CheckEmailResponse.class.equals(
                        type)) {
                return org.amortizer.loancalcservice.CheckEmailResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.DeleteEmailPIN.class.equals(type)) {
                return org.amortizer.loancalcservice.DeleteEmailPIN.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.DeleteEmailPINResponse.class.equals(
                        type)) {
                return org.amortizer.loancalcservice.DeleteEmailPINResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.VerifyEmailPIN.class.equals(type)) {
                return org.amortizer.loancalcservice.VerifyEmailPIN.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.amortizer.loancalcservice.VerifyEmailPINResponse.class.equals(
                        type)) {
                return org.amortizer.loancalcservice.VerifyEmailPINResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
