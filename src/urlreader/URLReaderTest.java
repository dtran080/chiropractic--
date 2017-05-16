package urlreader;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class URLReaderTest extends JFrame {

    private Vector<String> urlVector = new Vector<String>(25, 5);
    private int current;

    private URLReader urlReader;
    private JScrollPane scrollPane;
    private Panel botP = new Panel(new BorderLayout());
    private TextField urlText = new TextField();
    private JButton nextB = new JButton(" >> ");
    private JButton prevB = new JButton(" << ");
    private JButton goB = new JButton("Get Data");

    public static void main(String[] args) {
        new URLReaderTest();
    }

    public URLReaderTest() {

        super("URL Reader Test");
        setSize(500, 500);
        setResizable(false);
        setLocation(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        urlReader = new URLReader("www.bridgeport.edu");
        urlVector.addElement("www.bridgeport.edu");

        scrollPane = new JScrollPane(urlReader);
        add(scrollPane, BorderLayout.CENTER);
        add(urlReader, BorderLayout.CENTER); 
        botP.add(urlText, BorderLayout.NORTH);
        botP.add(prevB, BorderLayout.WEST);
        botP.add(nextB, BorderLayout.EAST);
        botP.add(goB, BorderLayout.CENTER);

        add(botP, BorderLayout.SOUTH);
        setVisible(true);

        urlText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                urlTextAction();
            }
        });

        urlReader.readURL();
    }

    public void urlTextAction() {
        String urlString = urlText.getText();

        if (urlReader.setURL(urlString)) {
            urlText.setText("Connecting to URL: " + urlString);
            urlReader.clearText();
            urlReader.readURL();
            if (!urlVector.contains(urlString)) {
                urlVector.addElement(urlString);
                current++;
            }
        } else {
            urlText.setText("URL: " + urlString + " not Found");
        }
    }

    public void showURL(String urlString) {
        urlReader.setURL(urlString);
        urlReader.clearText();
        urlReader.readURL();
    }
/*
    class URLButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == prevB) {
                if (current > 0) {
                    current--;
                    showURL((String) urlVector.elementAt(current));
                }
            } else if (e.getSource() == nextB) {
                if (current < urlVector.size() - 1) {
                    current++;
                    showURL((String) urlVector.elementAt(current));
                }
            }
        }*/

    //}  // URLButtonhandler

}
