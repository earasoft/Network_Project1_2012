package org.ques2;

import java.io.Serializable;

public class MessageObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String PreviousSystemTime;
	private String SystemTime;
	private Integer IntgerSequence;
	private String OneLineMessage;
	private Enum<?> MessageCurrentState;
	
	public Enum<?> getMessageCurrentState() {
		return MessageCurrentState;
	}
	
	public MessageObject(Integer intgerSequence,
			String oneLineMessage) {
		super();
		IntgerSequence = intgerSequence;
		OneLineMessage = oneLineMessage;
		
		if(MessageCurrentState==null){
			MessageCurrentState=MessageState.INTIAL;
		}
		
	}

	public MessageObject() {
		super();		
		if(MessageCurrentState==null){
			MessageCurrentState=MessageState.INTIAL;
		}
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void ChangeStateToAck(){
		MessageCurrentState=MessageState.ACK;
	}
	
	
	public void setSystemTimeCurrentTime() {
		PreviousSystemTime=this.SystemTime;
		SystemTime = System.currentTimeMillis()+"";
	}
	
	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}

	public Integer getIntgerSequence() {
		return IntgerSequence;
	}

	public void setIntgerSequence(Integer intgerSequence) {
		IntgerSequence = intgerSequence;
	}
	
	public void increaseIntegerByOne(){
		IntgerSequence=IntgerSequence+1;
	}

	public String getOneLineMessage() {
		return OneLineMessage;
	}

	public void setOneLineMessage(String oneLineMessage) {
		OneLineMessage = oneLineMessage;
	}

	public String getPreviousSystemTime() {
		return PreviousSystemTime;
	}

	public void setPreviousSystemTime(String previousSystemTime) {
		PreviousSystemTime = previousSystemTime;
	}

	@Override
	public String toString() {
		return "MessageObject [PreviousSystemTime=" + PreviousSystemTime
				+ ", SystemTime=" + SystemTime + ", IntgerSequence="
				+ IntgerSequence + ", OneLineMessage=" + OneLineMessage
				+ ", MessageCurrentState=" + MessageCurrentState + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IntgerSequence == null) ? 0 : IntgerSequence.hashCode());
		result = prime * result
				+ ((OneLineMessage == null) ? 0 : OneLineMessage.hashCode());
		result = prime * result
				+ ((SystemTime == null) ? 0 : SystemTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageObject other = (MessageObject) obj;
		if (IntgerSequence == null) {
			if (other.IntgerSequence != null)
				return false;
		} else if (!IntgerSequence.equals(other.IntgerSequence))
			return false;
		if (OneLineMessage == null) {
			if (other.OneLineMessage != null)
				return false;
		} else if (!OneLineMessage.equals(other.OneLineMessage))
			return false;
		if (SystemTime == null) {
			if (other.SystemTime != null)
				return false;
		} else if (!SystemTime.equals(other.SystemTime))
			return false;
		return true;
	}
	

}
