package com.work.workhub.admin.reserve.controller;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.admin.reserve.model.dto.MeetingRoomDTO;
import com.work.workhub.admin.reserve.model.service.RoomService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("asset/room")
public class RoomModifyController {
	
	private RoomService roomService;
	private MessageSource messageSource;
	
	
	@PostMapping("modify")
	public String modifyRoom(@RequestParam int roomNo, @ModelAttribute MeetingRoomDTO room, @RequestParam(value="singleFile") MultipartFile singleFile, HttpServletRequest request, Model model, RedirectAttributes rttr, Locale locale) throws Exception {
				
		log.info("수정될 룸 정보 결과값 : {}",room);
		
		MeetingRoomDTO oldRoom = roomService.selectRoomInfo(roomNo);
		
		if (singleFile != null) {
			/* 회의실 사진 등록 */
			log.info("singleFile : {}" + singleFile);
			
			// file 저장 경로 설정
			String root = request.getSession().getServletContext().getRealPath("resources");

			System.out.println("root : " + root);

			String savePath = root + "\\uploadFiles";

			File mkdir = new File(savePath);
			if (!mkdir.exists()) {
				mkdir.mkdirs();
			}

			// file name change
			String orgName = singleFile.getOriginalFilename();
			String ext = orgName.substring(orgName.lastIndexOf("."));
			String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

			// file Save
			try {
				singleFile.transferTo(new File(savePath + "\\" + savedName));
				model.addAttribute("message", "파일 업로드 성공!");

			} catch (IllegalStateException | IOException e) {
				model.addAttribute("message", "파일 업로드 실패!");
			}

			room.setOrgName(orgName);
			room.setSavedName(savedName);
			room.setSavePath(savePath);

			log.info("room info : {}", room);
			
			
			/* ★기존 있던 파일 삭제.*/
			File deleteFile = new File(savePath + "/" + savedName);
			boolean isDeleted = deleteFile.delete();
			
			if(isDeleted == true) {
				log.info("기존 파일 삭제 완료");
			} else {
				log.info("기존 파일 삭제 실패");
			}
			
		}
		
		roomService.modifyRoom(room);
		
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("modifyRoom", null, locale));
		
		return "redirect:/asset/room/list";
		
	}

}
