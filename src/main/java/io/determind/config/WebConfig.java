
package io.determind.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // Bind Maven webjars resources (e.g. bootstrap .js, .css) to the desired URI pattern
        registry.addResourceHandler("/webjars/**").addResourceLocations(new String[]{ "classpath:/META-INF/resources/webjars/" });

        // a custom WebMvcConfigurerAdapter needs to do its own mappings for static content
        registry.addResourceHandler("/images/**").addResourceLocations(new String[]{ "classpath:/static/images/" });

        // a custom WebMvcConfigurerAdapter needs to do its own mappings for static content
        registry.addResourceHandler("/css/**").addResourceLocations(new String[]{ "classpath:/static/css/" });

    }


    // Redirect HTTP Port 8080 requests to the secure connection established in the application.properties
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                // User data constraints establish a requirement that the constrained requests be received
                // over a protected transport layer connection. This guarantees how the data will be transported
                // between client and server. The strength of the required protection is defined by the value of
                // the transport guarantee. A transport guarantee of INTEGRAL is used to establish a requirement
                // for content integrity and a transport guarantee of CONFIDENTIAL is used to establish a
                // requirement for confidentiality.
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);

        return connector;
    }

}