package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;

import view.CreateImportOrderView;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class App {

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBounds(10, 89, 1244, 581);
		frame.getContentPane().add(buttonsPanel);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
		
		JButton sellEmployeeBtn = new JButton("Nhân viên bán hàng");
		buttonsPanel.add(sellEmployeeBtn);
		
		JButton siteEmployeeBtn = new JButton("Nhân viên thuộc Site nhập khẩu");
		buttonsPanel.add(siteEmployeeBtn);
		
		JButton createOrderBtn = new JButton("Nhân viên đặt hàng quốc tế");
		buttonsPanel.add(createOrderBtn);
		
		JButton storeEmployeeBtn = new JButton("Nhân viên quản lý kho");
		buttonsPanel.add(storeEmployeeBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(378, 11, 507, 67);
		frame.getContentPane().add(panel_1);
		
		JLabel welcomeLabel = new JLabel("Vui lòng chọn vị trí của bạn");
		panel_1.add(welcomeLabel);
		
		//Minh code
		sellEmployeeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		siteEmployeeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		storeEmployeeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		createOrderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		sellEmployeeBtn.setActionCommand("selling");
		siteEmployeeBtn.setActionCommand("site");
		storeEmployeeBtn.setActionCommand("store");
		createOrderBtn.setActionCommand("createOrder");
		
		sellEmployeeBtn.addActionListener(new ButtonClickListener(this.getFrame()));
		siteEmployeeBtn.addActionListener(new ButtonClickListener(this.getFrame()));
		storeEmployeeBtn.addActionListener(new ButtonClickListener(this.getFrame()));
		createOrderBtn.addActionListener(new ButtonClickListener(this.getFrame()));
		
	}
	
	private class ButtonClickListener implements ActionListener{
		JFrame frame;
		public ButtonClickListener(JFrame jFrame) {
			this.frame = jFrame;
		}
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			System.out.println(command);
			
			if(command.equals("selling")) {
				//Tao frame selling
			} else if(command.equals("site")) {
				
			}
			else if(command.equals("store")) {
				
			}
			else if(command.equals("createOrder")) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							CreateImportOrderView window = new CreateImportOrderView();
							window.getFrame().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				this.frame.setEnabled(false);
				
			}
			
			
			
		}
	}

	
}
