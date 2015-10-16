package nl.fontys.jee.workshop.akka.tasks;

import java.util.List;
import java.util.UUID;

import nl.fontys.jee.workshop.akka.tasks.impl.Data;

/**
 * A simple task that can be performed and split up.
 * @author florian
 */
public interface ITask {

	/**
	 * Performs a Task and deliverers a IResult
	 * 
	 * @return - the {@link IResult} of the task.
	 */
	public IResult perform(Data data);

	/**
	 * Divides a divisible ITask into two.
	 * 
	 * @return - {@link List<ITask>}
	 */
	public List<ITask> split();

	/**
	 * Returns the unique {@link UUID} of this {@link ITask}.
	 * 
	 * @return
	 */
	public UUID getUUID();
}
