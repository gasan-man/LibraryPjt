package com.goodee.library.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.library.util.UploadFileService;

@Controller
@RequestMapping("/book")
public class BookController {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	UploadFileService uploadFileService;
	
	@Autowired
	BookService bookService;
	
	//도서 등록 화면 이동
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createBookForm() {
		LOGGER.info("[BookController] createBookForm();");
		return "book/create";
	}
	
	//도서 등록 기능
	@RequestMapping(value="create", method=RequestMethod.POST)
	public String createBookConfirm(BookVo vo, @RequestParam("file") MultipartFile file) {
		LOGGER.info("[BookController] createBookConfirm();");
		int result = -1;
		// 1. 파일 파싱(UploadFileService)
		String savedFileName = 	uploadFileService.upload(file);
		// 2. 도서 등록
		if(savedFileName != null && "".equals(savedFileName) == false) {
			vo.setB_thumbnail(savedFileName);
			result = bookService.createBookConfirm(vo);
			
			// 1. BookService 빈 등록
			// 2. BookController에 BookService 의존성 주입
			// 3. BookService 도서 등록 메소드
			// 4. BookDao 데이터베이스에 도서 등록(Mapper한테 부탁)
			// 5. book_mapper.xml 생성
			// 6. insert구문 작성
			// 7. 테이블 tbl_book(단톡에 있는 쿼리)
		}
		if(result > 0) {
			return "book/create_success";
		}else
			return "book/create_fail";
		// 3. 결과 화면 전환
		
		// webapp/resources/css/book -> create_result.css
		// webapp/WEB-INF-views/book -> create_success.jsp
		// webapp/WEB-INF-views/book -> create_fail.jsp
	}
}
