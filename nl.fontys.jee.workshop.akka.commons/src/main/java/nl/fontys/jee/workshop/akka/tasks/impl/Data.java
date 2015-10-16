package nl.fontys.jee.workshop.akka.tasks.impl;

import java.util.UUID;

import nl.fontys.jee.workshop.akka.tasks.ITask;

/**
 * Some whatever data.
 * 
 * @author florian
 */
public class Data {

	private final UUID ID;

	/**
	 * Creates new Data form a {@link ITask}.
	 * 
	 * @param task
	 */
	public Data(ITask task) {
		this.ID = task.getUUID();
	}

	/**
	 * Returns the unique {@link UUID}.
	 * 
	 * @return {@link UUID} - unique ID.
	 */
	public UUID getID() {
		return ID;
	}

}
