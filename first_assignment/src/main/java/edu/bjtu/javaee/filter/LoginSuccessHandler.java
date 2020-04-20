package edu.bjtu.javaee.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse, Authentication authentication)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authCollection = authentication.getAuthorities();
        if (authCollection.isEmpty()) {
            return;
        }

        String role = null;

        for (GrantedAuthority authority:authCollection)
            role=authority.getAuthority();
        System.out.println(role);

        if (role.equalsIgnoreCase("role_teacher"))
         httpServletResponse.sendRedirect("/teacher");
        if (role.equalsIgnoreCase("role_student"))
            httpServletResponse.sendRedirect("/student");
        if (role.equalsIgnoreCase("role_user"))
            httpServletResponse.sendRedirect("/user");

    }
}
