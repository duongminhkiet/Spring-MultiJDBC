package com.zmk.spring.test.rd.db3.db2;

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
public class MultipleDB2Config {
	@Bean(name = "sqlServer2")
	@ConfigurationProperties(prefix = "spring.datasource2")
//	@Primary
	public DataSource sqlServer2DS() {
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "sqlServer2JdbcTemplate")
	public JdbcTemplate sqlServer2JdbcTemplate(@Qualifier("sqlServer2") 
                                              DataSource sqlServer2) {
		return new JdbcTemplate(sqlServer2);
	}
	
	@Bean(name = "sqlServer2NamedParameterJdbcTemplate")
//    @DependsOn("sqlServer2")
//	@Scope("prototype")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate2(@Qualifier("sqlServer2") DataSource sqlServer2) {
        return new NamedParameterJdbcTemplate(sqlServer2);
    }
	
    @Bean(name="sqlServer2Transaction")
    @Autowired
    @Primary
    DataSourceTransactionManager tm2(@Qualifier ("sqlServer2") DataSource datasource) {
        DataSourceTransactionManager txm  = new DataSourceTransactionManager(datasource);
        return txm;
    }
}
