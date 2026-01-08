package util;

import Dados.Veiculo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controle_TXT_Veiculo {
    
    private static final String NomeArquivo = "Veiculo.txt";
    
    //int para colocar automaticamente o id do veiculo
    public static int idveiculo = 0;
    
    public static void SalvarLinha(Veiculo veiculo) {
    
        try (FileWriter writer = new FileWriter(NomeArquivo, true)) {
            
            //Adiciona 1 no ID para automatizar
            idveiculo++;
            veiculo.setIDVeiculo(idveiculo);
            
            //Linha Escrita no Formato que Preferir
            String Linha = veiculo.getIDVeiculo() + "  |  " + veiculo.getPlaca() + "  |  " + veiculo.getMarca() 
                        + "  |  " + veiculo.getModelo() + "  |  " + veiculo.getAnoFab() + "  |  " + veiculo.getEstadoDoVeiculoTexto()+"\n";
            writer.write(Linha);

        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao salvar no arquivo: " + e.getMessage());
        }
    }
    
    public static ArrayList<Veiculo> LerArquivo() {
        ArrayList<Veiculo> Lista = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(NomeArquivo))){
            String Linha;
            
            while((Linha = br.readLine()) != null){
                //.split Separa a Linha recebida no formato de "  |  " 
                String[] Partes = Linha.split("  \\|  ");
                
                int IDVeic = Integer.parseInt(Partes[0]);
                String Placa = Partes[1];
                String Marca = Partes[2];
                String Modelo = Partes[3];
                int AnoFab = Integer.parseInt(Partes[4]);
                boolean Estado = Partes[5].equalsIgnoreCase("Ativo");
                
                Veiculo veiculo = new Veiculo(IDVeic, Placa, Marca, Modelo, AnoFab, Estado);
                Lista.add(veiculo);
            
            //idveiculo Recebe o valor do ultimo IDVeic para automatizar o ID
            idveiculo = IDVeic;
            }
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao Ler arquivo: "+e.getMessage());
        }
        
        return Lista;
    }
    
    //envia todas as informações que estava no Excel para o TXT
    public static void AtualizarTxtExcel(ArrayList<Veiculo> Lista){
        //false para Reescrever o arquivo em vez de apenas adicionar
        try(FileWriter writer = new FileWriter(NomeArquivo, false)){
            
            for(Veiculo veiculo : Lista){
                String Linha = veiculo.getIDVeiculo() + "  |  " + veiculo.getPlaca() + "  |  " + veiculo.getMarca() 
                        + "  |  " + veiculo.getModelo() + "  |  " + veiculo.getAnoFab() + "  |  " + veiculo.getEstadoDoVeiculoTexto()+"\n";

                writer.write(Linha);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Txt: "+e.getMessage());
        }
    }

}
