package ru.store.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    GoogleCaptcha googleCaptcha;

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String reCaptchaResponse = request.getParameter("g-recaptcha-response");
        GoogleCaptcha.CaptchaResponse captchaResponse = googleCaptcha.check(reCaptchaResponse);
        if (captchaResponse == null || !captchaResponse.success) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                }
            }
            response.sendRedirect("login?error");
        }
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
