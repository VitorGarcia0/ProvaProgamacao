package controller;

import java.util.ArrayList;

import model.bo.MontadoraBO;
import model.vo.Montadora;

public class MontadoraController {

	public ArrayList<Montadora> consultarMontadoras() {
		MontadoraBO bo = new MontadoraBO();
		return bo.consultarMontadoras();
	}
}
