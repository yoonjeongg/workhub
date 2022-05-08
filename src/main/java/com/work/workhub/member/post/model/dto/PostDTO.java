package com.work.workhub.member.post.model.dto;

import java.sql.Date;

import com.work.workhub.member.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class PostDTO {
	
	private int postNo;
	private String postTitle;
	private String postContent;
	private Date postDate; 
	private Date postUpdate;
	private Date postEndDate;
	private String postStatus;
	private int No;
	private int categoryNo;

}