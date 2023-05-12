package util;

import java.util.LinkedList;
import java.util.Optional;

public class Queue<T> {
	private LinkedList<T> queue;

	public Queue() {
		this.queue = new LinkedList<>();
	}

	public void enqueue(T obj) {
		this.queue.addLast(obj);
	}

	public Optional<T> dequeue() {
		return Optional.ofNullable(this.queue.remove());
	}

	public Optional<T> peek() {
		return Optional.ofNullable(this.queue.peekFirst());
	}

	public int size() {
		return this.queue.size();
	}

	public boolean isEmpty() {
		return this.queue.isEmpty();
	}
}
