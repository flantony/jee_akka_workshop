package nl.fontys.jee.workshop.akka;

import nl.fontys.jee.workshop.akka.util.MainWindow;

public class WorkshopMain extends MainWindow {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		WorkshopMain workshopMain = new WorkshopMain();
		workshopMain.initialize();
	}

	@Override
	protected void initializeActorSystem() {
	
	}
}
