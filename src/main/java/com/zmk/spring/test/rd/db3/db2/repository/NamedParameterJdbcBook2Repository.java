package com.zmk.spring.test.rd.db3.db2.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zmk.spring.test.rd.db3.db2.object.Book2;

@Repository("namedParameterJdbcBook2Repository") //default name = namedParameterJdbcBookRepository
public class NamedParameterJdbcBook2Repository extends JdbcBook2Repository {

    @Autowired
    @Qualifier("sqlServer2NamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @PostConstruct
    public void showField() {
    	System.out.print("\n NamedParameterJdbcTemplate in NamedParameterJdbcBook2Repository: "+namedParameterJdbcTemplate+"\n");
    }
    
    @Override
    public int update(Book2 book) {
        return namedParameterJdbcTemplate.update(
                "update books set price = :price where id = :id",
                new BeanPropertySqlParameterSource(book));
    }

    @Override
    public Optional<Book2> findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from books where id = :id",
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        Optional.of(new Book2(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        ))
        );
    }

    @Override
    public List<Book2> findByNameAndPrice(String name, BigDecimal price) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", "%" + name + "%");
        mapSqlParameterSource.addValue("price", price);

        return namedParameterJdbcTemplate.query(
                "select * from books where name like :name and price <= :price",
                mapSqlParameterSource,
                (rs, rowNum) ->
                        new Book2(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        )
        );
    }

}

