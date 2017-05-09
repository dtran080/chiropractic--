package urlreader;

/* 	URLReader.java

	Author:		Julius Dichter
	Description:	A Panel which reads a URL InputStream source
	We use the getContent() method of the URL class */

import java.net.*;
import java.io.*;
import java.awt.*;

public class URLReader extends java.awt.Panel implements Runnable  {

	URL  url;
	TextArea textArea;
	Object data;
	
	public URLReader() { this("www.bridgeport.edu"); }
	
	public URLReader (String urlString ) {
		setBackground(Color.pink);
		
		setURL(urlString);
		
		textArea = new TextArea(20,50);
		textArea.setEditable(false);
		textArea.setBackground(Color.white);
		add(textArea,BorderLayout.CENTER); 
	}
	
	public void clearText() {
		textArea.setText(""); }
	
	public boolean setURL(String urlString) 
	{
	
		try {
		  url = new URL("http://" + urlString);
		
		  if (url != null)
		   System.out.println("URL successfully created");  
		  else
		   System.out.println("URL NOT created");  
		
		data = url.getContent();
		System.out.println("Data: " + data.toString()); } // try
		
		catch (MalformedURLException e) { e.printStackTrace(); }
		catch (IOException e) { 
		   e.printStackTrace();
		   textArea.append("\nERROR on Connect\n"); } 
		finally { return url != null; } 
	}
	 
	public void readURL() {
	
		textArea.append("Begin Read\n\n");
		
		if (data instanceof InputStream) {
		  System.out.println("Getting URL Input Stream");
		  InputStream is = (InputStream) data;
		  String text = readStream(is);
		  textArea.append(text);
		  textArea.append("\n\nReading Complete"); 
		  } 
	} 
	
	protected String readStream (InputStream is) {
		boolean cut = false; // new ***
		StringBuffer sb = new StringBuffer();
		try {
			InputStreamReader isr = new InputStreamReader(is);
			int c;
		        while ((c = isr.read()) != -1) {
				//	sb.append((char)c);
		        // cut Tags out
		          if ( (char) c == '<')
		             cut = true;
		          else
		            if ((char) c == '>') 
		              cut = false;
		
		          if (!cut)
		            if ( ! ((char)c  == '>'))
		            sb.append((char)c); }
		          // end cut Tags out
		}catch (IOException ioe) { }
		return sb.toString();  
	}
	
	public void run() {  }  
} // URLReader.java
