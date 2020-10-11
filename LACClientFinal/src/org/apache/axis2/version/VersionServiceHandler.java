package org.apache.axis2.version;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.version.VersionStub.GetVersion;
import org.apache.axis2.version.VersionStub.GetVersionResponse;

public class VersionServiceHandler {

	private URL serviceUrl;
	private VersionStub vsStub;

	public VersionServiceHandler(String url) throws MalformedURLException, AxisFault {

		this.serviceUrl = new URL(url);
		vsStub = new VersionStub(this.serviceUrl.toString());
	}

	public GetVersionResponse pingService() throws RemoteException, VersionExceptionException {

		return vsStub.getVersion(new GetVersion());

	}
}
