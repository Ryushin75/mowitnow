package fr.xebia.mowitnow;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import fr.xebia.mowitnow.enumerations.Command;
import fr.xebia.mowitnow.lawn.Lawn;
import fr.xebia.mowitnow.mow.Mower;

/**
 * 
 * @author aymen
 * 
 * Class in charge of running mowers's commands
 */

public class MowerProgrammingSystem implements Observer {
	private final static Logger LOG = Logger
			.getLogger(MowerProgrammingSystem.class);

	private final Lawn lawn;
	private final List<Pair<Mower, Queue<Command>>> pairs;

	public MowerProgrammingSystem(Lawn lawn,
			List<Pair<Mower, Queue<Command>>> pairs) {
		this.lawn = lawn;
		this.pairs = pairs;
		
		for(Pair<Mower, Queue<Command>> pair : pairs){
			this.lawn.getCell(pair.getLeft().getPosition()).setOccupied(true);
		}
	}
	
	/**
	 * Execute mowers commands
	 */

	public void execute() {
		for (Pair<Mower, Queue<Command>> pair : pairs) {
			Mower mower = pair.getLeft();
			mower.addObserver(this);
			mower.startMowing(lawn, pair.getRight());
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Mower mow = (Mower) o;
		LOG.debug("Mower notified his position to : " + mow.toString());
	}
	
	public Lawn getLawn() {
		return lawn;
	}

	public List<Pair<Mower, Queue<Command>>> getPairs() {
		return pairs;
	}
	
	/**
	 * Print mowers position to console 
	 */
	public void printMowersPositions(){
		for (Pair<Mower, Queue<Command>> pair : pairs) {
			Mower mower = pair.getLeft();
			System.out.println("Mover id = " +mower.getId() + " position = " + mower);
		}
	}
}
