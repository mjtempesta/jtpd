package org.jtpd.web;

import org.apache.wicket.protocol.http.WebApplication;

public class Uygulama extends WebApplication {

	@Override
	public Class getHomePage() {
		return Basla.class;
	}
}
