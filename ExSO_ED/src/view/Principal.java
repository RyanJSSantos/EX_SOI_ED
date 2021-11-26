package view;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

import controller.FifaController;
import controller.IFifaController;

public class Principal {

	public static void main(String[] args) {
		String dir = "C:\\Temp";
		String arq = "data.csv";
		
		IFifaController fifaCont = new FifaController();
		
		try {
			Stack<String> pilhaBr = fifaCont.empilhaBrasileiros(dir, arq);
			fifaCont.desempilhaBonsBrasileiros(pilhaBr);
			System.out.println("==================================");
			List<String> listaNovos = fifaCont.listaRevelacoes(dir, arq);
			fifaCont.buscaListaBonsJovens(listaNovos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
