package com.zmk.spring.test.rd.db3.db1.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.zmk.spring.test.rd.db3.db1.object.Book1;

public interface Book1Repository {
    int count();

    int save(Book1 book);

    int update(Book1 book);

    int deleteById(Long id);

    List<Book1> findAll();

    List<Book1> findByNameAndPrice(String name, BigDecimal price);

    Optional<Book1> findById(Long id);

    String getNameById(Long id);
}
