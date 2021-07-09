package io.aithal.dailymilkapi;

import io.aithal.dailymilkapi.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DailyMilkApiApplication {

    public static void main ( String[] args ) {
        SpringApplication.run ( DailyMilkApiApplication.class, args );
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean ( ) {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<> ();
        AuthFilter authFilter = new AuthFilter ();
        registrationBean.setFilter ( authFilter );
        registrationBean.addUrlPatterns ( "/api/products/*" );
        registrationBean.addUrlPatterns ( "/api/riders/*" );
        return registrationBean;
    }
}
