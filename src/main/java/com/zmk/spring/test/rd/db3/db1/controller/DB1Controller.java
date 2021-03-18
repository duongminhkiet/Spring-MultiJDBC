package com.zmk.spring.test.rd.db3.db1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmk.spring.test.rd.db3.db1.service.Book1Service;

@RestController
public class DB1Controller {
	@Autowired
	private Book1Service bookService1;
	
    @GetMapping("/api/db1/insertBook1Ok")
    public void insertBook1Ok() {
         bookService1.insertBook1();
    }
}
