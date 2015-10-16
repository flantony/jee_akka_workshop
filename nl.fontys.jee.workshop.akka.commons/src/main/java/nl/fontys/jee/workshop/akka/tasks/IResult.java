package nl.fontys.jee.workshop.akka.tasks;

import java.util.UUID;

/**
 * Some whatever important result.
 * 
 * @author florian
 *
 */
public interface IResult {
	/**
	 * Returns an unique ID.
	 * 
	 * @return {@link UUID} - unique ID
	 */
	public UUID getTaskID();
}
