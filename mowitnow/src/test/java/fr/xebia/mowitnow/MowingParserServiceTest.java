package fr.xebia.mowitnow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import fr.xebia.mowitnow.enumerations.Command;
import fr.xebia.mowitnow.enumerations.Orientation;
import fr.xebia.mowitnow.lawn.Lawn;
import fr.xebia.mowitnow.lawn.Position;
import fr.xebia.mowitnow.mow.Mower;
import fr.xebia.mowitnow.parser.MowingParserService;

public class MowingParserServiceTest {

	@Test(expected = FileNotFoundException.class)
	public void fileNotfoundTest() throws IOException {
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName("file not found");
		mowingParserService.parseSettings(settings);
	}

	@Test(expected = UnsupportedCharsetException.class)
	public void unvalidCharset() throws IOException {
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/lawnArea.mow")
				.getPath());
		settings.setCharsetName("unknow");
		mowingParserService.parseSettings(settings);
	}

	@Test
	public void testDefaultCharset() throws FileNotFoundException, IOException {
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/lawnArea.mow")
				.getPath());
		mowingParserService.parseSettings(settings);
		Assert.assertEquals(Charset.defaultCharset(),
				mowingParserService.getCharset());
	}

	@Test
	public void lawnAreaTest() throws IOException {
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/lawnArea.mow")
				.getPath());
		MowerProgrammingSystem mowerProgrammingSystem = mowingParserService
				.parseSettings(settings);
		Assert.assertEquals(new Lawn(5, 5), mowerProgrammingSystem.getLawn());
		//We include the rows 0
		Assert.assertEquals((5+1)*(5+1), mowerProgrammingSystem.getLawn().getGridSize());

	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void lawnErrorTest() throws FileNotFoundException, IOException{
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/errorLawn.mow")
				.getPath());
		MowerProgrammingSystem mowerProgrammingSystem = mowingParserService
				.parseSettings(settings);
	}

	@Test
	public void initMowerTest() throws FileNotFoundException, IOException {
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/initMower.mow")
				.getPath());
		MowerProgrammingSystem mowerProgrammingSystem = mowingParserService
				.parseSettings(settings);
		Assert.assertEquals(1, mowerProgrammingSystem.getPairs().size());
		Mower mower = mowerProgrammingSystem.getPairs().get(0).getLeft();
		Assert.assertEquals(
				new Mower(0, new Position(1, 2), Orientation.North), mower);
		Queue<Command> commands = new LinkedList<Command>();
		Command[] array = { Command.Gauche, Command.Avance, Command.Gauche,
				Command.Avance, Command.Gauche, Command.Avance, Command.Gauche,
				Command.Avance, Command.Avance, };
		commands.addAll(Arrays.asList(array));
		Assert.assertEquals(commands, mowerProgrammingSystem.getPairs().get(0)
				.getRight());
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class )
	public void errorParseMowerPosition() throws FileNotFoundException, IOException{
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/errorMower1.mow")
				.getPath());
		MowerProgrammingSystem mowerProgrammingSystem = mowingParserService
				.parseSettings(settings);
	}
	
	@Test()
	public void errorParseMowerPosition2() throws FileNotFoundException, IOException{
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/errorMower2.mow")
				.getPath());
		MowerProgrammingSystem mowerProgrammingSystem = mowingParserService
				.parseSettings(settings);
		Assert.assertEquals(0, mowerProgrammingSystem.getPairs().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void errorParseMowerCommand() throws FileNotFoundException, IOException{
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/errorMower3.mow")
				.getPath());
		MowerProgrammingSystem mowerProgrammingSystem = mowingParserService
				.parseSettings(settings);
		Assert.assertEquals(0, mowerProgrammingSystem.getPairs().size());
	}
	
	@Test
	public void testCompleteInputFile() throws FileNotFoundException, IOException{
		MowingParserService mowingParserService = new MowingParserService();
		Settings settings = new Settings();
		settings.setInputFileName(this.getClass().getResource("/testInput.mow")
				.getPath());
		MowerProgrammingSystem mowerProgrammingSystem = mowingParserService
				.parseSettings(settings);
		Assert.assertEquals(2, mowerProgrammingSystem.getPairs().size());
		Assert.assertEquals(new Lawn(5, 5), mowerProgrammingSystem.getLawn());
		Mower mower1 = mowerProgrammingSystem.getPairs().get(0).getLeft();
		Mower mower2 = mowerProgrammingSystem.getPairs().get(1).getLeft();
		
		Assert.assertTrue(mowerProgrammingSystem.getLawn().getCell(mower1.getPosition()).isOccupied());
		Assert.assertTrue(mowerProgrammingSystem.getLawn().getCell(mower2.getPosition()).isOccupied());
	}
}
