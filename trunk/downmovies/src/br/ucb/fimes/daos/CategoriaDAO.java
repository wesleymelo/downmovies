package br.ucb.fimes.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucb.filmes.beans.Categoria;
import br.ucb.fimes.interfaces.DAO;

public class CategoriaDAO implements DAO<Categoria>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int insert(Categoria categoria) {
		String sql = "INSERT INTO categorias(descricao) VALUES (?)";
		Connection conn = null;
		PreparedStatement ps = null;
		int retorno = 0;
		
		
		System.out.println(categoria.getDescricao());
		
		try {
			conn = FactoryConnection.init();
			ps = conn.prepareStatement(sql);
			ps.setString(1, categoria.getDescricao());
			retorno = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FactoryConnection.closeConnection(conn, ps);
		return retorno;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int id, Categoria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categoria> recoverAll() {
		String sql = "SELECT * FROM categorias;";
 		Connection conn = null;
 		Statement stm = null;
 		ResultSet rs = null;
 		List<Categoria> categorias = new ArrayList<Categoria>();
 		
 		
 		try {
 			conn = FactoryConnection.init();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				categorias.add(getCategoria(rs));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		FactoryConnection.closeConnection(conn, stm, rs);
 		return categorias;
	}
	
	
	private Categoria getCategoria(ResultSet rs) throws SQLException {
		return new Categoria(rs.getInt("id_categoria"), rs.getString("descricao"));
	}

	
}
