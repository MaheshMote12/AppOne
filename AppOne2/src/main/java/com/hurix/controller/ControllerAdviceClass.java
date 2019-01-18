package com.hurix.controller;

import java.net.UnknownHostException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations=Controller.class)
public class ControllerAdviceClass {
	
	@ExceptionHandler( Exception.class)
	public String error() {
		return "error";
	}

}
