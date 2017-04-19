package com.mantou.common.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

/**
 * @author Niu Li
 * @date 2016/8/12
 */
@Configuration
@ConfigurationProperties(prefix="spring.datasource")
@Setter
@Getter
public class DataSourcesConfig {
    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Integer maxActive;
    private Integer maxWait;
    private Integer minIdle;
    private Integer initialSize;
    private String validationQuery;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean testWhileIdle;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer minEvictableIdleTimeMillis;
    private boolean PoolPreparedStatements;
    private Integer maxOpenPreparedStatements;
    private String filters;
    /**
     * druid初始化
     * @return
     * @throws SQLException
     */
    @Primary //默认数据源
    @Bean(name = "dataSource",destroyMethod = "close")
    public DruidDataSource Construction() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType(type);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //配置最大连接
        dataSource.setMaxActive(maxActive);
        //配置初始连接
        dataSource.setInitialSize(initialSize);
        //配置最小连接
        dataSource.setMinIdle(minIdle);
        //连接等待超时时间
        dataSource.setMaxWait(maxWait);
        //间隔多久进行检测,关闭空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接最小生存时间
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测是否有效的sql
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        //打开PSCache,并指定每个连接的PSCache大小
        dataSource.setPoolPreparedStatements(PoolPreparedStatements);
        dataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        //配置sql监控的filter
        dataSource.setFilters(filters);
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException("druid datasource init fail");
        }
        return dataSource;
    }

    /**
     * druid监控
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        //reg.addInitParameter("allow", "127.0.0.1");
        //reg.addInitParameter("deny","");
        reg.addInitParameter("loginUsername", "niuli");
        reg.addInitParameter("loginPassword", "123456");
        return reg;
    }

    /**
     * druid监控过滤
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
