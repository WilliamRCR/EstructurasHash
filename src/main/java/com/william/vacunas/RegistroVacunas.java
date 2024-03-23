/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.william.vacunas;

/**
 *
 * @author William
 */
public class RegistroVacunas {
    final String nombreVacuna;
    final String fechaAplicacion;

    public RegistroVacunas(String nombreVacuna, String fechaAplicacion) {
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public String getFechaAplicacion() {
        return fechaAplicacion;
    }

    @Override
    public String toString() {
        return "Vacuna: " + nombreVacuna + ", Fecha de Aplicación: " + fechaAplicacion;
    }
}
