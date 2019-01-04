package com.drpicox.queue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Queue {

    List<Object> messages;
    List<MessageReceiver> receivers;

    public Queue() {
        messages = new LinkedList<>();
        receivers = new LinkedList<>();
    }

    public void send(Object message) {
        verifyMessageIsSerializable(message);

        messages.add(message);
    }

    private void verifyMessageIsSerializable(Object message) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new ByteArrayOutputStream());
            outputStream.writeObject(message);
        } catch (IOException nonSerializableExceptionCause) {
            throw new NonSerializableException(nonSerializableExceptionCause);
        }
    }

    public <T> void receive(Class<T> messageClass, MessageReceiver<T> receiver) {
        receivers.add((Object message) -> {
            if (messageClass.isInstance(message)) {
                receiver.receive((T) message);
            }
        });
    }

    public void deliverMessages() {
        List<Object> copy;

        copy = new ArrayList<>(messages);
        while (!copy.isEmpty()) {
            messages.clear();
            copy.forEach(
                    (Object message) -> receivers.forEach(
                            (MessageReceiver receiver) -> receiver.receive(message)
                    )
            );

            copy = new ArrayList<>(messages);
        }
    }
}
