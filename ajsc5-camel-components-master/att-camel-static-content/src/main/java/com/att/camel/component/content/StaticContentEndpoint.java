package com.att.camel.component.content;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * Represents a static endpoint.
 */
public class StaticContentEndpoint extends DefaultEndpoint {
   private String defaultFile = "index.html";
   private String rootDir;

    public StaticContentEndpoint() {
    }

    public StaticContentEndpoint(String uri, StaticContentComponent component) {
        super(uri, component);
    }

    public Producer createProducer() throws Exception {
        return new StaticContentProducer(this);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
       throw new RuntimeCamelException("Cannot consume from a StaticContent endpoint: " + getEndpointUri());
    }

    public boolean isSingleton() {
        return true;
    }

   public String getDefaultFile() {
      return defaultFile;
   }

   public void setDefaultFile(String defaultFile) {
      this.defaultFile = defaultFile;
   }

   public String getRootDir() {
      return rootDir;
   }

   public void setRootDir(String rootDir) {
      this.rootDir = rootDir;
   }
}
