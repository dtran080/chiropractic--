package urlreader;

/* 	URLImageReader.java
	Author:		Julius Dichter
	Description:	A Panel which reads a URL Image source
	We use the getContent() method of the URL class */

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

public class URLImageReader extends java.awt.Canvas implements Runnable {
	
	URL  url;
	Object data;
	Image image;
	
	public URLImageReader() { this("www.bridgeport.edu/graph/front.jpg"); }
	
	public URLImageReader (String urlString ) {
		setBackground(Color.gray);
		setSize(200,200);
		setURL(urlString); 
	}
	
	public boolean setURL(String urlString) {
	
		try { url = new URL("http://" + urlString);
		  if (url != null)
		   System.out.println("URL successfully created");  
		  else
		   System.out.println("URL NOT created");  
		
		data = url.getContent();
		System.out.println("Data: " + data.toString()); } // try
		
		catch (MalformedURLException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); } 
		finally { return url != null; } 
	}
	 
	public void readURL() {
		if (data instanceof ImageProducer) {
		  System.out.println("Getting ImageProducer Data");
		  ImageProducer ip = (ImageProducer) data;
		  image = getToolkit().createImage(ip);
		  repaint();
		  System.out.println("\n\nImageProducer Complete"); 
		 } 
	} 
	
	public void paint(Graphics g) {
	  g.drawImage(image,10,10,image.getHeight(this),image.getHeight(this),this); }
	
	public void run() {  } 
}   /* URLImageReader.java */


