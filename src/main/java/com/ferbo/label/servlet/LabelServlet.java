package com.ferbo.label.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ferbo.inventario.business.ConstanciaDepositoBL;
import com.ferbo.inventario.business.TarimaBL;
import com.ferbo.inventario.model.ConstanciaDeposito;
import com.ferbo.inventario.model.Tarima;
import com.ferbo.label.model.response.RespuestaSistema;
import com.ferbo.tools.IOTool;
import com.ferbo.tools.JasperReportTool;
import com.google.gson.Gson;

/**
 * Servlet implementation class LabelServlet
 */
public class LabelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Logger log = LogManager.getLogger();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LabelServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RespuestaSistema respuesta = null;
        Gson prettyGson   = null;
        String jsonResponse = null;
        String mensaje = null;
        int httpStatus = -1;
		
		String authentication = request.getHeader("Authorization");
		
		try {
			
		} catch(Exception ex) {
			
		} finally {
			
		}
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folioCliente = null;
		
		String jasperPath = "/jasper/etiqueta-tarima.jrxml";
		String images = "/images/logo.png";
		File imgfile = null;
		URL resourceimg = null;
		URL resourceJasper = null;
		String img = null;
		String jrxmlPath = null;
		OutputStream output = null;
		
		List<Tarima> listaTarimas = null;
		
		try {
			folioCliente = request.getParameter("folio");
			resourceimg = getClass().getResource(images);
			resourceJasper = getClass().getResource(jasperPath);
			img = resourceimg.getFile();
			jrxmlPath = resourceJasper.getFile();
			imgfile = new File(img);
			
			log.info("Folio de la constancia: {}", folioCliente);
			listaTarimas = this.getListaTarimas(folioCliente);
			
			String fileName = String.format("etiqueta_%s.pdf", folioCliente);
			
			//String disposition = String.format("attachment; filename=\"%s\"", fileName);
			String disposition = String.format("inline; filename=\"%s\"", fileName);
			
			response.setHeader("Content-Disposition", disposition);
			response.addHeader("Content-Disposition", disposition);
			response.setContentType("application/pdf");
			
			output = response.getOutputStream();
			
			JasperReportTool<Tarima> jrTool = new JasperReportTool<Tarima>();
			Map<String,Object> jrParameters = new HashMap<String, Object>();
			jrParameters.put("logoPath", imgfile.getPath());
			jrTool.exportToPDF(listaTarimas, jrParameters, jrxmlPath, output);
			
		} catch(Exception ex) {
			log.error("Problema con la exportación a PDF...",  ex);
		} finally {
			IOTool.close(output);
		}
	}
	
	private List<Tarima> getListaTarimas(String folioCliente) {
		List<Tarima> lista = null;
		ConstanciaDepositoBL constanciaBO = new ConstanciaDepositoBL();
		TarimaBL tarimaBO = new TarimaBL();
		ConstanciaDeposito constancia = null;
		
		try {
			constancia = constanciaBO.get(folioCliente);
			lista = tarimaBO.getTarimas(constancia);
		} catch(Exception ex) {
			log.error("Problema para obtener el folio " + folioCliente, ex);
		} finally {
			
		}
		
		return lista;
	}
}
