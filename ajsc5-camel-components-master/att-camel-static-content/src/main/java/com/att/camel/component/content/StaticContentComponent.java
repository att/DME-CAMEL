package com.att.camel.component.content;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;



import java.util.Map;

/**
 * Represents the component that manages {@link StaticContentEndpoint}.
 */
public class StaticContentComponent extends DefaultComponent {

   protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
      StaticContentEndpoint endpoint = new StaticContentEndpoint(uri, this);
      setProperties(endpoint, parameters);

      String path = remaining;
      if (path == null) {
         throw new IllegalArgumentException("Resource path must be specified.");
      }
      endpoint.setRootDir(path);
      return endpoint;
   }
}
