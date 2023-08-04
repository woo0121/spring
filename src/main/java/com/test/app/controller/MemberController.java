package com.test.app.controller;

import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.app.domain.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/member")
public class MemberController {
	
	@InitBinder
	public void dataBinder(WebDataBinder dataBinder) {
		System.out.println("MemberController's dataBinder.." + dataBinder);
		//String("2022-01-01") -> LocalDate로 변환 Editor
		dataBinder.registerCustomEditor(LocalDate.class, "birthday",new MemberDtoEditor());
		//String[] 의 구분를 # 추가
		dataBinder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
	
	}
	@GetMapping("/join")
	public void get_memo() {
		log.info("GET /member/join");
	}
	
	@PostMapping("/join")
	public String post_memo(@Valid @ModelAttribute MemberDto dto,BindingResult bindingResult, Model model) {
		log.info("POST /member/j" + dto);
		if(bindingResult.hasFieldErrors()) {
			
			for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + ": " + error.getDefaultMessage());
                model.addAttribute(error.getField(),error.getDefaultMessage());
            }
			return "member/join";
		}
		
		return "";
	}

	@GetMapping("/list1")
	public void list1() throws Exception{
		System.out.println("GET /member/list1");
		throw new FileNotFoundException("파일없음");
	}
}

class MemberDtoEditor extends PropertyEditorSupport{
	//String -> Object
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("MemberDtoEditor's setAsText :" + text);
		setValue(LocalDate.parse(text,DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}
	
}

