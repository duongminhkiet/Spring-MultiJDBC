package com.zmk.spring.test.rd.db3.db2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zmk.spring.test.rd.db3.db2.service.Book2Service;

@RestController
public class DB2Controller {
	@Autowired
	private Book2Service bookService2;
	
    @GetMapping("/api/db2/insertBook2Ok")
    public void insertBook2Ok() {
         bookService2.insertBook2();
    }
    @GetMapping("/api/db2/insertBook2Failed1NoTS1")
    public void insertBook2Failed1NoTS() {
    	//insert 3, fail 1, false TS
         bookService2.insertBook2Failed1NoTS();
    }
    @GetMapping("/api/db2/insertBook2FailedTS2")
    public void insertBook2FailedTS2() {
    	//fail 4, true TS
         bookService2.insertBook2Failed();
    }
    @GetMapping("/api/db2/callOneIndirectInsertBook2FailedTS3")
    public void callOneIndirectInsertBook2FailedTS3() {
    	//failed 4, ~true TS
         bookService2.callOneIndirectInsertBook2Failed();
    }
    @GetMapping("/api/db2/insertBook12Ok")
    public void insertBook12Ok() {
         bookService2.insertBook12OK();
    }
    @GetMapping("/api/db2/insertBook1OK2Fail")
    public void insertBook1OK2Fail() {
         bookService2.insertBook1OK2Fail();
    }
}
