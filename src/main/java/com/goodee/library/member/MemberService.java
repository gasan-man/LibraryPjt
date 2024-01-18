package com.goodee.library.member;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	private Logger LOGGER = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	MemberDao dao;

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
		// 3. 생성된 비밀번호 업데이트
		// 4. 메일 보내기

		}

		return result;
	}
}
