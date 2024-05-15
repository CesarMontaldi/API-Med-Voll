package br.com.cesarmontaldi.repository;

import br.com.cesarmontaldi.model.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
