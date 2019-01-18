package com.hurix.serialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.hurix.model.dto.ClientAuthDTO;

public class ClientAuthDTODserializer extends JsonDeserializer<ClientAuthDTO> {

	@Override
	public ClientAuthDTO deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		
		 ObjectCodec oc = jsonParser.getCodec();
		 JsonNode node = oc.readTree(jsonParser);
		
		 ClientAuthDTO dto = new ClientAuthDTO();
		 dto.setClientID(node.get("clientID") != null? node.get("clientID").asLong() : 0);
		 dto.setResponseCode( node.get("responseCode") !=null? node.get("responseCode").asInt(): 0);
		 dto.setUserToken(node.get("userToken") != null? node.get("userToken").asText() : "");
		
		return dto;
	}

}
