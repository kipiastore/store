package ru.store.controllers.AuthenticationSuccessHandler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by Akex on 22.03.2017.
 */
public class AuthenticationSuccessHandlerImp implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        try{
        if (roles.contains("ROLE_DIRECTOR")) {
            response.sendRedirect("director/");
        }
        if (roles.contains("ROLE_ADMIN")) {
                response.sendRedirect("admin/");
            }
            if (roles.contains("ROLE_MANAGER")) {
                response.sendRedirect("manager/");
            }
            if (roles.contains("ROLE_CLIENT")) {
                response.sendRedirect("client/");
            }
            if (roles.contains("ROLE_OPERATOR")) {
                response.sendRedirect("operator/");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
