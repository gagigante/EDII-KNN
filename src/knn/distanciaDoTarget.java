/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn;

/**
 *
 * @author gabri
 */
public class distanciaDoTarget implements Comparable<distanciaDoTarget> {
    public String classe;
    public float distancia;
    
    public int compareTo(distanciaDoTarget d) { // ordenar lista pela distância
        if(this.distancia > d.distancia){
            return 1;
        }
        else if(this.distancia < d.distancia){
            return -1;
        }
        return this.classe.compareToIgnoreCase(d.classe);
    }
}
