package com.predictor.api.service;

import java.util.List;

import com.predictor.api.beans.DatosPartidoBean;
import com.predictor.api.beans.PartidoBean;
import com.predictor.api.beans.PartidoEstadisticasBean;
import com.predictor.api.beans.PartidosXmlBean;
import com.predictor.api.interfaces.IOperacionesPartidosDAO;
import com.predictor.api.interfaces.IOperacionesPartidosService;
import com.predictor.api.repositorio.OperacionesPartidosDAO;
import com.predictor.api.scraper.Scraper;

public class OperacionesPartidosService implements IOperacionesPartidosService {
	
	IOperacionesPartidosDAO operacionesPartidosDAO = new OperacionesPartidosDAO();
	
	public void insertarPartidos(PartidosXmlBean partidosXml) {
		operacionesPartidosDAO.insertarPartidos(partidosXml);
	}
	
	public List<DatosPartidoBean> listaPartidosDepurados() {
		
		List<String> hRefs = Scraper.listaHrefs();
		List<PartidoBean> partidos = Scraper.datosRecuperadosPartidos(hRefs);
		List<PartidoEstadisticasBean> partidosEstadisticas = Scraper.listaPartidosEstadisticas(partidos);
		String partidosEstadisticasXml = Scraper.listaPartidosEstadisticasXml(partidosEstadisticas);
		PartidosXmlBean partidosXml = new PartidosXmlBean();
		partidosXml.setPartidosXml(partidosEstadisticasXml);
		return operacionesPartidosDAO.partidosDepurados(partidosXml);
		
	}

}
