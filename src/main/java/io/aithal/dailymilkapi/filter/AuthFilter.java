package io.aithal.dailymilkapi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AuthFilter extends GenericFilterBean {
    @Override
    public void doFilter ( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain ) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String authHeader = request.getHeader ( "Authorization" );
        if (authHeader != null) {
            String[] authHeaderArr = authHeader.split ( "Bearer " );
            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                String token = authHeaderArr[1];
                try {
                    DecodedJWT decodedJWT = JWT.decode ( token );
                    Map<String, Claim> claims = decodedJWT.getClaims ();
                    request.setAttribute ( "userId", Integer.parseInt ( claims.get ( "userId" ).toString () ) );
                } catch (Exception e) {
                    response.sendError ( HttpStatus.FORBIDDEN.value (), "invalid/expired token" );
                    return;
                }
            } else {
                response.sendError ( HttpStatus.FORBIDDEN.value (), "Authorization header must be Bearer [token]" );
                return;
            }
        } else {
            response.sendError ( HttpStatus.FORBIDDEN.value (), "Authorization header must be provided" );
            return;
        }
        filterChain.doFilter ( request, response );
    }
}
