package com.zmk.spring.test.rd.db3.db1.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.spring.test.rd.db3.db1.object.Book1;
import com.zmk.spring.test.rd.db3.db1.repository.Book1Repository;


@Service
public class Book1Service {
	@Autowired
	@Qualifier("namedParameterJdbcBook1Repository")
	private Book1Repository book1Repository;
    
    @Transactional("sqlServer1Transaction")
    public void insertBook1(){
    	List<Book1> books = Arrays.asList(
                new Book1("2-1 insertBook1 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
                new Book1("2-1 insertBook1 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
                new Book1("2-1 insertBook1 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
                new Book1("2-1 insertBook1 Jake Tapper, nhà phân tích của CNN", new BigDecimal("41.19"))
        );

        books.forEach(book -> {
            System.out.print("saved book: "+book.getName());
            book1Repository.save(book);
        });
        
    }
}
