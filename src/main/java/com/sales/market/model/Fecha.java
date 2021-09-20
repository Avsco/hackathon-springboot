package com.sales.market.model;

import javax.persistence.Embeddable;

@Embeddable
public class Fecha {
    private int Dia;
    private int mes;

    public int getDia() {
        return Dia;
    }

    public void setDia(int dia) {
        Dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
}
