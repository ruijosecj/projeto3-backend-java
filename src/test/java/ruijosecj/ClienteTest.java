package ruijosecj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import dao.ClienteDAO;
import dao.IClienteDAO;
import dao.jdbc.ConnectionFactory;
import domain.Cliente;

public class ClienteTest {
	private IClienteDAO clienteDAO = new ClienteDAO();
	private Connection connection;
	

    

    @Before
    public void setUp() throws Exception {
        // Estabelece a conexão com o banco de dados de teste
        connection = ConnectionFactory.getConnection();
        clienteDAO = new ClienteDAO();
    }
	
	@Test
	public void cadastrarTest() throws Exception {
		
		
		Cliente cliente = new Cliente();
		cliente.setCodigo("10");
		cliente.setNome("Rodrigo Pires");
		int countCad = clienteDAO.cadastrar(cliente);
		assertTrue(countCad == 1);
		
		Cliente clienteBD = clienteDAO.buscar(cliente.getCodigo());
		assertNotNull(clienteBD);
		assertNotNull(clienteBD.getId());
		assertEquals(cliente.getCodigo(), clienteBD.getCodigo());
		assertEquals(cliente.getNome(), clienteBD.getNome());
		
		Integer countDel = clienteDAO.excluir(clienteBD);
		assertNotNull(countDel == 1);
	}
	
	@Test
    public void testAtualizar() throws Exception {
		
        // Insira um cliente de teste no banco de dados
        Cliente cliente = new Cliente();
        cliente.setNome("Rodrigo Pires");
        cliente.setCodigo("10");
        int idCliente = clienteDAO.cadastrar(cliente);

        // Modifica os detalhes do cliente
        Cliente clienteBD = clienteDAO.buscar(cliente.getCodigo());
        clienteBD.setNome("Rui Jose");
        clienteBD.setCodigo("11");

        // Executa o método atualizar
        clienteDAO.atualizar(clienteBD);

        // Verifica se os detalhes do cliente foram atualizados corretamente no banco de dados
        
            assertEquals("Rui Jose", clienteBD.getNome());
            assertEquals("11", clienteBD.getCodigo());
        
    }

}
