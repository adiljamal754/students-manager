package view;

import static javax.swing.BorderFactory.createLineBorder;
import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.StudentController;
import model.Student;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class StudentManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblTest1;
	private JTextField txtCode;
	private JLabel lblName_1;
	private JTextField txtName;
	private JTextField txtTest1;
	private JLabel lblTest1_1;
	private JTextField txtTest2;
	private JPanel panel_1;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnList;
	private JButton btnCancel;
	private JPanel panel_2;
	private JScrollPane scrollPane_2;
	private JTable table;

	private void clearInputs() {
		txtCode.setText("");
		txtName.setText("");
		txtTest1.setText("");
		txtTest2.setText("");
	}

	@SuppressWarnings("unused")
	private void validateFields() {
		if (txtCode.getText().equals("") || txtName.getText().equals("") || txtTest1.getText().equals("")
				|| txtTest2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Preecha todos os campos");
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManager frame = new StudentManager();
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
	public StudentManager() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);

		panel.setBorder(createTitledBorder(createLineBorder(new Color(255, 255, 255)), "DADOS DO Student",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER, new Font("Dialog", 0, 12),
				new Color(255, 255, 255)));

		panel.setBounds(0, 0, 416, 236);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblCode = new JLabel("CÓDIGO");
		lblCode.setForeground(Color.WHITE);
		lblCode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCode.setBounds(10, 32, 64, 14);
		panel.add(lblCode);

		lblTest1 = new JLabel("NOTA 2");
		lblTest1.setForeground(Color.WHITE);
		lblTest1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTest1.setBounds(10, 187, 64, 14);
		panel.add(lblTest1);

		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(83, 25, 146, 29);
		panel.add(txtCode);

		lblName_1 = new JLabel("NOME");
		lblName_1.setForeground(Color.WHITE);
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName_1.setBounds(10, 83, 46, 14);
		panel.add(lblName_1);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(83, 76, 260, 29);
		panel.add(txtName);

		txtTest1 = new JTextField();
		txtTest1.setColumns(10);
		txtTest1.setBounds(84, 126, 146, 29);
		panel.add(txtTest1);

		lblTest1_1 = new JLabel("NOTA 1");
		lblTest1_1.setForeground(Color.WHITE);
		lblTest1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTest1_1.setBounds(10, 133, 64, 14);
		panel.add(lblTest1_1);

		txtTest2 = new JTextField();
		txtTest2.setColumns(10);
		txtTest2.setBounds(84, 182, 146, 29);
		panel.add(txtTest2);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(createTitledBorder(createLineBorder(new Color(255, 255, 255)),

				"OPERAÇÕES", TitledBorder.DEFAULT_JUSTIFICATION,

				TitledBorder.CENTER, new Font("Dialog", 0, 12), new Color(255, 255, 255)));
		panel_1.setBackground(Color.GRAY);
		panel_1.setBounds(415, 0, 416, 236);
		contentPane.add(panel_1);

		JButton btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				validateFields();

				int code = Integer.parseInt(txtCode.getText().toString());
				String name = txtName.getText().toString();
				double test1 = Double.parseDouble(txtTest1.getText().toString());
				double test2 = Double.parseDouble(txtTest2.getText().toString());

				try {
					StudentController.add(code, name, test1, test2);
					JOptionPane.showMessageDialog(null, "Estudante adiconado com sucesso");
					clearInputs();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				;

				JOptionPane.showMessageDialog(null, "Estudante adiconado com sucesso");
				clearInputs();
			}
		});

		btnDelete = new JButton("Eliminar");
		btnDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int position = table.getSelectedRow();

				try {
					StudentController.delete(position);
					clearInputs();
					JOptionPane.showMessageDialog(null, "Estudante Eliminado com sucesso!!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnUpdate = new JButton("Atualizar");
		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					validateFields();
					
					int code = Integer.parseInt(txtCode.getText().trim());
					String name =txtName.getText().trim();
					double test1 = Double.parseDouble(txtTest1.getText().trim());
					double test2 = Double.parseDouble(txtTest2.getText().trim());
					
					//int position = table.getSelectedRow();
					StudentController.edit(code, name, test1, test2);
					clearInputs();
					
					JOptionPane.showMessageDialog(null, "Estudante Actualizado");
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Erro ao actualizar!!" + e2.getMessage(),"Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnList = new JButton("Listar");
		btnList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				tblModel.setRowCount(0);

				ArrayList<Student> students = StudentController.list();

				for (Student student : students) {
					tblModel.addRow(new Object[] { student.getCode(), student.getName(), student.getTest1(),
							student.getTest2(), student.caculateAverage() });
				}
				
				clearInputs();
			}
		});

		btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				clearInputs();
			}
		});

		btnAdd.setBounds(10, 22, 124, 31);
		panel_1.add(btnAdd);
		btnUpdate.setBounds(10, 78, 124, 31);
		panel_1.add(btnUpdate);
		btnCancel.setBounds(10, 132, 124, 31);

		btnDelete.setBounds(161, 22, 124, 31);
		panel_1.add(btnDelete);

		panel_1.add(btnCancel);

		btnList.setBounds(161, 78, 124, 31);
		panel_1.add(btnList);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(createTitledBorder(createLineBorder(new Color(255, 255, 255)),

				"LISTAGEM", TitledBorder.DEFAULT_JUSTIFICATION,

				TitledBorder.CENTER, new Font("Dialog", 0, 12), new Color(255, 255, 255)));

		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(0, 235, 814, 236);
		contentPane.add(panel_2);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 25, 794, 200);
		panel_2.add(scrollPane_2);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int pos = table.getSelectedRow();
				txtCode.enable(false);
				Student student = StudentController.list().get(pos);
				txtCode.setText(String.valueOf(student.getCode()));
				txtName.setText(student.getName());
				txtTest1.setText(String.valueOf(student.getTest1()));
				txtTest2.setText(String.valueOf(student.getTest2()));

			}
		});
		table.setBackground(Color.CYAN);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00D3DIGO", "NOME", "NOTA 1", "NOTA 2", "M\u00C9DIA" }));
		scrollPane_2.setViewportView(table);
	}
}
