package com.zxn.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shiro")
public class ShiroController {

	@RequestMapping("login")
	public String login(String username,String password){
		
		
		Subject subject = SecurityUtils.getSubject();
		
		if(!subject.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			System.out.println("login   "+token.hashCode());
			token.setRememberMe(true);
			try {
				subject.login(token);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		return "redirect:/list.jsp";
	}
}
