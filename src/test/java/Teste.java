
import com.agendame.model.Agenda;
import com.agendame.model.Cliente;
import com.agendame.model.Endereco;
import com.agendame.model.Grupo;
import com.agendame.model.Horario;
import com.agendame.model.Servico;
import com.agendame.model.TipoPessoa;
import com.agendame.model.Usuario;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Marcos-TSI
 */
public class Teste {

    private static Cliente cliente;
    private static Usuario usuario;
    private static Endereco endereco;
    private static Servico servico;
    private static Horario horario;
    private static List<Servico> servicos;
    private static List<Horario> horarios;
    private static Agenda agenda;

    public static void main(String[] args) {

        System.out.println("Iniciando EntityManagerFactory");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaMEPU");

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        System.out.println("Iniciando a criação das tabelas");

        cliente = new Cliente();
        cliente.setNome("Marcos Pascoski");
        cliente.setRg("20485476");
        cliente.setCpf("02569456160");
        cliente.setSexo("Masculino");
        cliente.setTelefone("(66)9919-3444");
        cliente.setTipo(TipoPessoa.FISICA);
        cliente.setUsuario(usuario);

        usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcospascoski@gmail.com");
        usuario.setSenha("123456");
        usuario.setGrupo(Grupo.ADMINISTRADOR);
        usuario.setCliente(cliente);

        endereco = new Endereco();
        endereco.setLogradouro("Rua Marilia");
        endereco.setNumero("70");
        endereco.setComplemento("W");
        endereco.setBairro("Centro");
        endereco.setCep("78575-000");
        endereco.setCidade("Juara");
        endereco.setUf("MT");
        endereco.setCliente(cliente);

        horarios = new ArrayList();
        horarios.add(horario);
        
        agenda = new Agenda();
        agenda.setCliente(cliente);
        agenda.setUsuario(usuario);
        agenda.setDescrisao("preciso colocar um novo hd no pc também");
        agenda.setHorarios(horarios);
        
        servico = new Servico();
        servico.setNome("formatacaoc/backup");
        
        servicos = new ArrayList();
        servicos.add(servico);
        
        horario = new Horario();
        horario.setHoraInicial(Time.valueOf("00:30:00"));
        horario.setHoraFinal(Time.valueOf("00:30:00"));
        horario.setAgenda(agenda);
        horario.setServicos(servicos);
        
        cliente.getEnderecos().add(endereco);
        servico.getHorarios().add(horario);
        horario.getServicos().add(servico);
        agenda.getHorarios().add(horario);
        cliente.getAgendamentos().add(agenda);

        em.persist(cliente);
        em.persist(usuario);
        em.persist(endereco);
        em.persist(horario);
        em.persist(servico);
        em.persist(agenda);

        et.commit();
        System.out.println("Tabelas criadas com sucesso");
    }

}
