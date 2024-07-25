package br.com.tiago.tabelafipe.principal;

import br.com.tiago.tabelafipe.model.Dados;
import br.com.tiago.tabelafipe.model.Modelos;
import br.com.tiago.tabelafipe.model.Veiculo;
import br.com.tiago.tabelafipe.service.ConsumoApi;
import br.com.tiago.tabelafipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner entrada = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converso = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

   public void exbirMenu() {
        var menu = """
                ***OPÇÕES***
                Carro
                Moto
                Caminhão
                ------------------------------------      
                """;
        System.out.print(menu);
        System.out.print("Informe uma das opções para consulta: ");
        String opcaoInformada = entrada.nextLine();
        String endereco;

        if (opcaoInformada.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "carros/marcas";
        } else if (opcaoInformada.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motos/marcas";
        } else {
            endereco = URL_BASE + "caminhoes/marcas";
        }
        var json = consumo.obterDados(endereco);
        System.out.println(json);


       var marcas = converso.obterLista(json, Dados.class);
       marcas.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

       System.out.print("Informe o código da marca para consultar: ");
       var codigoMarca = entrada.nextLine();

       endereco = endereco + "/" + codigoMarca + "/modelos";
       json = consumo.obterDados(endereco);
       var modeloLista = converso.obterDados(json, Modelos.class);
       System.out.println("\nModelos");
        modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

       System.out.print("\nInforme a marca do carro: ");
       var nomeVeiculo = entrada.nextLine();

       List<Dados> modeloFiltrado = modeloLista.modelos().stream()
               .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
               .collect(Collectors.toList());

       System.out.println("\nLista");
       modeloFiltrado.forEach(System.out::println);

       System.out.print("\nInforme o código: ");
       var codigo = entrada.nextLine();

       endereco = endereco + "/" + codigo + "/" + "/anos";
       json = consumo.obterDados(endereco);
       List<Dados> anos = converso.obterLista(json, Dados.class);
       List<Veiculo> veiculos = new ArrayList<>();

//       for (int i = 0; i < anos.size(); i++) {
//           var enderecoAnos = endereco + "/" + anos.get(i).codigo();
//           json = consumo.obterDados(enderecoAnos);
//           Veiculo veiculo = converso.obterDados (json, Veiculo.class);
//           veiculos.add(veiculo);
//       }
//
//       System.out.println("\nTodos os veiculos filtrados com avaliações por ano: "); veiculos.forEach(System.out::println);


   }
}
