package nl.fontys.jee.workshop.akka.tasks.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import akka.actor.ActorKilledException;
import nl.fontys.jee.workshop.akka.tasks.IResult;
import nl.fontys.jee.workshop.akka.tasks.ITask;
import scala.util.Random;

/**
 * 
 * @author florian antony - 2190372
 */
public class Task implements Serializable, ITask {

	public static final int DEFAULT_TASK_TIME = 4;

	private static final long serialVersionUID = -6730428422516387737L;

	/**
	 * Unique identifier for this task.
	 */
	protected final UUID taskID;

	protected final int requiredTime;

	protected final boolean failable;

	public Task(boolean failable, int requiredTime) {
		this.taskID = UUID.randomUUID();
		this.failable = failable;
		this.requiredTime = requiredTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * nl.fontys.jee.workshop.akka.tasks.ITask#perform(nl.fontys.jee.workshop
	 * .akka.tasks.impl.Data)
	 */
	@Override
	public IResult perform(Data data) {
		doSomthingReallyImportant(data);
		return new Result(this.taskID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.fontys.jee.workshop.akka.tasks.ITask#split()
	 */
	@Override
	public List<ITask> split() {
		ArrayList<ITask> subtask = new ArrayList<ITask>();
		if (this.requiredTime >= 2) {
			subtask.add(new SubTask(this.failable, this.requiredTime / 2,
					this.taskID));
			subtask.add(new SubTask(this.failable, this.requiredTime / 2,
					this.taskID));
		} else {
			subtask.add(this);
		}
		return subtask;
	}

	/**
	 * Simulates the a really heavy operation.
	 */
	protected void doSomthingReallyImportant(Data data) {
		if (data != null) {
			if (data.getID().equals(this.taskID)) {
				try {
					Thread.sleep(this.requiredTime / 4);
					potentiallyFail();
					Thread.sleep(this.requiredTime / 4);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		try {
			Thread.sleep(this.requiredTime / 2);
			potentiallyFail();
			Thread.sleep(this.requiredTime / 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void potentiallyFail() {
		if (this.failable && new Random().nextInt(10) == 0) {
			// This will cause a complete System failure in case no Supervisor
			// Strategy is implemented.
			throw new ActorKilledException("Failed to execute Task - "
					+ this.taskID.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see nl.fontys.jee.workshop.akka.tasks.ITask#getUUID()
	 */
	@Override
	public UUID getUUID() {
		return this.taskID;
	}

}
