
import com.agendame.model.Agenda;
import com.agendame.model.Endereco;
import com.agendame.model.Grupo;
import com.agendame.model.Horario;
import com.agendame.model.Cliente;
import com.agendame.model.Servico;
import com.agendame.model.Telefone;
import com.agendame.model.TipoPessoa;
import com.agendame.model.Usuario;
import java.sql.Time;
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
    private static Telefone telefone;
    private static Endereco endereco;
    private static Servico servico;
    private static Horario horario;
    private static Agenda agenda;
    private static List<Agenda> listaAgenda;

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
        cliente.setTelefone(telefone);
        cliente.setTipo(TipoPessoa.FISICA);
        cliente.setAgenda(listaAgenda);

        usuario = new Usuario();
        usuario.setEmail("marcospascoski@gmail.com");
        usuario.setSenha("123456");
        usuario.setGrupo(Grupo.ADMINISTRADOR);
        usuario.setAgenda(listaAgenda);

        telefone = new Telefone();
        telefone.setNumero("6635562871");
        telefone.setCliente(cliente);

        endereco = new Endereco();
        endereco.setLogradouro("Rua Marilia");
        endereco.setNumero("70");
        endereco.setComplemento("W");
        endereco.setBairro("Centro");
        endereco.setCep("78575-000");
        endereco.setCidade("Juara");
        endereco.setUf("MT");
        endereco.setCliente(cliente);

        servico.setNome("formatacao");
        servico.setAgendamentos(listaAgenda);

        horario.setHoraInicial(Time.valueOf("08:00"));
        horario.setHoraFinal(Time.valueOf("08:30"));

        agenda.setCliente(cliente);
        agenda.setUsuario(usuario);
        agenda.setServico(servico);
        agenda.setHorario(horario);

        cliente.getEnderecos().add(endereco);
        usuario.getAgenda().add(agenda);
        horario.getAgenda().add(agenda);
        servico.getAgendamentos().add(agenda);

        em.persist(cliente);
        em.persist(usuario);
        em.persist(telefone);
        em.persist(endereco);

        et.commit();
        System.out.println("Tabelas criadas com sucesso");
    }

}
