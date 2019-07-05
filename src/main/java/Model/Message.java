package Model;


import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {
    private User sender;
    private User reciever;
    private String time;
    private String subject;
    private String text;
    private String attachment;
    private String FileName;
    private boolean sent;
    private boolean recieved;
    private boolean readed;
    private boolean important;
    private boolean removed;
    private boolean removedForMe;
    private boolean importantForMe;

    public boolean isRemovedForMe() {
        return removedForMe;
    }

    public void setRemovedForMe(boolean removedForMe) {
        this.removedForMe = removedForMe;
    }

    public boolean isImportantForMe() {
        return importantForMe;
    }

    public void setImportantForMe(boolean importantForMe) {
        this.importantForMe = importantForMe;
    }

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

    public Message() {

    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return sent == message.sent &&
                recieved == message.recieved &&
                readed == message.readed &&
                important == message.important &&
                removed == message.removed &&
                sender.equals(message.sender) &&
                reciever.equals(message.reciever) &&
                time.equals(message.time) &&
                subject.equals(message.subject) &&
                text.equals(message.text) &&
                attachment.equals(message.attachment) &&
                FileName.equals(message.FileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, reciever, time, subject, text, attachment, FileName, sent, recieved, readed, important, removed);
    }

}
