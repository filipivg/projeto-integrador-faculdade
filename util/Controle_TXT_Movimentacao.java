package util;

import Dados.Movimentacao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controle_TXT_Movimentacao {
    
    private static final String NomeArquivo = "Movimentacao.txt";
    
    //Variaveis para automatizar o ID
    public static int idmovimentacao = 0;
    public static int iddespesa = 0;
    //Variavel para receber a Placa e verificar a Placa
    public static String placa = "";
    
    public static void SalvarLinha(Movimentacao movimentacao) {
    
        try (FileWriter writer = new FileWriter(NomeArquivo, true)) {
            
            //Adiciona 1 nos ID para automatizar
            idmovimentacao++;
            movimentacao.setIDMovimentacao(idmovimentacao);
            
            iddespesa++;
            movimentacao.setIDTipoDespesa(iddespesa);
            
            //Linha Escrita no Formato que Preferir
            String Linha = movimentacao.getIDMovimentacao() + "  |  " + movimentacao.getIDVeiculo() + "  |  " + movimentacao.getIDTipoDespesa() + "  |  "
                         + movimentacao.getPlaca() + "  |  " + movimentacao.getCategoria() + "  |  " + movimentacao.getDescricao() + "  |  " + movimentacao.getDia() 
                         + "  |  " + movimentacao.getMes() + "  |  "+ movimentacao.getAno() + "  |  " + movimentacao.getValor() +"\n";
            writer.write(Linha);

        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao salvar no arquivo: " + e.getMessage());
        }
    }
    
    public static ArrayList<Movimentacao> LerArquivo() {
        ArrayList<Movimentacao> Lista = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(NomeArquivo))){
            String Linha;
            
            while((Linha = br.readLine()) != null){
                //.split Separa a Linha recebida no formato de "  |  " 
                String[] Partes = Linha.split("  \\|  ");
                
                int IDMovimentacao = Integer.parseInt(Partes[0]);
                int IDVeiculo = Integer.parseInt(Partes[1]);
                int IDDespesa = Integer.parseInt(Partes[2]);
                String Placa = Partes[3];
                String Categoria = Partes[4];
                String Descricao = Partes[5];
                int Dia = Integer.parseInt(Partes[6]);
                int Mes = Integer.parseInt(Partes[7]);
                int Ano = Integer.parseInt(Partes[8]);
                double Valor = Double.parseDouble(Partes[9]);
                
                Movimentacao movimentacao = new Movimentacao(IDMovimentacao,IDVeiculo,IDDespesa,Placa,Categoria,Descricao,Dia,Mes,Ano,Valor);
                Lista.add(movimentacao);
                
                //Recebe o Valor do ultimo ID de cada para a automatização
                idmovimentacao = IDMovimentacao;
                iddespesa = IDDespesa;
            }
            
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao Ler arquivo: "+e.getMessage());
        }
        
        return Lista;
    }
    
    //envia todas as informações que estava no Excel para o TXT
    public static void AtualizarTxtExcel(ArrayList<Movimentacao> Lista){
        //false para Reescrever o arquivo em vez de apenas adicionar
        try(FileWriter writer = new FileWriter(NomeArquivo, false)){
            
            for(Movimentacao movimentacao : Lista){
                 String Linha = movimentacao.getIDMovimentacao() + "  |  " + movimentacao.getIDVeiculo() + "  |  " + movimentacao.getIDTipoDespesa() + "  |  "
                         + movimentacao.getPlaca() + "  |  " + movimentacao.getCategoria() + "  |  " + movimentacao.getDescricao() + "  |  " + movimentacao.getDia() 
                         + "  |  " + movimentacao.getMes() + "  |  "+ movimentacao.getAno() + "  |  " + movimentacao.getValor() +"\n";

                writer.write(Linha);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Txt: "+e.getMessage());
        }
    }
}
