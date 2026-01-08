package Dados;

public class TipoDespesa {
    
   private int IDTipoDespesa;
   private String Placa;
   private String Categoria;
   private String Descricao;

    public int getIDTipoDespesa() {
        return IDTipoDespesa;
    }

    public void setIDTipoDespesa(int IDTipoDespesa) {
        this.IDTipoDespesa = IDTipoDespesa;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }
    
    
    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
    
    public TipoDespesa(int IDTipoDespesa, String Placa,String Categoria, String Descricao){
        this.IDTipoDespesa = IDTipoDespesa;
        this.Placa = Placa;
        this.Categoria = Categoria;
        this.Descricao = Descricao;
    }
}
