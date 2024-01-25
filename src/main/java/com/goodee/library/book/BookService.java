package com.goodee.library.book;

import java.util.List;

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


	public List<BookVo> selectBookList(BookVo vo){
		LOGGER.info("[BookService] selectBookList();");
		List<BookVo> bookVos = bookdao.selectBookList(vo);
		return bookVos;
	}
	
	public int selectBookCount(String b_name) {
		LOGGER.info("[BookService] selectBookCount();");
		int totalCount = bookdao.selectBookCount(b_name);
		return totalCount;
	}
	
	public BookVo bookDetail(int b_no) {
		LOGGER.info("[BookService] bookDetail();");
		BookVo vo = bookdao.selectBookOne(b_no);
		return vo;
	}
	
	public int modifyConfirm(BookVo vo) {
		LOGGER.info("[BookService] modifyConfirm();");
		return bookdao.updateBook(vo);
	}
	
	public int deleteBook(int b_no) {
		LOGGER.info("[BookService] deleteBook();");
		return bookdao.deleteBook(b_no);
	}

}