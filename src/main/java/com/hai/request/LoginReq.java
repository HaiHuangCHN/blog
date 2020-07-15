package com.hai.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.hai.common.Constants;

public class LoginReq {
	@NotBlank(message = Constants.ERROR_MSG_2)
	private String username;
	@Size(min = 8, max = 30, message = Constants.ERROR_MSG_1)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
