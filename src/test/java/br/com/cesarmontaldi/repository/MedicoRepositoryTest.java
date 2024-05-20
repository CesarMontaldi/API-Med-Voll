package br.com.cesarmontaldi.repository;

import br.com.cesarmontaldi.enums.Especialidade;
import br.com.cesarmontaldi.model.consulta.Consulta;
import br.com.cesarmontaldi.model.endereco.DadosEndereco;
import br.com.cesarmontaldi.model.medico.DadosCadastroMedico;
import br.com.cesarmontaldi.model.medico.Medico;
import br.com.cesarmontaldi.model.paciente.DadosCadastroPaciente;
import br.com.cesarmontaldi.model.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deve retornar null quando unico médico cadastrado não esta disponível na data")
    void escolherMedicoAleatorioDataCenario1() {

        // given ou arrange
        var proximaSegundaFeiraAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Carlos Pedrozzo", "carlos.pedrozzo@vollmed.com", "216547", "(11)9 9520-8740", Especialidade.CARDIOLOGIA);

        var paciente = cadastrarPaciente("Fernanda Vasconcelos", "fernanda.vasconcelos@hotmail.com", "(15)9 8854-9067", "56504785031");
        cadastrarConsulta(medico, paciente, proximaSegundaFeiraAs10);

        // when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioData(Especialidade.CARDIOLOGIA, proximaSegundaFeiraAs10);

        // then ou assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deve retornar médico quando ele estiver disponível na data")
    void escolherMedicoAleatorioDataCenario2() {
        // given ou arrange
        var proximaSegundaFeiraAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var medico = cadastrarMedico("Carlos Pedrozzo", "carlos.pedrozzo@vollmed.com", "216547", "(11)9 9520-8740", Especialidade.CARDIOLOGIA);

        // when ou act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioData(Especialidade.CARDIOLOGIA, proximaSegundaFeiraAs10);

        // then ou assert
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private Medico cadastrarMedico(String nome, String email, String crm, String telefone, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, telefone, especialidade));
        entityManager.persist(medico);

        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String telefone, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, telefone, cpf));
        entityManager.persist(paciente);

        return paciente;
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data));
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, String telefone, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                crm,
                telefone,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String telefone, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                telefone,
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "Rua Floriano Peixoto",
                "Diadema",
                "13188200",
                "São Paulo",
                "SP",
                null,
                "510"
        );
    }

}