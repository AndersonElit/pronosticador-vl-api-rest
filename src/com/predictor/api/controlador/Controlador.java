package com.predictor.api.controlador;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.predictor.api.beans.DatosPartidoBean;
import com.predictor.api.interfaces.IOperacionesPartidosService;
import com.predictor.api.service.OperacionesPartidosService;

@Path("/partidos")
public class Controlador {
	
	@GET
	@Path("/listapartidos")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public List<DatosPartidoBean> partidosDepurados() {
		IOperacionesPartidosService operacionesPartidosService = new OperacionesPartidosService();
		return operacionesPartidosService.listaPartidosDepurados();
	}

}
