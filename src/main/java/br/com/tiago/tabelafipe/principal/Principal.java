package br.com.tiago.tabelafipe.principal;

import br.com.tiago.tabelafipe.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner entrada = new Scanner(System.in);
    ConsumoApi consumo = new ConsumoApi();
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
    }
}
