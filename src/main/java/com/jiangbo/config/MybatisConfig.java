package com.jiangbo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.omg.PortableInterceptor.Interceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class MybatisConfig {

    @Value("${mysql.driver.class.name}")
    private String driverClassName;

    @Value("${mysql.username}")
    private String userName;

    @Value("${mysql.password}")
    private String password;

    @Value("${mysql.url}")
    private String mysqlUrl;

    /**
     * <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
     *  <property name="driverClass" value="${driverClass}"/>
     *  <property name="jdbcUrl" value="${jdbcUrl}"></property>
     *  <property name="user" value="${user}" />
     *  <property name="password" value="${password}"/>
     * </bean>
     * @return
     * @throws Exception
     */
    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() throws Exception{
        /*DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName("com.mysql.jdbc.Driver");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("0000");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf8");
        druidSettings(druidDataSource);
        return druidDataSource;*/

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName(driverClassName);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(mysqlUrl);
        druidSettings(druidDataSource);
        return druidDataSource;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(DruidDataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.jiangbo.mapper");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    /**
     * <bean  class="org.mybatis.spring.SqlSessionFactoryBean">
     *  <property name="dataSource" ref="dataSource" />
     *  <property name="typeAliasesPackage" value="com.study.bean"/>
     *  </bean>
     * @param dataSource
     * @param resourcePatternResolver
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DruidDataSource dataSource, ResourcePatternResolver resourcePatternResolver) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath:com/jiangbo/mapper/*.xml"));
        /**
         * 此处相当于mybatis.xml的配置
         */
        Properties mybatisProp = new Properties();
        mybatisProp.setProperty("logPrefix", "dao.");
        mybatisProp.setProperty("logImpl", "COMMONS_LOGGING");
        mybatisProp.setProperty("cacheEnabled", "true");
        mybatisProp.setProperty("useGeneratedKeys", "true");
        mybatisProp.setProperty("mapUnderscoreToCamelCase", "true");
        sqlSessionFactoryBean.setConfigurationProperties(mybatisProp);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.jiangbo.entity");
        return sqlSessionFactoryBean;
    }

    public void druidSettings(DruidDataSource druidDataSource) throws Exception{
        druidDataSource.setMaxActive(40);
        druidDataSource.setInitialSize(3);
        druidDataSource.setUseUnfairLock(Boolean.TRUE);
        druidDataSource.setMinIdle(10);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setPoolPreparedStatements(Boolean.FALSE);
        druidDataSource.setTestOnBorrow(Boolean.FALSE);
        druidDataSource.setTestOnReturn(Boolean.FALSE);
        druidDataSource.setTestWhileIdle(Boolean.TRUE);
        druidDataSource.setTimeBetweenEvictionRunsMillis(6000L);
        druidDataSource.setMinEvictableIdleTimeMillis(30000 * 4);
        druidDataSource.setRemoveAbandoned( Boolean.TRUE);
        druidDataSource.setRemoveAbandonedTimeout(180);
        druidDataSource.setLogAbandoned(Boolean.TRUE);
        druidDataSource.setFilters("stat");
        druidDataSource.setTimeBetweenLogStatsMillis( 60000 * 60); //每10分钟自动将监控的数据存储到日志中
        druidDataSource.setDriver(new Driver());
    }


}
