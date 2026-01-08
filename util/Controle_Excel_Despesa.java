package util;

import Dados.TipoDespesa;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Controle_Excel_Despesa {

    public static void Transf_Excel(ArrayList<TipoDespesa> ListaD, String Caminho){
        //Cria o arquivo Excel
        try(Workbook wbk = new XSSFWorkbook()){
            Sheet sheet = wbk.createSheet("Despesas");
            Caminho = ("Despesas.xlsx");
            
            //Criação do cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Despesa");
            header.createCell(1).setCellValue("Placa");
            header.createCell(2).setCellValue("Categoria");
            header.createCell(3).setCellValue("Descricao");
            
            int NumLinha = 1;
            
            //Percorre a Lista inteira Adicionando as informações do objeto Despesa
            for(TipoDespesa Despesa : ListaD){
                Row row = sheet.createRow(NumLinha++);
                row.createCell(0).setCellValue(Despesa.getIDTipoDespesa());
                row.createCell(1).setCellValue(Despesa.getPlaca());
                row.createCell(2).setCellValue(Despesa.getCategoria());
                row.createCell(3).setCellValue(Despesa.getDescricao());
                
            }
            
            //ajusta o tamanho da coluna do Excel
            for (int i = 0; i < 5; i++) {
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
    
    public static ArrayList<TipoDespesa> LerExcel(String Caminho){
        ArrayList<TipoDespesa> Lista = new ArrayList<>();
        
        //Abre o Arquivo para a leitura usando a String Caminho como um direcionamento do Arquivo
        try(FileInputStream fis = new FileInputStream(Caminho)){
            Workbook wbk = new XSSFWorkbook(fis);
            
            Sheet sheet = wbk.getSheetAt(0);
            //Recebe a Quantia de Linhas que tem no Excel
            int Linhas = sheet.getPhysicalNumberOfRows();
            
            //Percorre todo o Excel Recebendo o Valor em variaveis para adicionar em uma Lista
            for(int i=1; i < Linhas; i++){
                Row row = sheet.getRow(i);
                
                int IDDespesa = (int) row.getCell(0).getNumericCellValue();
                String Placa = row.getCell(1).getStringCellValue();
                String Categoria = row.getCell(2).getStringCellValue();
                String Descricao = row.getCell(3).getStringCellValue();
               
                TipoDespesa Despesa = new TipoDespesa(IDDespesa,Placa,Descricao,Categoria);
                Lista.add(Despesa);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao Ler Excel: "+e.getMessage());
        }
        
        return Lista;
    }
    
}
