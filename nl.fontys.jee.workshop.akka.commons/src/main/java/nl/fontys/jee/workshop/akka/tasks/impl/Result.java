package nl.fontys.jee.workshop.akka.tasks.impl;

import java.util.UUID;

import nl.fontys.jee.workshop.akka.tasks.IResult;
import nl.fontys.jee.workshop.akka.tasks.ITask;

public class Result implements IResult {

	protected final UUID taskID;

	/**
	 * Creates a {@link Result} for a {@link ITask}.
	 * 
	 * @param taskID
	 *            - unique ID of the corresponding {@link ITask}
	 */
	public Result(UUID taskID) {
		this.taskID = taskID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.fontys.jee.workshop.akka.tasks.IResult#getTaskID()
	 */
	public UUID getTaskID() {
		return taskID;
	}
}
