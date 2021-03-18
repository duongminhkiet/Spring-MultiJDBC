package com.zmk.spring.test.rd.db3.db2.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.zmk.spring.test.rd.db3.db2.object.Book2;

public interface Book2Repository {
    int count();

    int save(Book2 book);

    int update(Book2 book);

    int deleteById(Long id);

    List<Book2> findAll();

    List<Book2> findByNameAndPrice(String name, BigDecimal price);

    Optional<Book2> findById(Long id);

    String getNameById(Long id);
}
