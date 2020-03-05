package com.dhenton9000.spring.mvc.auth;

import com.okta.authn.sdk.AuthenticationStateHandlerAdapter;
import com.okta.authn.sdk.resource.AuthenticationResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AuthenticationStateHandler extends AuthenticationStateHandlerAdapter {

    static final String PREVIOUS_AUTHN_RESULT = AuthenticationResponse.class.getName();
    private static Logger LOG = LogManager.getLogger(AuthenticationStateHandler.class);
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public AuthenticationStateHandler(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public void handleSuccess(AuthenticationResponse successResponse) {
        // the last request was a success, but if we do not have a session token
        // we need to force the flow to start over
        if (successResponse.getSessionToken() != null) {
            // if we have a Session Token add the corresponding user to the Session
         //   LOG.info("inserting into session in the handler");
            request.getSession(true)
                    .setAttribute(OktaFilter.USER_SESSION_KEY,
                            successResponse.getUser());
        }

        String relayState = successResponse.getRelayState();
        String dest = relayState != null ? relayState : "/";
        redirect(dest, successResponse);
    }

    @Override
    public void handleUnknown(AuthenticationResponse unknownResponse) {
        redirect("/authn/login?error=Unsupported State: "
                + unknownResponse.getStatus().name(), unknownResponse);
    }

    private void redirect(String location, AuthenticationResponse authenticationResponse) {
        try {
            setAuthNResult(authenticationResponse);
            response.sendRedirect(location);
        } catch (IOException e) {
            throw new IllegalStateException("failed to redirect.", e);
        }
    }

    private void setAuthNResult(AuthenticationResponse authenticationResponse) {
        request.getSession(true)
                .setAttribute(AuthenticationStateHandler.PREVIOUS_AUTHN_RESULT,
                        authenticationResponse);
    }
}
