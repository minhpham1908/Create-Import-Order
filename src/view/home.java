package view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class home extends JPanel {

	/**
	 * Create the panel.
	 */
	public home() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton createImportOrderBtn = new JButton("Tạo đơn hàng");
		add(createImportOrderBtn);
		
	}

}
