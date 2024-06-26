package br.com.cesarmontaldi.service;

import br.com.cesarmontaldi.model.usuario.Usuario;
import br.com.cesarmontaldi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario salvar(Usuario usuario) {
        var senhaBCrypt = encoder.encode(usuario.getSenha());

        return repository.save(new Usuario(usuario.getLogin(), senhaBCrypt));
    }
}
