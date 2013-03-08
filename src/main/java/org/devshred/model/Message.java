package org.devshred.model;

public class Message {
	private final int id;
	private final String owner;

	public Message(final int id, final String owner) {
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
