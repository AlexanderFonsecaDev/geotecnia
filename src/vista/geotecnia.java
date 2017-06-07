/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javax.swing.JOptionPane;
import model.Arena;

/**
 *
 * @author Dante
 */
public class geotecnia {

    public static void main(String[] args) {
        try {
            
            double relacion_vacios = Double.parseDouble(JOptionPane.showInputDialog("relacion de vacios"));
            int angulo_friccion = Integer.parseInt(JOptionPane.showInputDialog("Angulo de friccion")) ;
            double densidad_efectiva = Double.parseDouble(JOptionPane.showInputDialog("Densidad Efectiva"));
            double estrato_i = Double.parseDouble(JOptionPane.showInputDialog("Estrato inicial"));
            double estrato_f = Double.parseDouble(JOptionPane.showInputDialog("Estrato Final"));
            
            Arena arn = new Arena(relacion_vacios, angulo_friccion, densidad_efectiva, estrato_i, estrato_f);
            double [] profundidades = arn.getProfundidad();
            double [] esfuerzo_efectivo = arn.calcularEsfuerzoEfectivo();
            double [] esfuerzo_KO = arn.esfuerzoEfectivoKO();
            double [] kpa = arn.calcularKPA();
            double [] modulo_cortante = arn.calcularModuloCortante();
            double [] velocidad_onda = arn.calcularVelocidadOnda();
           for (int i = 0; i < 3; i++) {
                System.out.println("Profundidades :" +" "+profundidades[i]);
                System.out.println("Esfuerzo Efectivo :" +" "+ esfuerzo_efectivo[i]);
                System.out.println("Esfuerzo KO :" +" "+esfuerzo_KO[i]);
                System.out.println("KPA :" +" "+kpa[i]);
                System.out.println("Modulo Cortante :" +" "+modulo_cortante[i]);
                System.out.println("Velocidad Onda :" +" "+velocidad_onda[i]);
                System.out.println("****************************************************");
                
            }
            
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
