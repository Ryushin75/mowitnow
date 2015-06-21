package fr.xebia.mowitnow.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import static com.google.common.base.Preconditions.checkArgument;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import com.google.common.base.Splitter;

import fr.xebia.lawn.Lawn;
import fr.xebia.lawn.Position;
import fr.xebia.mowitnow.MowerProgrammingSystem;
import fr.xebia.mowitnow.Settings;
import fr.xebia.mowitnow.enumerations.Command;
import fr.xebia.mowitnow.enumerations.Orientation;
import fr.xebia.mowitnow.mow.Mower;

public class MowingParserService {
	private static final Logger LOG = Logger.getLogger(MowingParserService.class);
	private static final String SEPARATOR = " ";
	private static final String LAWN_PATTERN = "^\\d+ \\d+$";
	private static final String MOWER_PATTERN = "^\\d+ \\d+ [N|E|W|S]$";
	private static final String COMMAND_PATTERN = "^[A|D|G]*$";

	private Charset charset;
	private int mowerID;

	private final Parser<String, Queue<Command>> commandParser;
	private final Parser<String, Mower> mowerParser;
	private final Parser<String, Lawn> lawnParser;
	
	public MowingParserService() {
		// Generate parser using lambda
		lawnParser  = (line) -> {
			checkArgument(line.matches(LAWN_PATTERN),
					"Error while parsing lawn init value, expected: 'height weight'; got: '"
							+ line + "']");
			List<String> champs = Splitter.on(SEPARATOR).splitToList(line);
			return new Lawn(Integer.valueOf(champs.get(0)),
					Integer.valueOf(champs.get(1)));
		};
		
		mowerParser = (line) -> {
			checkArgument(
					line.matches(MOWER_PATTERN),
					"Error while parsing mower init value, expected: 'xPosition yPosition orientation'; got: '"
							+ line + "']");
			List<String> field = Splitter.on(SEPARATOR).splitToList(line);
		    int x = Integer.valueOf(field.get(0));
		    int y = Integer.valueOf(field.get(1));
		    Orientation orientation = Orientation.getoOrientationFromCode(field.get(2));
		    return new Mower(mowerID++, new Position(x, y), orientation);
		};
		
		commandParser = (line) -> {
			checkArgument(
					line.matches(COMMAND_PATTERN),
					"Error while parsing command values, expected: 'A|D|G'; got: '"
							+ line + "']");
			  Queue<Command> commands = new LinkedList<Command>();
			  for (char code : line.toCharArray()) {
				commands.add(Command.getCommandeFromCode(String.valueOf(code)));
			  }
			  return commands;
		};
	}
	public MowerProgrammingSystem parseSettings(Settings settings) throws IOException, FileNotFoundException{
		parseCharset(settings.getCharsetName());
		return parseInput(settings.getInputFileName());
	}

	private MowerProgrammingSystem parseInput(String inputFileName) throws IOException,FileNotFoundException {
		if (StringUtils.isNotEmpty(inputFileName)) {
			return parseFile(inputFileName);
		} else {
			throw new NoSuchMethodError("Soon...");
		}
	}

	private void parseCharset(String charsetName) {
		if (StringUtils.isNotEmpty(charsetName)) {
			try {
				charset = Charset.forName(charsetName);
			} catch (IllegalCharsetNameException e) {
				LOG.error("Unvalid charset name : '" + charsetName + "'");
				throw e;
			} catch (UnsupportedCharsetException e) {
				LOG.error("Unsupported charset name : '" + charsetName + "'");
				throw e;
			}
		} else {
			charset = Charset.defaultCharset();
		}
	}

	private MowerProgrammingSystem parseFile(String inputFileName) throws IOException,FileNotFoundException{
		try (InputStream in = new FileInputStream(inputFileName);) {
			return parse(in);
		} catch (FileNotFoundException e) {
			LOG.error("File with name '" + inputFileName + "' not found");
			LOG.error(e.getMessage());
			throw e;
		} catch (IOException e) {
			LOG.error("Error while parsing '" + inputFileName + "'");
			LOG.error(e.getMessage());
			throw e;
		}
	}

	private MowerProgrammingSystem parse(InputStream in) {
		try (Scanner sc = new Scanner(in, charset.name())) {
			
			// Parse Lawn height and weight
			Lawn lawn = lawnParser.parse(sc.nextLine());
			List<Pair<Mower, Queue<Command>>> pairs = new ArrayList<>();
			
			//Parse Mowers and their commands
			while (sc.hasNextLine()) {		
				Mower mower = mowerParser.parse(sc.nextLine());
				if(!sc.hasNextLine()){
					throw new IllegalStateException("A Mower is supposed to have an associated command");
				}
				Queue<Command> commands = commandParser.parse(sc.nextLine());
				if(lawn.contains(mower.getPosition())){
					pairs.add(new ImmutablePair<Mower, Queue<Command>>(mower,commands));
				}else{
					LOG.error("Mower position is not contained in the lawn");
				}
			}
			return new MowerProgrammingSystem(lawn, pairs);
		}
	}
	
	public Charset getCharset() {
		return charset;
	}
}
