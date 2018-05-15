package com.github.phillipkruger.membership.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

/**
 * Activate JAX-RS. 
 * All REST Endpoints available under /api
 * 
 * @author Phillip Kruger (phillip.kruger@phillip-kruger.com)
 */
@ApplicationPath("/rest")
@OpenAPIDefinition(info = @Info(title = "GraphQL Demo", version = "1.0.0",contact = @Contact(name = "Phillip Kruger", email = "phillip.kruger@phillip-kruger.com",url = "http://www.phillip-kruger.com")))
@Server(url = "/membership")
public class ApplicationConfig extends Application {

}
