package fr.xebia.mowitnow.parser;

/**
 * 
 * @author aymen
 *
 * Parse an input T, an return a new output O
 * @param <I> input
 * @param <O> output
 */

@FunctionalInterface
public interface Parser<I,O> {
	public O parse(final I source);
}
