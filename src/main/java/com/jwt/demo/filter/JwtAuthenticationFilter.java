package com.jwt.demo.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jwt.demo.service.CustomUserDetailService;
import com.jwt.demo.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String bearerToken = request.getHeader("Authorization");
		String username = null;
		String token = null;

		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			token = bearerToken.substring(7);

			try {
				username = jwtUtil.extractUsername(token);

				UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());

					upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(upat);

				} else {
					System.out.println("Invalid Token!!");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid Bearer Token Format!!");
		}
		
		filterChain.doFilter(request, response);
	}

}
