
/**
 * VersionExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.9  Built on : Nov 16, 2018 (12:05:37 GMT)
 */

package org.apache.axis2.version;

public class VersionExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1600305852792L;
    
    private org.apache.axis2.version.VersionStub.VersionException faultMessage;

    
        public VersionExceptionException() {
            super("VersionExceptionException");
        }

        public VersionExceptionException(java.lang.String s) {
           super(s);
        }

        public VersionExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public VersionExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(org.apache.axis2.version.VersionStub.VersionException msg){
       faultMessage = msg;
    }
    
    public org.apache.axis2.version.VersionStub.VersionException getFaultMessage(){
       return faultMessage;
    }
}
    