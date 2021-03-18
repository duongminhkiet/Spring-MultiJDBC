package com.zmk.spring.test.rd.db3;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zmk.spring.test.rd.db3.db1.object.Book1;
import com.zmk.spring.test.rd.db3.db1.repository.Book1Repository;
import com.zmk.spring.test.rd.db3.db2.object.Book2;
import com.zmk.spring.test.rd.db3.db2.repository.Book2Repository;

@SpringBootApplication
public class SpringMultiDbJdbcTemplate3Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringMultiDbJdbcTemplate3Application.class, args);
	}

	//start db1
	@Autowired
	@Qualifier("sqlServer1JdbcTemplate")
	JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("namedParameterJdbcBook1Repository") // jdbcBook1Repository
	private Book1Repository book1Repository;
	//end db1
	
	//start db2
	@Autowired
	@Qualifier("sqlServer2JdbcTemplate")
	JdbcTemplate jdbcTemplate2;

	@Autowired
	@Qualifier("namedParameterJdbcBook2Repository") // jdbcBook1Repository
	private Book2Repository book2Repository;
	//end db2
	
    @Autowired
    @Qualifier("sqlServer2NamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate2;
    
    @Autowired
    @Qualifier("sqlServer1NamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate1;
	@Override
	public void run(String... args) throws Exception {
		System.out.print("\n namedParameterJdbcTemplate1: "+namedParameterJdbcTemplate1+"\n");
		System.out.print("\n namedParameterJdbcTemplate2: "+namedParameterJdbcTemplate2+"\n");
		
		System.out.print("\n book1Repository: "+book1Repository+"\n");
		System.out.print("\n book2Repository: "+book2Repository+"\n");
		runJDBC1();
		runJDBC2();
	}
	void runJDBC1() {
        jdbcTemplate1.execute("DROP TABLE  IF EXISTS books");
        jdbcTemplate1.execute("CREATE TABLE books(" +
                "id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, name NVARCHAR(255), price NUMERIC(15, 2))");

//        List<Book1> books = Arrays.asList(
//                new Book1("1 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
//                new Book1("1 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
//                new Book1("1 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
//                new Book1("1 Jake Tapper, nhà phân tích của CNN", new BigDecimal("41.19"))
//        );
//
//        books.forEach(book -> {
//            System.out.print("saved book: "+book.getName());
//            book1Repository.save(book);
//        });
	}
	void runJDBC2() {
        jdbcTemplate2.execute("DROP TABLE  IF EXISTS books");
        jdbcTemplate2.execute("CREATE TABLE books(" +
                "id INT NOT NULL IDENTITY(1,1) PRIMARY KEY, name NVARCHAR(255) NOT NULL, price NUMERIC(15, 2))");

//        List<Book2> books = Arrays.asList(
//                new Book2("2 Trump hỏi người điều hành Kristen Welker", new BigDecimal("46.32")),
//                new Book2("2 Biden phản bác đòn tấn công", new BigDecimal("1.99")),
//                new Book2("2 Đòn công kích Trump sử dụng trong suốt cuộc đấu", new BigDecimal("37.3")),
//                new Book2("2 Jake Tapper, nhà phân tích của CNN", new BigDecimal("41.19"))
//        );
//
//        books.forEach(book -> {
//            System.out.print("saved book: "+book.getName());
//            book2Repository.save(book);
//        });
	}
}
