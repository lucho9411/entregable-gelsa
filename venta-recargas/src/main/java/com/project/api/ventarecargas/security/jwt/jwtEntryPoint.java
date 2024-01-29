package com.project.api.ventarecargas.security.jwt;

import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.project.api.ventarecargas.security.covers.MessageCover;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class jwtEntryPoint implements AuthenticationEntryPoint{
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String json1 = null;
		MessageCover messageCover = new MessageCover(403, null);
		json1 = new Gson().toJson(messageCover);
		response.getWriter().write("[" + json1 + "]");
		response.getWriter().flush();
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

}
