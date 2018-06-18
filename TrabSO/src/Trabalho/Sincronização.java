/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class Sincronização {

    private int posicao = 1, total = 5, cont = 1;
    public int vez = 0;
    public boolean flag = false;
    Random r = new Random();
    public int percurso = (r.nextInt(101-80) + 80);
    List<String> nomes = new ArrayList<String>(Arrays.asList("Canguru 1", "Canguru 2", "Canguru 3", "Canguru 4", "Canguru 5"));
    private String[] podio = new String[5];

    public synchronized void set(int pulo, int distancia, int nPulos) {
        try {
            while (flag) {
                wait();
            }
        } catch (Exception exc) {
            exc.getStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Pulou " + pulo + " metros, percorreu no total " + distancia + " metros com " + nPulos + " pulos.");

        flag = true;
        notify();
    }

    public synchronized void getVez() {
        if (total >= 0) {
            try {
                while (!flag) {
                    wait();
                }

            } catch (Exception exc) {
                exc.getStackTrace();
            }
            vez = cont % total;
            //System.out.println("Vez do " + nomes.get(vez) + " saltar");
            cont++;
            flag = false;
            notify();
        }
    }

    public synchronized int getPosicao() {
        podio[posicao - 1] = Thread.currentThread().getName();
        nomes.remove(vez);
        posicao++;
        total--;
        if (total != 0) {
            vez = cont-1 % total;
        } 
        return posicao - 1;
    }

    public void getPodio() {
        System.out.println("\n\n ---------------------------- "
                + "\n|   Colocação  |   Canguru   |"
                + "\n|----------------------------|");
        for (int i = 0; i < 5; i++) {
            System.out.println("|   " + (i + 1) + "º Lugar   |  " + podio[i] + "  |");
        }
        System.out.println(" ---------------------------- ");

    }

}
