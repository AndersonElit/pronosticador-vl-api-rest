package com.predictor.api.service;

import com.predictor.api.beans.PartidosXmlBean;
import com.predictor.api.interfaces.IOperacionesPartidosDAO;
import com.predictor.api.interfaces.IOperacionesPartidosService;
import com.predictor.api.repositorio.OperacionesPartidosDAO;

public class OperacionesPartidosService implements IOperacionesPartidosService {
	
	IOperacionesPartidosDAO operacionesPartidosDAO = new OperacionesPartidosDAO();
	
	public void insertarPartidos(PartidosXmlBean partidosXml) {
		operacionesPartidosDAO.insertarPartidos(partidosXml);
	}

}
