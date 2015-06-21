package fr.xebia.mowitnow;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import fr.xebia.lawn.Lawn;
import fr.xebia.lawn.Position;
import fr.xebia.mowitnow.enumerations.Command;
import fr.xebia.mowitnow.enumerations.Orientation;
import fr.xebia.mowitnow.mow.Mower;

public class MowerTest {
	
	@Test
	public void mowerTestTurnLeft(){
		Mower mower = new Mower(new Position(1, 2), Orientation.North);
		mower.turnLeft();
		Assert.assertEquals(Orientation.West, mower.getOrientation());
		mower.turnLeft();
		Assert.assertEquals(Orientation.South, mower.getOrientation());
		mower.turnLeft();
		Assert.assertEquals(Orientation.East, mower.getOrientation());
		mower.turnLeft();
		Assert.assertEquals(Orientation.North, mower.getOrientation());
	}
	
	@Test
	public void mowerTestTurnRight(){
		Mower mower = new Mower(new Position(1, 2), Orientation.North);
		mower.turnRight();
		Assert.assertEquals(Orientation.East, mower.getOrientation());
		mower.turnRight();
		Assert.assertEquals(Orientation.South, mower.getOrientation());
		mower.turnRight();
		Assert.assertEquals(Orientation.West, mower.getOrientation());
		mower.turnRight();
		Assert.assertEquals(Orientation.North, mower.getOrientation());
	}
	
	@Test
	public void moweForwardNorth(){
		Mower mower = new Mower(new Position(1, 2), Orientation.North);
		Lawn lawn = new Lawn(5, 5);
		
		Queue<Command> commands = new LinkedList<Command>();
		Command[] array = { Command.Avance};
		commands.addAll(Arrays.asList(array));
		mower.startMowing(lawn, commands);
		
		Assert.assertEquals(new Position(1, 3), mower.getPosition());
		Assert.assertEquals(Orientation.North, mower.getOrientation());
	}
	
	@Test
	public void moweForwardSouth(){
		Mower mower = new Mower(new Position(1, 2), Orientation.South);
		Lawn lawn = new Lawn(5, 5);
		
		Queue<Command> commands = new LinkedList<Command>();
		Command[] array = { Command.Avance};
		commands.addAll(Arrays.asList(array));
		mower.startMowing(lawn, commands);
		
		Assert.assertEquals(new Position(1, 1), mower.getPosition());
		Assert.assertEquals(Orientation.South, mower.getOrientation());
	}
	
	@Test
	public void moweForwardEast(){
		Mower mower = new Mower(new Position(1, 2), Orientation.East);
		Lawn lawn = new Lawn(5, 5);
		
		Queue<Command> commands = new LinkedList<Command>();
		Command[] array = { Command.Avance};
		commands.addAll(Arrays.asList(array));
		mower.startMowing(lawn, commands);
		
		Assert.assertEquals(new Position(2, 2), mower.getPosition());
		Assert.assertEquals(Orientation.East, mower.getOrientation());
	}
	
	@Test
	public void moweForwardWest(){
		Mower mower = new Mower(new Position(1, 2), Orientation.West);
		Lawn lawn = new Lawn(5, 5);
		
		Queue<Command> commands = new LinkedList<Command>();
		Command[] array = { Command.Avance};
		commands.addAll(Arrays.asList(array));
		mower.startMowing(lawn, commands);
		
		Assert.assertEquals(new Position(0, 2), mower.getPosition());
		Assert.assertEquals(Orientation.West, mower.getOrientation());
	}
	
	@Test
	public void moweThroughWall(){
		Mower mower = new Mower(new Position(1, 2), Orientation.North);
		Lawn lawn = new Lawn(5, 5);
		
		Queue<Command> commands = new LinkedList<Command>();
		Command[] array = { Command.Avance,Command.Avance,Command.Avance,Command.Avance,Command.Avance};
		commands.addAll(Arrays.asList(array));
		mower.startMowing(lawn, commands);
		
		Assert.assertEquals(new Position(1, 5), mower.getPosition());
		Assert.assertEquals(Orientation.North, mower.getOrientation());
	}
	
	@Test
	public void startExecutingCommandTest(){
		Mower mower = new Mower(new Position(1, 2), Orientation.North);
		Lawn lawn = new Lawn(5, 5);
		
		Queue<Command> commands = new LinkedList<Command>();
		Command[] array = { Command.Gauche, Command.Avance, Command.Gauche,
				Command.Avance, Command.Gauche, Command.Avance, Command.Gauche,
				Command.Avance, Command.Avance, };
		commands.addAll(Arrays.asList(array));
		mower.startMowing(lawn, commands);
		
		Assert.assertEquals(new Position(1, 3), mower.getPosition());
		Assert.assertEquals(Orientation.North, mower.getOrientation());
		
		Mower mower2 = new Mower(new Position(3, 3), Orientation.East);
		Queue<Command> commands2 = new LinkedList<Command>();
		Command[] array2 = { Command.Avance, Command.Avance, Command.Droite,
				Command.Avance, Command.Avance, Command.Droite, Command.Avance,
				Command.Droite, Command.Droite,Command.Avance };
		commands2.addAll(Arrays.asList(array2));
		mower2.startMowing(lawn, commands2);
		
		Assert.assertEquals(new Position(5, 1), mower2.getPosition());
		Assert.assertEquals(Orientation.East, mower2.getOrientation());
	}
}
