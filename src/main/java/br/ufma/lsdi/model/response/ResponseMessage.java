package br.ufma.lsdi.model.response;

public class ResponseMessage {
	private Integer status;
	private String message;
	private Object entity;
	
	public ResponseMessage() {

	}

	public ResponseMessage(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ResponseMessage(Integer status, String message, Object entity) {
		super();
		this.status = status;
		this.message = message;
		this.entity = entity;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

}