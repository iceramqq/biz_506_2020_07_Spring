package com.biz.book.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.biz.book.mapper.BookDao;
import com.biz.book.model.BookVO;
import com.biz.book.model.ReadBookVO;

import lombok.extern.slf4j.Slf4j;

/*
 * @Transactional annotation 을 ㅋ르래스 채원에서 설정하면
 * 현재 클래스의 모든 method에서 db와 연동되는 부분이
 * 자동으로 transaction이 작동된다
 */

@SessionAttributes("bookVO")
@Transactional
@Slf4j
@Controller
@RequestMapping(value = "/books")
public class BooksController {

	@Autowired
	private BookDao bookDao;

	@ModelAttribute("bookVO")
	public BookVO newBookVO() {
		LocalDate localDate = LocalDate.now();
		String todayString = DateTimeFormatter
								.ofPattern("YYYY-MM-dd")
								.format(localDate);
		
		BookVO bookVO = BookVO.builder()
							.buydate(todayString)
							.buyprice(10000)
							.build();
		return bookVO;
	};
	/*
	 * sqlsessionTemplate 대신
	 * 이것을 mybatis-context.xml에 만들오주고
	 * appServlet/tx-context.xml 을 만들고
	 * 
	 */
	
	// localhost:8080/book/books
	// localhost:8080/book/books/
	// @ResponseBody
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String list(Model model) {

		List<BookVO> bookList = bookDao.selectAll();
		model.addAttribute("BOOKS", bookList);
		model.addAttribute("BODY","BOOK-LIST");
		return "home";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(@ModelAttribute("bookVO") BookVO bookVO, Model model) {
		
		
		model.addAttribute("BODY","BOOK-WRITE");
		model.addAttribute("bookVO", bookVO);
		return "home";
		
		// controller의 mapping mathod의 return type이 string일때
		// null 값을 return 하면
		// method를 호출할때 상용했던 mapping
		// 자동으로 생성된다
		// return null;
	}
	
	/*
	 * spring form tagllib를 사용하여 write form을 만들었
	 */
	@RequestMapping(value = "/input",method = RequestMethod.POST)
	public String input(@ModelAttribute("bookVO") BookVO bookVO,SessionStatus status) {
		log.debug(bookVO.toString());
		
		int ret = bookDao.insert(bookVO);
		if(ret < 1) {
			// insert가 실패햇으므로 그에 대한 메시지를 보여주는 페이지로 jump
		}
		
		/*
		 * sessionattributes를 controller에 설정했을 경우
		 * 입력박스에 담긴값을 post받아 db에 반영환 후에는
		 * 반드시 sessionStatus
		 */
		status.setComplete();
		return "redirect:/books";
	}
	
	//localhost:8080/book/books/detail/3 이라고 request가 오면
	// 맨 끝의 숫자 3을 mapping 주소의 {book_seq}위치에 mapping한다
	//
	// 3의 값이 할당되어 method에 전달된다
	
	@RequestMapping(value = "/detail/{book_seq}",method = RequestMethod.GET)
	public String detail(@PathVariable("book_seq") String id, Model model) {
		
		log.debug("PATH : {}",id);
		long seq = Long.valueOf(id);
		BookVO bookVO = bookDao.findById(seq);
		model.addAttribute("BOOKVO",bookVO);
		
		LocalDateTime lDateTime = LocalDateTime.now();
		String lDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(lDateTime);
		String lTime = DateTimeFormatter.ofPattern("HH:mm:SS").format(lDateTime);
		
		ReadBookVO readBookVO = ReadBookVO.builder().r_date(lDate).r_stime(lTime).build();
		model.addAttribute("readBookVO",readBookVO);
		model.addAttribute("BODY","BOOK-DETAIL");
		return "home";
	}
	
	@RequestMapping(value = "/delete/{seq}",method = RequestMethod.GET)
	public String delete(@PathVariable("seq") String seq, @ModelAttribute("bookVO") BookVO bookVO) {
		
		long id = Long.valueOf(seq);
		bookDao.delete(id);
		
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/update/{seq}",method = RequestMethod.GET)
	public String delete(@PathVariable("seq") String seq, @ModelAttribute("bookVO") BookVO bookVO,Model model) {
		
		long id = Long.valueOf(seq);
		bookVO = bookDao.findById(id);
		model.addAttribute("bookVO",bookVO);
		model.addAttribute("BODY","BOOK-WRITE");
		
		return "home";
	}
	
	@RequestMapping(value = "/update/{seq}",method = RequestMethod.POST)
	public String delete(@PathVariable("seq") String seq, @ModelAttribute("bookVO") BookVO bookVO,Model model,SessionStatus status) {
		
		bookDao.update(bookVO);
		status.setComplete();
		return "redirect:/books/detail/"+seq;
	}
}
