

import java.awt.Color;
import java.awt.EventQueue;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
/*import javax.swing.text.Document;*/

import com.google.gson.Gson;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class Start implements ActionListener {

	private JFrame frame;
	private JPanel contentPane;

	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;

	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JButton btn_submit;
	Common common =new Common(); 
	
	String path = System.getProperty("user.home")+File.separator + common.FolderName;
	
	HttpClient httpClient = HttpClient.newBuilder().build();
	Gson gson = new Gson();
	private JLabel lblNewLabel_6;

	/**
	 * Launch the application.
	 */
	
	//main method
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	
	public Start() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	//Constructor
	public void initialize() {
		
		frame = new JFrame();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/ssc-pune-logo.png"));
		frame.setIconImage(icon.getImage());
		frame.setBounds(100, 100, 450, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Inspection tool");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 235, 205));
		lblNewLabel.setBounds(0, 0, 494, 372);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayout(null);

		/*long millis = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");*/
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(132, 0, 349, 357);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_3 = new JLabel("MARCH-"+common.Year()+" Online Exam Inspection Tool");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBackground(new Color(255, 239, 213));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(93, 69, 311, 27);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("College Index Number");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(163, 108, 157, 21);
		contentPane.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("J :-");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(new Color(165, 42, 42));
		lblNewLabel_5.setBackground(new Color(178, 34, 34));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(110, 154, 32, 34);
		contentPane.add(lblNewLabel_5);

		txt1 = new JTextField();
		txt1.setBounds(160, 164, 37, 27);
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setDocument(new JTextFieldLimit(2));
		txt1.setColumns(10);
		//validation of textfield to accept only 2 Digits
		txt1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			 String value = txt1.getText();
				if (value.length() >= 2 ) {
					txt2.requestFocus();
				}
			}

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		contentPane.add(txt1);
		
		txt2 = new JTextField();
		txt2.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.setBounds(225, 164, 37, 27);
		txt2.setDocument(new JTextFieldLimit(2));
		txt2.setColumns(10);
		txt2.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				String value = txt2.getText();
				if (value.length() >= 2) {
					txt3.requestFocus();
				}
			}

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		contentPane.add(txt2);
		
		txt3 = new JTextField();
		txt3.setDocument(new JTextFieldLimit(3));
		txt3.setBounds(283, 164, 37, 27);
		txt3.setColumns(10);
		txt3.setHorizontalAlignment(SwingConstants.CENTER);
		txt3.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) 
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		contentPane.add(txt3);
		
		lblNewLabel_7 = new JLabel("_");
		lblNewLabel_7.setBounds(207, 164, 15, 24);
		contentPane.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("_");
		lblNewLabel_8.setBounds(270, 170, 15, 14);
		contentPane.add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("(Enter digits only)");
		lblNewLabel_9.setForeground(new Color(115, 0, 0));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_9.setBounds(179, 224, 167, 27);
		contentPane.add(lblNewLabel_9);

		btn_submit = new JButton("SUBMIT");
		btn_submit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_submit.setBounds(194, 282, 109, 27);
		contentPane.add(btn_submit);

		lblNewLabel_10 = new JLabel(
				"Copyright "+common.Year()+"@Maharashtra State Board of Secondary  and  Higher Secondary  Education");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_10.setBounds(10, 336, 457, 14);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon (getClass().getResource("/Image/MSBSHSE-Opacity.png")));
		lblNewLabel_2.setBounds(0, 0, 484, 361);
		contentPane.add(lblNewLabel_2);
		
		//get current date and time
		long millis = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		lblNewLabel_6 = new JLabel("Date:"+formatter.format(millis));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_6.setBounds(298, 26, 128, 21);
		contentPane.add(lblNewLabel_6);
		
		// ------------------------------------------------------------------------

		//submit button Actions if machine already inspected 
		btn_submit.addActionListener(this);
		Scanner scanner;
		try {
			
			scanner = new Scanner(new File(path+ "/Index_No.txt"));
			
			//System.out.println(path+ "/Index_No.txt"+"----"+common.Key);
		
		while (scanner.hasNextLine()) {
		  String line = scanner.nextLine();
		  String txt=AESCipher.aesDecryptString(line, common.Key);
		  //System.out.println(line+"---"+txt);
		  if(txt.length()==7) {
						     common.ShowMessage("Machine Already Inspected!!!");
						     frame.setVisible(false);
						     System.exit(0);
		  }
		   // process the line
		}
		} catch (Exception e3) {
			
		}
		
	
	}

	
	//submit button Actions
	public void actionPerformed(ActionEvent e) 
	{
		
		   if (e.getSource() == btn_submit) {
			String Index_No = txt1.getText() + txt2.getText() + txt3.getText();
			Request_Model model = new Request_Model();
		
			if (Index_No.length() != 7) 
			{
				 common.ShowMessage("Invalid Index Number.");
				return;
			}
			model.Index_No = Index_No;
			String json = gson.toJson(model);
			HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
					.header("Content-Type", "application/json").uri(URI.create(common.URL + "Index_Exists")).build();

			try {
				HttpResponse<String> respons = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
				System.out.println(json);
				common.ShowMessage("-*-*-"+respons.body());
				
				Response_Model reponce_model = gson.fromJson(respons.body(), Response_Model.class);
				System.out.println(gson.toJson(reponce_model)); 
				
				if (reponce_model.Result.equals("Success")) 
				{
					
					if (reponce_model.Message == 1) 
					{
						//System.out.println(Index_No);
						//System.out.println("Model 1:-  "+ reponce_model.Message);
						frame.dispose();
						Wait nw = new Wait(Index_No,reponce_model.ID);
						nw.show();
						nw.setLocationRelativeTo(null);
					} 
					
					else if (reponce_model.Message == 0)
					{
						//System.out.println("Model 0:-  "+reponce_model.Message);
						frame.dispose();
						BasicInfo InspectionBasicInfo = new BasicInfo(Index_No);
						InspectionBasicInfo.frame.show();
						InspectionBasicInfo.Inspection_Basic_Info_Page(Index_No);
						InspectionBasicInfo.frame.setLocationRelativeTo(null);
					}
				}
				else 
				{
					common.ShowMessage("Invalid Index Number");
					return;
				}
			} catch (IOException e1) {
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f, "Error:" + e1);

			} catch (InterruptedException e1) {
				JFrame f = new JFrame();
				JOptionPane.showMessageDialog(f, "Error:" + e1);
			}
		}

	}
}

