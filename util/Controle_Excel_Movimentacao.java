package util;

import Dados.Movimentacao;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Controle_Excel_Movimentacao {
    
    public static void Transf_Excel(ArrayList<Movimentacao> Lista, String Caminho){
        //Cria o arquivo Excel
        try(Workbook wbk = new XSSFWorkbook()){
            Sheet sheet = wbk.createSheet("Movimentacao");
            Caminho = ("Movimentacao.xlsx");
            
            //Criação do cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Movimentacao");
            header.createCell(1).setCellValue("ID Veiculo");
            header.createCell(2).setCellValue("ID Despesa");
            header.createCell(3).setCellValue("Placa");
            header.createCell(4).setCellValue("Categoria");
            header.createCell(5).setCellValue("Descricao");
            header.createCell(6).setCellValue("Dia");
            header.createCell(7).setCellValue("Mes");
            header.createCell(8).setCellValue("Ano");
            header.createCell(9).setCellValue("Valor");
            
            int NumLinha = 1;
            
            //Percorre a Lista inteira Adicionando as informações do objeto Movimentacao
            for(Movimentacao movimentacao : Lista){
                Row row = sheet.createRow(NumLinha++);
                
                row.createCell(0).setCellValue(movimentacao.getIDMovimentacao());
                row.createCell(1).setCellValue(movimentacao.getIDVeiculo());
                row.createCell(2).setCellValue(movimentacao.getIDTipoDespesa());
                row.createCell(3).setCellValue(movimentacao.getPlaca());
                row.createCell(4).setCellValue(movimentacao.getCategoria());
                row.createCell(5).setCellValue(movimentacao.getDescricao());
                row.createCell(6).setCellValue(movimentacao.getDia());
                row.createCell(7).setCellValue(movimentacao.getMes());
                row.createCell(8).setCellValue(movimentacao.getAno());
                row.createCell(9).setCellValue(movimentacao.getValor());
            }
            
            //ajusta o tamanho da coluna do Excel
            for (int i = 0; i < 11; i++) {
                sheet.autoSizeColumn(i);
            }
            
            //Caso o Arquivo ja exista irá so reescreve e caso não exista irá apenas criar 
            //o Arquivo usando a String Caminho
            try (FileOutputStream fos = new FileOutputStream(Caminho)) {
                wbk.write(fos);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao Exportar Excel: "+e.getMessage());
        }
    }
    
    //Variaveis De Calculo
    public static double ValorTotalMes;
    public static double ValorTotalMesComb;
    public static double ValorIPVAAno;
    
    //Variaveis De Valores
    public static int MesValor;
    public static int MesValorComb;
    public static int AnoIPVA;
    
    public static ArrayList<Movimentacao> LerExcel(String Caminho){
        ArrayList<Movimentacao> Lista = new ArrayList<>();
        
        //Abre o Arquivo para a leitura usando a String Caminho como um direcionamento do Arquivo
        try(FileInputStream fis = new FileInputStream(Caminho)){
            Workbook wbk = new XSSFWorkbook(fis);
            
            Sheet sheet = wbk.getSheetAt(0);
            //Recebe a Quantia de Linhas que tem no Excel
            int Linhas = sheet.getPhysicalNumberOfRows();
            
            //Percorre todo o Excel Recebendo o Valor em variaveis para adicionar em uma Lista
            for(int i=1; i < Linhas; i++){
                Row row = sheet.getRow(i);
                
                int IDMovimentacao = (int) row.getCell(0).getNumericCellValue();
                int IDVeiculo = (int) row.getCell(1).getNumericCellValue();
                int IDDespesa = (int) row.getCell(2).getNumericCellValue();
                String Placa = row.getCell(3).getStringCellValue();
                String Categoria = row.getCell(4).getStringCellValue();
                String Descricao = row.getCell(5).getStringCellValue();
                int Dia = (int) row.getCell(6).getNumericCellValue();
                int Mes = (int) row.getCell(7).getNumericCellValue();
                int Ano = (int) row.getCell(8).getNumericCellValue();
                double Valor = (double) row.getCell(9).getNumericCellValue();
                
                Movimentacao movimentacao = new Movimentacao(IDMovimentacao,IDVeiculo,IDDespesa,Placa,Categoria,Descricao,Dia,Mes,Ano,Valor);
                Lista.add(movimentacao);
                
                //Faz as Verificações para os Calculos dos Relatorios
                if(MesValor == Mes){
                    ValorTotalMes += Valor;
                }
                
                if(MesValorComb == Mes && Categoria.equals("Combustível")){
                    ValorTotalMesComb += Valor;
                }
                
                if(AnoIPVA == Ano && Categoria.equals("IPVA")){
                    ValorIPVAAno += Valor;
                }
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao Ler Excel: "+e.getMessage());
        }
        
        return Lista;
    }
    
}
