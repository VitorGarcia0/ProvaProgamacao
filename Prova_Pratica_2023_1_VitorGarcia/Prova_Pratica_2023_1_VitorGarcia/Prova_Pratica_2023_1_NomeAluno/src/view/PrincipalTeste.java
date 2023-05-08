package view;

import java.util.ArrayList;

import model.dao.CarroDAO;
import model.dao.MontadoraDAO;
import model.vo.Carro;
import model.vo.Montadora;

public class PrincipalTeste {

	public static void main(String[] args) {
		CarroDAO cDAO = new CarroDAO();
		ArrayList<Carro> todosCarros = cDAO.listarTodos();

		System.out.println("********* Todos os carros *********\n");
		for(Carro c: todosCarros) {
			System.out.println(c);
		}
		System.out.println("************************************\n");
		
		MontadoraDAO montadoraDAO = new MontadoraDAO();
		ArrayList<Montadora> montadoras = montadoraDAO.listarTodas();

		System.out.println("********* Todos as montadoras *********\n");
		for(Montadora m: montadoras) {
			System.out.println(m);
		}
		System.out.println("************************************\n");
	}

}
