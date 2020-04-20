package edu.bjtu.javaee.filter;


import edu.bjtu.javaee.domain.User;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User user= (User) session.getAttribute("user");
        if(user != null){
            filterChain.doFilter(request, response);
        }else{
            Cookie[] cookies = request.getCookies();

            Cookie cookie=null;
            for (Cookie c:cookies)
            {
                if (c.getName().equals("loginactivity"))
                    cookie=c;
            }

            if(cookie  == null){
                filterChain.doFilter(request, response);
            }else{
                String value = cookie.getValue();
                String username = value.split("||")[0];
                String password = value.split("||")[1];
                //完成登录
                User auser = new User();
                auser.setEmail(username);
                auser.setPassword(password);

                //使用session存这个值到域中，方便下一次未过期前还可以用。
                request.getSession().setAttribute("user", auser);

                filterChain.doFilter(request, response);
            }
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
