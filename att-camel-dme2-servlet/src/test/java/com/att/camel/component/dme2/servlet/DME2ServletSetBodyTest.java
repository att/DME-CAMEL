/*******************************************************************************
 * Copyright (c) 2016 AT&T Intellectual Property. All rights reserved.
 *******************************************************************************/
package com.att.camel.component.dme2.servlet;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletUnitClient;

import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

public class DME2ServletSetBodyTest extends ServletCamelRouterTestSupport {

	@Test
	public void testSetBody() throws Exception {

		//System.out.println("Endpoint Map" + context.getEndpointMap());
		WebRequest req = new GetMethodWebRequest(CONTEXT_URL + "/");
		ServletUnitClient client = newClient();
		WebResponse response = null;
		response = client.getResponse(req);
		assertEquals("The response message is", "Hello World",
				response.getText());
	}

	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("att-dme2-servlet:///").setBody().constant(
						"Hello World");
			}
		};
	}

}
