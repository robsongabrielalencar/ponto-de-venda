
import java.awt.GridLayout;
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

@SuppressWarnings("serial")
public class Ponto_de_venda extends JFrame implements ActionListener {

	private JLabel label1, label2, label3, label4, label5, label6, label7;
	private JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7;
	private JButton addButton, viewButton, editButton, deleteButton, clearButton, exitButton;
	private JPanel panel;

	private ArrayList<String[]> produtos = new ArrayList<String[]>();

	public Ponto_de_venda() {
		setTitle("Sistema de Gerenciamento");
		setSize(600, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		label1 = new JLabel("ID Produto:");
		label2 = new JLabel("Nome do Produto:");
		label3 = new JLabel("Marca:");
		label4 = new JLabel("Fornecedor:");
		label5 = new JLabel("Data de fabricação:");
		label6 = new JLabel("Codigo de barras:");
		label7 = new JLabel("Quantidade: ");

		textField1 = new JTextField(10);
		textField2 = new JTextField(20);
		textField3 = new JTextField(20);
		textField4 = new JTextField(20);
		textField5 = new JTextField(10);
		textField6 = new JTextField(20);
		textField7 = new JTextField(10);

		addButton = new JButton("Adicionar");
		viewButton = new JButton("Visializar");
		editButton = new JButton("Editar");
		deleteButton = new JButton("Deletar");
		clearButton = new JButton("Limpar");
		exitButton = new JButton("Sair");

		addButton.addActionListener(this);
		viewButton.addActionListener(this);
		editButton.addActionListener(this);
		deleteButton.addActionListener(this);
		clearButton.addActionListener(this);
		exitButton.addActionListener(this);

		panel = new JPanel(new GridLayout(10, 2));
		panel.add(label1);
		panel.add(textField1);
		panel.add(label2);
		panel.add(textField2);
		panel.add(label3);
		panel.add(textField3);
		panel.add(label4);
		panel.add(textField4);
		panel.add(label5);
		panel.add(textField5);
		panel.add(label6);
		panel.add(textField6);
		panel.add(label7);
		panel.add(textField7);
		panel.add(viewButton);
		panel.add(addButton);
		panel.add(editButton);
		panel.add(deleteButton);
		panel.add(clearButton);
		panel.add(exitButton);

		add(panel);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			String[] prd = new String[7];
			prd[0] = textField1.getText();
			prd[1] = textField2.getText();
			prd[2] = textField3.getText();
			prd[3] = textField4.getText();
			prd[4] = textField5.getText();
			prd[5] = textField6.getText();
			prd[6] = textField7.getText();
			produtos.add(prd);
			JOptionPane.showMessageDialog(this, "Produto acidicionado com sucesso");
			clearFields();
		} else if (e.getSource() == viewButton) {
			String[] columns = { "ID Produto", "Nome do Produto", "Marca", "Fabricante", "Data de Fabricação",
					"Cod.De Barras", "Quantidade" };
			Object[][] data = new Object[produtos.size()][7];
			for (int i = 0; i < produtos.size(); i++) {
				data[i][0] = produtos.get(i)[0];
				data[i][1] = produtos.get(i)[1];
				data[i][2] = produtos.get(i)[2];
				data[i][3] = produtos.get(i)[3];
				data[i][4] = produtos.get(i)[4];
				data[i][5] = produtos.get(i)[5];
				data[i][6] = produtos.get(i)[6];
			}
			JTable table = new JTable(data, columns);
			JScrollPane srollPane = new JScrollPane(table);
			JFrame frame = new JFrame("Tabela dos Produtos");
			frame.add(srollPane);
			frame.setSize(800, 400);
			frame.setVisible(true);
		} else if (e.getSource() == editButton) {
			String produtoID = JOptionPane.showInputDialog(this, "Digite o ID para Editar");
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i)[0].equals(produtoID)) {
					String[] pro = new String[7];
					pro[0] = produtoID;
					pro[1] = textField2.getText();
					pro[2] = textField3.getText();
					pro[3] = textField4.getText();
					pro[4] = textField5.getText();
					pro[5] = textField6.getText();
					pro[6] = textField7.getText();
					produtos.set(i, pro);
					JOptionPane.showMessageDialog(this, "Produto Editado");
					clearFields();
					return;
				}
			}
			JOptionPane.showMessageDialog(this, "Produto não encontrado");
		} else if (e.getSource() == deleteButton) {
			String produtoID = JOptionPane.showInputDialog(this, "Entre com o ID para deletar o produto");
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i)[0].equals(produtoID)) {
					int confirmResult = JOptionPane.showConfirmDialog(this, "Deseja deletar o produto?");
					if (confirmResult == JOptionPane.YES_OPTION) {
						produtos.remove(i);
						JOptionPane.showMessageDialog(this, "Produto deletado");
						clearFields();
						return;
					} else if (confirmResult == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(this, "Produto não foi deletado");
						return;
					} else if (confirmResult == JOptionPane.CANCEL_OPTION) {
						JOptionPane.showMessageDialog(this, "Ação cancelada");
						return;
					}
				}
			}
			JOptionPane.showMessageDialog(this, "Produto não encotrado");

		} else if (e.getSource() == clearButton) {
			clearFields();
		} else if (e.getSource() == exitButton) {
			System.exit(0);
		}

	}

	private void clearFields() {
		textField1.setText("");
		textField2.setText("");
		textField3.setText("");
		textField4.setText("");
		textField5.setText("");
		textField6.setText("");
		textField7.setText("");
	}

	public static void main(String[] args) {
		new Ponto_de_venda();
	}
}