package googlePage;

import javax.swing.*;
//import javax.swing.text.Element;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
//import com.google.gson.Gson;

public class searchMain extends JFrame {

    private int screenWidth, screenHeight;
    private JScrollPane scrollPane;
    private JTextArea searchBox, resultBox;
    private JButton prevBtn, nextBtn, submitBtn;
    private boolean resultDisplayed;
    private ActionListener submitBtnAL;
    private Font font;
    private Object data;
    private List<String> urlList;
    private String cx = "008146553695872254382:hrkred5fziw";
    private String api = "AIzaSyDdz1733p7cqS5-VlyIVY16gex9GY2qIFo";

    public searchMain() {
        screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width*7/10;
        screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height*6/10;
        urlList = new ArrayList<String>();
        resultDisplayed = false;
        font = new Font("Serif", Font.BOLD, 12);
        setTitle("Google result search");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        resultBox = new JTextArea("Result goes here");
        //action listener
        submitBtnAL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (searchBox.getForeground() == Color.BLACK && searchBox.getText().length() > 3) {// display the work here	
                    try {
                        String google = "https://www.google.com/search?q=";
                        String search = searchBox.getText();
                        String charset = "UTF-8";
                        String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!
                        System.out.println("123");
                        org.jsoup.select.Elements links = Jsoup.connect(google + URLEncoder.encode(search, charset))
                                .userAgent(userAgent).get().select(".g>.r>a");
                        System.out.println("length: " + links.size());
                        if (links.size() > 0) {
                            resultBox.setText("__________\nBegin reading links: \n__________\n");

                        }
                        for (org.jsoup.nodes.Element link : links) {
                            String title = link.text();
                            String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
                            url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), charset);
                            /* if (!url.startsWith("http")) {
                             continue; 
                             }*/

                            resultBox.append(String.format("Title: %s\n", title));
                            resultBox.append(String.format("URL: %s\n", url));
                            urlList.add(url);
                        }
                        resultDisplayed = true;
                    } catch (UnsupportedEncodingException ssse) {
                        ssse.printStackTrace();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }
        };
        initializeView();
    }

    private void initializeView() {
        setLayout(null);
        setLocationRelativeTo(null);
        setBounds(0, 0, screenWidth, screenHeight);
        //scroll pane
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, screenWidth, screenHeight - 50);
        add(scrollPane);
        //search
        searchBox = new JTextArea("Enter your search here...");
        searchBox.setForeground(Color.gray);
        searchBox.setBounds(50, screenHeight - 50, screenWidth - 150, 30);
        searchBox.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
                searchBox.setForeground(Color.BLACK);
                searchBox.setText("");
                resultBox.setEditable(true);
            }
        });
        add(searchBox);
		//resultBox

        resultBox.setBounds(0, 0, screenWidth, screenHeight);
        resultBox.setEditable(false);
        scrollPane.add(resultBox);
        //prev,next
        prevBtn = new JButton();
        prevBtn.setFont(font);
        prevBtn.setText("<");
        prevBtn.setBounds(0, screenHeight - 50, 50, 30);
        add(prevBtn);
        nextBtn = new JButton();
        nextBtn.setFont(font);
        nextBtn.setText(">");
        nextBtn.setBounds(screenWidth - 100, screenHeight - 50, 50, 30);
        add(nextBtn);
        //submit btn
        submitBtn = new JButton();
        submitBtn.setBackground(Color.GREEN);
        submitBtn.setBounds(screenWidth - 50, screenHeight - 50, 50, 30);
        submitBtn.addActionListener(submitBtnAL);
        add(submitBtn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new searchMain().setVisible(true);

            }
        });
    }

}
