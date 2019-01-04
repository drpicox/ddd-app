package com.drpicox.queue;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class QueueTest {

    private String receivedString;
    private int receiveCount;
    private String receiveOrder;

    @Test
    public void deliverMessage() {
        receivedString = null;

        Queue queue = new Queue();
        queue.send(new String("hello"));
        queue.receive(String.class, (String s) -> receivedString = s);
        queue.deliverMessages();

        assertEquals("hello", receivedString);
    }

    @Test
    public void deliverTwoMessages() {
        receiveCount = 0;

        Queue queue = new Queue();
        queue.send("first message");
        queue.send("second message");
        queue.receive(String.class, (String s) -> receiveCount++);
        queue.deliverMessages();

        assertEquals(2, receiveCount);
    }

    @Test
    public void deliverToTwoReceivers() {
        receiveCount = 0;

        Queue queue = new Queue();
        queue.send("message");
        queue.receive(String.class, (String s) -> receiveCount++);
        queue.receive(String.class, (String s) -> receiveCount++);
        queue.deliverMessages();

        assertEquals(2, receiveCount);
    }

    @Test
    public void deliverMessagesOnce() {
        receiveCount = 0;

        Queue queue = new Queue();
        queue.send("message");
        queue.receive(String.class, (String s) -> receiveCount++);
        queue.deliverMessages();
        queue.deliverMessages();

        assertEquals(1, receiveCount);
    }

    @Test
    public void deliverOnlyToCorrectTypeReceivers() {
        receiveCount = 0;

        Queue queue = new Queue();
        queue.send("message");
        queue.receive(Long.class, (Long s) -> receiveCount++);
        queue.receive(String.class, (String s) -> receiveCount++);
        queue.deliverMessages();

        assertEquals(1, receiveCount);
    }

    @Test
    public void receiversCanDeliverNewMessages() {
        receiveCount = 0;

        Queue queue = new Queue();
        queue.send("message");
        queue.receive(Long.class, (Long s) -> receiveCount++);
        queue.receive(String.class, (String s) -> queue.send(3L));
        queue.deliverMessages();

        assertEquals(1, receiveCount);
    }

    @Test
    public void doNotDeliverMessagesIfDeliverMessagesIsNotInvoked() {
        receiveCount = 0;

        Queue queue = new Queue();
        queue.send("message");
        queue.receive(String.class, (String s) -> receiveCount++);

        assertEquals(0, receiveCount);
    }

    @Test(expected=NonSerializableException.class)
    public void nonSerializableMessagesFailAtSend() {
        class NonSerializable {}
        LinkedList<NonSerializable> noSerializableList = new LinkedList<>();
        noSerializableList.add(new NonSerializable());

        Queue queue = new Queue();
        queue.send(noSerializableList);
    }
}