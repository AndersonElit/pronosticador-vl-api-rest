package com.predictor.api.scraper;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.predictor.api.beans.ListaPartidosEstadisticasBean;
import com.predictor.api.beans.PartidoBean;
import com.predictor.api.beans.PartidoEstadisticasBean;

public class Scraper {
	
	public static List<String> listaHrefs() {
		
		WebClient webClient = new WebClient();
		webClient.getOptions().setJavaScriptEnabled(false);
		List<String> hRefs = new ArrayList<String>();
		
		try {
			HtmlPage page1 = webClient.getPage("https://www.soccerstats.com/matches.asp?matchday=2&daym=tomorrow");
			HtmlButton button = (HtmlButton) page1.getElementsByTagName("button").get(0);
			HtmlPage page2 = button.click();
			List<HtmlElement> aTags = page2.getByXPath("//a[@class='vsmall']");
			
			for (HtmlElement aTag : aTags) {
				
				String hRef = aTag.getAttribute("href");
				
				if (hRef.indexOf("&stats") != -1) {
					hRefs.add(hRef);
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Errores al cargar los partidos...");
			//e.printStackTrace();
		}
		
		webClient.close();

		return hRefs;
	}
	
	public static PartidoBean datosPartido(String url) {
		
		PartidoBean partido = new PartidoBean();
		
		try {
			
			Document doc = Jsoup.connect(url).get();
			
			//extraer nombre de la liga
			String liga = doc.select("font[style$=color:#555555;font-size:17px;]").first().text();
			partido.setLiga(liga);
			
			Element div = doc.select("div[class$=five columns]").first();
			Elements tables = div.select("table");
			
			//extraer equipos
			Elements tds1 = tables.get(0).select("tr.trow2").first().select("td");
			String local = tds1.get(0).select("font").first().select("b").first().select("a").first().text();
			String visitante = tds1.get(2).select("font").first().select("b").first().select("a").first().text();
			partido.setLocal(local);
			partido.setVisitante(visitante);
			
			//extraer numero partidos ganados, emparados y perdidos local/vsitante y totalLocal/totalVistante
			Elements trs = tables.get(4).select("tr");
			
			//local/visitante
			Elements tds2 = trs.get(1).select("td");
			int partidosLocal = Integer.parseInt(tds2.get(1).select("font").first().text());
			int partidosGanadosLocal = Integer.parseInt(tds2.get(2).select("font").first().select("b").first().text());
			int partidosEmpatadosLocal = Integer.parseInt(tds2.get(3).select("font").first().select("b").first().text());
			int partidosPerdidosLocal = Integer.parseInt(tds2.get(4).select("font").first().select("b").first().text());
			int partidosVisitante = Integer.parseInt(tds2.get(6).select("font").first().text());
			int partidosGanadosVisitante = Integer.parseInt(tds2.get(7).select("font").first().select("b").first().text());
			int partidosEmpatadosVisitante = Integer.parseInt(tds2.get(8).select("font").first().select("b").first().text());
			int partidosPerdidosVisitante = Integer.parseInt(tds2.get(9).select("font").first().select("b").first().text());
			partido.setPartidosLocal(partidosLocal);
			partido.setPartidosVisitante(partidosVisitante);
			
			//totalLocal/totalVisitante
			Elements tds3 = trs.get(2).select("td");
			int partidosTotalLocal = Integer.parseInt(tds3.get(1).text());
			int partidosGanadosTotalLocal = Integer.parseInt(tds3.get(2).text());
			int partidosEmpatadosTotalLocal = Integer.parseInt(tds3.get(3).text());
			int partidosPerdidosTotalLocal = Integer.parseInt(tds3.get(4).text());
			int partidosTotalVisitante = Integer.parseInt(tds3.get(6).text());
			int partidosGanadosTotalVisitante = Integer.parseInt(tds3.get(7).text());
			int partidosEmpatadosTotalVisitante = Integer.parseInt(tds3.get(8).text());
			int partidosPerdidosTotalVisitante = Integer.parseInt(tds3.get(9).text());
			partido.setPartidosTotalLocal(partidosTotalLocal);
			partido.setPartidosTotalVisitante(partidosTotalVisitante);
			
			//extarer cs/fts local/visitante totalLocal/totalVisitante
			Elements trs2 = tables.get(27).select("tr");
			
			//cs
			int csLocal = Integer.parseInt(trs2.get(1).select("td").get(0).select("b").text().replace("%", ""));
			int csTotalLocal = Integer.parseInt(trs2.get(1).select("td").get(1).text().replace("%", ""));
			int csVisitante = Integer.parseInt(trs2.get(1).select("td").get(4).select("b").text().replace("%", ""));
			int csTotalVisitante = Integer.parseInt(trs2.get(1).select("td").get(3).text().replace("%", ""));
			partido.setCsLocal(csLocal);
			partido.setCsTotalLocal(csTotalLocal);
			partido.setCsVisitante(csVisitante);
			partido.setCsTotalVisitante(csTotalVisitante);
			
			//fts
			int ftsLocal = Integer.parseInt(trs2.get(5).select("td").get(0).select("b").text().replace("%", ""));
			int ftsTotalLocal = Integer.parseInt(trs2.get(5).select("td").get(1).text().replace("%", ""));
			int ftsVisitante = Integer.parseInt(trs2.get(5).select("td").get(4).select("b").text().replace("%", ""));
			int ftsTotalVisitante = Integer.parseInt(trs2.get(5).select("td").get(3).text().replace("%", ""));
			partido.setFtsLocal(ftsLocal);
			partido.setFtsTotalLocal(ftsTotalLocal);
			partido.setFtsVisitante(ftsVisitante);
			partido.setFtsTotalVisitante(ftsTotalVisitante);
			
			//calcular porcentaje GEP local/visitante totalLocal/totalVisitante
			//local/visitante
			int pGanadosLocal = (int) (((float) partidosGanadosLocal/partidosLocal)*100);
			int pEmpatadosLocal = (int) (((float) partidosEmpatadosLocal/partidosLocal)*100);
			int pPerdidosLocal = (int) (((float) partidosPerdidosLocal/partidosLocal)*100);
			int pGanadosVisitante = (int) (((float) partidosGanadosVisitante/partidosVisitante)*100);
			int pEmpatadosVisitante = (int) (((float) partidosEmpatadosVisitante/partidosVisitante)*100);
			int pPerdidosVisitante = (int) (((float) partidosPerdidosVisitante/partidosVisitante)*100);
			partido.setpGanadosLocal(pGanadosLocal);
			partido.setpEmpatadosLocal(pEmpatadosLocal);
			partido.setpPerdidosLocal(pPerdidosLocal);
			partido.setpGanadosVisitante(pGanadosVisitante);
			partido.setpEmpatadosVisitante(pEmpatadosVisitante);
			partido.setpPerdidosVisitante(pPerdidosVisitante);
			
			//totalLocal/totalVisitante
			int pGanadosTotalLocal = (int) (((float) partidosGanadosTotalLocal/partidosTotalLocal)*100);
			int pEmpatadosTotalLocal = (int) (((float) partidosEmpatadosTotalLocal/partidosTotalLocal)*100);
			int pPerdidosTotalLocal = (int) (((float) partidosPerdidosTotalLocal/partidosTotalLocal)*100);
			int pGanadosTotalVisitante = (int) (((float) partidosGanadosTotalVisitante/partidosTotalVisitante)*100);
			int pEmpatadosTotalVisitante = (int) (((float) partidosEmpatadosTotalVisitante/partidosTotalVisitante)*100);
			int pPerdidosTotalVisitante = (int) (((float) partidosPerdidosTotalVisitante/partidosTotalVisitante)*100);
			partido.setpGanadosTotalLocal(pGanadosTotalLocal);
			partido.setpEmpatadosTotalLocal(pEmpatadosTotalLocal);
			partido.setpPerdidosTotalLocal(pPerdidosTotalLocal);
			partido.setpGanadosTotalVisitante(pGanadosTotalVisitante);
			partido.setpEmpatadosTotalVisitante(pEmpatadosTotalVisitante);
			partido.setpPerdidosTotalVisitante(pPerdidosTotalVisitante);
			partido.setCodigoError(0);
			
		} catch (Exception e) {
			partido.setCodigoError(1);
		}
		
		return partido;
	}
	
	public static List<PartidoBean> datosRecuperadosPartidos(List<String> hRefs) {
		
		List<PartidoBean> partidos = new ArrayList<PartidoBean>();
		
		for (String hRef : hRefs) {
			
			String url = "https://www.soccerstats.com/" + hRef;
			PartidoBean partido = Scraper.datosPartido(url);
			
			if (partido.getCodigoError() == 0) {
				partidos.add(partido);
			}
			
		}
		
		return partidos;
	}
	
	public static List<PartidoEstadisticasBean> listaPartidosEstadisticas(List<PartidoBean> partidos) {
		
		List<PartidoEstadisticasBean> partidosEstadisticas = new ArrayList<PartidoEstadisticasBean>();
		
		for (PartidoBean partido : partidos) {
			
			PartidoEstadisticasBean partidoEstadisticas = new PartidoEstadisticasBean();
			
			int dCS = partido.getCsLocal() - partido.getCsVisitante();
			int dCSTotal = partido.getCsTotalLocal() - partido.getCsTotalVisitante();
			int dFTS = partido.getFtsLocal() - partido.getFtsVisitante();
			int dFTSTotal = partido.getFtsTotalLocal() - partido.getFtsTotalVisitante();
			int dpGanados = partido.getpGanadosLocal() - partido.getpGanadosVisitante();
			int dpGanadosTotal = partido.getpGanadosTotalLocal() - partido.getpGanadosTotalVisitante();
			int dpEmpatados = partido.getpEmpatadosLocal() - partido.getpEmpatadosVisitante();
			int dpEmpatadosTotal = partido.getpEmpatadosTotalLocal() - partido.getpEmpatadosTotalVisitante();
			int dpPerdidos = partido.getpPerdidosLocal() - partido.getpPerdidosVisitante();
			int dpPerdidosTotal = partido.getpPerdidosTotalLocal() - partido.getpPerdidosTotalVisitante();
			
			partidoEstadisticas.setLiga(partido.getLiga());
			partidoEstadisticas.setLocal(partido.getLocal());
			partidoEstadisticas.setVisitante(partido.getVisitante());
			partidoEstadisticas.setPartidosLocal(partido.getPartidosLocal());
			partidoEstadisticas.setPartidosVisitante(partido.getPartidosVisitante());
			partidoEstadisticas.setPartidosTotalLocal(partido.getPartidosTotalLocal());
			partidoEstadisticas.setPartidosTotalVisitante(partido.getPartidosTotalVisitante());
			partidoEstadisticas.setdCS(dCS);
			partidoEstadisticas.setdCSTotal(dCSTotal);
			partidoEstadisticas.setdFTS(dFTS);
			partidoEstadisticas.setdFTSTotal(dFTSTotal);
			partidoEstadisticas.setDpGanados(dpGanados);
			partidoEstadisticas.setDpGanadosTotal(dpGanadosTotal);
			partidoEstadisticas.setDpEmpatados(dpEmpatados);
			partidoEstadisticas.setDpEmpatadosTotal(dpEmpatadosTotal);
			partidoEstadisticas.setDpPerdidos(dpPerdidos);
			partidoEstadisticas.setDpPerdidosTotal(dpPerdidosTotal);
			partidosEstadisticas.add(partidoEstadisticas);
			
		}
		
		return partidosEstadisticas;
	}
	
	public static String listaPartidosEstadisticasXml(List<PartidoEstadisticasBean> partidosEstadisticas) {
		
		ListaPartidosEstadisticasBean listaPartidosEstadisticasBean = new ListaPartidosEstadisticasBean();
		listaPartidosEstadisticasBean.setPartidosEstadisticas(partidosEstadisticas);
		String xmlString = null;
		
		try {
			StringWriter sw = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(listaPartidosEstadisticasBean.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.marshal(listaPartidosEstadisticasBean, sw);
			xmlString = sw.toString();
			
			/*
			//insertar contenido dentro de un archivo nuevo xml
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			String ruta = "C:\\Users\\rodri\\Desktop\\partidos_recuperados.xml";
			StreamResult result = new StreamResult(new File(ruta));
			transformer.transform(source, result);
			*/
			
		} catch (Exception e) {
			System.out.println("Errores al generar el xml...");
			//e.printStackTrace();
		}
		
		return xmlString;
	}

}
