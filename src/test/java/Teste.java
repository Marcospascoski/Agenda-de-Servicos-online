
import com.agendame.model.Agenda;
import com.agendame.model.Endereco;
import com.agendame.model.Grupo;
import com.agendame.model.Horario;
import com.agendame.model.Pessoa;
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
    
    private static Pessoa pessoa;
    private static Usuario usuario;
    private static Telefone telefone;
    private static Endereco endereco;
    private static Servico servico;
    private static Horario horario;
    private static Agenda agenda;
    private static List<Agenda> listaAgenda;
    
    public static void main(String [] args){
        
        System.out.println("Iniciando EntityManagerFactory");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaMEPU");
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
        System.out.println("Iniciando a criação das tabelas");
        pessoa = new Pessoa();
        pessoa.setNome("Marcos Pascoski");
        pessoa.setRg("1234567894");
        pessoa.setCpf("123456789");
        pessoa.setSexo("Masculino");
        pessoa.setTelefone(telefone);
        pessoa.setTipo(TipoPessoa.FISICA);
        pessoa.setAgenda(listaAgenda);
        
        usuario = new Usuario();
        usuario.setEmail("marcospascoski@gmail.com");
        usuario.setSenha("123456");
        usuario.setGrupo(Grupo.ADMINISTRADOR);
        usuario.setAgenda(listaAgenda);

        telefone = new Telefone();
        telefone.setNumero("6635562871");
        telefone.setPessoa(pessoa);
        
        endereco = new Endereco();
        endereco.setLogradouro("Rua Marilia");
        endereco.setNumero("70");
        endereco.setComplemento("W");
        endereco.setBairro("Centro");
        endereco.setCep("78575-000");
        endereco.setCidade("Juara");
        endereco.setUf("MT");
        endereco.setPessoa(pessoa);
        
        servico.setNome("formatacao");
        servico.setAgendamentos(listaAgenda);
        
        horario.setHoraInicial(Time.valueOf("08:00"));
        horario.setHoraFinal(Time.valueOf("08:30"));
        
        agenda.setPessoa(pessoa);
        agenda.setUsuario(usuario);
        agenda.setServico(servico);
        agenda.setHorario(horario);
        
        pessoa.getEnderecos().add(endereco);
        usuario.getAgenda().add(agenda);
        horario.getAgenda().add(agenda);
        servico.getAgendamentos().add(agenda);
        
        
        em.persist(pessoa);
        em.persist(usuario);
        em.persist(telefone);
        em.persist(endereco);
        em.persist(servico);
        em.persist(agenda);
        
        et.commit();
        System.out.println("Tabelas criadas com sucesso");
    }

}
