package com.predictor.api.repositorio;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.predictor.api.beans.DatosPartidoBean;
import com.predictor.api.beans.PartidosXmlBean;
import com.predictor.api.beans.PartidosXmlOutBean;
import com.predictor.api.connect.ConnectMybatis;
import com.predictor.api.interfaces.IOperacionesPartidosDAO;

public class OperacionesPartidosDAO implements IOperacionesPartidosDAO {
	
	SqlSession session = null;
	
	public void insertarPartidos(PartidosXmlBean partidosXml) {
		
		try {
			
			session = ConnectMybatis.getSqlSessionFactory().openSession();
			PartidosXmlBean partidosXmlBean = new PartidosXmlBean();
			partidosXmlBean.setPartidosXml(partidosXml.getPartidosXml());
			session.insert("PartidosMap.insertarPartidosXml", partidosXmlBean);
			session.commit();
			System.out.println("Se insertaron los partidos.");
			
		} catch(Exception ex) {
			System.out.println("Errores al insertar los partidos en la basa de datos...");
			//ex.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
	
	public List<DatosPartidoBean> partidosDepurados(PartidosXmlBean partidosXml) {
		
		try {
			session = ConnectMybatis.getSqlSessionFactory().openSession();
			PartidosXmlOutBean partidosXmlOutBean = new PartidosXmlOutBean();
			partidosXmlOutBean.setPartidosXml(partidosXml.getPartidosXml());
			session.insert("PartidosMap.listaPartidosDepuradosXml", partidosXmlOutBean);
			session.commit();
			System.out.println("Se insertaron los partidos.");
			return partidosXmlOutBean.getPartidosDepurados();
		} catch(Exception ex) {
			System.out.println("Errores al insertar los partidos en la basa de datos...");
			//ex.printStackTrace();
			session.rollback();
			return null;
		} finally {
			session.close();
		}
		
	}

}
