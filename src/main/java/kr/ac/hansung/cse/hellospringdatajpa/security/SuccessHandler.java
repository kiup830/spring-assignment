package kr.ac.hansung.cse.hellospringdatajpa.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        String email = authentication.getName(); // 로그인된 사용자 이메일
        String redirectUrl = "/products?success=true&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8);
        request.getSession().removeAttribute("loginEmail");
        request.getSession().removeAttribute("loginError");
        response.sendRedirect(redirectUrl);
    }
}
