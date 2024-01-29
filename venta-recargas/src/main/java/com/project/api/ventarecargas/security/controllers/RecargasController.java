package com.project.api.ventarecargas.security.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.api.ventarecargas.security.dto.RecargasDTO;
import com.project.api.ventarecargas.security.inventory.EndPointsGenerales;
import com.project.api.ventarecargas.security.services.RecargasServicesImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(value = EndPointsGenerales.RECARGAS)
public class RecargasController {
	
	@Autowired
	private RecargasServicesImpl recargasServices;
	
	@GetMapping(value = EndPointsGenerales.LISTAR)
	public String listarRecargas() {
		return recargasServices.listarRecargasGson();
	}
	
	@GetMapping(value = EndPointsGenerales.CONSULTAR)
	public String consultarRecarga(@RequestParam("idRecarga") long id) {
		return recargasServices.consultarRecargaGson(id);
	}
	
	@PostMapping(value = EndPointsGenerales.AGREGAR)
	public String agregarRecarga(@RequestBody RecargasDTO recargaDTO) {
		return recargasServices.crearRecarga(recargaDTO);
	}
	
	@PutMapping(value = EndPointsGenerales.EDITAR)
	public String editarRecarga(@RequestBody RecargasDTO recargaDTO) {
		return recargasServices.editarRecarga(recargaDTO);
	}
	
	@DeleteMapping(value = EndPointsGenerales.ELIMINAR)
	public String eliminarRecarga(@RequestParam("idRecarga") long id) {
		return recargasServices.eliminarRecarga(id);
	}
	
	@DeleteMapping(value = EndPointsGenerales.ELIMINAR_MASIVO)
	public String eliminarRecargasMasivas(@RequestBody List<RecargasDTO> recargasDTO) {
		return recargasServices.eliminarRecargasMasivas(recargasDTO);
	}

}
