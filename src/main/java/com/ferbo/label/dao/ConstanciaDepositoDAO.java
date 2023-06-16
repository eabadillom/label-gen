package com.ferbo.label.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ferbo.inventario.model.ConstanciaDeposito;

public class ConstanciaDepositoDAO extends DAO implements IDAO<ConstanciaDeposito>{
	private static final String SELECT = "SELECT FOLIO, CTE_CVE, FECHA_INGRESO, NOMBRE_TRANSPORTISTA, PLACAS_TRANSPORTE, OBSERVACIONES, folio_cliente, valor_declarado, status, aviso_cve, temperatura "
			+ "FROM CONSTANCIA_DE_DEPOSITO "
			;

	@Override
	public ConstanciaDeposito getBean(ResultSet rs)
	throws SQLException {
		ConstanciaDeposito bean = new ConstanciaDeposito();
		bean.setFolio(getInteger(rs, "FOLIO"));
		bean.setIdCliente(getInteger(rs, "CTE_CVE"));
		bean.setFechaIngreso(rs.getDate("FECHA_INGRESO"));
		bean.setNombreTransportista(getTrim(rs.getString("NOMBRE_TRANSPORTISTA")));
		bean.setPlacasTransporte(getTrim(rs.getString("PLACAS_TRANSPORTE")));
		bean.setObservaciones(getTrim(rs.getString("OBSERVACIONES")));
		bean.setFolioCliente(getTrim(rs.getString("folio_cliente")));
		bean.setValorDeclarado(rs.getBigDecimal("valor_declarado"));
		bean.setStatus(getInteger(rs, "status"));
		bean.setIdAviso(getInteger(rs, "aviso_cve"));
		bean.setTemperatura(getTrim(rs.getString("temperatura")));
		
		return bean;
	}
	
	@Override
	public ConstanciaDeposito get(Connection conn, Object id) throws SQLException {
		ConstanciaDeposito bean = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		int idx = 1;
		
		try {
			sql = SELECT + "WHERE folio_cliente = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(idx++, (String) id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bean = this.getBean(rs);
			}
			
		} finally {
			close(rs);
			close(ps);
		}
		
		return bean;
	}

	@Override
	public List<ConstanciaDeposito> get(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Connection conn, ConstanciaDeposito bean) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Connection conn, ConstanciaDeposito bean) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Connection conn, ConstanciaDeposito bean) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}


	

	
}
