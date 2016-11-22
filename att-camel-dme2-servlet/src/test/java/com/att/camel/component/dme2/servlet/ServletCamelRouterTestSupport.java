package com.att.camel.component.dme2.servlet;
/*******************************************************************************
 * Copyright (c) 2016 AT&T Intellectual Property. All rights reserved.
 *******************************************************************************/

import java.io.InputStream;

import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;

public class ServletCamelRouterTestSupport extends CamelTestSupport {
    public static final String CONTEXT = "/ajsc";
    public static final String CONTEXT_URL = "http://localhost/ajsc";
    protected ServletRunner sr;
    protected boolean startCamelContext = true;

    @Before
    public void setUp() throws Exception {
        InputStream is = this.getClass().getResourceAsStream(getConfiguration());
        assertNotNull("The configuration input stream should not be null", is);
        sr = new ServletRunner(is, CONTEXT);
        
        HttpUnitOptions.setExceptionsThrownOnErrorStatus(true);
        if (startCamelContext) {        
            super.setUp();
        }
    }
    
    @After
    public void tearDown() throws Exception {
        if (startCamelContext) {
            super.tearDown();
        }
        sr.shutDown();
    }
    
    /**
     * @return The web.xml to use for testing.
     */
    protected String getConfiguration() {
        return "/org/apache/camel/component/dme2/web.xml";
    }

    protected ServletUnitClient newClient() {
        return sr.newClient();
    }

}
