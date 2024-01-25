package com.goodee.library.book;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BookDao {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(BookDao.class);

	@Autowired
	private SqlSession sqlSession;
	
	private final String namespace = "com.goodee.library.book.BookDao.";
	
	public int insertBook(BookVo vo) {
		LOGGER.info("[BookDao] insertBook();");
		int result = sqlSession.insert(namespace+"insertBook",vo);
		return result;
		 
	}
	
	public List<BookVo> selectBookList(BookVo vo){
		LOGGER.info("[BookDao] selectBookList();");
		List<BookVo> bookVos = sqlSession.selectList(namespace+"selectBookList",vo);
		return bookVos;
	}
	
	public int selectBookCount (String b_name) {
		LOGGER.info("[BookDao] selectBookCount();");
		int totalCount = sqlSession.selectOne(namespace+"selectBookCount",b_name);
		return totalCount;
	}
	
	public BookVo selectBookOne (int b_no) {
		LOGGER.info("[BookDao] selectBookOne();");
		BookVo vo = sqlSession.selectOne(namespace+"selectBookOne",b_no);
		return vo;
	}
	public int updateBook (BookVo vo) {
		LOGGER.info("[BookDao] updateBook();");
		int result = sqlSession.update(namespace+"updateBook",vo);
		return result;
	}
	public int deleteBook (int b_no) {
		LOGGER.info("[BookDao] deleteBook();");
		int result = sqlSession.delete(namespace+"deleteBook",b_no);
		return result; 
	}
}

	
