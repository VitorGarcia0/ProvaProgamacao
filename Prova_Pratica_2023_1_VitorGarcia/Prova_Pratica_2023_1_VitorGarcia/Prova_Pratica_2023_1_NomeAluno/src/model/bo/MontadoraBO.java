package model.bo;

import java.util.ArrayList;

import model.dao.MontadoraDAO;
import model.vo.Montadora;

public class MontadoraBO {

	public ArrayList<Montadora> consultarMontadoras() {
		MontadoraDAO mDAO = new MontadoraDAO();
		return mDAO.listarTodas();
	}

}
