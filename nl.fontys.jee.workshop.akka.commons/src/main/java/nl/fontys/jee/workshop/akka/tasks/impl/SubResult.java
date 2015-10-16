package nl.fontys.jee.workshop.akka.tasks.impl;

import java.util.UUID;

/**
 * {@link Task} are splitable into {@link SubTask}.
 * @author florian
 *
 */
public class SubResult extends Result {

	private final int timeSpend;
	
	/**
	 * 
	 * @param taskID
	 * @param timeSpend
	 */
	public SubResult(UUID taskID, int timeSpend) {
		super(taskID);
		this.timeSpend = timeSpend;
	}

	/**
	 * @param otherTask
	 * @return
	 */
	public Result reassemble(SubResult otherTask) {
		if (this.taskID.equals(otherTask.taskID)) {
			int totalTimeSpend = this.timeSpend + otherTask.getTimeSpend();
			if (totalTimeSpend == Task.DEFAULT_TASK_TIME) {
				return new Result(this.taskID);
			}
			return new SubResult(this.taskID, totalTimeSpend);
		}
		throw new RuntimeException("These Tasks can not be reassembled");
	}

	/**
	 * @return
	 */
	public int getTimeSpend() {
		return timeSpend;
	}
}
