package com.project.api.ventarecargas.security.services;

import java.util.List;
import com.project.api.ventarecargas.security.dto.RecargasDTO;

public interface RecargasServicesImpl {
	
	public List<RecargasDTO> listarRecargas();
	public String listarRecargasGson();
	
	public RecargasDTO consultarRecarga(long id);
	public String consultarRecargaGson(long id);
	
	public String crearRecarga(RecargasDTO recarga);
	
	public String editarRecarga(RecargasDTO recarga);
	
	public String eliminarRecarga(long id);
	public String eliminarRecargasMasivas(List<RecargasDTO> recargas);
	
}
