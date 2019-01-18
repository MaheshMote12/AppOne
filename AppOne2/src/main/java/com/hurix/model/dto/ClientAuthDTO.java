package com.hurix.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hurix.serialize.ClientAuthDTODserializer;

import lombok.Data;

@Data
@JsonDeserialize(using=ClientAuthDTODserializer.class)
public class ClientAuthDTO {

	private int responseCode;
	private String userToken;
	private Long clientID;
	
}
