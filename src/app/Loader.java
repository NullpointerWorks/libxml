package app;

import java.io.InputStream;
import java.net.URL;

public class Loader 
{
	public static URL getResource(String path)
	{
		return instance.getClass().getResource(path);
	}
	
	public static InputStream getResourceAsStream(String path)
	{
		return instance.getClass().getResourceAsStream(path);
	}
	
	private static Loader instance = new Loader();
}
