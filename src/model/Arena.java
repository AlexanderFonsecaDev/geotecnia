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
public class Arena {
    
    private double gravedad_especifica;
    private double relacion_vacios;
    private int angulo_friccion;
    private double densidad_efectiva;
    private double estrato_i;
    private double estrato_f;
    private double[] profundidad;

    public Arena(double relacion_vacios, int angulo_friccion, double densidad_efectiva, double estrato_i, double estrato_f) {
        this.relacion_vacios = relacion_vacios;
        this.angulo_friccion = angulo_friccion;
        this.densidad_efectiva = densidad_efectiva;
        this.estrato_i = estrato_i;
        this.estrato_f = estrato_f;
        this.gravedad_especifica = 2.65;
        this.profundidad = new double[3];
        this.generarProfundiades();
    }

    public double getGravedad_especifica() {
        return gravedad_especifica;
    }

    public void setGravedad_especifica(double gravedad_especifica) {
        this.gravedad_especifica = gravedad_especifica;
    }

    public double getRelacion_vacios() {
        return relacion_vacios;
    }

    public void setRelacion_vacios(double relacion_vacios) {
        this.relacion_vacios = relacion_vacios;
    }

    public int getAngulo_friccion() {
        return angulo_friccion;
    }

    public void setAngulo_friccion(int angulo_friccion) {
        this.angulo_friccion = angulo_friccion;
    }

    public double getDensidad_efectiva() {
        return densidad_efectiva;
    }

    public void setDensidad_efectiva(double densidad_efectiva) {
        this.densidad_efectiva = densidad_efectiva;
    }

    public double getEstrato_i() {
        return estrato_i;
    }

    public void setEstrato_i(double estrato_i) {
        this.estrato_i = estrato_i;
    }

    public double getEstrato_f() {
        return estrato_f;
    }

    public void setEstrato_f(double estrato_f) {
        this.estrato_f = estrato_f;
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
        double resultado = 1 - Math.sin((this.angulo_friccion * Math.PI)/180);
        return resultado;
    }
    
    public double[] calcularEsfuerzoEfectivo(){
        double [] resultado =  new double[3];
        resultado[0] = this.densidad_efectiva * this.profundidad[0];
        resultado[1] = this.densidad_efectiva * this.profundidad[1];        
        resultado[2] = (this.profundidad[2] - this.profundidad[1])*(this.densidad_efectiva + resultado[1]);
        return resultado;
    }
    
    public double[] esfuerzoEfectivoKO(){
        double [] resultado =  new double[3];
        double [] esfuerzo_efectivo = this.calcularEsfuerzoEfectivo();
        double KO = this.generarKO();
        resultado[0] = KO * esfuerzo_efectivo[0];
        resultado[1] = KO * esfuerzo_efectivo[1];
        resultado[2] = KO * esfuerzo_efectivo[2];
        return resultado;
    }
    
    public double[] calcularKPA(){
        double [] resultado =  new double[3];
        double [] esfuerzo_efectivo = this.calcularEsfuerzoEfectivo();
        double [] esfuerzoKO = this.esfuerzoEfectivoKO();
        double op1 = 2 * esfuerzoKO[0];
        double op2 = 2 * esfuerzoKO[1];
        double op3 = 2 * esfuerzoKO[2];
        resultado[0] = (1 / 3)* esfuerzo_efectivo[0] + op1; 
        resultado[1] = (1 / 3)* esfuerzo_efectivo[1] + op2;
        resultado[2] = (1 / 3)* esfuerzo_efectivo[2] + op3;
        
        return resultado;
    }
    
    public double[] calcularModuloCortante(){
        double [] resultado =  new double[3];
        double [] esfuerzoKPA = this.calcularKPA();
        resultado[0] =  (6908 * (2.17 - this.relacion_vacios) * Math.pow(esfuerzoKPA[0],0.5))/(this.relacion_vacios +1);
        resultado[1] =  (6908 * (2.17 - this.relacion_vacios) * Math.pow(esfuerzoKPA[1],0.5))/(this.relacion_vacios +1);
        resultado[2] =  (6908 * (2.17 - this.relacion_vacios) * Math.pow(esfuerzoKPA[2],0.5))/(this.relacion_vacios +1);
        return resultado;
    }
    
    public double [] calcularVelocidadOnda(){
        double [] resultado =  new double[3];
        double [] modCortante = this.calcularModuloCortante();
        for (int i = 0; i < resultado.length; i++) {
            resultado[i] = Math.pow( (modCortante[i] / this.densidad_efectiva),0.5 );
        }
        return resultado;
    }
    
    
    
    
    
}
