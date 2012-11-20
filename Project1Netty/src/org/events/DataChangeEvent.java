package org.events;

//----------------- Event --------------
public class DataChangeEvent {
  private String message;

  public DataChangeEvent(String message) {
      this.message = message;
  }

  public String getMessage() {
      return message;
  }


}
