
import java.sql.*;
import java.time.LocalDate;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
//import java.time.LocalDate;

public class mini extends JFrame {

	private JPanel contentPane;
	private JTextField txtwater;
	private JTextField txtburnt;
	private JButton btnNewButton_1;
	private JLabel lblAddWaterIntake;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTable table;
	public String username, currentDate;
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//mini frame = new mini();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public mini(String username) {
		this.username = username;
		
		initialization();
		Connect();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JScrollPane scrollPane;
	private JButton btnbmi;
	 
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
	
	
	
	
	
	public void table_load()
    {
     try
     {
    pst = con.prepareStatement("select burnt, waterintake, day from record where Usern = '" + username + "' ");
    rs = pst.executeQuery();

    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public void initialization() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1536, 1152);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtwater = new JTextField();
		txtwater.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		txtwater.setForeground(Color.DARK_GRAY);
		txtwater.setBackground(new Color(255, 255, 255));
		txtwater.setBounds(602, 164, 180, 50);
		contentPane.add(txtwater);
		txtwater.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Add Calories Burnt");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setBounds(163, 107, 250, 45);
		contentPane.add(lblNewLabel);
		
		txtburnt = new JTextField();
		txtburnt.setForeground(Color.DARK_GRAY);
		txtburnt.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		txtburnt.setBounds(161, 164, 180, 50);
		contentPane.add(txtburnt);
		txtburnt.setColumns(10);
		
		btnNewButton_1 = new JButton("ADD");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String burnt,waterintake;
				burnt = txtburnt.getText();
				waterintake = txtwater.getText();
				
				try {
				
					java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
					//java.sql.Timestamp sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
					
				pst = con.prepareStatement("insert into record(Day,burnt,waterintake,Usern)values(?,?,?,?)");
				//pst.setDate(1, sqlDate);
				pst.setDate(1, sqlDate);
				pst.setString(2, burnt);
				pst.setString(3, waterintake);
				pst.setString(4, username);
				
				pst.executeUpdate();
				//JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
				table_load();

				txtburnt.setText("");
				txtwater.setText("");
				txtburnt.requestFocus();
				   }
				 
				catch (SQLException e1)
				        {
				e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_1.setForeground(new Color(0, 0, 128));
		btnNewButton_1.setBackground(new Color(255, 153, 153));
		btnNewButton_1.setBounds(381, 265, 180, 50);
		contentPane.add(btnNewButton_1);
		
		lblAddWaterIntake = new JLabel("Add WaterIntake (in litres)");
		lblAddWaterIntake.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblAddWaterIntake.setForeground(new Color(0, 0, 128));
		lblAddWaterIntake.setBounds(602, 107, 350, 45);
		contentPane.add(lblAddWaterIntake);
		
		lblNewLabel_1 = new JLabel("Welcome " + username + " ! Please Enter Todays Data");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Arial Unicode MS", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(246, 6, 589, 39);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(mini.class.getResource("/mini/kindpng_1148391-2.png")));
		lblNewLabel_2.setBounds(929, 70, 480, 320);
		contentPane.add(lblNewLabel_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(179, 218, 222));
		scrollPane.setBounds(7, 444, 1414, 328);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		table.getTableHeader().setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		table.setBackground(new Color(163, 182, 222));
		scrollPane.setViewportView(table);
		
		btnbmi = new JButton("BMI");
		btnbmi.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnbmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//frame.dispose();
				BMICalculator bmicalculator2 = new BMICalculator(username);
				bmicalculator2.frame.setVisible(true);
				
			}
		});
		btnbmi.setForeground(new Color(0, 0, 128));
		btnbmi.setBackground(new Color(255, 153, 153));
		btnbmi.setBounds(381, 339, 180, 50);
		contentPane.add(btnbmi);
	}
}
