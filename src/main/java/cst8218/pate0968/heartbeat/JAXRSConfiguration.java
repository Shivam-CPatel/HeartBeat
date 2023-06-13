package cst8218.pate0968.heartbeat;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@ApplicationPath("resources")
@BasicAuthenticationMechanismDefinition
//@FormAuthenticationMechanismDefinition(
//    loginToContinue = @LoginToContinue(
//    loginPage = "/index.xhtml",
//    errorPage = "/index.xhtml")
//)
@DatabaseIdentityStoreDefinition(
   dataSourceLookup = "${'java:comp/DefaultDataSource'}",
   callerQuery = "#{'select PASSWORD from APP.APPUSER where USER_ID = ?'}",
   groupsQuery = "select GROUPNAME from APP.APPUSER where USER_ID = ?",
   hashAlgorithm = PasswordHash.class,
   priority = 10
)
@Named
@ApplicationScoped
public class JAXRSConfiguration extends Application {
    
}
