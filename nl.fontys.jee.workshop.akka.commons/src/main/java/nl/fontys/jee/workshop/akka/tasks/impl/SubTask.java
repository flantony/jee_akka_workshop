package nl.fontys.jee.workshop.akka.tasks.impl;

import java.util.UUID;

public class SubTask extends Task {

	private static final long serialVersionUID = 3470665356624237468L;

	private final UUID parentID;

	public SubTask(boolean failable, int requiredTime, UUID taskID) {
		super(failable, requiredTime);
		this.parentID = taskID;
	}

	@Override
	public Result perform(Data data) {
		doSomthingReallyImportant(data);
		return new SubResult(this.parentID, this.requiredTime);
	}

	public UUID getParentID() {
		return parentID;
	}
}
