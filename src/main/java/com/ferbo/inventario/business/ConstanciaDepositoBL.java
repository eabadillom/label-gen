package com.ferbo.inventario.business;

import java.sql.Connection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.inventario.model.Camara;
import com.ferbo.inventario.model.Cliente;
import com.ferbo.inventario.model.ConstanciaDeposito;
import com.ferbo.inventario.model.DetallePartida;
import com.ferbo.inventario.model.Partida;
import com.ferbo.inventario.model.Planta;
import com.ferbo.inventario.model.Producto;
import com.ferbo.inventario.model.UnidadManejo;
import com.ferbo.inventario.model.UnidadProducto;
import com.ferbo.label.dao.ClienteDAO;
import com.ferbo.label.dao.ConstanciaDepositoDAO;
import com.ferbo.label.servlet.CamaraDAO;
import com.ferbo.label.servlet.DetallePartidaDAO;
import com.ferbo.label.servlet.PartidaDAO;
import com.ferbo.label.servlet.PlantaDAO;
import com.ferbo.label.servlet.ProductoDAO;
import com.ferbo.label.servlet.UnidadManejoDAO;
import com.ferbo.label.servlet.UnidadProductoDAO;
import com.ferbo.tools.DBConn;

public class ConstanciaDepositoBL {
	private static Logger log = LogManager.getLogger(ConstanciaDepositoBL.class);
	
	public ConstanciaDeposito get(String folioCliente) {
		ConstanciaDeposito constancia = null;
		
		Connection conn = null;
		ConstanciaDepositoDAO constanciaDAO = null;
		Cliente cliente = null;
		ClienteDAO clienteDAO = null;
		List<Partida> partidaList = null;
		PartidaDAO partidaDAO = null;
		UnidadProducto udp = null;
		UnidadProductoDAO udpDAO = null;
		UnidadManejo udm = null;
		UnidadManejoDAO udmDAO = null;
		Producto producto = null;
		ProductoDAO productoDAO = null;
		List<DetallePartida> detallePartidaList = null;
		DetallePartidaDAO dpDAO = null;
		Camara camara = null;
		CamaraDAO camaraDAO = null;
		Planta planta = null;
		PlantaDAO plantaDAO = null;
		
		try {
			conn = DBConn.dsConexion();
			constanciaDAO = new ConstanciaDepositoDAO();
			clienteDAO = new ClienteDAO();
			partidaDAO = new PartidaDAO();
			udpDAO = new UnidadProductoDAO();
			udmDAO = new UnidadManejoDAO();
			productoDAO = new ProductoDAO();
			dpDAO = new DetallePartidaDAO();
			camaraDAO = new CamaraDAO();
			plantaDAO = new PlantaDAO();
			
			constancia = constanciaDAO.get(conn, folioCliente);
			cliente = clienteDAO.get(conn, constancia.getIdCliente());
			partidaList = partidaDAO.getByFolio(conn, constancia.getFolio());
			
			constancia.setCliente(cliente);
			constancia.setPartidaList(partidaList);
			
			for(Partida partida : partidaList) {
				udp = udpDAO.get(conn, partida.getIdUnidadProducto());
				producto = productoDAO.get(conn, udp.getIdProducto());
				udm = udmDAO.get(conn, udp.getIdUnidadManejo());
				detallePartidaList = dpDAO.getByIdPartida(conn, partida.getIdPartida());
				udp.setProducto(producto);
				udp.setUnidadManejo(udm);
				camara = camaraDAO.get(conn, partida.getIdCamara());
				planta = plantaDAO.get(conn, camara.getIdPlanta());
				camara.setPlanta(planta);
				partida.setUnidadProducto(udp);
				partida.setDetallePartidaList(detallePartidaList);
				partida.setCamara(camara);
			}
		} catch(Exception ex) {
			log.error("Problema para obtener el folio " + folioCliente, ex);
		} finally {
			DBConn.close(conn);
		}
		
		
		return constancia;
	}
}
