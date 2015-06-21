package fr.xebia.mowitnow.enumerations;

import com.google.common.collect.Iterators;

/**
 * 
 * @author aymen
 * Enumeration of all possible orientation with their  left and right neighbor
 */

public enum Orientation {
	North("N", "W", "E","North"),
	East("E", "N", "S","East"),
	West("W", "S", "N","West"),
	South("S", "E", "W","South");
		
	private final String code;
	private final String left;
	private final String right;
	private final String fullName;
	
	private Orientation(final String code,final String leftCode,final String rightCode, final String fullname) {
		this.code = code;
		this.left = leftCode;
		this.right = rightCode;
		this.fullName = fullname;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public Orientation getLeftOrientation() {
		return getoOrientationFromCode(left);
	}
	
	public Orientation getRightOrientation() {
		return getoOrientationFromCode(right);
	}
	
	public static Orientation getoOrientationFromCode(final String code) {
		return Iterators.tryFind(Iterators.forArray(values()), (input) -> {
			 return input != null && code.equals(input.code);
		}).get();
	}
	
	@Override
	public String toString() {
		return this.code;
	}
	
	public String getDisplayText(){
		return this.fullName;
	}
}