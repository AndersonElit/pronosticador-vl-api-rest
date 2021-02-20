package com.predictor.api.beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "partido")
public class PartidoEstadisticasBean {
	
	private String liga;
	private String local;
	private String visitante;
	private int partidosLocal;
	private int partidosVisitante;
	private int partidosTotalLocal;
	private int partidosTotalVisitante;
	private int dCS;
	private int dCSTotal;
	private int dFTS;
	private int dFTSTotal;
	private int dpGanados;
	private int dpGanadosTotal;
	private int dpEmpatados;
	private int dpEmpatadosTotal;
	private int dpPerdidos;
	private int dpPerdidosTotal;
	
	public String getLiga() {
		return liga;
	}
	
	@XmlElement(name = "liga")
	public void setLiga(String liga) {
		this.liga = liga;
	}

	public String getLocal() {
		return local;
	}
	
	@XmlElement(name = "local")
	public void setLocal(String local) {
		this.local = local;
	}

	public String getVisitante() {
		return visitante;
	}
	
	@XmlElement(name = "visitante")
	public void setVisitante(String visitante) {
		this.visitante = visitante;
	}

	public int getPartidosLocal() {
		return partidosLocal;
	}
	
	@XmlElement(name = "partidoslocal")
	public void setPartidosLocal(int partidosLocal) {
		this.partidosLocal = partidosLocal;
	}

	public int getPartidosVisitante() {
		return partidosVisitante;
	}
	
	@XmlElement(name = "partidosvisitante")
	public void setPartidosVisitante(int partidosVisitante) {
		this.partidosVisitante = partidosVisitante;
	}

	public int getPartidosTotalLocal() {
		return partidosTotalLocal;
	}
	
	@XmlElement(name = "partidostotallocal")
	public void setPartidosTotalLocal(int partidosTotalLocal) {
		this.partidosTotalLocal = partidosTotalLocal;
	}

	public int getPartidosTotalVisitante() {
		return partidosTotalVisitante;
	}
	
	@XmlElement(name = "partidostotalvisitante")
	public void setPartidosTotalVisitante(int partidosTotalVisitante) {
		this.partidosTotalVisitante = partidosTotalVisitante;
	}

	public int getdCS() {
		return dCS;
	}
	
	@XmlElement(name = "dcs")
	public void setdCS(int dCS) {
		this.dCS = dCS;
	}
	
	public int getdCSTotal() {
		return dCSTotal;
	}
	
	@XmlElement(name = "dcstotal")
	public void setdCSTotal(int dCSTotal) {
		this.dCSTotal = dCSTotal;
	}

	public int getdFTSTotal() {
		return dFTSTotal;
	}
	
	@XmlElement(name = "dftstotal")
	public void setdFTSTotal(int dFTSTotal) {
		this.dFTSTotal = dFTSTotal;
	}

	public int getdFTS() {
		return dFTS;
	}
	
	@XmlElement(name = "dfts")
	public void setdFTS(int dFTS) {
		this.dFTS = dFTS;
	}

	public int getDpGanados() {
		return dpGanados;
	}
	
	@XmlElement(name = "dpganados")
	public void setDpGanados(int dpGanados) {
		this.dpGanados = dpGanados;
	}

	public int getDpGanadosTotal() {
		return dpGanadosTotal;
	}
	
	@XmlElement(name = "dpganadostotal")
	public void setDpGanadosTotal(int dpGanadosTotal) {
		this.dpGanadosTotal = dpGanadosTotal;
	}

	public int getDpEmpatados() {
		return dpEmpatados;
	}
	
	@XmlElement(name = "dpempatados")
	public void setDpEmpatados(int dpEmpatados) {
		this.dpEmpatados = dpEmpatados;
	}

	public int getDpEmpatadosTotal() {
		return dpEmpatadosTotal;
	}
	
	@XmlElement(name = "dpempatadostotal")
	public void setDpEmpatadosTotal(int dpEmpatadosTotal) {
		this.dpEmpatadosTotal = dpEmpatadosTotal;
	}

	public int getDpPerdidos() {
		return dpPerdidos;
	}
	
	@XmlElement(name = "dpperdidos")
	public void setDpPerdidos(int dpPerdidos) {
		this.dpPerdidos = dpPerdidos;
	}

	public int getDpPerdidosTotal() {
		return dpPerdidosTotal;
	}
	
	@XmlElement(name = "dpperdidostotal")
	public void setDpPerdidosTotal(int dpPerdidosTotal) {
		this.dpPerdidosTotal = dpPerdidosTotal;
	}
	
	

}
