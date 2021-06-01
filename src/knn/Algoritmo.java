/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author gabri
 */
public class Algoritmo {
    public LinkedList<String[]> dataset = new LinkedList<>();
    
    public float euclidianDistance(String currentReg, String target){
        String[] currentVetor = currentReg.split(","); // array da linha 
        String[] targ = target.split(","); // array da linha
        float somatorio = 0;
        
        for(int i = 0; i < currentVetor.length - 1; i++) {
            float diferenca = Float.parseFloat(currentVetor[i]) - Float.parseFloat(targ[i]); // conta da diferença
            somatorio += Math.pow(diferenca, 2); // somatorio das diferenças
        }
        
        return (float)Math.sqrt(somatorio);
    }
    
    public String classify(LinkedList<String[]> dataset, int k, String target){
        if(dataset == null){
            return null;
        }
        
        if(k == 0){
            k = 1;
        }
        
        ArrayList<distanciaDoTarget> lista = new ArrayList();

        for(String[] vetor : dataset){
            String currentRegValor = "";

            for(String valor : vetor){
                currentRegValor += valor + ",";
            }
            
            distanciaDoTarget d = new distanciaDoTarget();
            
            d.distancia = this.euclidianDistance(currentRegValor.substring(0, currentRegValor.length() - 1), target); // chama o método euclidianDistance
            d.classe = vetor[vetor.length - 1];
            lista.add(d);
        }
        
        Collections.sort(lista); // ordena lista de objeto
        
        ArrayList<String> listaClasse = new ArrayList();
        int i = 0;
        while(i < k){
            distanciaDoTarget obj = lista.get(i);
            listaClasse.add(obj.classe); // lista de strings de classes
            i++;
        }
        
        Map<String, Long> classeAgrupada = listaClasse.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // agrupar por quantidade de classes
        
        int maior = 0;
        String classeSugerida = "";
        for(Map.Entry<String,Long> contador : classeAgrupada.entrySet()) { // itera map classeAgrupada
            if(contador.getValue().intValue() > maior){ // verifica qual classe aparece mais
                maior = contador.getValue().intValue();
                classeSugerida = contador.getKey(); // atualiza a classe sugerida
            }
        }        
        
        return classeSugerida;
    }    
}
