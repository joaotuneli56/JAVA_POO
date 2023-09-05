package empregado.menu;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;

import java.util.List;

import empregado.dao.DepartamentoDAO;
import empregado.dao.EmpregadoDAO;
import empregado.entidade.Departamento;
import empregado.entidade.Empregado;
import empregado.excecao.OpcaoInvalidaException;

public class Menu {

	// método para exibir  o menu da aplicação
	public void menu() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenu()));
				if(opcao < 1 || opcao > 7) {
					throw new OpcaoInvalidaException("Opção inválida");
				} else {
					switch(opcao) {
						case 1:
							cadastrarDepartamento();
							break;
						case 2:
							cadastrarEmpregado();
							break;
						case 3:
							pesquisarEmpregado();
							break;
						case 4:
							listarEmpregado();
							break;
						case 5:
							atualizarEmpregado();
							break;
						case 6:
							excluirEmpregado();
							break;							
					}
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "Você deve digitar um número\n" + e);
			}
			catch(OpcaoInvalidaException e) {
				showMessageDialog(null, e.getMessage());
			}
		} while(opcao != 7);		
	}
	
	private void excluirEmpregado() {
		EmpregadoDAO dao = new EmpregadoDAO();
		int id = parseInt(showInputDialog("ID"));
		Empregado empregado = dao.pesquisar(id);
		if(empregado == null) {
			showMessageDialog(null, "Empregado não encontrado");
		} else {
			dao.remover(id);
		}		
	}

	private void atualizarEmpregado() {
		EmpregadoDAO dao = new EmpregadoDAO();
		int id = parseInt(showInputDialog("ID"));
		Empregado empregado = dao.pesquisar(id);
		if(empregado == null) {
			showMessageDialog(null, "Empregado não encontrado");
		} else {
			String novoNome = showInputDialog("Novo nome");
			double novoSalario = parseDouble(showInputDialog("Novo salário"));
			empregado.setId(id);
			empregado.setNome(novoNome);
			empregado.setSalario(novoSalario);
			dao.atualizar(empregado);
		}	
		
	}

	private void listarEmpregado() {
		EmpregadoDAO dao = new EmpregadoDAO();
		List<Empregado> lista = dao.listar();
		String aux = "";
		for(Empregado e : lista) {
			aux += e + "\n";
		}
		showMessageDialog(null, aux);
	}

	private void pesquisarEmpregado() {
		EmpregadoDAO dao = new EmpregadoDAO();
		int id = parseInt(showInputDialog("ID"));
		Empregado empregado = dao.pesquisar(id);
		if(empregado == null) {
			showMessageDialog(null, "Empregado não encontrado");
		} else {
			showMessageDialog(null, empregado);
		}		
	}

	private void cadastrarEmpregado() {
		DepartamentoDAO depDao = new DepartamentoDAO();
		EmpregadoDAO empDao = new EmpregadoDAO();
		int idEmp, idDep;
		String nome;
		double salario;
		
		idEmp = parseInt(showInputDialog("ID"));
		if(empDao.pesquisar(idEmp) != null) {
			showMessageDialog(null, "Empregado já está cadastrado");
		} else {
			List<Departamento> lista = depDao.listar();
			String aux = "";
			for(Departamento d : lista) {
				aux += d.getId() + "  " + d.getNome() + "\n";
			}
			nome = showInputDialog("Nome");
			salario = parseDouble(showInputDialog("Salário"));
			idDep = parseInt(showInputDialog(aux));
			Departamento departamento = new Departamento(idDep, null);
			Empregado empregado = new Empregado(idEmp, nome, salario, departamento);
			empDao.inserir(empregado);
		}
		
	}

	private void cadastrarDepartamento() {
		int id = parseInt(showInputDialog("ID"));
		String nome = showInputDialog("Nome do Departamento");
		
		Departamento departamento = new Departamento(id, nome);
		DepartamentoDAO dao = new DepartamentoDAO();
		
		if(dao.pesquisar(departamento)) {
			showMessageDialog(null, "Departamento já existe");
		} else {
			dao.inserir(departamento);
		}
	}

	// método para gerar o menu da aplicação
	private String gerarMenu() {
		String menu = "CONTROLE DE EMPREGADOS - ESCOLHA UMA OPÇÃO\n";
		menu += "1. Cadastrar departamento\n";
		menu += "2. Cadastrar empregado\n";
		menu += "3. Pesquisar empregado\n";
		menu += "4. Listar empregado\n";
		menu += "5. Atualizar empregado\n";
		menu += "6. Excluir empregado\n";
		menu += "7. Finalizar\n";
		return menu;
	}
}
