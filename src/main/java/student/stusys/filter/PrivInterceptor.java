package student.stusys.filter;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrivInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        boolean flag = true;
        //System.out.println(request.getSession().getAttribute("stu_code"));
        //String requestURL = request.getRequestURI();
        //System.out.println(requestURL);
        return  flag;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                              ModelAndView modelAndView){
        /*
        System.out.println(request.getSession().getAttribute("stu_code"));
        if(request.getSession().getAttribute("stu_code") == null) {
            try {
                response.sendRedirect("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        String requestURL = request.getRequestURI();
        System.out.println(requestURL);

    }
}
