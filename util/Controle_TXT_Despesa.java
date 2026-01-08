package util;

import Dados.TipoDespesa;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controle_TXT_Despesa {
    
    private static final String NomeArquivo = "Despesas.txt";
    
    public static void SalvarLinha(TipoDespesa Despesa) {
    
        try (FileWriter writer = new FileWriter(NomeArquivo, true)) {
            
            //Linha Escrita no Formato que Preferir
            String Linha = Despesa.getIDTipoDespesa() + "  |  " + Despesa.getPlaca() + "  |  "+Despesa.getCategoria() + "  |  " + Despesa.getDescricao() +"\n";
            writer.write(Linha);

        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao salvar no arquivo: " + e.getMessage());
        }
    }
    
    public static ArrayList<TipoDespesa> LerArquivo() {
        ArrayList<TipoDespesa> Lista = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(NomeArquivo))){
            String Linha;
            
            while((Linha = br.readLine()) != null){
                //.split Separa a Linha recebida no formato de "  |  " 
                String[] Partes = Linha.split("  \\|  ");
                
                int IDDespesa = Integer.parseInt(Partes[0]);
                String Placa = Partes[1];
                String Categoria = Partes[2];
                String Descricao = Partes[3];
                
                TipoDespesa despesa = new TipoDespesa(IDDespesa,Placa,Categoria,Descricao);
                Lista.add(despesa);
                
            }
            
    }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao Ler arquivo: "+e.getMessage());
        }
        return Lista;
    }
    //envia todas as informações que estava no Excel para o TXT
    public static void AtualizarTxtExcel(ArrayList<TipoDespesa> Lista){
         //false para Reescrever o arquivo em vez de apenas adicionar
        try(FileWriter writer = new FileWriter(NomeArquivo, false)){
            
            for(TipoDespesa Despesa : Lista){
                String Linha = Despesa.getIDTipoDespesa() + "  |  " + Despesa.getPlaca() + "  |  "+Despesa.getCategoria() + "  |  " + Despesa.getDescricao() +"\n";
       
                writer.write(Linha);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Txt: "+e.getMessage());
        }
    }
}
