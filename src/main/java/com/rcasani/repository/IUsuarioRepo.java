package com.rcasani.repository;

import com.rcasani.model.Usuario;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer> {

    //@Query -> FROM User WHERE u.username = :username
    Usuario findOneByUsername(String username);
}
