package org.devshred.model;

public class Message {
    final int id;
    final String owner;

    public Message(final int id, final String owner){
        this.id = id;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }
}
