package controller;

import model.Exception.CampoInvalidoException;
import model.bo.ProprietarioBO;
import model.vo.Proprietario;

public class ProprietarioController {

	private ProprietarioBO proprietarioBO = new ProprietarioBO();

	public Proprietario inserir(Proprietario novoProprietario) throws CampoInvalidoException {
		this.validarCampos(novoProprietario);
		this.validarCPF(novoProprietario);

		return proprietarioBO.inserir(novoProprietario);
	}

	private void validarCPF(Proprietario novoProprietario) throws CampoInvalidoException {
		String mensagemValidacao = "";

		if (novoProprietario.getCPF() == null) {
			mensagemValidacao += "Informe um CPF \n";
		} else {
			String cpfSemMascara = novoProprietario.getCPF().replace(".", "");
			cpfSemMascara = novoProprietario.getCPF().replace("-", "");
			novoProprietario.setCPF(cpfSemMascara);

		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}

	}

	private void validarCampos(Proprietario novoProprietario) throws CampoInvalidoException {
		String mensagemValidacao = "";
			
		if (novoProprietario.getNomeCompleto() == null || novoProprietario.getNomeCompleto().trim().length() < 3) {
			mensagemValidacao += "Nome inválido \n";
		}

		if (novoProprietario.getCarros() == null) {
			mensagemValidacao += "Selecione um carro: \n";
		}

		if (novoProprietario.getCNH() == null || novoProprietario.getCNH().trim().length() < 11
				|| novoProprietario.getCNH().trim().length() > 11) {
			mensagemValidacao += "Informe um CNH com 11 dígitos: \n";
		}

		if(novoProprietario.getCarros == null || novoProprietario.getCarros.isEmpty() ) {
			mensagemValidacao += "Informe algum carro para o usuário! "
		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}

	}

}
