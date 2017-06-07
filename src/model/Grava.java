/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dante
 */
public class Grava {

    private double gravedad_especifica;
    private double relacion_vacios;
    private double indice_plasticidad;
    private double ocr;
    private double densidad_efectiva;
    private double estrato_i;
    private double estrato_f;
    private double[] profundidad;

    public Grava(double relacion_vacios, double indice_plasticidad, double ocr, double densidad_efectiva, double estrato_i, double estrato_f) {
        this.gravedad_especifica = 2.78;
        this.relacion_vacios = relacion_vacios;
        this.indice_plasticidad = indice_plasticidad;
        this.ocr = ocr;
        this.densidad_efectiva = densidad_efectiva;
        this.estrato_i = estrato_i;
        this.estrato_f = estrato_f;
        this.profundidad = new double[3];
        this.generarProfundiades();
    }    

    public double getIndice_plasticidad() {
        return indice_plasticidad;
    }

    public void setIndice_plasticidad(double indice_plasticidad) {
        this.indice_plasticidad = indice_plasticidad;
    }

    public double getOcr() {
        return ocr;
    }

    public void setOcr(double ocr) {
        this.ocr = ocr;
    }

    public double getDensidad_efectiva() {
        return densidad_efectiva;
    }

    public void setDensidad_efectiva(double densidad_efectiva) {
        this.densidad_efectiva = densidad_efectiva;
    }

    public double[] getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double[] profundidad) {
        this.profundidad = profundidad;
    }

    private void generarProfundiades() {
        this.profundidad[0] = this.estrato_i;
        this.profundidad[1] = (this.estrato_f - this.estrato_i) / 2;
        this.profundidad[2] = this.estrato_f;
    }

    public double generarKO() {
        double resultado = 0;
        if (this.indice_plasticidad <= 40) {
            resultado = (0.007 * this.indice_plasticidad) + 0.4;
        }else{
            resultado = (0.001 * (this.indice_plasticidad - 40))+0.68;
        }
        return resultado;
    }
    
   
    
    

}
