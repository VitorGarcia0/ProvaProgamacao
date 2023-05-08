package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.CampoInvalidoException.CampoInvalidoException;
import model.dao.CarroDAO;
import controller.CarroController;
import controller.ProprietarioController;
import model.vo.Carro;
import model.vo.Proprietario;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaCadastroProprietario {

	private JFrame frmCadastroProprietario;
	private JTextField txtNomeCompleto;
	private JLabel lblNomeCompleto;
	private JLabel lblCNH;
	private JLabel lblCPF;
	private JButton btnSalvar;
	private JLabel lblCarros;
	private MaskFormatter mascaraCPF;
	private JFormattedTextField txtCPF;
	private JComboBox cbCarros;
	private JTextField txtCNH;
	private JButton btnAdicionar;
	private JLabel lblTotalCarros;

	private List<Carro> carrosDoProprietario = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroProprietario window = new TelaCadastroProprietario();
					window.frmCadastroProprietario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public TelaCadastroProprietario() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		frmCadastroProprietario = new JFrame();
		frmCadastroProprietario.setTitle("Cadastro de Proprietario");
		frmCadastroProprietario.setBounds(100, 100, 600, 400);
		frmCadastroProprietario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroProprietario.getContentPane().setLayout(null);

		lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setBounds(30, 33, 113, 15);
		frmCadastroProprietario.getContentPane().add(lblNomeCompleto);

		lblCNH = new JLabel("CNH:");
		lblCNH.setBounds(30, 92, 46, 14);
		frmCadastroProprietario.getContentPane().add(lblCNH);

		lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(333, 33, 46, 14);
		frmCadastroProprietario.getContentPane().add(lblCPF);

		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setBounds(130, 30, 170, 20);
		frmCadastroProprietario.getContentPane().add(txtNomeCompleto);
		txtNomeCompleto.setColumns(10);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Proprietario novoProprietario = new Proprietario();
				novoProprietario.setNomeCompleto(txtNomeCompleto.getText());
				novoProprietario.setCNH(txtCNH.getText());
				try {
					String cpfSemMascara = (String) mascaraCPF.stringToValue(txtCPF.getText());
					novoProprietario.setCPF(cpfSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", "Erro", JOptionPane.ERROR_MESSAGE);
				}

				novoProprietario.setCarros(carrosDoProprietario);
				ProprietarioController proprietarioController = new ProprietarioController();

				try {
					proprietarioController.inserir(novoProprietario);
					JOptionPane.showMessageDialog(null, "Proprietario salvo com sucesso", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (CampoInvalidoException excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSalvar.setBounds(421, 277, 114, 29);
		frmCadastroProprietario.getContentPane().add(btnSalvar);

		CarroController carrosController = new CarroController();
		List<Carro> listaCarros = carrosController.consultarTodos();

		cbCarros = new JComboBox<>(listaCarros.toArray());
		cbCarros.setToolTipText("Selecione");
		cbCarros.setSelectedIndex(-1);
		cbCarros.setBounds(119, 146, 331, 35);
		frmCadastroProprietario.getContentPane().add(cbCarros);

		lblCarros = new JLabel("Carros:");
		lblCarros.setBounds(30, 146, 46, 35);
		frmCadastroProprietario.getContentPane().add(lblCarros);

		// MASCARA CPF
		mascaraCPF = new MaskFormatter("###.###.###-##");
		mascaraCPF.setValueContainsLiteralCharacters(false);

		txtCPF = new JFormattedTextField(mascaraCPF);
		txtCPF.setBounds(389, 30, 170, 20);
		frmCadastroProprietario.getContentPane().add(txtCPF);

		txtCNH = new JTextField();
		txtCNH.setBounds(130, 89, 175, 20);
		frmCadastroProprietario.getContentPane().add(txtCNH);
		txtCNH.setColumns(10);

		btnAdicionar = new JButton("+");

		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// BOTÃO DE +, fazer com que jogue para o botão txt de Total de Carros
				carrosDoProprietario.add((Carro) cbCarros.getSelectedItem());
				lblTotalCarros.setText("Total " + carrosDoProprietario.size());

			}
		});

		btnAdicionar.setBounds(485, 146, 50, 35);
		frmCadastroProprietario.getContentPane().add(btnAdicionar);

		lblTotalCarros = new JLabel("Total de Carros");
		lblTotalCarros.setBounds(30, 250, 103, 15);
		frmCadastroProprietario.getContentPane().add(lblTotalCarros);

	}
}
