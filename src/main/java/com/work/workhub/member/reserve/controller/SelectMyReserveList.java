package com.work.workhub.member.reserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("reserve")
public class SelectMyReserveList {
	
	@GetMapping("mylist")
	public void selectMyReserveList() {
		
	}

}
