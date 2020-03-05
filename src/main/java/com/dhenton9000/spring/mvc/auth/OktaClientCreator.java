 

package com.dhenton9000.spring.mvc.auth;

 
import com.okta.authn.sdk.client.AuthenticationClient;
import com.okta.authn.sdk.client.AuthenticationClients;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

 
public class OktaClientCreator {

     private static Logger LOG = LogManager.getLogger(OktaClientCreator.class);
     private final   AuthenticationClient authenticationClient;
     public OktaClientCreator(String oktaUrl) {
           LOG.debug("***** "+oktaUrl);
          authenticationClient = AuthenticationClients.builder()
            .setOrgUrl(oktaUrl)
            .build();
     }
    
     public  AuthenticationClient getClient() {
         return this.authenticationClient;
     }
}

 