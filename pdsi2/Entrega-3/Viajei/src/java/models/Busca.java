package models;

import java.sql.Date;

public class Busca {

    private Date data_ida;
    private Date data_volta;
    private float valor;

    public Busca(Date data_ida, Date data_volta, float valor) {
        this.data_ida = data_ida;
        this.data_volta = data_volta;
        this.valor = valor;
    }

    public Date getData_ida() {
        return data_ida;
    }

    public void setData_ida(Date data_ida) {
        this.data_ida = data_ida;
    }

    public Date getData_volta() {
        return data_volta;
    }

    public void setData_volta(Date data_volta) {
        this.data_volta = data_volta;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
