package com.guild.simple.chat.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ExceptionResponse {
	
    private String returnCode;
    private String returnMessage;

    public ExceptionResponse() {
    }

	/**
	 * @return the returnCode
	 */
	public String getReturnCode() {
		return returnCode;
	}

	/**
	 * @param returnCode the returnCode to set
	 */
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * @return the returnMessage
	 */
	public String getReturnMessage() {
		return returnMessage;
	}

	/**
	 * @param returnMessage the returnMessage to set
	 */
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

}
