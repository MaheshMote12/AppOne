package com.hurix.client;

import com.hurix.model.dto.ClientAuthDTO;

public interface AuthClient {

	public ClientAuthDTO authenticateClient(String username, String password);
}
