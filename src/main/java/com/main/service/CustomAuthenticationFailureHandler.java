package com.main.service;



import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws java.io.IOException {
        String voterCardNo = request.getParameter("voterCardNo");
        String redirectUrl = "/loginpage1?error=true";
        if (voterCardNo != null) {
            redirectUrl += "&voterCardNo=" + voterCardNo;
        }
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
