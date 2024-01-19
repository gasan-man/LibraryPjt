package com.goodee.library.member;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	private Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	MemberDao dao;
	
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;

	public int createMember(MemberVo vo) {
		LOGGER.info("[MemberService] createMember();");
		int result = 0;
		if (dao.isMemberCheck(vo.getM_id()) == false) {
			result = dao.insertMember(vo);
		}
		return result;
	}

	public MemberVo loginMember(MemberVo vo) {
		LOGGER.info("[MemberService] loginMember();");
		MemberVo loginedMember = dao.selectMember(vo);
		return loginedMember;
	}

	public List<MemberVo> listupMember() {
		LOGGER.info("[MemberService] listupMember();");
		return dao.selectMemberList();
	}

	public int modifyMember(MemberVo vo) {
		LOGGER.info("[MemberService] modifyMember();");
		return dao.updateMember(vo);
	}

	// 회원 단일 정보 조회
	public MemberVo getLoginedMemberVo(int m_no) {
		LOGGER.info("[MemberService] getLoginedMemberVo();");
		return dao.selectMemberOne(m_no);
	}

	public int findPasswordConfirm(MemberVo vo) {
		// 1. 입력한 정보와 일치하는 사용자가 있는지 확인
		LOGGER.info("[MemberService] findPasswordConfirm();");
		MemberVo selectedMember = dao.selectMemberOne(vo);
		int result = 0;
		if (selectedMember != null) {
		// 2. 새로운 비밀번호 생성
			String newPassword = createNewPassword();
		// 3. 생성된 비밀번호 업데이트
			result = dao.updatePassword(vo.getM_id(),newPassword);
			if(result > 0) {
				// 4. 메일 보내기
				sendNewPasswordByMail(vo.getM_mail(),newPassword);
				
			}
		}
		return result;
	}
	
	//회원가입, 로그인, 로그아웃, 목록, 비밀번호 수정, 회원정보 수정
	
	
	
	//메일로 비밀번호 보내기
	private void sendNewPasswordByMail(String toMailAddr, String newPw) {
		LOGGER.info("[MemberService] sendNewPasswordByMail();");
		
		final MimeMessagePreparator mime = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception{
				final MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, true,"UTF-8");
				mimeHelper.setTo(toMailAddr);
				mimeHelper.setSubject("[구디 도서관] 새로운 비밀번호 안내입니다.");
				mimeHelper.setText("새 비밀번호 : "+newPw, true);
			}
		};
		javaMailSenderImpl.send(mime);
	}
	
//	// 메일로 비밀번호 보내기
//	private void sendNewPasswordByMail(String toMailAddr, String newPw) {
//		LOGGER.info("[MemberService] sendNewPasswordByMail();");
//		
//		final MimeMessagePreparator mime = new MimeMessagePreparator() {
//			
//			@Override
//			public void prepare(MimeMessage mimeMessage) throws Exception{
//				final MimeMessageHelper mimeHelper = new MimeMessagerHelper(mimeMessage, ture, "UTF-8");
//				mimeHelper.setTo(toMailAddr);
//				mimeHelper.setSubject("[구디 도서관] 새로운 비밀번호 안내입니다.");
//				mimeHelper.setText("새 비밀번호 : "+newPw,true);
//				
//			}
//		};
//		javaMailSenderImpl.send(mime);
//		
//	}
	
	// 비밀번호 생성
	private String createNewPassword() {
		LOGGER.info("[MemberService] createNewPassword();");
		char[] chars = new char[] {
			    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
			    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
			    'u', 'v', 'w', 'x', 'y', 'z'
			         };
		StringBuffer sb = new StringBuffer();
		SecureRandom sr = new SecureRandom();
		sr.setSeed(new Date().getTime());
		int index = 0;
		int length = chars.length;
		for(int i = 0 ; i < 8 ; i++) {
			index = sr.nextInt(length);
			if(index % 2 == 0) {
			// sb.append(chars[index]);
			// sb.append(String.valueOf(chars[index]));	
			 sb.append(String.valueOf(chars[index]).toUpperCase());
			} else {
				sb.append(String.valueOf(chars[index]).toLowerCase());
			}
		}
		return sb.toString();
	}
}
