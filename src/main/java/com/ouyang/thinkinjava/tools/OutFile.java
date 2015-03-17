//: OutFile.java
// Shorthand class for opening an output file
// for data storage.
package com.ouyang.thinkinjava.tools;

import java.io.*;

public class OutFile extends DataOutputStream
{
	public OutFile(String filename)
			throws IOException
	{
		super(
				new BufferedOutputStream(
						new FileOutputStream(filename)));
	}

	public OutFile(File file)
			throws IOException
	{
		this(file.getPath());
	}
} // /