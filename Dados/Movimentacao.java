package Dados;

public class Movimentacao extends TipoDespesa{
    
    private int IDMovimentacao;
    private int IDVeiculo;
    private int Dia;
    private int Mes;
    private int Ano;
    private Double Valor;

    public int getIDMovimentacao() {
        return IDMovimentacao;
    }

    public void setIDMovimentacao(int IDMovimentacao) {
        this.IDMovimentacao = IDMovimentacao;
    }

    public int getIDVeiculo() {
        return IDVeiculo;
    }

    public void setIDVeiculo(int IDVeiculo) {
        this.IDVeiculo = IDVeiculo;
    }

    public int getDia() {
        return Dia;
    }

    public void setDia(int Dia) {
        this.Dia = Dia;
    }

    public int getMes() {
        return Mes;
    }

    public void setMes(int Mes) {
        this.Mes = Mes;
    }

    public int getAno() {
        return Ano;
    }

    public void setAno(int Ano) {
        this.Ano = Ano;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }
    
    public Movimentacao(int IDMovimentacao, int IDVeiculo,int IDTipoDespesa, String Placa, String Categoria,String Descricao ,int Dia, int Mes, int Ano, Double Valor){
        super(IDTipoDespesa,Placa,Categoria,Descricao);
        this.IDMovimentacao = IDMovimentacao;
        this.IDVeiculo = IDVeiculo;
        this.Dia = Dia;
        this.Mes = Mes;
        this.Ano = Ano;
        this.Valor = Valor;
    }
}
