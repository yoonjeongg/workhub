package com.work.workhub.member.message.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.work.workhub.member.member.dto.DepartmentDTO;
import com.work.workhub.member.member.dto.MemberDTO;
import com.work.workhub.member.member.dto.UserImpl;
import com.work.workhub.member.message.model.dto.MessageDTO;
import com.work.workhub.member.message.model.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {
	
	private MessageService messageService;
	private MessageSource messageSource;
	
	@Autowired
	public MessageController(MessageService messageService, MessageSource messageSource) {
		this.messageService = messageService;
		this.messageSource = messageSource;
	}
	
	
	@GetMapping("inboxList")
	public ModelAndView findMessageInboxList(ModelAndView mv, @AuthenticationPrincipal UserImpl user) {
		
		List<MessageDTO> messageInboxList = messageService.findMessageInbox();
		
		mv.addObject("messageInboxList", messageInboxList);
		mv.setViewName("message/inboxList");
		
		return mv;
		
	}
	
	@GetMapping("sentList")
	public ModelAndView findMessageSentList(ModelAndView mv) {
		
		List<MessageDTO> messageSentList = messageService.findMessageSent();
		
		
		mv.addObject("messageSentList", messageSentList);
		mv.setViewName("message/sentList");
		
		return mv;
	}
	
	@GetMapping("recyclebinList")
	public ModelAndView findMessageRecyclebinList(ModelAndView mv) {
		
		List<MessageDTO> messageRecyclebinList = messageService.findMessageRecyclebinList();
		
		mv.addObject("messageRecyclebinList", messageRecyclebinList);
		mv.setViewName("message/recyclebinList");
		
		return mv;
	}
	@GetMapping("sendMessage")
	public ModelAndView sendMessagePage(ModelAndView mv) {
		
		List<DepartmentDTO> departmentList = messageService.selectDepartmentList();
		List<MemberDTO> memberList = messageService.selectMemberList();
		
		mv.addObject("departmentList", departmentList);
		mv.addObject("memberList", memberList);
		mv.setViewName("message/sendMessage");
		
		log.info("부서 목록 : {}", departmentList);
		log.info("사원 목록 : {}", memberList);
		
		return mv;
	}
	
	@PostMapping("sendMessage")
	public String sendMessage(@ModelAttribute MessageDTO message, RedirectAttributes rttr, Locale locale) throws Exception {
	
		messageService.sendMessage(message);
		rttr.addFlashAttribute("successMessage", messageSource.getMessage("sendMessage", null, locale));
		
		return "redirect:/message/inboxList";
	}

}