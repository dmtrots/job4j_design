package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int out = 0;
    private int in = 0;

    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (out == 0) {
            while (in > 0) {
                output.push(input.pop());
                in--;
                out++;
            }
        }
        T result = output.pop();
        out--;
        return result;
    }

    public void push(T value) {
        input.push(value);
        in++;
    }

    public boolean isEmpty() {
        return in == 0 && out == 0;
    }
}