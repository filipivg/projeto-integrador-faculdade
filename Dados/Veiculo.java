package Dados;

public class Veiculo {
    
    private int IDVeiculo;
    private String Placa;
    private String Marca;
    private String Modelo;
    private int anoFab;
    private Boolean EstadoDoVeic;

    public int getIDVeiculo() {
        return IDVeiculo;
    }

    public void setIDVeiculo(int IDVeiculo) {
        this.IDVeiculo = IDVeiculo;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getAnoFab() {
        return anoFab;
    }

    public void setAnoFab(int anoFab) {
        this.anoFab = anoFab;
    }

    public boolean isEstadoDoVeic() {
        return EstadoDoVeic;
    }

    public void setEstadoDoVeic(boolean EstadoDoVeic) {
        this.EstadoDoVeic = EstadoDoVeic;
    }
    
    public Veiculo(int IDVeiculo, String Placa, String Marca, String Modelo,int AnoFab,boolean EstadoDoVeic){
        this.IDVeiculo = IDVeiculo;
        this.Placa = Placa;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.anoFab = AnoFab;
        this.EstadoDoVeic = EstadoDoVeic;
    }
    
    //Receber o Estado em boolean Ex: caso seja "true" retorna um String "Ativo"
    public String getEstadoDoVeiculoTexto() {
        return EstadoDoVeic ? "Ativo" : "Inativo"; 
    }
    
}
