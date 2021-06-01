/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author gabri
 */
public class KNN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        LinkedList<String> datasetSemClasse = new LinkedList<>();

//              dataset.txt 
//              Height,Weight,Age,Class
//              1.70,65,20,Programmer
//              1.90,85,33,Builder
//              1.78,76,31,Builder
//              1.73,74,24,Programmer
//              1.81,75,35,Builder
//              1.73,70,75,Scientist
//              1.80,71,63,Scientist
//              1.75,69,25,Programmer

        try {
            FileReader arquivo = new FileReader("dataset.txt"); // ALTERAR AQUI A URL
            BufferedReader lerArq = new BufferedReader(arquivo);

            String linha = lerArq.readLine(); // lê a primeira linha
            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            
            String atributos[] = linha.split(","); //primeira linha (são os atributos)
            
            Boolean isAtributo = true;
            
            Algoritmo a = new Algoritmo();
            
            while (linha != null) {
                if(isAtributo){ // não pegar a primeira linha
                    isAtributo = false;
                }else{
                    a.dataset.add(linha.split(",")); // lista array de strings
                    datasetSemClasse.add(linha.substring(0, linha.lastIndexOf(","))); // lista de strings
                }
                
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }       
            
            arquivo.close();
            
            try{ // verifica se as strings podem ser transformados em float
                for(String vetor : datasetSemClasse){
                    for(String valor : vetor.split(",")){
                        float num = Float.parseFloat(valor);
                    }
                }
                
                String classeSugerida = a.classify(a.dataset, 3, datasetSemClasse.get(5)); // chama o método classify
                System.out.println("A classe sugerida é: " + classeSugerida); // printa a resposta KNN
            } catch (NumberFormatException e) {
                System.out.println("Não pode ser transformado num tipo float");
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
        }
        
    }
    
    
}
