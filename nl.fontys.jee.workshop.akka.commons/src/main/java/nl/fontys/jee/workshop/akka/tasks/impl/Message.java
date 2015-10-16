package nl.fontys.jee.workshop.akka.tasks.impl;

import nl.fontys.jee.workshop.akka.tasks.ITask;

/**
 * Simple message that contains a ITask and some Data.
 *
 * @author florian
 *
 */
public class Message {

	private final ITask task;

	private final Data data;

	public Message(ITask task, Data data) {
		this.task = task;
		this.data = data;
	}

	public Message(ITask task) {
		this.task = task;
		this.data = null;
	}

	/**
	 * Returns the {@link ITask} of this message.
	 * 
	 * @return
	 */
	public ITask getTask() {
		return task;
	}

	/**
	 * Returns the {@link Data} of this message.
	 * 
	 * @return
	 */
	public Data getData() {
		return data;
	}

	@Override
	public String toString() {
		if(data != null){
			return "Yay, now I have some important Data";
		}
		return "Yay, I'm a message with no data";
	}
}
