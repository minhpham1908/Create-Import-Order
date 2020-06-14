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

import controller.ProcessOrderController;
import model.MerchandiseToImport;
import model.Site;
import util.TimeProcessor;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.Dimension;

public class OrderView extends JDialog {
	private JTable table;
	private JLabel merchandiseName;
	private String unit;

	public OrderView(ArrayList<Site> chosenSites, String unit) {
		setAlwaysOnTop(true);
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
		setBounds(100, 100, 600, 800);
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
			merchandiseName = new JLabel("Sản phẩm ...");
			merchandiseName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			getContentPane().add(merchandiseName, BorderLayout.NORTH);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable() {
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				table.setPreferredScrollableViewportSize(new Dimension(600, 800));
				table.setModel(new DefaultTableModel(new Object[][] {},
						new String[] { "Import Site", "Quality", "Unit", "Delivery by", "Delivery on" }) {
					Class[] columnTypes = new Class[] { Object.class, Object.class, String.class, Object.class,
							Object.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					boolean[] columnEditables = new boolean[] { false, false, true, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				table.getColumnModel().getColumn(0).setResizable(false);
				table.getColumnModel().getColumn(1).setResizable(false);
				table.getColumnModel().getColumn(3).setResizable(false);
				table.getColumnModel().getColumn(4).setResizable(false);
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(JLabel.CENTER);
				table.setDefaultRenderer(String.class, centerRenderer);
				table.setDefaultRenderer(Integer.class, centerRenderer);
				((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
						.setHorizontalAlignment(JLabel.CENTER);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setReorderingAllowed(false);
				table.setRowHeight(40);
				
				scrollPane.setViewportView(table);
			
				this.unit = unit;

				initTable(chosenSites);
			}
		}
	}

	public void setOrderLabel(String merchName, String merchCode) {
		String label = "Đơn hàng cho sản phẩm \"" + merchName + "\" | mã sản phẩm: " + merchCode;
		merchandiseName.setText(label);
	}

	private void initTable(ArrayList<Site> chosenSites) {
		for (Site site : chosenSites) {
			addChosenSites(site);
		}
	}

	private void addChosenSites(Site site) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.addRow(new Object[] { site.getSiteName(), site.getSellingMerchandiseStock(), this.unit,
				site.getTransportation(), site.getNumberOfDayTransporting() + " ngày" });
	}

}
