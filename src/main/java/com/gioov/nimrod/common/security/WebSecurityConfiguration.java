package com.gioov.nimrod.common.security;

import com.gioov.nimrod.common.constant.Api;
import com.gioov.nimrod.common.constant.Page;
import com.gioov.nimrod.common.constant.Url;
import com.gioov.nimrod.common.druid.DruidConfiguration;
import com.gioov.nimrod.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfiguration.class);

    @Autowired
    @Qualifier("simpleUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyCodeFilter verifyCodeFilter;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private LogoutHandler logoutHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class)
                // 禁用 csrf
                .csrf().disable()

                // 禁用 ROLE_ANONYMOUS 角色
//                .anonymous().disable()

                // 解决 in a frame because it set 'X-Frame-Options' to 'deny'. 问题
                .headers().frameOptions().disable().and()

                .authorizeRequests()

                // 静态资源 url ，无需登录认证权限
                .antMatchers(Url.STATIC).permitAll()

                .antMatchers(Api.System.VERIFY_CODE, Page.User.LOGIN).permitAll()

                // Druid 需要权限或者系统管理员角色才能访问
                .antMatchers(DruidConfiguration.DRUID_URL).hasAnyAuthority("ROLE_" + SYSTEM_ADMIN, DruidConfiguration.DRUID_URL.toUpperCase())

                // 其它请求均需要认证 或 .antMatchers(Url.PATH_PATTERN).authenticated()
                .anyRequest().authenticated().and()

                // 开启表单登录，设置登录 url
                .formLogin().loginPage(Page.User.LOGIN).usernameParameter(Page.User.LOGIN_ACCOUNT_STRING).passwordParameter(Page.User.LOGIN_PASSWORD_STRING)

                // 自定义登录表单提交 url
                .loginProcessingUrl(Api.User.LOGIN)

                // 登录成功处理
                .successHandler(
                        // 登录成功，返回 status 200 ， json 返回 SimpleUser
                        authenticationSuccessHandler)
                // 登录失败处理
                .failureHandler(
                        // 登录失败，返回 status 404 ， json 返回失败提示
                        authenticationFailureHandler).and()
                // 开启记住我功能， cookie 保存登录数据
                .rememberMe().rememberMeParameter(Page.User.LOGIN_REMEMBER_ME_STRING).and()

                .logout().logoutUrl(Api.User.LOGOUT).addLogoutHandler(logoutHandler).deleteCookies("JSESSIONID");

    }


}
