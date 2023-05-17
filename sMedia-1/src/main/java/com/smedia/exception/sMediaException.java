package com.smedia.exception;

import org.springframework.http.HttpStatus;

public class sMediaException extends RuntimeException{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private HttpStatus status;
		private String message;
		
		
		public sMediaException() {
			super();
		}
		public sMediaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
		public sMediaException(String message, Throwable cause) {
			super(message, cause);
		}
		public sMediaException(String message) {
			super(message);
		}
		public sMediaException(Throwable cause) {
			super(cause);
		}
		
		public sMediaException(HttpStatus status, String message) {
			super();
			this.status = status;
			this.message = message;
		}
		
		
		public HttpStatus getStatus() {
			return status;
		}
		public String getMessage() {
			return message;
		}
}
