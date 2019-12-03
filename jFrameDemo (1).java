package com.fundamental;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class jFrameDemo implements ActionListener{

	JFrame f;
	JLabel l1,l2,l3,l4;
	JTextField t1,t2,t3,t4;
	JButton b1,b2,b3,b4;
	public jFrameDemo() {

		f=new JFrame("My Frame");
		f.setSize(500, 500);
		f.setVisible(true);
		//f.setLayout(new FlowLayout());
		//f.setLayout(new GridLayout(4, 2));
		f.setLayout(null);
		
		l1=new JLabel("Id");
		l2=new JLabel("Fname");
		l3=new JLabel("Lname");
		l4=new JLabel("Email");
		
		t1=new JTextField(20);
		t2=new JTextField(20);
		t3=new JTextField(20);
		t4=new JTextField(20);
		
		b1=new JButton("Insert");
		b2=new JButton("Search");
		b3=new JButton("Update");
		b4=new JButton("Delete");
		
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(l3);
		f.add(t3);
		f.add(l4);
		f.add(t4);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		l1.setBounds(50, 50, 120, 20);
		l2.setBounds(50, 80, 120, 20);
		l3.setBounds(50, 110, 120, 20);
		l4.setBounds(50, 140, 120, 20);
		
		t1.setBounds(120, 50, 120, 20);
		t2.setBounds(120, 80, 120, 20);
		t3.setBounds(120, 110, 120, 20);
		t4.setBounds(120, 140, 120, 20);
		
		b1.setBounds(50, 170, 90, 20);
		b2.setBounds(150, 170, 90, 20);
		b3.setBounds(50, 200, 90, 20);
		b4.setBounds(150, 200, 90, 20);
	}
	public static void main(String[] args) {
		new jFrameDemo();
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==b1)
		{
			String f=t2.getText();
			String l=t3.getText();
			String em=t4.getText();
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/placement", "root", "");
				/*String sql="insert into student(fname,lname,email) values('"+f+"','"+l+"','"+em+"')";
				Statement stmt=conn.createStatement();
				stmt.execute(sql);*/
				String sql="insert into student(fname,lname,email) value(?,?,?)";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, f);
				pst.setString(2, l);
				pst.setString(3, em);
				pst.executeUpdate();
				System.out.println("Data Inserted Successfully");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(e.getSource()==b2)
		{
			int id=Integer.parseInt(t1.getText());
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/placement","root", "");
				String sql="Select * from student where id=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs=pst.executeQuery();
				
				if(rs.next())
				{
					t2.setText(rs.getString("fname"));
					t3.setText(rs.getString("lname"));
					t4.setText(rs.getString("email"));
				}
				else
				{
					System.out.println("Id Not Found");
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(e.getSource()==b3)
		{
			int id=Integer.parseInt(t1.getText());
			String f=t2.getText();
			String l=t3.getText();
			String em=t4.getText();
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/placement","root","");
				String sql="update student set fname=?,lname=?,email=? where id=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, f);
				pst.setString(2, l);
				pst.setString(3, em);
				pst.setInt(4, id);
				pst.executeUpdate();
				System.out.println("Data Updated Successfully");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(e.getSource()==b4)
		{
			int id=Integer.parseInt(t1.getText());
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3307/placement","root","");
				String sql="delete from student where id=?";
				PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, id);
				pst.executeUpdate();
				System.out.println("Data Deleted Successfully");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
