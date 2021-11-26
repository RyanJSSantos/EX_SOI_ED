package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FifaController implements IFifaController {
	
	@Override
	public Stack<String> empilhaBrasileiros(String caminho, String nome) throws IOException {
		Stack<String> pilhaBr = new Stack<String>();
		File arquivo = new File(caminho, nome);
		if (arquivo.exists() && arquivo.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leFluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader (leFluxo);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while(linha != null) {
				String[] vetLinha = linha.split(",");
				if (vetLinha.length > 5) {
					if (vetLinha[5].equals("Brazil")) {
						pilhaBr.push(linha);
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leFluxo.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido");
		}
		return pilhaBr;
	}
	
	@Override
	public void desempilhaBonsBrasileiros(Stack<String> pilha) throws IOException {
		while (!pilha.isEmpty()) {
			String linha = pilha.pop();
			String [] vetLinha = linha.split(",");
			int overall = 0;
			if (vetLinha.length > 7) {
				String overallStr = vetLinha[7];
				try {
					overall = Integer.parseInt(overallStr);
					if (overall >= 80) {
						System.out.println(vetLinha[2]+" - "+overall);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public List<String> listaRevelacoes (String caminho, String nome) throws IOException {
		List<String> lista = new LinkedList<String>();
		File arquivo = new File(caminho, nome);
		if (arquivo.exists() && arquivo.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leFluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader (leFluxo);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while(linha != null) {
				String [] vetLinha = linha.split(",");
				int idade = 0;
				if (vetLinha.length > 3) {
					String idadeStr = vetLinha[3];
					try {
						idade = Integer.parseInt(idadeStr);
						if (idade <= 20) {
							lista.add(linha);
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leFluxo.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo Inválido");
		}
		return lista;
	}
	
	@Override
	public void buscaListaBonsJovens(List<String> lista) throws IOException {
		for (String linha : lista) {
			String[] vetLinha = linha.split(",");
			int overall = 0;
			int idade = 0;
			if (vetLinha.length > 7) {
				String overallStr = vetLinha[7];
				String idadeStr = vetLinha[3];
				try {
					overall = Integer.parseInt(overallStr);
					idade = Integer.parseInt(idadeStr);
					if (overall >= 80 && idade <=20) {
						System.out.println(
								vetLinha[2]+" - "+idade+" anos"+" - "+
						overall);
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
