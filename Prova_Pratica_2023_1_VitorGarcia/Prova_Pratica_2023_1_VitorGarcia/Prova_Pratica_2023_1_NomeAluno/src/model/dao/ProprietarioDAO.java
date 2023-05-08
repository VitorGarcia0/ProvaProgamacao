package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Banco;
import model.vo.Carro;
import model.vo.Proprietario;

public class ProprietarioDAO {
	
	public Proprietario inserir(Proprietario novoProprietario) {
		Connection conn = Banco.getConnection();

		int idInserido = -1;
		String sql = "INSERT INTO PROPRIETARIO (NOMECOMPLETO, CNH, CPF) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setString(1, novoProprietario.getNomeCompleto());
			ps.setString(2, novoProprietario.getCNH());
			ps.setString(3, novoProprietario.getCPF());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				idInserido = rs.getInt(1);
			}

			if (idInserido > 0) {
				System.out.println("Carro inserido com sucesso");
			} else {
				System.out.println("Erro ao inserir carro");
			}
			
			CarroDAO carroDAO = new CarroDAO();
			for (Carro c: novoProprietario.getCarros()) {
				c.setProprietario(novoProprietario);
				carroDAO.atualizar(c);
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return novoProprietario;
	}


}
