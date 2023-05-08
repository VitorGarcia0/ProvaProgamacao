package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Banco;
import model.vo.Carro;
import model.vo.Montadora;

public class CarroDAO {

	public Carro consultarPorId(int idCarro) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String sql = " SELECT * FROM CARRO WHERE ID = " + idCarro;

		Carro c = null;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				c = this.criarCarroResultSet(rs);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	public Carro inserir(Carro novoCarro) {
		Connection conn = Banco.getConnection();

		int idInserido = -1;
		String sql = "INSERT INTO CARRO(IDMONTADORA, ANO, MODELO, VALOR, PLACA) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// Preenche a consulta com os atributos do objeto
			ps.setInt(1, novoCarro.getMontadora().getId());
			ps.setInt(2, novoCarro.getAno());
			ps.setString(3, novoCarro.getModelo());
			ps.setDouble(4, novoCarro.getValor());
			ps.setString(5, novoCarro.getPlaca());
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

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return novoCarro;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		boolean removidoSucesso = false;
		String query = "DELETE FROM Carro WHERE id = " + id;

		try {
			int codigoRetorno = stmt.executeUpdate(query);
			if (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				System.out.println("Carro removido com sucesso");
				removidoSucesso = true;
			} else {
				System.out.println("Erro ao remover carro.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return removidoSucesso;
	}

	public boolean atualizar(Carro c) {
		Connection conn = Banco.getConnection();

		boolean atualizadoSucesso = false;
		String sql = "UPDATE CARRO SET IDMONTADORA=?, ANO=?, MODELO=?, VALOR=?, PLACA=?, IDPROPRIETARIO=? WHERE ID=?";

		try {
			PreparedStatement query = conn.prepareStatement(sql);
			query.setInt(1, c.getMontadora().getId());
			query.setInt(2, c.getAno());
			query.setString(3, c.getModelo());
			query.setDouble(4, c.getValor());
			query.setString(5, c.getString());
			query.setInt(6, c.getProprietario().getId());
			query.setInt(7, c.getId());

			int codigoRetorno = query.executeUpdate();
			if (codigoRetorno == Banco.CODIGO_RETORNO_SUCESSO) {
				System.out.println("Carro atualizado com sucesso");
				atualizadoSucesso = true;
			} else {
				System.out.println("Erro ao atualizar carro.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atualizadoSucesso;
	}

	public ArrayList<Carro> listarTodos() {
		ArrayList<Carro> carros = new ArrayList<Carro>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT * FROM CARRO";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Carro c = criarCarroResultSet(resultado);
				carros.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os carros.");
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return carros;
	}

	/**
	 * Constrói um carro a partir de uma linha do ResultSet
	 * 
	 * @param resultadoConsulta
	 * @return o carro construído
	 */
	private Carro criarCarroResultSet(ResultSet resultadoConsulta) {
		Carro c = null;

		try {
			c = new Carro();
			c.setId(resultadoConsulta.getInt("id"));

			// Obtém a montadora do carro chamando MontadoraDAO
			// Afinal, a tabela Carro tem apenas o IDMONtadora
			int idMontadora = resultadoConsulta.getInt("idmontadora");
			MontadoraDAO montadoraDAO = new MontadoraDAO();
			Montadora m = montadoraDAO.consultarPorId(idMontadora);

			c.setMontadora(m);
			c.setAno(resultadoConsulta.getInt("ano"));
			c.setModelo(resultadoConsulta.getString("modelo"));
			c.setValor(resultadoConsulta.getDouble("valor"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}
}