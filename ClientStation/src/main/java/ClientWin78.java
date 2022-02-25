

//File name ClientWin78.java
//Eiar 5770  update Sivan  5778
//Levian Yehonatan
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.util.Random;

public class ClientWin78 extends JFrame implements ActionListener,KeyListener
{

    private JTextPane paneTextUp;
    private StyledDocument doc;
    private JTextArea textAreaDown;
    private Style base = StyleContext.getDefaultStyleContext().
            getStyle(StyleContext.DEFAULT_STYLE);
    private Style myStyle, otherStyle;
    private Style myHeaderStyle, otherHeaderStyle;
    public JButton send;
    private String myName = "Your";
    private String otherName = "Server (Dialog)";
    private Client78 myClient;

    public ClientWin78(String header, Client78 myClient)
    {
        super(header);
        this.myClient = myClient;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(350,350);

        paneTextUp = new JTextPane();
        paneTextUp.setEditable(false);

        doc = paneTextUp.getStyledDocument();

        myStyle = doc.addStyle("myStyle", base);
        StyleConstants.setFontSize(myStyle, 14);
        StyleConstants.setForeground(myStyle, Color.CYAN);

        myHeaderStyle = doc.addStyle("myHeaderStyle", myStyle);
        StyleConstants.setBold(myHeaderStyle, true);

        otherStyle = doc.addStyle("otherStyle", base);
        StyleConstants.setFontSize(otherStyle, 16);
        StyleConstants.setForeground(otherStyle, Color.RED);

        otherHeaderStyle = doc.addStyle("otherHeaderStyle", otherStyle);
        StyleConstants.setBold(otherHeaderStyle, true);

        JScrollPane scrollPaneUp = new JScrollPane(paneTextUp);
        scrollPaneUp.setPreferredSize(new Dimension(300, 300));

      
        JScrollPane scrollPaneDown = new JScrollPane(textAreaDown);

      

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(scrollPaneUp);
        mainPanel.add(scrollPaneDown);
        add(mainPanel);
        pack();
        setVisible(false);
    }

    public void printMe(String str)
    {
        try
        {		
            doc.insertString(doc.getLength(), myName + ':' + '\n', myHeaderStyle);
            doc.insertString(doc.getLength(), "   " + str + '\n', myStyle);
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
        paneTextUp.setCaretPosition(paneTextUp.getDocument().getLength());
    }

    public void printOther(String str)
    {
        try
        {
            doc.insertString(doc.getLength(), otherName + ':' + '\n', otherHeaderStyle);
            doc.insertString(doc.getLength(), "   " + str + '\n', otherStyle);
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
        paneTextUp.setCaretPosition(paneTextUp.getDocument().getLength());
    }
    
  ///////////////////////////////////////////////////////////////////////////////////////  
    public void print(String str)
    {
        try
        {	paneTextUp.setText("");
            doc.insertString(doc.getLength(), "   " + str + '\n', otherStyle);
        } catch (BadLocationException e)
        {
            e.printStackTrace();
        }
       
        paneTextUp.setCaretPosition(paneTextUp.getDocument().getLength());
    }
//////////////////////////////////////////////////////////////////////////////////
    
    
    
    public void actionPerformed(ActionEvent arg0)
    {
        if (((JButton) arg0.getSource()).getText().equals("Close"))
        {
            System.exit(1);
        }
        printMe(textAreaDown.getText());
        myClient.bufferSocketOut.println(textAreaDown.getText());
        textAreaDown.setText("");
    }

	
	public void keyPressed(KeyEvent arg0) 
	{
		if( arg0.getKeyCode() == KeyEvent.VK_ENTER )
		{
			printMe(textAreaDown.getText());
			myClient.bufferSocketOut.println(textAreaDown.getText());
			textAreaDown.setText("");
		}
	}
	
	///////////////////////////////////////////////////////////////////////
	public String sendStationName() {
		////////get from user
		//int name= (new Random().nextInt(1000));
		String stationSerialNumber=JOptionPane.showInputDialog("serial num Station ");
		 setVisible(true);
		print("station number: "+stationSerialNumber);
		///send to server
		myClient.bufferSocketOut.println(stationSerialNumber);
		return stationSerialNumber;
		
	}
	/////////////////////////////////////////////////////////////////////////

	public void keyReleased(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}

	
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
}
