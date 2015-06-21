package fr.xebia.mowitnow;

import com.beust.jcommander.Parameter;

public class Settings {
    @Parameter(names = { "-c", "--charset" }, description = "Charset")
    private String charsetName;
	@Parameter(names = { "-i", "--input" }, description = "Input file name")
    private String inputFileName;
    @Parameter(names = "--help", help = true)
    private boolean help;
    
	public boolean isHelp() {return help;}
	
	public void setHelp(boolean help) {this.help = help;}
   
	public String getCharsetName() {return charsetName;}
	
	public void setCharsetName(String charsetName) {this.charsetName = charsetName;}
	
	public String getInputFileName() {return inputFileName;}
	
	public void setInputFileName(String inputFileName) {this.inputFileName = inputFileName;}
	
}
