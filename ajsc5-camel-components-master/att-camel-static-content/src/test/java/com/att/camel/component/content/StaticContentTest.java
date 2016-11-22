package com.att.camel.component.content;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

public class StaticContentTest extends CamelTestSupport {

   @Test
   public void testResponseCodes() throws Exception {
      Exchange exchange = sendExchange("/foo/bar");

      MockEndpoint mock = getMockEndpoint("mock:result");
      mock.expectedMinimumMessageCount(1);

      assertMockEndpointsSatisfied();
      assertEquals("404", exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));

      exchange = sendExchange("index.html");

      mock = getMockEndpoint("mock:result");
      mock.expectedMinimumMessageCount(1);

      assertMockEndpointsSatisfied();
      assertEquals("200", exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE));
   }

   @Test
   public void testData() throws Exception {
      Exchange exchange = sendExchange("test.txt");
      MockEndpoint mock = getMockEndpoint("mock:result");
      mock.expectedMinimumMessageCount(1);

      assertMockEndpointsSatisfied();
      assertEquals("this is a test\n", exchange.getIn().getBody(String.class));
   }

   @Test
   public void testMimeTypes() throws Exception {
    //  checkMimeType("apache_feather.png", "image/png");
      checkMimeType("block.jpg", "image/jpeg");
      checkMimeType("index.html", "text/html");
      checkMimeType("test.txt", "text/plain");
      checkMimeType("menu.xml", "text/xml");
      checkMimeType("style.css", "text/css");
      checkMimeType("menu.json", "application/json");
   }

   @Test
   public void testDefaultFile() throws Exception {

   }

   private void checkMimeType(final String fileName, String mimeType) throws Exception {
      Exchange exchange = sendExchange(fileName);

      MockEndpoint mock = getMockEndpoint("mock:result");
      mock.expectedMinimumMessageCount(1);

      assertMockEndpointsSatisfied();
      assertEquals(mimeType, exchange.getIn().getHeader(Exchange.CONTENT_TYPE));
   }

   private Exchange sendExchange(final String fileName) {
      return template.send("direct:start", ExchangePattern.InOnly, new Processor() {
            public void process(Exchange exchange) throws Exception {
               exchange.getIn().setHeader(Exchange.HTTP_PATH, fileName);
            }
         });
   }

   @Override
   protected RouteBuilder createRouteBuilder() throws Exception {
      final String basePath = System.getProperty("user.dir") + "/src/test/static?defaultFile=bogus.rtf";
      return new RouteBuilder() {
         public void configure() {
            from("direct:start")
                  .to("att-static-content://" + basePath)
                  .to("mock:result");
         }
      };
   }
}
