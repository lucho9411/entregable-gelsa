package com.project.api.ventarecargas.security.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.project.api.ventarecargas.security.covers.MessageCover;
import com.project.api.ventarecargas.security.dto.RecargasDTO;
import com.project.api.ventarecargas.security.entities.Recargas;
import com.project.api.ventarecargas.security.repositories.RecargasRepository;

@Service
public class RecargasServicesJPA implements RecargasServicesImpl {
	
	@Autowired
	private RecargasRepository recargasRepo;
	@Autowired
	private OperadoresServicesImpl operadoresServices;
	@Autowired
	private UsuariosServicesImpl usuariosServices;
	
	private String json1 = null, json2 = null;
	private MessageCover messageCover = null;

	@Override
	public List<RecargasDTO> listarRecargas() {
		List<RecargasDTO> lista = new ArrayList<RecargasDTO>();
		List<Recargas> recargas = recargasRepo.findAll();
		try {
			for(Recargas recarga: recargas) {
				System.err.println(recarga);
				RecargasDTO recargasDTO = new RecargasDTO();
				recargasDTO.setIdRecarga(recarga.getId());
				recargasDTO.setFechaRecarga(recarga.getFechaHora().toString());
				recargasDTO.setNumeroTelefono(recarga.getNumero());
				recargasDTO.setPrecio(recarga.getValor());
				recargasDTO.setOperador(operadoresServices.consultarOperador(recarga.getOperador().getId()));
				recargasDTO.setVendedor(usuariosServices.consultarUsuario(recarga.getUser().getId()));
				lista.add(recargasDTO);
			}
			return lista;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String listarRecargasGson() {
		List<RecargasDTO> lista = new ArrayList<RecargasDTO>();
		List<Recargas> recargas = recargasRepo.findAll();
		try {
			if (recargas.isEmpty()) {
				messageCover = new MessageCover(404, "Lista vacía");
				json2 = new Gson().toJson(recargas);
			}
			else {
				for(Recargas recarga: recargas) {
					RecargasDTO recargasDTO = new RecargasDTO();
					recargasDTO.setIdRecarga(recarga.getId());
					recargasDTO.setFechaRecarga(recarga.getFechaHora().toString());
					recargasDTO.setNumeroTelefono(recarga.getNumero());
					recargasDTO.setPrecio(recarga.getValor());
					recargasDTO.setOperador(operadoresServices.consultarOperador(recarga.getOperador().getId()));
					recargasDTO.setVendedor(usuariosServices.consultarUsuario(recarga.getUser().getId()));
					lista.add(recargasDTO);
				}
				messageCover = new MessageCover(200, null);
				json2 = new Gson().toJson(lista);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(500, null);
			json2 = new Gson().toJson(null);
		}
		json1 = new Gson().toJson(messageCover);
		return "[" + json1 + "," + json2 + "]";
	}

	@Override
	public RecargasDTO consultarRecarga(long id) {
		RecargasDTO recargaDTO = new RecargasDTO();
		Recargas recarga;
		try {
			if (recargasRepo.findById(id).isPresent()) {
				recarga = recargasRepo.findById(id).get();
				recargaDTO.setIdRecarga(recarga.getId());
				recargaDTO.setFechaRecarga(recarga.getFechaHora().toString());
				recargaDTO.setNumeroTelefono(recarga.getNumero());
				recargaDTO.setPrecio(recarga.getValor());
				recargaDTO.setOperador(operadoresServices.consultarOperador(recarga.getOperador().getId()));
				recargaDTO.setVendedor(usuariosServices.consultarUsuario(recarga.getUser().getId()));
				return recargaDTO;
			}
			else {
				return null;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public String consultarRecargaGson(long id) {
		RecargasDTO recargaDTO = new RecargasDTO();
		Recargas recarga;
		try {
			if (recargasRepo.findById(id).isPresent()) {
				recarga = recargasRepo.findById(id).get();
				recargaDTO.setIdRecarga(recarga.getId());
				recargaDTO.setFechaRecarga(recarga.getFechaHora().toString());
				recargaDTO.setNumeroTelefono(recarga.getNumero());
				recargaDTO.setPrecio(recarga.getValor());
				recargaDTO.setOperador(operadoresServices.consultarOperador(recarga.getOperador().getId()));
				recargaDTO.setVendedor(usuariosServices.consultarUsuario(recarga.getUser().getId()));
				messageCover = new MessageCover(200, null);
				json2 = new Gson().toJson(recargaDTO);
			}
			else {
				messageCover = new MessageCover(404, "No existe!");
				json2 = new Gson().toJson(null);
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(500, null);
			json2 = new Gson().toJson(null);
		}
		json1 = new Gson().toJson(messageCover);
		return "[" + json1 + "," + json2 + "]";
	}

	@Override
	public String crearRecarga(RecargasDTO recargaDTO) {
		try {
			Recargas recarga = new Recargas();
			recarga.setFechaHora(null);
			recarga.setNumero(recargaDTO.getNumeroTelefono());
			recarga.setValor(recargaDTO.getPrecio());
			recarga.setUser(usuariosServices.consultarUsuarioEntity(recargaDTO.getVendedor().getIdUser()));
			recarga.setOperador(operadoresServices.consultarOperadorEntity(recargaDTO.getOperador().getIdOperador()));
			recargasRepo.save(recarga);
			messageCover = new MessageCover(200, null);
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(500, null);
		}
		json1 = new Gson().toJson(messageCover);
		return "[" + json1 + "]";
	}

	@Override
	public String editarRecarga(RecargasDTO recargaDTO) {
		try {
			if (recargasRepo.findById(recargaDTO.getIdRecarga()).isPresent()) {
				Recargas recarga = recargasRepo.findById(recargaDTO.getIdRecarga()).get();
				recarga.setNumero(recargaDTO.getNumeroTelefono());
				recarga.setValor(recargaDTO.getPrecio());
				recarga.setOperador(operadoresServices.consultarOperadorEntity(recargaDTO.getOperador().getIdOperador()));
				recargasRepo.save(recarga);
				messageCover = new MessageCover(200, null);
			}
			else {
				messageCover = new MessageCover(404, "No existe!");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(500, null);
		}
		json1 = new Gson().toJson(messageCover);
		return "[" + json1 + "]";
	}

	@Override
	public String eliminarRecarga(long id) {
		try {
			if (recargasRepo.findById(id).isPresent()) {
				recargasRepo.delete(recargasRepo.findById(id).get());
				messageCover = new MessageCover(200, null);
			}
			else {
				messageCover = new MessageCover(404, "No existe!");
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(500, null);
		}
		json1 = new Gson().toJson(messageCover);
		return "[" + json1 + "]";
	}

	@SuppressWarnings("deprecation")
	@Override
	public String eliminarRecargasMasivas(List<RecargasDTO> recargasDTO) {
		List<Recargas> recargas = new ArrayList<Recargas>();
		try {
			for (RecargasDTO recargaDTO: recargasDTO) {
				if (recargasRepo.findById(recargaDTO.getIdRecarga()).isPresent()) {
					recargas.add(recargasRepo.findById(recargaDTO.getIdRecarga()).get());
				}
			}
			recargasRepo.deleteInBatch(recargas);
			messageCover = new MessageCover(200, null);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			messageCover = new MessageCover(500, null);
		}
		json1 = new Gson().toJson(messageCover);
		return "[" + json1 + "]";
	}

}
