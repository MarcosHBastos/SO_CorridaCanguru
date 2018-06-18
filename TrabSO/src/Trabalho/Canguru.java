/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import java.util.Random;

/**
 *
 * @author Marcos
 */
public class Canguru extends Thread {

    private int classificação = 0;
    private int pulo = 0;
    private int nPulos = 0;
    private int distancia = 0;
    private Sincronização corrida;

    public Canguru(String name, Sincronização corrida) {
        super(name);
        this.corrida = corrida;
    }

    public int pulo() {
        Random p = new Random();
        return (p.nextInt(10));
    }

    @Override
    public synchronized void run() {
        System.out.println(this.getName() + " largou!");
        while (true) {
            try {
                this.pulo = pulo();
                this.distancia += this.pulo;
                this.nPulos++;
                while (!corrida.nomes.get(corrida.vez).equals(this.getName())) {
                    sleep(100);
                }
                corrida.set(this.pulo, this.distancia, this.nPulos);
                if (this.distancia > corrida.percurso) {
                    this.classificação = corrida.getPosicao();
                    if (this.classificação != 5) {
                        corrida.getVez();
                    }
                    break;
                } else {
                    corrida.getVez();
                }
                //System.out.println(this.getName() + ": Pulou " + pulo + " metros, percorreu no total " + distancia + " metros com " + nPulos + "pulos.");
            } catch (InterruptedException exc) {
                exc.printStackTrace();
            }
        }
        System.out.println("\n" + this.getName() + " chegou na posição " + this.classificação + "\n");
        if (this.classificação == 5) {
            corrida.getPodio();
        }
    }
}
