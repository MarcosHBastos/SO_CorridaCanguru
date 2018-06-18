/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabalho;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class Largada {

    public static void main(String[] args) {
        Sincronização sinc = new Sincronização();
        System.out.println("A corrida terá o percurso de " + sinc.percurso + " metros!");

        Canguru c1 = new Canguru("Canguru 1", sinc);
        Canguru c2 = new Canguru("Canguru 2", sinc);
        Canguru c3 = new Canguru("Canguru 3", sinc);
        Canguru c4 = new Canguru("Canguru 4", sinc);
        Canguru c5 = new Canguru("Canguru 5", sinc);

        System.out.println("3.....");
        try {
            sleep(1000);
            System.out.println("2.....");
            sleep(1000);
            System.out.println("1.....");
            sleep(1000);
            System.out.println("Vai!\n");
        } catch (InterruptedException ex) {
            Logger.getLogger(Largada.class.getName()).log(Level.SEVERE, null, ex);
        }
        c5.start();
        c4.start();
        c3.start();
        c2.start();
        c1.start();

    }
}
