package com.bisu.www;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class MynewApp {

	public JFrame frame;
	public JTextField namefield;
	public JTextField agefield;
	public JTextField deptfield;
	public JTextField salfield;
	public JTextField contactfield;
	public JTextField Idfield;
	public JTextField delfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 MynewApp window = new MynewApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	void update()
	{
		namefield.setText("");
		agefield.setText("");
		deptfield.setText("");
		salfield.setText("");
		contactfield.setText("");
		Idfield.setText("");
		delfield.setText("");
	}
	/**
	 * Create the application.
	 */
	public MynewApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","youtube1234");
			frame = new JFrame();
			frame.setBounds(100, 100, 604, 507);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setTitle("Employee Info App");
			
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(74, 31, 46, 14);
			frame.getContentPane().add(lblName);
			
			namefield = new JTextField();
			namefield.setBounds(117, 28, 191, 20);
			frame.getContentPane().add(namefield);
			namefield.setColumns(10);
			
			JLabel lblAge = new JLabel("Age:");
			lblAge.setBounds(74, 91, 46, 14);
			frame.getContentPane().add(lblAge);
			
			agefield = new JTextField();
			agefield.setBounds(148, 88, 86, 20);
			frame.getContentPane().add(agefield);
			agefield.setColumns(10);
			
			JButton btnInsert = new JButton("Insert");
			btnInsert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String val2=namefield.getText();
					String val1=Idfield.getText();
					String val3=agefield.getText();
					String val4=deptfield.getText();
					String val5=salfield.getText();
					String val6=contactfield.getText();
					try {
						String sql = "insert into mytable "
								+ " (id,name, age,department,salary,contact)" + " values (?, ?,?,?,?,?)";
						
						PreparedStatement statement=connection.prepareStatement(sql);
						statement.setString(1,val1);
						statement.setString(2,val2);
						statement.setString(3,val3);
						statement.setString(4,val4);
						statement.setString(5,val5);
						statement.setString(6,val6);
						int err=statement.executeUpdate();
						String res="";
						if(err>0){			
							 res+="Information Updated Successfully!";
								JTextArea output=new JTextArea(16,14);
								output.setText(res);
								JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.INFORMATION_MESSAGE );
						}
						else 	
							{
							 res+="Some Error Occured!";
								JTextArea output=new JTextArea(16,14);
								output.setText(res);
								JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
							}
						update();
						
					} catch (SQLException e) {
						String res="Error id already Exists!";
						JTextArea output=new JTextArea(16,14);
						output.setText(res);
						JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
						update();
						//e.printStackTrace();
					}
				}
			});
			btnInsert.setBounds(246, 212, 89, 23);
			frame.getContentPane().add(btnInsert);
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String deleting=delfield.getText();
					String sql="delete from mytable where id='"+deleting+"'";
					try {
						PreparedStatement statement=connection.prepareStatement(sql);
						int err=statement.executeUpdate();
						String res="";
						if(err>0){			
							 res+="Deleted Successfully!";
								JTextArea output=new JTextArea(16,14);
								output.setText(res);
								JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.INFORMATION_MESSAGE );
						}
						else 	
							{
							 res+="Invalid Id!";
								JTextArea output=new JTextArea(16,14);
								output.setText(res);
								JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
							}
						update();
					} catch (SQLException e) {
						String res="Invalid Id!";
						JTextArea output=new JTextArea(16,14);
						output.setText(res);
						JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
						update();
						//e.printStackTrace();
					}
				}
			});
			btnDelete.setBounds(85, 373, 89, 23);
			frame.getContentPane().add(btnDelete);
			
			JButton btnSearch = new JButton("Search");
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//frame.dispose();
					SearchFrame sf=new SearchFrame();
					//sf.setVisible(true);
					sf.newscreen();
					update();
				}
			});
			btnSearch.setBounds(399, 325, 89, 23);
			frame.getContentPane().add(btnSearch);
			
			JLabel lblDepartment = new JLabel("Department:");
			lblDepartment.setBounds(296, 91, 79, 14);
			frame.getContentPane().add(lblDepartment);
			
			JLabel lblSalary = new JLabel("Salary:");
			lblSalary.setBounds(74, 154, 46, 14);
			frame.getContentPane().add(lblSalary);
			
			JLabel lblContact = new JLabel("Contact:");
			lblContact.setBounds(296, 154, 79, 14);
			frame.getContentPane().add(lblContact);
			
			deptfield = new JTextField();
			deptfield.setBounds(409, 88, 152, 20);
			frame.getContentPane().add(deptfield);
			deptfield.setColumns(10);
			
			salfield = new JTextField();
			salfield.setBounds(130, 151, 104, 20);
			frame.getContentPane().add(salfield);
			salfield.setColumns(10);
			
			contactfield = new JTextField();
			contactfield.setBounds(385, 151, 156, 20);
			frame.getContentPane().add(contactfield);
			contactfield.setColumns(10);
			
			JLabel lblEmployeeId = new JLabel("Employee ID:");
			lblEmployeeId.setBounds(343, 31, 79, 14);
			frame.getContentPane().add(lblEmployeeId);
			
			Idfield = new JTextField();
			Idfield.setBounds(432, 28, 86, 20);
			frame.getContentPane().add(Idfield);
			Idfield.setColumns(10);
			
			JLabel lblEnterIdTo = new JLabel("Enter ID to delete:");
			lblEnterIdTo.setBounds(34, 325, 109, 14);
			frame.getContentPane().add(lblEnterIdTo);
			
			delfield = new JTextField();
			delfield.setBounds(148, 322, 86, 20);
			frame.getContentPane().add(delfield);
			delfield.setColumns(10);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
