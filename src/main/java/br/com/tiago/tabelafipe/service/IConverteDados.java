package br.com.tiago.tabelafipe.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class classe);
}
