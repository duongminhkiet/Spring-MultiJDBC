package com.zmk.spring.test.rd.db3.db2.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmk.spring.test.rd.db3.db1.object.Book1;
import com.zmk.spring.test.rd.db3.db1.repository.Book1Repository;
import com.zmk.spring.test.rd.db3.db2.object.Book2;
import com.zmk.spring.test.rd.db3.db2.repository.Book2Repository;

@Service
public class Book2Service {
    
	@Autowired
	@Qualifier("namedParameterJdbcBook2Repository")
	private Book2Repository book2Repository;
    
	@Autowired
	@Qualifier("namedParameterJdbcBook1Repository")
	private Book1Repository book1Repository;
    

	@Transactional("sqlServer2Transaction")
	public void insertBook2Failed1() {
		insertBook2Failed();
	}
	
	public void insertBook2Failed1NoTS() {
		insertBook2Failed();
	}
	@Transactional("sqlServer2Transaction")
	public void callOneIndirectInsertBook2Failed() {
		callInsertBook2Failed();
	}
	public void callInsertBook2Failed() {
		insertBook2Failed();
	}
	
	public void insertBook1OK2Fail(){
    	insertBook1();
    	insertBook2Failed();
    }
    public void insertBook12OK(){
    	insertBook1();
    	insertBook2();
    }
    @Transactional//("sqlServer1Transaction,sqlServer2Transaction")
    public void insertBook1FailBook2Ok(){
    	List<Book2> books2 = Arrays.asList(
                new Book2("2F-1 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
                new Book2("2F-1 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
                new Book2("2F-1 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
                new Book2("2F-1 Jake Tapper, nhà phân tích của CNN", new BigDecimal("41.19"))
        );

        books2.forEach(book -> {
            System.out.print("saved book: "+book.getName());
            book2Repository.save(book);
        });
    	List<Book1> books = Arrays.asList(
                new Book1(1L,"2F-1 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
                new Book1(2L,"2F-1 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
                new Book1(3L,"2F-1 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
                new Book1(4L,"2F-1 Jake Tapper, nhà phân tích của CNN", new BigDecimal("41.19"))
        );

        books.forEach(book -> {
            System.out.print("saved book: "+book.getName());
            book1Repository.save(book);
        });
        
    }
    @Transactional("sqlServer1Transaction")
    public void insertBook1Failed(){
    	List<Book1> books = Arrays.asList(
                new Book1("2-1 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
                new Book1("2-1 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
                new Book1("2-1 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
                new Book1(null, new BigDecimal("41.19"))
        );

        books.forEach(book -> {
            System.out.print("saved book: "+book.getName());
            book1Repository.save(book);
        });
        
    }
    @Transactional("sqlServer2Transaction")
    public void insertBook2Failed(){
    	List<Book2> books = Arrays.asList(
                new Book2("2-1 insertBook2 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
                new Book2("2-1 insertBook2 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
                new Book2("2-1 insertBook2 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
                new Book2(null, new BigDecimal("41.19"))
        );

        books.forEach(book -> {
            System.out.print("saved book: "+book.getName());
            book2Repository.save(book);
        });
        
    }
    @Transactional("sqlServer1Transaction")
    public void insertBook1(){
    	List<Book1> books = Arrays.asList(
                new Book1("2-1 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
                new Book1("2-1 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
                new Book1("2-1 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
                new Book1("2-1 Jake Tapper, nhà phân tích của CNN", new BigDecimal("41.19"))
        );

        books.forEach(book -> {
            System.out.print("saved book: "+book.getName());
            book1Repository.save(book);
        });
        
    }
	
    @Transactional("sqlServer2Transaction")
    public void insertBook2(){
    	List<Book2> books = Arrays.asList(
                new Book2("2-1 insertBook2 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
                new Book2("2-1 insertBook2 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
                new Book2("2-1 insertBook2 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
                new Book2("2-1 insertBook2 Jake Tapper, nhà phân tích của CNN", new BigDecimal("41.19"))
        );

        books.forEach(book -> {
            System.out.print("saved book: "+book.getName());
            book2Repository.save(book);
        });
        
    }
}
