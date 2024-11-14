package applicattion;

import java.net.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class Client {

	private JFrame frame;
	private JTextField title;
	private JTextField numberOne;
	private JTextField numberTwo;
	private JButton btnSendButton;
	private JTextArea chatPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Title
		title = new JTextField();
		title.setBackground(new Color(192, 192, 192));
		title.setFont(new Font("Arial", Font.BOLD, 14));
		title.setText("CLIENT");
		title.setBounds(108, 11, 61, 20);
		frame.getContentPane().add(title);
		title.setColumns(10);
		
		//Chat message 
		numberOne = new JTextField();
		numberOne.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		numberOne.setBackground(new Color(192, 192, 192));
		numberOne.setBounds(30,50, 82, 20);
		frame.getContentPane().add(numberOne);
		numberOne.setColumns(10);
		
		
		
		//Chat message 
		numberTwo = new JTextField();
		numberTwo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		numberTwo.setBackground(new Color(192, 192, 192));
		numberTwo.setBounds(170,50, 82, 20);
		frame.getContentPane().add(numberTwo);
		numberTwo.setColumns(10);

		
		
		
		btnSendButton = new JButton("SEND");
		btnSendButton.setBackground(new Color(192, 192, 192));
		btnSendButton.setEnabled(false);
		btnSendButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		// Enable button dynamically based on input validation
        KeyAdapter validationAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                boolean enabled = checkNumber(numberOne) && checkNumber(numberTwo);
                chatPanel.setText("");
                enableButton(enabled);
            }
        };
        numberOne.addKeyListener(validationAdapter);
        numberTwo.addKeyListener(validationAdapter);
		
		
		btnSendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					int number1 = conversor(numberOne);
					int number2 = conversor(numberTwo);
					
					sendData(number1, number2);	
					
			}
		});
		btnSendButton.setBounds(95, 100, 89, 23);
		frame.getContentPane().add(btnSendButton);
		
		//Allows scroll chat.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(90, 150, 100, 100);
		frame.getContentPane().add(scrollPane);
		
		
		//Chat panel.
		chatPanel = new JTextArea();
		chatPanel.setFont(new Font("Monospaced", Font.PLAIN, 38));
		chatPanel.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(chatPanel);
		
		
	}
	
	
	
	public boolean checkNumber (JTextField string) {
		
		try {
			int number = Integer.parseInt(string.getText());
			
		}catch(NumberFormatException nb) {
			return false;
		}
		
		return true;		
	}
	
	
	
	public int conversor (JTextField string){
		return Integer.parseInt((String)string.getText());
	}
	
	
	
	public void enableButton (boolean enable) {
		btnSendButton.setEnabled(enable);
	}
	
	
	
	public int sendData(int numberOne, int numberTwo) {
		
		int result = 0;
		
		 try (Socket mySocket = new Socket("localhost", 3000);
		         DataOutputStream dataOut = new DataOutputStream(mySocket.getOutputStream());
		         DataInputStream dataIn = new DataInputStream(mySocket.getInputStream())) {
			
			System.out.println("Imprimimos" + numberOne + " y " + numberTwo);
			dataOut.writeInt(numberOne);
			
			dataOut.writeInt(numberTwo);
			
			
			
			result = dataIn.readInt();
			
			chatPanel.append(String.valueOf(result));
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:" + e.getMessage());
			e.printStackTrace();
		} finally {
		}
		System.out.println(numberOne +
				 " ' " + numberTwo);
		return result;
		
	}
}
