package shopping.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 中文乱码解决
 */
@Configuration
public class Charset  {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
    	FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    	
    	 CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    	 characterEncodingFilter.setForceEncoding(true);
    	 characterEncodingFilter.setEncoding("UTF-8");
    	 registrationBean.setFilter(characterEncodingFilter);
    	 registrationBean.addUrlPatterns("/*");
    	 return registrationBean;
    }
    
}

