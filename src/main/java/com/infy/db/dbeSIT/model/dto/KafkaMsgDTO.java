package com.infy.db.dbeSIT.model.dto;

public class KafkaMsgDTO  implements AppDTOi{
	private String msgId;
	private String msgType;
	private String msgTo;
	private String msgBody;

	public KafkaMsgDTO() {
		super();
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(String msgTo) {
		this.msgTo = msgTo;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "KafkaMsgDTO [msgId=" + msgId + ", msgType=" + msgType + ", msgTo=" + msgTo + ", msgBody=" + msgBody
				+ "]";
	}
}
