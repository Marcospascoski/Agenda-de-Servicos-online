
import com.agendame.model.Agenda;
import com.agendame.model.Cliente;
import com.agendame.model.Endereco;
import com.agendame.model.Grupo;
import com.agendame.model.Servico;
import com.agendame.model.TipoPessoa;
import com.agendame.model.Usuario;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static Grupo grupo;
    private static Endereco endereco;
    private static Servico servico;
    private static List<Servico> servicos;
    private static List<Grupo> grupos;
    private static List<Usuario> usuarios;
    private static Agenda agenda;

    public static void main(String[] args) throws ParseException {

        System.out.println("Iniciando EntityManagerFactory");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaMEPU");

        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        System.out.println("Iniciando a criação das tabelas");

        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("16/11/1984");
        Date dateTempo = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("16/11/1984 15:30:00");
        long time = dateTempo.getTime();
        Timestamp timestamp = new Timestamp(time);

        cliente = new Cliente();
        cliente.setNome("Marcos Pascoski");
        cliente.setDocReceitaFederal("789.456.123-78");
        cliente.setDataNascimento(date);
        cliente.setTelefone("(66)9919-3444");
        cliente.setTipo(TipoPessoa.JURIDICA);

        usuarios = new ArrayList();
        usuarios.add(usuario);

        grupos = new ArrayList();
        grupos.add(grupo);

        usuario = new Usuario();
        usuario.setNome("Marcos");
        usuario.setEmail("marcospascoski@gmail.com");
        usuario.setSenha("123456");
        usuario.setGrupos(grupos);

        grupo = new Grupo();
        grupo.setNome("Administradores");
        grupo.setDescricao("Administradores da agenda");
        grupo.setUsuarios(usuarios);

        endereco = new Endereco();
        endereco.setLogradouro("Rua Marilia");
        endereco.setNumero("70");
        endereco.setComplemento("W");
        endereco.setBairro("Centro");
        endereco.setCep("78575-000");
        endereco.setCidade("Juara");
        endereco.setUf("MT");
        endereco.setCliente(cliente);

        servico = new Servico();
        servico.setNome("formatacaoc/backup");

        servicos = new ArrayList();
        servicos.add(servico);

        agenda = new Agenda();
        agenda.setCliente(cliente);
        agenda.setDataInicio(timestamp);
        agenda.setDataFim(timestamp);
        agenda.setObservacao("preciso colocar um novo hd no pc também");
        agenda.setDiaTodo(false);
        agenda.setProfissional(usuario);
        agenda.setServico(servico);

        cliente.getEnderecos().add(endereco);
        grupo.getUsuarios().add(usuario);
        usuario.getGrupos().add(grupo);
        cliente.getAgendamentos().add(agenda);
        
        em.persist(cliente);
        em.persist(usuario);
        em.persist(grupo);
        em.persist(endereco);
        em.persist(servico);
        em.persist(agenda);

        et.commit();
        System.out.println("Tabelas criadas com sucesso");
        em.close();
    }

}
