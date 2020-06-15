package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.ProcessOrderController;
import model.MerchandiseToImport;
import model.MerchandiseToImportTableModel;
import util.SQLConnect;
import util.TimeProcessor;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class CreateImportOrderView implements Observer {

	private JFrame frame;
	private JTable table;

	public JFrame getFrame() {
		return frame;
	}

	private ProcessOrderController controller;
	private List<MerchandiseToImport> merchandiseToImportList;
	MerchandiseToImportTableModel tableModel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateImportOrderView window = new CreateImportOrderView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public CreateImportOrderView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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

		frame = new JFrame();

		frame.setResizable(false);
		frame.setName("frame");
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JButton btnBack = new JButton("Back");
		panel.add(btnBack);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

		JButton createOrderBtn = new JButton("Xu ly don hang");
		createOrderBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Click");
				int row = table.getSelectedRow();
				MerchandiseToImport merch = merchandiseToImportList.get(row);
				controller.onCreateOrderBtnClick(merch.getMerchandiseToImportCode());
				((DefaultTableModel) table.getModel()).fireTableDataChanged();
			}
		});
		panel_1.add(createOrderBtn);

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		tableModel = new MerchandiseToImportTableModel();
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setForeground(Color.BLACK);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Merchandise Code", "Merchandise Name",
				"Quantity odered", "Unit", "Desired delivery date" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, Integer.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);

		// custom by Minh
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(50);

		// end custom by Minh
		scrollPane.setViewportView(table);

		controller = new ProcessOrderController();

		initTable();

	}

	private void initTable() {
		this.merchandiseToImportList = controller.getMerchandiseToImportList();
		for (int i = 0; i < merchandiseToImportList.size(); i++) {
			MerchandiseToImport merch = merchandiseToImportList.get(i);
			addMerchToList(merch.getCode(), merch.getName(), merch.getNumberRequire(), merch.getUnit(),
					TimeProcessor.getStringLocalDateTime(merch.getDayShipRequire(), "dd MMM uuuu HH:mm:ss"));
		}
	}

	private void addMerchToList(String merchCode, String merchName, int quantity, String unit, String deliveryDate) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { merchCode, merchName, quantity, unit, deliveryDate });
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
