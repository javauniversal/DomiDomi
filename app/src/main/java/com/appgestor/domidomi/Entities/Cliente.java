package com.appgestor.domidomi.Entities;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int codigo;
    private String nombre;
    private String celular;
    private String telefono;
    private String calle_carrera;
    private String dir_1;
    private String dir_2;
    private String dir_3;
    private String ciudad;
    private String zona;
    private int incluir;


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getIncluir() {
        return incluir;
    }

    public void setIncluir(int incluir) {
        this.incluir = incluir;
    }

    public String getCalle_carrera() {
        return calle_carrera;
    }

    public void setCalle_carrera(String calle_carrera) {
        this.calle_carrera = calle_carrera;
    }

    public String getDir_1() {
        return dir_1;
    }

    public void setDir_1(String dir_1) {
        this.dir_1 = dir_1;
    }

    public String getDir_2() {
        return dir_2;
    }

    public void setDir_2(String dir_2) {
        this.dir_2 = dir_2;
    }

    public String getDir_3() {
        return dir_3;
    }

    public void setDir_3(String dir_3) {
        this.dir_3 = dir_3;
    }

}
