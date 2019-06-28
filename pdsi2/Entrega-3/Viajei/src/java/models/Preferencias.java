package models;

public class Preferencias {
    private String clima;
    private String transporte;
    private String perfil;
    private String servicos;
    private String alimentacao;
    private String pagamento;

    public Preferencias(String clima, String transporte, String perfil, String servicos, String alimentacao, String pagamento) {
        this.clima = clima;
        this.transporte = transporte;
        this.perfil = perfil;
        this.servicos = servicos;
        this.alimentacao = alimentacao;
        this.pagamento = pagamento;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getServicos() {
        return servicos;
    }

    public void setServicos(String servicos) {
        this.servicos = servicos;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }
}
