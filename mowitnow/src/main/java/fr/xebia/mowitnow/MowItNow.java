package fr.xebia.mowitnow;

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
			}
		} catch (ParameterException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
			jc.usage();
		}
	}
}
