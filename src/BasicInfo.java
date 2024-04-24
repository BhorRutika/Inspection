
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;

public class BasicInfo implements KeyListener, ActionListener {

	public String IndexNo;
	JFrame frame;
	private JTextField txt_College_Name;
	private JTextField txt_Student_Count;
	private JTextField txt_System_Count;
	private JTextField txt_ITTeacher;
	private JTextField txt_Contact;
	private JButton btn_submit;
	Common common = new Common();
	//Wait wait=new Wait(null, Index_No);
	HttpClient httpClient = HttpClient.newBuilder().build();
	Gson gson = new Gson();
	
	/**
	 * Launch the application.
	 * 
	 * @return
	 */
	public void Inspection_Basic_Info_Page(String Index_No) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicInfo window = new BasicInfo(Index_No);
					// System.out.println(Index_No);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param index_No
	 */
	public BasicInfo(String Index_No) {
		IndexNo=Index_No;
		initialize(Index_No);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(String Index_No) {
		frame = new JFrame();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/ssc-pune-logo.png"));
		frame.setIconImage(icon.getImage());
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setTitle("Inspection tool");
		frame.setLocationRelativeTo(null);
		frame.setSize(530, 447);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);

		JLabel background;
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 235, 205));
		lblNewLabel.setBounds(0, 0, 494, 372);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		background = new JLabel("");
		background.setIcon(new ImageIcon(getClass().getResource("/Image/MSBSHSE-Opacity.png")));
		background.setFont(new Font("Tahoma", Font.BOLD, 11));
		background.setBackground(Color.GRAY);
		background.setBounds(144, 0, 360, 408);
		contentPane.add(background);
		contentPane.setLayout(null);

		// getting current date and time
		long millis = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		JLabel lblDate = new JLabel("Date :-" + formatter.format(millis));
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(305, 21, 199, 25);
		contentPane.add(lblDate);

		JLabel lblNewLabel_1 = new JLabel("College Index No :-");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(23, 104, 140, 19);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Name Of College :-");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(23, 150, 140, 19);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("No.Of Student  :-");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(23, 186, 143, 19);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("No.Of  System  :-");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(23, 216, 143, 25);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Name Of IT Teacher :-");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(23, 252, 154, 19);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Contact No :-");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(23, 286, 119, 19);
		contentPane.add(lblNewLabel_6);

		txt_College_Name = new JTextField();
		txt_College_Name.setBounds(197, 149, 132, 20);
		contentPane.add(txt_College_Name);
		txt_College_Name.setColumns(10);

		
		txt_Student_Count = new JTextField();
		txt_Student_Count.setBounds(196, 187, 133, 20);
		txt_Student_Count.setColumns(10);
		txt_Student_Count.addKeyListener(this);
		contentPane.add(txt_Student_Count);

		txt_System_Count = new JTextField();
		txt_System_Count.setBounds(196, 220, 133, 20);
		txt_System_Count.setColumns(10);
		txt_System_Count.addKeyListener(this);
		contentPane.add(txt_System_Count);

		txt_ITTeacher = new JTextField();
		txt_ITTeacher.setBounds(196, 253, 133, 20);
		contentPane.add(txt_ITTeacher);
		txt_ITTeacher.setColumns(10);

		txt_Contact = new JTextField();
		txt_Contact.setBounds(196, 287, 133, 20);
		txt_Contact.setColumns(10);
		txt_Contact.setDocument(new JTextFieldLimit(10));
		txt_Contact.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
					e.consume();
				}
			}
		});

		contentPane.add(txt_Contact);

		btn_submit = new JButton("Submit");
		btn_submit.addActionListener(this);
		btn_submit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_submit.setBounds(173, 329, 89, 23);
		contentPane.add(btn_submit);

		JLabel lbl_IndexNo = new JLabel("" + Index_No);
		System.out.println("********************"+Index_No);
		lbl_IndexNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_IndexNo.setBounds(201, 108, 111, 15);
		contentPane.add(lbl_IndexNo);

		JLabel lblNewLabel_7 = new JLabel("Copyright " + common.Year()
				+ "@Maharashtra State Board of Secondary  and  Higher Secondary  Education");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(0, 394, 515, 14);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("(Enter Digits Only)");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(361, 290, 119, 14);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("(Enter Digits Only)");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(361, 223, 119, 14);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("(Enter Digits Only)");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_10.setBounds(361, 190, 119, 14);
		contentPane.add(lblNewLabel_10);

		// --------------------------------------------------------------------------------

	}

	@Override
	public void keyTyped(KeyEvent e) {

		char c = e.getKeyChar();
		if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {

			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (txt_College_Name.getText().equals("")) {
			common.ShowMessage("Enter College Name");
			return;
		}
		
		if(txt_Student_Count.getText().equals("")) {
			common.ShowMessage("Enter Number of Students");
			return;
		}
		
		if(txt_System_Count.getText().equals("")) {
			common.ShowMessage("Enter Number of Systems");
			return;
		}
		
		if(txt_ITTeacher.getText().equals("")) {
			common.ShowMessage("Enter Name of IT Teacher");
			return;
		}
		
		if(txt_Contact.getText().length()<10) {
			common.ShowMessage("Enter Valid Contact Number");
			return;
		}
		else {
		frame.dispose();
			
		String Clg_name = txt_College_Name.getText();
		String Teacher_name=txt_ITTeacher.getText();
		String Co_No=txt_Contact.getText();
		int No_Students=Integer.parseInt(txt_Student_Count.getText());
		int No_Systems=Integer.parseInt(txt_System_Count.getText());
		
		Response_Model response=new Response_Model();
		
		Input_Model input = new Input_Model() ;
		input.Index_No=IndexNo;
		try {
			input.IP_Address=Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		response.College_Name=""+Clg_name;
		response.Teacher_Name=""+Teacher_name;
		response.Contact_No=""+Co_No;
		response.No_Of_Student=No_Students;
		response.No_Of_System=No_Systems;
		response.IP_Address=""+input.IP_Address;
		response.Index_No=""+input.Index_No;
		
				
		String json = gson.toJson(response);
		System.out.println(".....\n"+json);
		HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.header("Content-Type", "application/json").uri(URI.create(common.URL + "Add_Inspection_Basic_Info")).build();
		try {
			HttpResponse<String> respons = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			
			 Response_Model model = gson.fromJson(respons.body(), Response_Model.class);
			 		model.College_Name=""+Clg_name;
					 model.Teacher_Name=""+Teacher_name;
					 model.Contact_No=""+Co_No;
					 model.No_Of_Student=No_Students;
					 model.No_Of_System=No_Systems;
					 model.IP_Address=""+input.IP_Address;
					 model.Index_No=""+input.Index_No;
					
					 System.out.println("/*/*//*"+respons.body());
					 
					 
					 if(model.Result.equals("Success"))
						{// System.out.println("///////////////"+Index_No);
						Wait ww = new Wait(IndexNo, null);
						System.out.println("Indexxxxxxxx:"+model.Index_No);
						ww.setVisible(true);
						ww.show();
						ww.setLocationRelativeTo(null);
						return;}
			
					
					 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		}

	}

}
