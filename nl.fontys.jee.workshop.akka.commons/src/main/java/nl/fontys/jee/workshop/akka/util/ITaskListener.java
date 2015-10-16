package nl.fontys.jee.workshop.akka.util;

import nl.fontys.jee.workshop.akka.tasks.IResult;
import nl.fontys.jee.workshop.akka.tasks.ITask;

public interface ITaskListener {

	public void onTaskSubmitted(ITask task);

	public void onResultSubmitted(IResult result);

	public void onSpeedSubmitted(int tasksPerMinute);
}
