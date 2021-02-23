package com.predictor.api.beans;

import java.util.List;

public class PartidosXmlOutBean {
	
	private String partidosXml;
	private List<DatosPartidoBean> partidosDepurados;
	
	public PartidosXmlOutBean() {
		
	}
	
	public PartidosXmlOutBean(String partidosXml) {
		this.partidosXml = partidosXml;
	}

	public String getPartidosXml() {
		return partidosXml;
	}

	public void setPartidosXml(String partidosXml) {
		this.partidosXml = partidosXml;
	}

	public List<DatosPartidoBean> getPartidosDepurados() {
		return partidosDepurados;
	}

	public void setPartidosDepurados(List<DatosPartidoBean> partidosDepurados) {
		this.partidosDepurados = partidosDepurados;
	}

}
