package com.nelioalves.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nelioalves.security.UserSS;

public class UserService {
	
	//com o static o metodo pode ser chamado sem instanciar a classe
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //essa funcao retorna o usuario logado
		}
		catch (Exception e) {
			return null;
		}
	}

}
