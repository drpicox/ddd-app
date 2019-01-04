package com.drpicox.queue;

public interface MessageReceiver<T> {

    void receive(T s);
}
