package com.predictor.api.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "partidos")
public class ListaPartidosEstadisticasBean {
	
	private List<PartidoEstadisticasBean> partidosEstadisticas;

	public List<PartidoEstadisticasBean> getPartidosEstadisticas() {
		return partidosEstadisticas;
	}
	
	@XmlElement(name = "partido")
	public void setPartidosEstadisticas(List<PartidoEstadisticasBean> partidosEstadisticas) {
		this.partidosEstadisticas = partidosEstadisticas;
	}

}
