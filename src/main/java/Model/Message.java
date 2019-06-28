package Model;


import java.io.Serializable;

public class Message implements Serializable {
  private User sender;
  private User reciever;
  private String time;
  private String subject;
  private String text;
  private String attachment;
  private boolean sent;
  private boolean recieved;
  private boolean readed;
  private boolean important;
  private boolean removed;

  public Message(User sender, User reciever, String time, String subject, String text) {
    this.sender = sender;
    this.reciever = reciever;
    this.time = time;
    this.subject = subject;
    this.text = text;
    this.important = false;
  }

  public Message(User sender, User reciever, String time, String subject, String text,
      String attachment) {
    this.sender = sender;
    this.reciever = reciever;
    this.time = time;
    this.subject = subject;
    this.text = text;
    this.attachment = attachment;
    this.important = false;
  }

  public String getTime() {
    return time;
  }

  public String getSubject() {
    return subject;
  }

  public String getText() {
    return text;
  }

  public boolean isReaded() {
    return readed;
  }

  public boolean isImportant() {
    return important;
  }

  public User getSender() {
    return sender;
  }

  public void setSender(User sender) {
    this.sender = sender;
  }

  public void setReciever(User reciever) {
    this.reciever = reciever;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getAttachment() {
    return attachment;
  }

  public void setAttachment(String attachment) {
    this.attachment = attachment;
  }

  public boolean isSent() {
    return sent;
  }

  public void setSent(boolean sent) {
    this.sent = sent;
  }

  public boolean isRecieved() {
    return recieved;
  }

  public void setRecieved(boolean recieved) {
    this.recieved = recieved;
  }

  public void setReaded(boolean readed) {
    this.readed = readed;
  }

  public boolean isRemoved() {
    return removed;
  }

  public void setRemoved(boolean removed) {
    this.removed = removed;
  }

  public User getReciever() {
    return reciever;
  }

  public void setImportant(boolean important) {
    this.important = important;
  }

}