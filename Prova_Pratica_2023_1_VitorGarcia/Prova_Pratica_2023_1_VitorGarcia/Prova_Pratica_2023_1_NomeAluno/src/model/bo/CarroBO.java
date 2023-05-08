package model.bo;

import java.util.ArrayList;

import model.dao.CarroDAO;
import model.vo.Carro;

public class CarroBO {

	private CarroDAO dao = new CarroDAO();

	public ArrayList<Carro> consultarTodos() {
		return dao.listarTodos();
	}
	
	public Carro inserir(Carro novoCarro) {
		return dao.inserir(novoCarro);
	}
}
