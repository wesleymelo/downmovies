package br.ucb.fimes.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucb.filmes.beans.Categoria;
import br.ucb.filmes.beans.Filme;
import br.ucb.fimes.interfaces.DAO;

public class FilmeDAO implements DAO<Filme> {

	public int insert(Filme filme) {
		String sql = "INSERT INTO filmes(id_filme, formato, id_categoria, titulo, descricao, diretor, ano_lancamento, idioma, qualidade, legenda, tamanho, tempo_duracao) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		int retorno = 0;
		
		try {
			conn = FactoryConnection.init();
			ps = conn.prepareStatement(sql);
			ps.setString(1, filme.getFormato());
			ps.setInt(2, filme.getCategoria().getId());
			ps.setString(3, filme.getTitulo());
			ps.setString(4, filme.getDescricao());
			ps.setString(5, filme.getDiretor());
			ps.setInt(6, filme.getAnoLancamento());
			ps.setString(7, filme.getIdioma());
			ps.setString(8, filme.getLegenda());
			ps.setString(9, filme.getQualidade());
			ps.setDouble(10, filme.getTamanho());
			ps.setInt(11, filme.getTempoDuracao());
			retorno = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FactoryConnection.closeConnection(conn, ps);
		return retorno;
	}

	public void delete(int id) {
		String sql = "DELETE FROM filmes WHERE id_filme = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		
		try {
			conn = FactoryConnection.init();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FactoryConnection.closeConnection(conn, ps);
		
	}

	public void update(int id, Filme filme) {
		String sql = "UPDATE filmes SET formato = ?, id_categoria = ?, titulo = ?, descricao = ?, diretor = ?, ano_lancamento = ?, idioma = ?, qualidade = ?, legenda = ?, tamanho = ?, tempo_duracao = ? where id_filme = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		
		
		try {
			conn = FactoryConnection.init();
			ps = conn.prepareStatement(sql);
			ps.setString(1, filme.getFormato());
			ps.setInt(2, filme.getCategoria().getId());
			ps.setString(3, filme.getTitulo());
			ps.setString(4, filme.getDescricao());
			ps.setString(5, filme.getDiretor());
			ps.setInt(6, filme.getAnoLancamento());
			ps.setString(7, filme.getIdioma());
			ps.setString(8, filme.getLegenda());
			ps.setString(9, filme.getQualidade());
			ps.setDouble(10, filme.getTamanho());
			ps.setInt(11, filme.getTempoDuracao());
			ps.setInt(12, filme.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FactoryConnection.closeConnection(conn, ps);
		
	}

	public List<Filme> recoverAll() {
 		String sql = "SELECT * FROM filmes;";
 		Connection conn = null;
 		Statement stm = null;
 		ResultSet rs = null;
 		List<Filme> filmes = new ArrayList<Filme>();
 		
 		
 		try {
 			conn = FactoryConnection.init();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				filmes.add(getFilme(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		FactoryConnection.closeConnection(conn, stm, rs);
 		return filmes;
	}

	private Filme getFilme(ResultSet rs) throws SQLException {
		return new Filme(rs.getInt("id_filme"), rs.getString("formato"), new Categoria(), rs.getString("titulo"), rs.getString("descricao"), rs.getString("diretor"), rs.getInt("anoLancamento"), rs.getString("idioma"), rs.getString("legenda"), rs.getString("qualidade"), rs.getDouble("tamanho"), rs.getInt("tempoDuracao"));
	}
	
}
