package nl.fontys.jee.workshop.akka.util;

import java.util.concurrent.TimeUnit;

import nl.fontys.jee.workshop.akka.tasks.IResult;
import nl.fontys.jee.workshop.akka.tasks.impl.Message;
import nl.fontys.jee.workshop.akka.tasks.impl.Result;
import nl.fontys.jee.workshop.akka.tasks.impl.Task;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class TaskGenerator extends UntypedActor {

	public static final String I_AM_DEAD = "iamdead";
	private final String BENCH = "benchmark";
	private ActorSelection selection;
	private final ITaskListener taskListener;
	private boolean producesFailableTasks;
	private boolean serviceIsResponding = false;
	private int tasksPerformed;

	public static Props props(ITaskListener taskListener,
			boolean producesFailableTasks) {
		return Props.create(TaskGenerator.class, taskListener,
				producesFailableTasks);
	}

	public TaskGenerator(ITaskListener taskListener,
			boolean producesFailableTasks) {
		super();
		this.taskListener = taskListener;
		this.producesFailableTasks = producesFailableTasks;
	}

	@Override
	public void preStart() throws Exception {
		super.preStart();
		FiniteDuration interval = Duration.create(1, TimeUnit.SECONDS);
		context()
				.system()
				.scheduler()
				.schedule(Duration.Zero(), interval, getSelf(), BENCH,
						context().system().dispatcher(), null);
		this.selection = context().system().actorSelection("user/worker");
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if (message.equals(BENCH)) {
			if (!this.serviceIsResponding) {
				submitNewTask();
			} else {
				calculateAndSubmitTaskSpeed();
			}
		}
		if (message instanceof Result) {
			Result result = (Result) message;
			processResult(result);
		}
		if(message.equals(I_AM_DEAD)){
			submitNewTask();
		}
	}

	private void calculateAndSubmitTaskSpeed() {
		int tasksPerMinute = this.tasksPerformed * 60;
		taskListener.onSpeedSubmitted(tasksPerMinute);
		this.tasksPerformed = 0;
	}

	private void processResult(IResult result) {
		this.serviceIsResponding = true;
		this.taskListener.onResultSubmitted(result);
		this.tasksPerformed++;
		submitNewTask();
	}

	private void submitNewTask() {
		Task task = new Task(producesFailableTasks, Task.DEFAULT_TASK_TIME);
		selection.tell(new Message(task), getSelf());
		this.taskListener.onTaskSubmitted(task);
	}
}
