package br.com.cesarmontaldi.repository;

import br.com.cesarmontaldi.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository <Medico, Long> {

    public Page<Medico> findAllByAtivoTrue(Pageable paginacao);

}
