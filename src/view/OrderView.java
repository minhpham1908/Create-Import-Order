package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class OrderView extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			OrderView dialog = new OrderView();
//			dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("serial")
	public OrderView() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("Đơn hàng cho ...");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton createOrderBtn = new JButton("Create");
				createOrderBtn.setActionCommand("OK");
				buttonPane.add(createOrderBtn);
				getRootPane().setDefaultButton(createOrderBtn);
			}
		}
		{
			JLabel merchandiseName = new JLabel("Sản phẩm ...");
			getContentPane().add(merchandiseName, BorderLayout.NORTH);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable(){
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Import Site", "Quality", "Delivery by", "Delivery on" }) {
					boolean[] columnEditables = new boolean[] { false, false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}

					Class[] columnTypes = new Class[] { String.class, Integer.class, String.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(2).setResizable(false);
				table.getColumnModel().getColumn(3).setResizable(false);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(JLabel.CENTER);
				table.setDefaultRenderer(String.class, centerRenderer);
				table.setDefaultRenderer(Integer.class, centerRenderer);
				((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
						.setHorizontalAlignment(JLabel.CENTER);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setReorderingAllowed(false);
				scrollPane.setViewportView(table);
				
				
			}
		}
	}

}
