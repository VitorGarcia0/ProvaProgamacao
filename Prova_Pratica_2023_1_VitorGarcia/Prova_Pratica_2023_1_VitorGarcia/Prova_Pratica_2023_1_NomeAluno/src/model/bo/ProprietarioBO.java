package model.bo;

import model.dao.ProprietarioDAO;
import model.vo.Proprietario;

public class ProprietarioBO {

	private ProprietarioDAO dao = new ProprietarioDAO();

	public Proprietario inserir(Proprietario novoProprietario) {

		return dao.inserir(novoProprietario);
	}

}
