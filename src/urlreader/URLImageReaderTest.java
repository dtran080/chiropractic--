package urlreader;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class  URLImageReaderTest extends JFrame {

	private URLImageReader urlImageReader;
	private JPanel botP = new JPanel(new BorderLayout());
	private JTextField urlText = new JTextField();
	private String link;

	public static void main(String[] args) 	
	{ 
	    new URLImageReaderTest() ;
	}

	public URLImageReaderTest() {

		super("URL Image Reader Test");
		setSize(400,400);
		setLocation(300,200);
		link = "news.bridgeport.edu/wp-content/uploads/2017/03/hagley_featurephoto-341x220.jpg?x75128";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		urlImageReader = new URLImageReader(link);
		urlText.setText(link);
		
		add(urlImageReader, BorderLayout.CENTER); 
		botP.add(urlText, BorderLayout.NORTH); 
		
		add(botP, BorderLayout.SOUTH); 
		setVisible(true);
		
		urlText.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    urlTextAction();}});
		
		urlImageReader.readURL(); 
	}
	
	public void urlTextAction() 
	{ 
	
		String urlString = urlText.getText();
		
		urlImageReader.setURL(urlString);
		    urlText.setText("Connecting to URL: " + urlString);
		    urlImageReader.readURL(); 
		   }
	
	public void showURL(String urlString) {
	  urlImageReader.setURL(urlString);  
	  urlImageReader.readURL(); }

}
