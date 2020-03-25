package shopping.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shopping.interceptor.LoginInterceptor;

   @Component
   public class MyConfig implements WebMvcConfigurer {
   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
	   registry.addViewController("/admin/").setViewName("admin/login");
	   registry.addViewController("/admin/error").setViewName("admin/error");
	   registry.addViewController("/admin/index").setViewName("admin/index");
		
}
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	//还原springboot 默认静态资源映射规则
    	registry.addResourceHandler("/META-INF/resources/**").addResourceLocations("classpath:/META-INF/resources/");
    	registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
    	registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    	registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
    	
    	//配置上传资源静态资源映射规则
    	registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/demo2/src/main/resources/static/upload/");
	
    	
}
    
    @Override
    	public void addInterceptors(InterceptorRegistry registry) {
    		registry.addInterceptor(new LoginInterceptor())
				 .addPathPatterns("/admin/**") 
    		.excludePathPatterns("/","/admin/","/admin/login","/admin/validate","/static/admin/**");
    	}	

}
