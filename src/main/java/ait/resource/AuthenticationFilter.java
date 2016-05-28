package ait.resource;


import ait.servlet.utils.OnlineUsers;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

import static ait.servlet.utils.LoginUtils.TOKEN;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        Map<String, Cookie> cookies = requestContext.getCookies();
        String token;

        if (authorizationHeader != null && authorizationHeader.startsWith("Token ")) {
            token = authorizationHeader.substring("Token".length()).trim();
        } else if (cookies.containsKey(TOKEN)) {
            token = cookies.get(TOKEN).getValue();
        } else {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        if (!OnlineUsers.isTokenValid(token)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}