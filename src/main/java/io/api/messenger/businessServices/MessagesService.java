package io.api.messenger.businessServices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import io.api.messenger.exceptions.DataNotFoundException;
import io.api.messenger.models.entities.Message;
import io.api.messenger.models.tables.Database;

public class MessagesService {

	private Map<Long, Message> messages = Database.getMessagesTable();

	public MessagesService() {
		super();
		this.messages.put(1L, new Message(1L, "Message1", "Author1"));
		this.messages.put(2L, new Message(2L, "Message2", "Author2"));
		this.messages.put(3L, new Message(3L, "Message3", "Author3"));
		this.messages.put(4L, new Message(4L, "Message4", "Author4"));
		this.messages.put(5L, new Message(5L, "Message5", "Author5"));
		this.messages.put(6L, new Message(6L, "Message6", "Author6"));
		this.messages.put(7L, new Message(7L, "Message7", "Author7"));
		this.messages.put(8L, new Message(8L, "Message8", "Author8"));
		this.messages.put(9L, new Message(9L, "Message9", "Author9"));
		this.messages.put(10L, new Message(10L, "Message10", "Author10"));
	}

	public List<Message> getAll() {
		return new ArrayList<Message>(this.messages.values());
	}

	public List<Message> getAllByYear(Integer year) {
		ArrayList<Message> messageslist = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message m : this.messages.values()) {
			cal.setTime(m.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageslist.add(m);
			}
		}
		return messageslist;
	}

	public List<Message> getAllByPaginated(Integer start, Integer size) {
		ArrayList<Message> messageslist = new ArrayList<>(this.messages.values());
		if (start + size > messageslist.size()) { return new ArrayList<Message>(); }
		return messageslist.subList(start, start + size);
	}
	
	public List<Message> getAllByYearPaginated(Integer year, Integer start, Integer size) {
		List<Message> messageslist = this.getAllByYear(year);
		if (start + size > messageslist.size()) { return new ArrayList<Message>(); }
		return messageslist.subList(start, start + size);
	}

	public Message getOne(Long id) {
		Message m = this.messages.get(id);
		if(m == null) {
			throw new DataNotFoundException("Message with id = " + id + " not found!");
		}
		return m;
	}

	public Message addMessage(Message m) {
		m.setId(this.messages.size() + 1L);
		this.messages.put(m.getId(), m);
		return m;
	}

	public Message updateMessage(Message m) {
		if (m.getId() <= 0)
			return null;
		this.messages.put(m.getId(), m);
		return m;
	}

	public Message removeMessage(Long m) {
		return this.messages.remove(m);
	}
}