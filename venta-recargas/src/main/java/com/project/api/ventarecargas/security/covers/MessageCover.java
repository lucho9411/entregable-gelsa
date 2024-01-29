package com.project.api.ventarecargas.security.covers;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.api.ventarecargas.security.dto.MessageInfoDTO;

public class MessageCover implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private MessageInfoDTO Msg = new MessageInfoDTO();
	
	public MessageCover(int Codigo, String texto)
	{
		switch(Codigo) {
			case 200:
				Msg.setText("Transacción exitosa");
				break;
			case 500:
				Msg.setText("Error en el servidor");
				break;
			case 409:
				Msg.setText("Recurso incompleto");
				break;
			case 401:
				Msg.setText(texto);
				break;
			case 403:
				Msg.setText("Acceso denegado");
				break;
			case 404:
				Msg.setText("Recurso no existe");
				break;
			case 405:
				Msg.setText("Recurso ya existe");
				break;
			case 204:
				Msg.setText("No existen registros");
				break;
			default:
				break;
		}
		Msg.setCode(Codigo);
	}

	@JsonProperty("Message")
	public MessageInfoDTO getMessage() {
		return Msg;
	}

	public void setMessage(String Message, int Codigo) {
		Msg.setText(Message);
		Msg.setCode(Codigo);
	}


}
