package data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {
		
	private String nome;
	private Integer idade;
	private String sexo;
	private String cidade;
	private Date nascimento;
	private String bioFileName;
	
	public Pessoa(String nome, Integer idade, String sexo, String cidade, Date nascimento, String bioFileName) {
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.cidade = cidade;
		this.nascimento = nascimento;
		this.bioFileName = bioFileName;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		String str = "Name: " + nome + "\n";
		str += "Age: " + idade + "\n";
		str += "Sex: " + sexo + "\n";
		str += "City: " + cidade + "\n";
		str += "Birthdade: " + new SimpleDateFormat("dd/mm/yyyy").format(nascimento) + "\n";
		str += "Bio File: " + bioFileName + "\n";
		
		return str;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getBioFileName() {
		return bioFileName;
	}

	public void setBioFileName(String bioFileName) {
		this.bioFileName = bioFileName;
	}
	
}