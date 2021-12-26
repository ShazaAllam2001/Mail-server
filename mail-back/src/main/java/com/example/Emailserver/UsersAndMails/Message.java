package com.example.Emailserver.UsersAndMails;

//////////////////message class
public class Message {
    private int  ID;  
    private MessageBody body;      /////////fields of message 
    private MessageHeader header;
    private Attachments attaches;
    private String time;
    private boolean priority;

//////////////////////set message attribute////////////////
    public Message(int ID, MessageBody body, MessageHeader header, Attachments attaches, String time, boolean priority) {
        this.ID = ID;
        this.body = body;
        this.header = header;
        this.attaches = attaches;
        this.time = time;
        this.priority = priority;
    }
//////////////////getters and setters of the message
    public int getID() {
        return ID;
    }
    public MessageBody getBody() {
        return body;
    }
    public MessageHeader getHeader() {
        return header;
    }
    public Attachments getAttaches() {
        return attaches;
    }
    public String getTime() {
        return time;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }
/////////////to string function ///////////
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Message{");
        sb.append("ID=").append(ID);
        sb.append(", body=").append(body);
        sb.append(", header=").append(header);
        sb.append('}');
        return sb.toString();
    }


}
