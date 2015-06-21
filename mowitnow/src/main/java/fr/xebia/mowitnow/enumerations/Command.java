package fr.xebia.mowitnow.enumerations;

import com.google.common.collect.Iterators;

/**
 * 
 * @author aymen
 *	Enumeration of all possible commands
 */
public enum Command {
	Avance("A"),
	Droite("D"),
	Gauche("G");

	private String code;
	
	private Command(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static Command getCommandeFromCode(final String code) {
		return Iterators.tryFind(Iterators.forArray(values()), (input) -> {
			 return input != null && code.equals(input.code);
		}).get();
	}
}
