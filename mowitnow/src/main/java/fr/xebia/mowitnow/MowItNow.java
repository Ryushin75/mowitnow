package fr.xebia.mowitnow;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import fr.xebia.mowitnow.parser.MowingParserService;

public class MowItNow {
	private static final Logger LOG = Logger.getLogger(MowItNow.class);

	public static void main(String[] args) {
		Settings settings = new Settings();
		JCommander jc = new JCommander(settings);
		try {
			jc.parse(args);
			if (settings.isHelp()) {
				jc.usage();
			} else {
				MowingParserService mowingService = new MowingParserService();
				MowerProgrammingSystem programmingSystem = mowingService
						.parseSettings(settings);
				programmingSystem.execute();
				programmingSystem.printMowersPositions();
			}
		} catch (ParameterException e) {
			LOG.error(e.getMessage());
			jc.usage();
		} catch (IOException e) {
			LOG.error(e.getMessage());
		} catch(IllegalStateException e){
			LOG.error(e.getMessage());
		}
	}
}
