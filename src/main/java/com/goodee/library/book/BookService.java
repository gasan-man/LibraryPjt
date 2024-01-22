package com.goodee.library.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	private Logger LOGGER = LoggerFactory.getLogger(BookService.class);

@Autowired 
BookDao bookdao;

public int createBookConfirm(BookVo vo) {
	LOGGER.info("[BookService] createConfirm();");
	int result = bookdao.insertBook(vo);
	return result;
}
}
