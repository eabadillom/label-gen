package com.ferbo.inventario.model;

import java.math.BigDecimal;
import java.util.Date;

public class Tarima {
	
	private Integer folio;
	private Integer idCliente; 
	private String nombreCliente;
	private Date fechaIngreso;
	private String folioCliente;
	private Integer idPartida;
	private String nombreProducto;
	private Integer cantidadTotal;
	private String unidadManejo;
	private BigDecimal pesoTotal;
	private String camara;
	private String nombreCamara;
	private String planta;
	private String nombrePlanta;
	private Date caducidad;
	private String lote;
	private String po;
	private String mp;
	private String pedimento;
	private String sap;
	
	public Integer getFolio() {
		return folio;
	}
	public void setFolio(Integer folio) {
		this.folio = folio;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getFolioCliente() {
		return folioCliente;
	}
	public void setFolioCliente(String folioCliente) {
		this.folioCliente = folioCliente;
	}
	public Integer getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(Integer idPartida) {
		this.idPartida = idPartida;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public Integer getCantidadTotal() {
		return cantidadTotal;
	}
	public void setCantidadTotal(Integer cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}
	public String getUnidadManejo() {
		return unidadManejo;
	}
	public void setUnidadManejo(String unidadManejo) {
		this.unidadManejo = unidadManejo;
	}
	public BigDecimal getPesoTotal() {
		return pesoTotal;
	}
	public void setPesoTotal(BigDecimal pesoTotal) {
		this.pesoTotal = pesoTotal;
	}
	public String getCamara() {
		return camara;
	}
	public void setCamara(String camara) {
		this.camara = camara;
	}
	public String getNombreCamara() {
		return nombreCamara;
	}
	public void setNombreCamara(String nombreCamara) {
		this.nombreCamara = nombreCamara;
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		this.planta = planta;
	}
	public String getNombrePlanta() {
		return nombrePlanta;
	}
	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}
	public Date getCaducidad() {
		return caducidad;
	}
	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public String getMp() {
		return mp;
	}
	public void setMp(String mp) {
		this.mp = mp;
	}
	public String getPedimento() {
		return pedimento;
	}
	public void setPedimento(String pedimento) {
		this.pedimento = pedimento;
	}
	public String getSap() {
		return sap;
	}
	public void setSap(String sap) {
		this.sap = sap;
	}
	@Override
	public String toString() {
		return "{\"folio\":\"" + folio + "\", \"idCliente\":\"" + idCliente + "\", \"nombreCliente\":\"" + nombreCliente
				+ "\", \"fechaIngreso\":\"" + fechaIngreso + "\", \"folioCliente\":\"" + folioCliente
				+ "\", \"idPartida\":\"" + idPartida + "\", \"nombreProducto\":\"" + nombreProducto
				+ "\", \"cantidadTotal\":\"" + cantidadTotal + "\", \"unidadManejo\":\"" + unidadManejo
				+ "\", \"pesoTotal\":\"" + pesoTotal + "\", \"camara\":\"" + camara + "\", \"nombreCamara\":\""
				+ nombreCamara + "\", \"planta\":\"" + planta + "\", \"nombrePlanta\":\"" + nombrePlanta
				+ "\", \"caducidad\":\"" + caducidad + "\", \"lote\":\"" + lote + "\", \"po\":\"" + po + "\", \"mp\":\""
				+ mp + "\", \"pedimento\":\"" + pedimento + "\", \"sap\":\"" + sap + "\"}";
	}
}
