package com.predictor.api.interfaces;

import java.util.List;

import com.predictor.api.beans.DatosPartidoBean;
import com.predictor.api.beans.PartidosXmlBean;

public interface IOperacionesPartidosService {
	
	public void insertarPartidos(PartidosXmlBean partidosXml);
	
	public List<DatosPartidoBean> listaPartidosDepurados();

}
