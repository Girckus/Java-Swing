package data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DAO {

	private List<Pessoa> pessoas = new ArrayList<>();
	private List<String> states = new ArrayList<>();
	private Map<String, List<String>> cities = new HashMap<>();
	
	public DAO() {
		String sp = "São Paulo";
		String rj = "Rio De Janeiro";
		String mg = "Minas Gerais";
		String pr = "Paraná";
		
		states.add(sp);
		states.add(rj);
		states.add(mg);
		states.add(pr);
		
		List<String> citiesSaoPaulo = new ArrayList<>();
		citiesSaoPaulo.add("Araraquara");
		citiesSaoPaulo.add("Bauro");
		citiesSaoPaulo.add("Campinas");
		citiesSaoPaulo.add("Guarulhos");
		citiesSaoPaulo.add("São Bernado");
		citiesSaoPaulo.add("São Ceatano");
		citiesSaoPaulo.add("São Carlos");
		citiesSaoPaulo.add("São Paulo");
		citiesSaoPaulo.add("Santos");
		
		List<String> citiesRio = new ArrayList<>();
		citiesRio.add("Duque De Caxias");
		citiesRio.add("Mesquita");
		citiesRio.add("Niteroi");
		citiesRio.add("Nova Iguaçu");
		citiesRio.add("Rio De Janeiro");
		
		List<String> citiesMinas = new ArrayList<>();
		citiesMinas.add("Belo Horizonte");
		citiesMinas.add("Diamantina");
		citiesMinas.add("Governador Valadares");
		citiesMinas.add("Lavras");
		citiesMinas.add("Uberaba");
		
		List<String> citiesParana = new ArrayList<>();
		citiesParana.add("Cascavel");
		citiesParana.add("Curitiba");
		citiesParana.add("Foz Do Iguaçu");
		citiesParana.add("Londrina");
		citiesParana.add("Maringá");
		citiesParana.add("Ponta Grossa");
		
		cities.put(sp, citiesSaoPaulo);
		cities.put(rj, citiesRio);
		cities.put(mg, citiesMinas);
		cities.put(pr, citiesParana);
		
		String bioFileName = "bio.txt";
		
		Calendar nascimento = Calendar.getInstance();
		nascimento.set(1988, 10, 20);
		Date dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Cléston", 35, "Male", "São Paulo", dataNascimento, bioFileName));

		nascimento.set(2008, 3, 13);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Uelington", 15, "Other", "São Paulo", dataNascimento, bioFileName));
		
		nascimento.set(1942, 9, 1);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Margarete", 81, "Female", "São Paulo", dataNascimento, bioFileName));
		
		nascimento.set(2000, 1, 1);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Sidicleison", 23, "Male", "Rio De Janeiro", dataNascimento, bioFileName));
		
		nascimento.set(1992, 4, 8);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Morgana", 31, "Female", "Rio De Janeiro", dataNascimento, bioFileName));
		
		nascimento.set(1977, 6, 19);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Maria Celestina", 46, "Other", "Guarulhos", dataNascimento, bioFileName));
		
		nascimento.set(1968, 12, 10);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Rebecco", 55, "Other", "Curitiba", dataNascimento, bioFileName));
		
		nascimento.set(2004, 8, 29);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Rebecca", 19, "Female", "Londrina", dataNascimento, bioFileName));
		
		nascimento.set(1957, 5, 5);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Geisy", 66, "Female", "Belo Horizonte", dataNascimento, bioFileName));
		
		nascimento.set(1950, 2, 24);
		dataNascimento = nascimento.getTime();
		pessoas.add(new Pessoa("Adalberto", 73, "Male", "Niterói", dataNascimento, bioFileName));
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public List<Pessoa> getPessoasByCity(String cidade) {
		Stream<Pessoa> stream = getPessoas().stream();
		
		return stream.filter(p -> p.getCidade().equals(cidade))
				     .collect(Collectors.toList());
	}
	
	public Optional<Pessoa> getPessoaByName(String nome) {
		Stream<Pessoa> stream = getPessoas().stream();
		
		return stream.filter(p -> p.getNome().equals(nome))
				     .findFirst();
	}
	
	public List<String> getStates() {
		return states;
	}
	
	public List<String> getCities(String state) {
		return cities.get(state);
	}
	
	public void addPessoa(Pessoa pessoa) {
		pessoas.add(pessoa);
	}
}
