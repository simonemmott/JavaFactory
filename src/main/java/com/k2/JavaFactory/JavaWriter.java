package com.k2.JavaFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;
import com.k2.Util.FileUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.Wiget.templateFactory.spec.TemplateImplementation;
import com.k2.Wiget.templateFactory.spec.TemplateSpecification;
import com.k2.Wiget.templateFactory.types.TemplateDef;

/**
 * The TemplateWriter writes the java source for the template assembly weget specification and implementation classes as defined by the supplied data
 * 
 * @author simon
 *
 */
public class JavaWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

//	protected JavaFactory factory = new JavaFactory("com.k2.core.javaFactory.impl");
	protected File outputFolder;
	protected File archiveFolder;
	/**
	 * Create a template writer to write the template classes to the given output folder.
	 * Template writers created in this way move current versions of the template classes to an archive folder created in the output folder.
	 * 
	 * @param outputFolder	The output folder in which to write the java source code for the template assembly wigets
	 * @throws JavaWriterException	If there is a problem with the given source location
	 */
	public JavaWriter(String outputPath) throws JavaWriterException {
		this.outputFolder = new File(outputPath);
		validateLocation("output", outputFolder);
		archiveFolder = outputFolder.toPath().resolve("archive").toFile();
		
		logger.debug("Preparing to write Java source code to {}", outputFolder.getAbsolutePath());

	}
	/**
	 * Create a template writer to write the template classes to the given output folder and archive folder.
	 * Template writers created in this way move current versions of the template classes to the given archive folder.
	 * 
	 * @param outputFolder	The output folder in which to write the java source code for the template assembly wigets
	 * @param archiveFolder	The floder in which existing java source files will be backed up
	 * @throws JavaWriterException	If there is a problem with the given source or archive locations
	 */
	public JavaWriter(File outputFolder, File archiveFolder) throws JavaWriterException {
		validateLocation("output", outputFolder);
		validateLocation("archive", archiveFolder);
		
		logger.debug("Preparing to write templates to {} archiveing to {}", outputFolder.getAbsolutePath(), archiveFolder.getAbsolutePath());

		this.outputFolder = outputFolder;
		this.archiveFolder = archiveFolder;
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
	
	private void archiveResource(File resource, Path resourcePath) throws JavaWriterException {
		if (resource.exists()) {
			FileUtil.buildTree(archiveFolder, resourcePath);
			int i = 0;
			File archiveResource = archiveFolder.toPath().resolve(resourcePath).resolve(resource.getName()+"."+i++).toFile();
			while (archiveResource.exists()) {
				archiveResource = archiveFolder.toPath().resolve(resourcePath).resolve(resource.getName()+"."+i++).toFile();
			}
			
			try {
				Files.move(resource, archiveResource);
			} catch (IOException e) {
				throw new JavaWriterException("Unable to archive {} - {}", e, resource, e.getMessage());
			}
		}
		
	}
	
	
	
	

}
