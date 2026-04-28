package com.expense_tracker.config;

import com.expense_tracker.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filtro responsável por interceptar todas as requisições HTTP
 * e validar o token JWT presente no header Authorization.
 *
 * Se o token for válido:
 * - extrai o userId
 * - autentica o usuário no contexto do Spring Security
 *
 * Isso permite que as rotas protegidas reconheçam o usuário autenticado.
 */

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwt;


    public JwtFilter(JwtService jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filter) throws ServletException, IOException {
        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                String userId = jwt.validateToken(token);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userId, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                res.setStatus(401);
            }
        }

        filter.doFilter(req, res);
    }
}
