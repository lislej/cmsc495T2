package org.amortizer.loancalcservice.client;

import java.net.MalformedURLException;

import javax.swing.SwingUtilities;

import org.apache.axis2.AxisFault;

public class LACBootStrap {

	public static void main(String[] args) throws MalformedURLException {

	    SwingUtilities.invokeLater(new Runnable() {
	      @Override
	      public void run() {
			try {
				new LoanCalcController();
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
}
