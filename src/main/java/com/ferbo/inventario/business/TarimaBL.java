package com.ferbo.inventario.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.inventario.model.ConstanciaDeposito;
import com.ferbo.inventario.model.Partida;
import com.ferbo.inventario.model.Tarima;

public class TarimaBL {
	
	private static Logger log = LogManager.getLogger(TarimaBL.class);
	
	private Tarima getTarima(Partida partida) {
		Tarima tarima = new Tarima();
		
		tarima.setIdPartida(partida.getIdPartida());
		tarima.setNombreProducto(partida.getUnidadProducto().getProducto().getNombre());
		tarima.setCantidadTotal(partida.getCantidadTotal());
		tarima.setUnidadManejo(partida.getUnidadProducto().getUnidadManejo().getNombre());
		tarima.setPesoTotal(partida.getPesoTotal());
		tarima.setCamara(partida.getCamara().getAbreviatura());
		tarima.setNombreCamara(partida.getCamara().getNombre());
		tarima.setNombrePlanta(partida.getCamara().getPlanta().getNombre());
		tarima.setPlanta(partida.getCamara().getPlanta().getAbreviatura());
		tarima.setCaducidad(partida.getDetallePartidaList().get(0).getCaducidad());
		tarima.setLote(partida.getDetallePartidaList().get(0).getLote());
		tarima.setPo(partida.getDetallePartidaList().get(0).getPo());
		tarima.setMp(partida.getDetallePartidaList().get(0).getMp());
		tarima.setPedimento(partida.getDetallePartidaList().get(0).getPedimento());
		tarima.setSap(partida.getDetallePartidaList().get(0).getSap());
		
		return tarima;
	}
	
	public List<Tarima> getTarimas(ConstanciaDeposito constancia) {
		List<Tarima> tarimas = new ArrayList<Tarima>();
		List<Partida> partidas = null;
		
		try {
			partidas = constancia.getPartidaList();
			
			for(Partida partida : partidas) {
				tarimas.addAll(this.procesa(partida));
			}
			
		} catch(Exception ex) {
			
		} finally {
			
			for(Tarima t : tarimas) {
				t.setFolio(constancia.getFolio());
				t.setIdCliente(constancia.getCliente().getIdCliente());
				t.setNombreCliente(constancia.getCliente().getNombre());
				t.setFechaIngreso(constancia.getFechaIngreso());
				t.setFolioCliente(constancia.getFolioCliente());
			}
			
		}
		
		return tarimas;
	}
	
	private List<Tarima> procesa(Partida partida ) {
		List<Tarima> tarimas = new ArrayList<>();
		Tarima t = null;
		
		BigDecimal cantidad = null;
		BigDecimal peso = null;
		
		if(partida.getNumeroTarimas().compareTo(BigDecimal.ONE) > 0) {
			
			int noTarimas = partida.getNumeroTarimas().intValue();
			BigDecimal fraccionTarima = partida.getNumeroTarimas().subtract(new BigDecimal(noTarimas).setScale(3, BigDecimal.ROUND_HALF_UP));
			
			cantidad = new BigDecimal(partida.getCantidadTotal()).divide(partida.getNumeroTarimas(), BigDecimal.ROUND_HALF_UP).setScale(3);
			peso = partida.getPesoTotal().divide(new BigDecimal(partida.getCantidadTotal()), BigDecimal.ROUND_HALF_UP).multiply(cantidad).setScale(3, BigDecimal.ROUND_HALF_UP);
			
			for(int i = 0; i < noTarimas; i++) {
				t = getTarima(partida);
				t.setCantidadTotal(cantidad.intValue());
				t.setPesoTotal(peso);
				
				tarimas.add(t);
			}
			
			if(fraccionTarima.compareTo(BigDecimal.ZERO) > 0) {
				t = getTarima(partida);
				t.setCantidadTotal(cantidad.intValue());
				t.setPesoTotal(peso);
			}
			
		} else {
			t = getTarima(partida);
			tarimas.add(t);
		}
		
		return tarimas;
	}

}
