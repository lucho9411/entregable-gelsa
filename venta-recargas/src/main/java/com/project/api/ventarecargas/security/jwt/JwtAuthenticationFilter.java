package com.project.api.ventarecargas.security.jwt;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.google.gson.Gson;
import com.project.api.ventarecargas.security.covers.MessageCover;
import com.project.api.ventarecargas.security.inventory.EndPointsSeguridad;
import com.project.api.ventarecargas.security.services.UserDetailServicesImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	jwtProvider jwtProvider;
	@Autowired
	UserDetailServicesImpl UserDetailServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String json1 = null;
		try {
			String token = getToken(request);
			if(token != null && jwtProvider.validateToken(token)) {
				String username = jwtProvider.getUsernameFromToken(token);
				UserDetails userDetails = UserDetailServiceImpl.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			else {
				if(!request.getRequestURI().contains(EndPointsSeguridad.RUTA_PRINCIPAL)) {
					MessageCover messageCover = new MessageCover(403, null);
					json1 = new Gson().toJson(messageCover);
					response.getWriter().write("[" + json1 + "]");
					response.getWriter().flush();
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
		catch(Exception ex) {
			System.err.println(ex);
		}
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer", "");
		}
		else {
			return null;
		}
	}
	
	public String getToken(String bearer) {
		if(bearer != null) {
			return bearer.replace("Bearer ", "");
		}
		else {
			return null;
		}
	}

}
