import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class welcome {

	private JFrame frame;
	private JTextField user;
	private JTextField height;
	private JTextField weight;
	private JButton btnSignUp;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome window = new welcome();
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
	public welcome() {
		initialize();
		Connect();
	}

	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	 
	public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/mini", "root","");
	        }
	        catch (ClassNotFoundException ex)
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	 
	    }
	
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(254, 255, 255));
		frame.setBounds(100, 100, 1536, 1152);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		user = new JTextField();
		user.setForeground(new Color(65, 66, 66));
		user.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		user.setBounds(1031, 177, 250, 50);
		frame.getContentPane().add(user);
		user.setColumns(10);
		
		height = new JTextField();
		height.setForeground(Color.DARK_GRAY);
		height.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		height.setColumns(10);
		height.setBounds(1031, 412, 110, 50);
		frame.getContentPane().add(height);
		
		weight = new JTextField();
		weight.setForeground(Color.DARK_GRAY);
		weight.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		weight.setColumns(10);
		weight.setBounds(1171, 412, 110, 50);
		frame.getContentPane().add(weight);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mini", "root","");
					Statement stmt=con.createStatement();
					String sql="Select * from userdata where Username='"+user.getText()+"' and Password='"+pass.getText().toString()+"'";
					ResultSet rs =stmt.executeQuery(sql);
					if(rs.next())
					{//JOptionPane.showMessageDialog(null,"Login Successful");
						String username = user.getText();
						frame.dispose();
					mini frame2 = new mini(username);
					frame2.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null,"Incorrect Credentials");
					con.close();
				} catch (SQLException e1)
		        {
		e1.printStackTrace();
		}
			}
		});
		btnNewButton.setForeground(new Color(0, 51, 204));
		btnNewButton.setBackground(new Color(35, 190, 251));
		btnNewButton.setFont(new Font("Kohinoor Telugu", Font.PLAIN, 24));
		btnNewButton.setBounds(1031, 493, 250, 50);
		frame.getContentPane().add(btnNewButton);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String userr,pas,heig,weig;
				userr = user.getText();
				pas = pass.getText();
				heig = height.getText();
				weig = weight.getText();
				try {

				pst = con.prepareStatement("insert into userdata(Username,Password,height,weight)values(?,?,?,?)");
				pst.setString(1, userr);
				pst.setString(2, pas);
				pst.setString(3, heig);
				pst.setString(4, weig);
				
				pst.executeUpdate();
				//JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				//table_load();

				user.setText("");
				pass.setText("");
				height.setText("");
				weight.setText("");
				user.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnSignUp.setForeground(new Color(0, 51, 204));
		btnSignUp.setFont(new Font("Kohinoor Telugu", Font.PLAIN, 24));
		btnSignUp.setBackground(new Color(35, 190, 251));
		btnSignUp.setBounds(1031, 576, 250, 50);
		frame.getContentPane().add(btnSignUp);
		
		pass = new JPasswordField();
		pass.setForeground(Color.GRAY);
		pass.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		pass.setBounds(1031, 292, 250, 50);
		frame.getContentPane().add(pass);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Lao Sangam MN", Font.PLAIN, 21));
		lblNewLabel.setForeground(new Color(51, 51, 51));
		lblNewLabel.setBounds(1031, 136, 108, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(51, 51, 51));
		lblPassword.setFont(new Font("Lao Sangam MN", Font.PLAIN, 22));
		lblPassword.setBounds(1031, 250, 108, 30);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblHeightcm = new JLabel("Height (cm)");
		lblHeightcm.setForeground(new Color(51, 51, 51));
		lblHeightcm.setFont(new Font("Lao Sangam MN", Font.PLAIN, 22));
		lblHeightcm.setBounds(1035, 370, 124, 30);
		frame.getContentPane().add(lblHeightcm);
		
		JLabel lblWeightkg = new JLabel("Weight (kg)");
		lblWeightkg.setForeground(new Color(51, 51, 51));
		lblWeightkg.setFont(new Font("Lao Sangam MN", Font.PLAIN, 22));
		lblWeightkg.setBounds(1171, 370, 124, 30);
		frame.getContentPane().add(lblWeightkg);
		
		JLabel lblNewLabel_1 = new JLabel("Hey there !");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 45));
		lblNewLabel_1.setForeground(new Color(51, 102, 204));
		lblNewLabel_1.setBounds(630, 19, 250, 50);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sign up to get started");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel_2.setBounds(630, 81, 350, 35);
		frame.getContentPane().add(lblNewLabel_2);
		
		/*JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("/Users/shubham/Downloads/PngItem_2747104-2.png"));
		lblNewLabel_3.setBounds(197, 176, 223, 283);
		frame.getContentPane().add(lblNewLabel_3);*/
		
		JLabel lblNewLabel_4 = new JLabel("Health & Fitness");
		lblNewLabel_4.setForeground(new Color(153, 153, 153));
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.ITALIC, 55));
		lblNewLabel_4.setBounds(489, 278, 440, 75);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Tracker");
		lblNewLabel_4_1.setForeground(new Color(153, 153, 153));
		lblNewLabel_4_1.setFont(new Font("Lucida Grande", Font.ITALIC, 55));
		lblNewLabel_4_1.setBounds(610, 350, 370, 75);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(welcome.class.getResource("/mini/PngItem_2747104.png")));
		lblNewLabel_3.setBounds(6, 58, 705, 625);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
