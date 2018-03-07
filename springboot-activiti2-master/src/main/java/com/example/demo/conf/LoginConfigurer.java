package com.example.demo.conf;

import com.aukey.auth.DataAuthListener;
import com.aukey.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
@Component
@EnableOAuth2Sso
public  class LoginConfigurer extends WebSecurityConfigurerAdapter {
	
	  @Value("${security.oauth2.custom.server-logout-url}")
	  private String serverLogoutUrl;
	  @Value("${security.oauth2.custom.server-logouted-redirect-url}")
	  private String serverLogoutedRedirectUrl;
	  @Value("${spring.datasource.url}")
	  private String datasourceUrl;	
	@Value("${spring.application.name}")
	private String applicatonname;

	
	  private SecurityAccessDecisionManager securityAccessDecisionManager=new SecurityAccessDecisionManager(); 
	  
	  private SecurityAccessDeniedHandler securityAccessDeniedHandler=new SecurityAccessDeniedHandler() ; 
 
	  private SecurityLoginUrlAuthenticationEntryPoint securityLoginUrlAuthenticationEntryPoint=new SecurityLoginUrlAuthenticationEntryPoint() ; 

	 
	 
	  
	  private InvocationSecurityMetadataSourceService invocationSecurityMetadataSourceService() {
			return new InvocationSecurityMetadataSourceService(applicatonname);

		}
	  
	  

	@Value("${url.cas.server}")
	private String casserverUrl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		super.configure(auth);
	}

	   /**
	    * ignored public module - resource;
	    */
	   @Override
	   public void configure(WebSecurity web) throws Exception {
	         web.ignoring().antMatchers("/css/**", "/js/**", "/images/**","/bootstrap/**","/bootstrap-datetimepicker/**","/bootstrap-fileinput/**","/bootstrap-lightbox/**","/bootstrap-modal/**", 
				      "/bootstrap-select/**","/bootstrap-table/**","/category/**","/ckeditor/**","/echarts/**","/flyer/**","/fonts/**","/landing/**","/wishaccount/**","/ztree/**");
	    }
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		 http.headers().frameOptions().disable(); //解决frame-set跨域问题

		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
		//joinModuleAndRole(registry);
		registry.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            public <O extends FilterSecurityInterceptor> O postProcess(
                    O fsi) {
                fsi.setSecurityMetadataSource(invocationSecurityMetadataSourceService());
                return fsi;
            }
        });
		
		registry.anyRequest()
				.authenticated()
				//权限决策器
				.accessDecisionManager(securityAccessDecisionManager)
				.and()
				.addFilterAfter(new CustomBeforeSecurityContextFilter(casserverUrl), SecurityContextPersistenceFilter.class)
				.addFilterBefore(new MatchSecurityUserFilter(casserverUrl), FilterSecurityInterceptor.class)
				.formLogin()
				//.successHandler(new LoginSuccessHandler())
//				.failureHandler(loginFailureHandler)
				.and()
				.exceptionHandling()
//				验证用户对URL是否有权限类(针对ajax, security配置原本不支持ajax，必须重写)
				.accessDeniedHandler(securityAccessDeniedHandler)
				.authenticationEntryPoint(securityLoginUrlAuthenticationEntryPoint)
				.and()
				.csrf().disable()
				.addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
				.logout()
				.deleteCookies("JSESSIONID","XSRF-TOKEN")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.invalidateHttpSession(true).logoutSuccessUrl(serverLogoutUrl + "?next=" + serverLogoutedRedirectUrl).permitAll()
				.and().headers().cacheControl();
	}

	private Filter csrfHeaderFilter() {
		return new OncePerRequestFilter() {
			@Override
			protected void doFilterInternal(HttpServletRequest request,
					HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
				CsrfToken csrf = (CsrfToken) request
						.getAttribute(CsrfToken.class.getName());
				if (csrf != null) {
					Cookie cookie = new Cookie("XSRF-TOKEN",
							csrf.getToken());
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				filterChain.doFilter(request, response);
			}
		};
	}
	

}