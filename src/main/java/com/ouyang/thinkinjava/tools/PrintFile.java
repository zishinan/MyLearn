//: PrintFile.java
// Shorthand class for opening an output file
// for human-readable output.
package com.ouyang.thinkinjava.tools;

import java.io.*;

public class PrintFile extends PrintStream
{
	public PrintFile(String filename)
			throws IOException
	{
		super(
				new BufferedOutputStream(
						new FileOutputStream(filename)));
	}

	public PrintFile(File file)
			throws IOException
	{
		this(file.getPath());
	}
} // /:~