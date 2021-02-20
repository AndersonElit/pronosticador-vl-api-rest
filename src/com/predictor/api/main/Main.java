package com.predictor.api.main;

import java.util.List;

import com.predictor.api.beans.PartidoBean;
import com.predictor.api.beans.PartidoEstadisticasBean;
import com.predictor.api.beans.PartidosXmlBean;
import com.predictor.api.interfaces.IOperacionesPartidosService;
import com.predictor.api.scraper.Scraper;
import com.predictor.api.service.OperacionesPartidosService;

public class Main {

	public static void main(String[] args) {
		
		List<String> hRefs = Scraper.listaHrefs();
		List<PartidoBean> partidos = Scraper.datosRecuperadosPartidos(hRefs);
		List<PartidoEstadisticasBean> partidosEstadisticas = Scraper.listaPartidosEstadisticas(partidos);
		String partidosEstadisticasXml = Scraper.listaPartidosEstadisticasXml(partidosEstadisticas);
		System.out.println("se genero el Xml.");
		PartidosXmlBean partidosXmlBean = new PartidosXmlBean();
		partidosXmlBean.setPartidosXml(partidosEstadisticasXml);;
		IOperacionesPartidosService operacionesPartidosService = new OperacionesPartidosService();
		operacionesPartidosService.insertarPartidos(partidosXmlBean);
		//System.out.println(partidosEstadisticasXml);		
		
	}

}
