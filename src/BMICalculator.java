
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BMICalculator implements ActionListener{
    JTextField tfHeight,tfWeight;
    static JLabel labelHeight,labelWeight,status,result,result2;
    JButton btn;
    JFrame frame;
    private JButton btnBack;
    public String username;
    
    
    BMICalculator(final String username){
    	this.username = username;
        frame = new JFrame();
        Color c = new Color(254, 255, 255);
        frame.getContentPane().setBackground(c);
        //Image icon = new ImageIcon(this.getClass().getResource("/icons/logo.png")).getImage(); 
        labelWeight = new JLabel("Weight in kg");
        labelWeight.setFont(new Font("Kohinoor Telugu", Font.PLAIN, 22));
        labelWeight.setForeground(Color.BLACK);
        labelWeight.setBounds(620,129,150,30);
        tfWeight = new JTextField();
        tfWeight.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        tfWeight.setForeground(Color.DARK_GRAY);
        tfWeight.setToolTipText("Enter weight");
        tfWeight.setBounds(620,175,250,50);
        labelHeight = new JLabel("Height in cm");
        labelHeight.setFont(new Font("Kohinoor Telugu", Font.PLAIN, 22));
        labelHeight.setForeground(Color.BLACK);
        labelHeight.setBounds(620,270,140,30);
        tfHeight = new JTextField();
        tfHeight.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
        tfHeight.setForeground(Color.DARK_GRAY);
        tfHeight.setToolTipText("Enter height");
        tfHeight.setBounds(620,312,250,50);
        status = new JLabel();
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setBounds(60,185,190,40);
        btn=new JButton("Calculate");
        btn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        btn.setBounds(620,421,250,50);
        btn.setBackground(Color.BLACK);
        btn.setForeground(new Color(0, 51, 204));
        result = new JLabel();
        result.setBounds(620,475,150,40);
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result2 = new JLabel();
        result2.setBounds(620,512,250,60);
        result2.setHorizontalAlignment(SwingConstants.CENTER);
        result2.setOpaque(false);
        btn.addActionListener(this);
       // frame.setIconImage(icon);
        frame.getContentPane().add(labelHeight);
        frame.getContentPane().add(labelWeight);
        frame.getContentPane().add(tfHeight);
        frame.getContentPane().add(tfWeight);
        frame.getContentPane().add(btn);
        frame.getContentPane().add(status);
        frame.getContentPane().add(result);
        frame.getContentPane().add(result2);
        frame.setSize(1536, 1152);
        frame.getContentPane().setLayout(null);
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
				mini frame2 = new mini(username);
				frame2.setVisible(true);
        		
        	}
        });
        btnBack.setForeground(new Color(0, 51, 204));
        btnBack.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        btnBack.setBackground(Color.BLACK);
        btnBack.setBounds(620, 702, 250, 50);
        frame.getContentPane().add(btnBack);
        frame.setTitle(" Calculator");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }
    // Convert height in centimeter to meter
    public static double centimeterToMeter(double cm){
        double metre = cm/100;
        return metre;
    }
    // Calculate BMI
    public static double findBMI(double height,double weight){
        double h = centimeterToMeter(height);
        double bmi = weight/h/h;
        DecimalFormat df = new DecimalFormat("##.##");
        String trimBmi=df.format(bmi);
        result.setFont(new Font("Serif",Font.PLAIN,22));
        result.setForeground(Color.black);
        result.setText("BMI is "+trimBmi);
        status.setOpaque(false);
        return bmi;
    }
    // Display bmi by JLabel
    public static void setResult(double bmi){
        result2.setFont(new Font("Serif",Font.PLAIN,22));
        result2.setOpaque(true);
        if(bmi<15){
            String s = "<html>Very severely<br> underweight</html>";
            result2.setBackground(new Color(0,0,255));
            result2.setForeground(Color.WHITE);
            result2.setText(s);
        }
        else if(bmi>=15 && bmi<16){
            result2.setBackground(new Color(102,153,255));
            result2.setForeground(Color.WHITE);
            result2.setText("Severely Underweight");
        }
        else if(bmi>=16 && bmi<18.5){
            result2.setBackground(new Color(102,255,255));
            result2.setForeground(Color.black);
            result2.setText("Underweight");
        }
        else if(bmi>=18.5 && bmi<=24.9){
            result2.setBackground(Color.GREEN);
            result2.setForeground(Color.black);
            result2.setText("Healthy");
        }
        else if(bmi>=25 && bmi<=29.9){
            result2.setBackground(Color.yellow);
            result2.setForeground(Color.black);
            result2.setText("Overweight");
        }
        else if(bmi>=30 && bmi<40){
            result2.setBackground(Color.orange);
            result2.setForeground(Color.black);
            result2.setText("Obese");
        }
        else if(bmi>=40){
            result2.setBackground(Color.red);
            result2.setForeground(Color.white);
            result2.setText("Extremely Obese");
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        BMICalculator bmicalculator1 = new BMICalculator();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(tfWeight.getText().trim().isEmpty()){
            status.setOpaque(true);
            status.setBackground(new Color(204,153,255));
            status.setFont(new Font("Serif",Font.PLAIN,18));
            status.setText("Weight cannot be empty");
        }
        else if(tfHeight.getText().trim().isEmpty()){
            status.setOpaque(true);
            status.setBackground(new Color(204,153,255));
            status.setFont(new Font("Serif",Font.PLAIN,18));
            status.setText("Height cannot be empty");
        }
        else{
            try{
                double wtf = Double.parseDouble(tfWeight.getText());
                double htf = Double.parseDouble(tfHeight.getText());
                if(wtf==0 || htf==0){
                    status.setText("");
                    status.setOpaque(false);
                    result.setText("");
                    result2.setText("");
                    result2.setOpaque(false);
                    JOptionPane.showMessageDialog(frame,"Height and weight cannot be 0");   
                }
                else{
                    double userbmi=findBMI(htf,wtf);
                    setResult(userbmi);
                    status.setText("");
                }
            } catch(NumberFormatException nfe){
                status.setOpaque(true);
                status.setText("Enter only numbers");
                status.setForeground(Color.DARK_GRAY);
                status.setBackground(new Color(204,153,255));
                status.setFont(new Font("Serif",Font.PLAIN,18));
            }
        }
    }
    
}