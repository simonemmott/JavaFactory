package com.k2.JavaFactory;

import java.io.File;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The JavaOutputLocation validates a given path as a suitable location for outputting generated java source code.
 * This class should be extended specifying a specific instance of a java factory and defining the methods to cause the required java source code
 * to be generated 
 * 
 * @author simon
 *
 */
public class JavaOutputLocation {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	protected File outputFolder;
	/**
	 * Create a java output location to write the java classes to the given output folder.
	 * 
	 * @param outputPath	The output folder in which to write the java source code for the java assembly wigets
	 * @throws JavaWriterException	If there is a problem with the given source location
	 */
	public JavaOutputLocation(String outputPath) throws JavaWriterException {
		this.outputFolder = new File(outputPath);
		validateLocation("output", outputFolder);
		
		logger.debug("Preparing to write Java source code to {}", outputFolder.getAbsolutePath());

	}
	
	private void validateLocation(String locationDescription, File location) throws JavaWriterException {
		if (location == null)
			throw new JavaWriterException("Unable to generate java source code - The given {} location is null!", locationDescription);
		
		if (!location.exists())
			throw new JavaWriterException("Unable to generate java source code - The {} location {} does not exist!", locationDescription, location.getAbsolutePath());
		
		if (!location.isDirectory())
			throw new JavaWriterException("Unable to generate java source code - The {} location {} is not a directory!", locationDescription, location.getAbsolutePath());
		
		if (!location.canWrite())
			throw new JavaWriterException("Unable to generate java source code - Unable to write to the {} location {}!", locationDescription, location.getAbsolutePath());
		
	}	

}
