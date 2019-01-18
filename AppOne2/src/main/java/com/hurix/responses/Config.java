package com.hurix.responses;


import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@XmlRootElement
@Getter @Setter @ToString
public class Config {

	private Mode online_transfer;
	private Mode offline_transfer;
	private Mode test_online_transfer;
	private Mode test_offline_transfer;
	private String isEncrypted;
	
	
	
}
