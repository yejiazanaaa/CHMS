package student.stusys.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//注册拦截器。这样可以使用拦截功能。
@Configuration
public class MyWebConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PrivInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/js/*")
                .excludePathPatterns("/css/*")
                .excludePathPatterns("/images/*")
                .excludePathPatterns("/css/img/*")
                .excludePathPatterns("/error")
                .excludePathPatterns("/css/font/*")
                .excludePathPatterns("/*.js")
                .excludePathPatterns("/*.html")
                .excludePathPatterns("register_student_info")
                .excludePathPatterns("check_student_info");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/static/**").addResourceLocations("/static/");
        //registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
        //super.addResourceHandlers(registry);
    }
}
