package controller;

import java.util.ArrayList;

import model.bo.CarroBO;
import model.vo.Carro;

public class CarroController {
	
	private CarroBO carroBO = new CarroBO();
	
	public ArrayList<Carro> consultarTodos() {
		CarroBO bo = new CarroBO();
		return bo.consultarTodos();
	}
	
	public Carro inserir(Carro novoCarro) {
		return carroBO.inserir(novoCarro);
	}
}
