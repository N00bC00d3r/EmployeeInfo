package com.bisu.www;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void newscreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	void Refresh()
	{
		DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name", "Age","Department","Salary","Contact"}, 0);
		table.setModel(model);
	}
	public SearchFrame() {
		super("Search Window App");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(705, 100, 624, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(43, 11, 46, 14);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(85, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(221, 11, 46, 14);
		contentPane.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(297, 8, 110, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name", "Age","Department","Salary","Contact"}, 0);
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","youtube1234");
					String val1=textField.getText();
					String val2=textField_1.getText();
					if(val1.equals(""))
					{
						Refresh();
						String sql="select * from mytable where name='"+val2+"'";
						try {
							PreparedStatement statement=connection.prepareStatement(sql);
							ResultSet rs=statement.executeQuery();
							while(rs.next())
							{
								String t1=rs.getString("Id");
								String t2=rs.getString("Name");
								String t3=rs.getString("Age");
								String t4=rs.getString("Department");
								String t5=rs.getString("Salary");
								String t6=rs.getString("Contact");
								model.addRow(new Object[]{t1, t2,t3,t4,t5,t6});
								table.setModel(model);
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							String res="Table or view doesn't exist!";
							JTextArea output=new JTextArea(16,14);
							output.setText(res);
							JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
							//e.printStackTrace();
						}
					}
					else if(val2.equals(""))
					{
						Refresh();
						String sql="select * from mytable where id='"+val1+"'";
						try {
							PreparedStatement statement=connection.prepareStatement(sql);
							ResultSet rs=statement.executeQuery();
							while(rs.next())
							{
								String t1=rs.getString("Id");
								String t2=rs.getString("Name");
								String t3=rs.getString("Age");
								String t4=rs.getString("Department");
								String t5=rs.getString("Salary");
								String t6=rs.getString("Contact");
								model.addRow(new Object[]{t1, t2,t3,t4,t5,t6});
								table.setModel(model);
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							String res="Table or view doesn't exist!";
							JTextArea output=new JTextArea(16,14);
							output.setText(res);
							JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
							//e.printStackTrace();
						}
					}
					else if(val2.equals("-1") && val1.equals("-1"))
					{
						Refresh();
						String sql="select * from mytable ";
						try {
							PreparedStatement statement=connection.prepareStatement(sql);
							ResultSet rs=statement.executeQuery();
							while(rs.next())
							{
								String t1=rs.getString("Id");
								String t2=rs.getString("Name");
								String t3=rs.getString("Age");
								String t4=rs.getString("Department");
								String t5=rs.getString("Salary");
								String t6=rs.getString("Contact");
								model.addRow(new Object[]{t1, t2,t3,t4,t5,t6});
								table.setModel(model);
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
							String res="Table or view doesn't exist!";
							JTextArea output=new JTextArea(16,14);
							output.setText(res);
							JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					String res="Table or view doesn't exist!";
					JTextArea output=new JTextArea(16,14);
					output.setText(res);
					JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
				}

			}
		});
		btnSearch.setBounds(95, 49, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnNewButton = new JButton("Show All");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql="select * from mytable ";
				DefaultTableModel model = new DefaultTableModel(new String[]{"Id","Name", "Age","Department","Salary","Contact"}, 0);
				try {
					Refresh();
					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","youtube1234");
					PreparedStatement statement=connection.prepareStatement(sql);
					ResultSet rs=statement.executeQuery();
					while(rs.next())
					{
						String t1=rs.getString("Id");
						String t2=rs.getString("Name");
						String t3=rs.getString("Age");
						String t4=rs.getString("Department");
						String t5=rs.getString("Salary");
						String t6=rs.getString("Contact");
						model.addRow(new Object[]{t1, t2,t3,t4,t5,t6});
						table.setModel(model);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					String res="Table or view doesn't exist!";
					JTextArea output=new JTextArea(16,14);
					output.setText(res);
					JOptionPane.showMessageDialog(null, res,"Information messege dialog box",JOptionPane.ERROR_MESSAGE );
				}
			
					
				
			}
		});
		btnNewButton.setBounds(307, 49, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 104, 496, 323);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
	}
}
