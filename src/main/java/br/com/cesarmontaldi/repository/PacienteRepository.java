package br.com.cesarmontaldi.repository;

import br.com.cesarmontaldi.model.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository <Paciente, Long> {

    public Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("select p.ativo from Paciente p where p.id = :id")
    boolean findAtivoById(Long id);
}
