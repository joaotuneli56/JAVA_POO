package empregado.entidade;

public class Empregado {
	private int id;
	private String nome;
	private double salario;
	private Departamento departamento;
	
	public Empregado(int id, String nome, double salario, Departamento departamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.salario = salario;
		this.departamento = departamento;
	}
	
	@Override
	public String toString() {
		String aux = "";
		aux += "Nome: " + nome + "\n";
		aux += "Salário: " + salario + "\n";
		aux += "Departamento: " + departamento.getNome() + "\n";
		return aux;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}	
}
