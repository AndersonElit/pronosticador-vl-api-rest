package com.predictor.api.repositorio;

import org.apache.ibatis.session.SqlSession;

import com.predictor.api.beans.PartidosXmlBean;
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

}
