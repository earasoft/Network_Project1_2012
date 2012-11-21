package org.ques2;

import java.io.Serializable;

public class MessageObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String SystemTime;
	String IntgerSequence;
	String OneLineMessage;
	
	
	
	public MessageObject(String systemTime, String intgerSequence,
			String oneLineMessage) {
		super();
		SystemTime = systemTime;
		IntgerSequence = intgerSequence;
		OneLineMessage = oneLineMessage;
	}

	public String getSystemTime() {
		return SystemTime;
	}

	public void setSystemTime(String systemTime) {
		SystemTime = systemTime;
	}

	public String getIntgerSequence() {
		return IntgerSequence;
	}

	public void setIntgerSequence(String intgerSequence) {
		IntgerSequence = intgerSequence;
	}

	public String getOneLineMessage() {
		return OneLineMessage;
	}

	public void setOneLineMessage(String oneLineMessage) {
		OneLineMessage = oneLineMessage;
	}

	@Override
	public String toString() {
		return "MessageObject [SystemTime=" + SystemTime + ", IntgerSequence="
				+ IntgerSequence + ", OneLineMessage=" + OneLineMessage + "]";
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
