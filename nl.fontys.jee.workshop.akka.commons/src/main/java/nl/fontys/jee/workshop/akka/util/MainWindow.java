package nl.fontys.jee.workshop.akka.util;

import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import akka.actor.ActorSystem;
import akka.actor.Props;
import nl.fontys.jee.workshop.akka.tasks.IResult;
import nl.fontys.jee.workshop.akka.tasks.ITask;

/**
 * IMPORTANT: In the context of this tutorial the single purpose of this class
 * is to hide the ugly Swing stuff. Do not waste your time by studying what's
 * going on here. There is no AKKA Stuff in here.
 * 
 * @author florian antony - 2190372
 *
 */
public abstract class MainWindow extends JFrame implements ITaskListener {

	private static final long serialVersionUID = 1L;

	protected volatile int emittedTasks = 0;

	protected volatile int processedTasks = 0;

	private volatile int preMinuteTasks = 0;

	protected ActorSystem actorSystem;

	private JLabel tasksEmmited;

	private JLabel tasksCompleted;

	private JLabel tasksPerMinute;
	
	private ChatPanel chatPanel;

	public MainWindow() throws HeadlessException {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600,600);
		this.setLayout(new GridLayout(2, 1));
		this.tasksEmmited = new JLabel();
		this.tasksCompleted = new JLabel();
		this.tasksPerMinute = new JLabel();
		this.chatPanel = new ChatPanel();
		this.tasksEmmited.setText("Task created " + this.emittedTasks);
		this.tasksCompleted.setText("Task completed " + this.processedTasks);
		this.tasksPerMinute.setText("Task perMinute " + this.preMinuteTasks);

		this.add(tasksEmmited);
		this.add(tasksCompleted);
		this.add(tasksPerMinute);
		this.add(chatPanel);
		
		this.setVisible(true);
	}

	/**
	 * OK, OK, you caught me in a lie... there is a little bit more going on
	 * here. Once you created the {@link ActorSystem} a {@link TaskGenerator} is
	 * initialized and added to the {@link ActorSystem}. You may configure the
	 * the {@link TaskGenerator} here, but there is no need to inspect it right
	 * now.
	 */
	protected void initialize() {
		initializeActorSystem();
		if (actorSystem != null) {
			// switch this to 'true' if you want to have Tasks that might throw
			// an Exception on execution.
			boolean producesFailableTasks = false;
			Props props = TaskGenerator.props(this, producesFailableTasks);
			this.actorSystem.actorOf(props, "taskGenerator");
		}
	}

	@Override
	public void onTaskSubmitted(ITask task) {
		this.emittedTasks++;
		this.tasksEmmited.setText("Task created " + this.emittedTasks);
		this.repaint();
	}

	@Override
	public void onResultSubmitted(IResult result) {
		this.processedTasks++;
		this.tasksCompleted.setText("Task completed " + this.processedTasks);
		this.repaint();
	}

	@Override
	public void onSpeedSubmitted(int tasksPerMinute) {
		this.tasksPerMinute.setText("Tasks per minute " + tasksPerMinute);
		this.repaint();
	}

	protected abstract void initializeActorSystem();
}
