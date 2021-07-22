package com.guild.simple.chat.exception;

public class InvalidRequestException extends RuntimeException{
	private static final long serialVersionUID = 2671571197542942959L;
	private String key;

    public InvalidRequestException(String key, String message) {
        super(message);
        this.key = key;
    }

}
