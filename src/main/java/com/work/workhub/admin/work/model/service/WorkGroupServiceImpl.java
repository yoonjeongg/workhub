package com.work.workhub.admin.work.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.work.workhub.admin.work.model.dao.WorkMapper;
import com.work.workhub.admin.work.model.dto.WorkGroupDTO;




@Service("workgroupService")
@Transactional
public class WorkGroupServiceImpl implements WorkGroupService{
	
	private final WorkMapper workMapper;
	
	@Autowired
	public WorkGroupServiceImpl(WorkMapper workMapper) {
		this.workMapper = workMapper;
	}
	
	//근무 그룹 조회
	@Override
	public List<WorkGroupDTO> selectAllworkGroup() {
		
		return workMapper.workGroupSelect();
	}
	
	//근무 그룹 작성
	@Override
	public boolean workGroupWrite(WorkGroupDTO post) throws Exception {
		
        int result = workMapper.workGroupWrite(post);
		
		if(result <= 0) {
			throw new Exception("게시글 등록에 실패하였습니다.");
		}
		
		return result > 0 ? true : false;
		
	}
	
	//근무그룹조회 use='사용'
//	@Override
//	public List<WorkGroupDTO> selectAllworkGroups() {
//		
//		return workMapper.workGroupSelects();
//	}



	
	
	



}
