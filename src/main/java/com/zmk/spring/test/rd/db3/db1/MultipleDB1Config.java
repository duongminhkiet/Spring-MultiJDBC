package com.zmk.spring.test.rd.db3.db1;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class MultipleDB1Config {
	@Bean(name = "sqlServer1")
	@ConfigurationProperties(prefix = "spring.datasource1")
	public DataSource sqlServer1DS() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "sqlServer1JdbcTemplate")
	public JdbcTemplate sqlServer1JdbcTemplate(@Qualifier("sqlServer1") DataSource sqlServer1) {
		return new JdbcTemplate(sqlServer1);
	}
	
	@Bean(name = "sqlServer1NamedParameterJdbcTemplate")
//    @DependsOn("sqlServer1")
//	@Scope("prototype")
	@Primary
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate1(@Qualifier("sqlServer1") DataSource sqlServer1) {
        return new NamedParameterJdbcTemplate(sqlServer1);
    }
	
    @Bean(name="sqlServer1Transaction")
    @Autowired
    @Primary
    DataSourceTransactionManager tm1(@Qualifier ("sqlServer1") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
        return txm;
    }
}
