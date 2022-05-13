package com.work.workhub.admin.work.model.service;

import java.util.List;

import com.work.workhub.admin.work.model.dto.MemberGroupDTO;
import com.work.workhub.admin.work.model.dto.WorkGroupDTO;
import com.work.workhub.member.member.dto.MemberDTO;


public interface MemberGroupService {
	
	/* 근무그룹 select box*/
	List<WorkGroupDTO> selectAllbox();
	
	/*사원 근무그룹 등록*/
	boolean memberGroupWrite(MemberGroupDTO post) throws Exception;
	
	//사원 아이디 검색으로 근무그룹 조회
	List<MemberDTO> selectAllmemberGroup(int no);
	
	
	
	

	

	

}
