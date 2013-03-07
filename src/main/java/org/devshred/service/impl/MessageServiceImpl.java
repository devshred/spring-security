package org.devshred.service.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.devshred.exception.ObjectNotFoundException;
import org.devshred.model.Message;
import org.devshred.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class MessageServiceImpl implements MessageService {
    static final private List<Message> messages = newArrayList();

    static {
        for (int i = 0; i < 10; i++) {
            final String owner = i % 2 == 0 ? "admin" : "user";
            messages.add(new Message(i, owner));
        }
    }

    @Override
    public Collection<Message> findByUsername(final String username) {
        return Collections2.filter(messages, new Predicate<Message>() {
            @Override
            public boolean apply(final Message message) {
                return message.getOwner().equals(username);
            }
        });
    }

    @Override
    public Message findById(int messageId) throws ObjectNotFoundException {
        try{
        return messages.get(messageId);
        } catch(IndexOutOfBoundsException e){
              throw new ObjectNotFoundException();
        }
    }
}
