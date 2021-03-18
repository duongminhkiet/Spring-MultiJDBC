package com.zmk.spring.test.rd.db3.db1.repository;

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

import com.zmk.spring.test.rd.db3.db1.object.Book1;


@Repository("namedParameterJdbcBook1Repository") //default name = namedParameterJdbcBookRepository
public class NamedParameterJdbcBook1Repository extends JdbcBook1Repository {

    @Autowired
    @Qualifier("sqlServer1NamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostConstruct
    public void showField() {
    	System.out.print("\n NamedParameterJdbcTemplate in NamedParameterJdbcBook1Repository: "+namedParameterJdbcTemplate+"\n");
    }
    @Override
    public int update(Book1 book) {
        return namedParameterJdbcTemplate.update(
                "update books set price = :price where id = :id",
                new BeanPropertySqlParameterSource(book));
    }

    @Override
    public Optional<Book1> findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from books where id = :id",
                new MapSqlParameterSource("id", id),
                (rs, rowNum) ->
                        Optional.of(new Book1(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        ))
        );
    }

    @Override
    public List<Book1> findByNameAndPrice(String name, BigDecimal price) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", "%" + name + "%");
        mapSqlParameterSource.addValue("price", price);

        return namedParameterJdbcTemplate.query(
                "select * from books where name like :name and price <= :price",
                mapSqlParameterSource,
                (rs, rowNum) ->
                        new Book1(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getBigDecimal("price")
                        )
        );
    }

}
