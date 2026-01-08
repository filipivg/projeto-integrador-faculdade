package util;

import Dados.Veiculo;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Controle_Excel_Veiculo {
    
    public static void Transf_Excel(ArrayList<Veiculo> Lista, String Caminho){
        //Cria o arquivo Excel
        try(Workbook wbk = new XSSFWorkbook()){
            Sheet sheet = wbk.createSheet("Veiculo");
            Caminho = ("Veiculo.xlsx");
            
            //Criação do cabeçalho
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID Veiculo");
            header.createCell(1).setCellValue("Placa");
            header.createCell(2).setCellValue("Marca");
            header.createCell(3).setCellValue("Modelo");
            header.createCell(4).setCellValue("Ano De Fabricacao");
            header.createCell(5).setCellValue("Estado");
            
            int NumLinha = 1;
            
            //Percorre a Lista inteira Adicionando as informações do objeto veiculo
            for(Veiculo veiculo : Lista){
                Row row = sheet.createRow(NumLinha++);
                row.createCell(0).setCellValue(veiculo.getIDVeiculo());
                row.createCell(1).setCellValue(veiculo.getPlaca());
                row.createCell(2).setCellValue(veiculo.getMarca());
                row.createCell(3).setCellValue(veiculo.getModelo());
                row.createCell(4).setCellValue(veiculo.getAnoFab());
                row.createCell(5).setCellValue(veiculo.getEstadoDoVeiculoTexto());
            }
            
            //ajusta o tamanho da coluna do Excel
            for (int i = 0; i < 7; i++) {
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
    
    public static ArrayList<Veiculo> LerExcel(String Caminho){
        ArrayList<Veiculo> Lista = new ArrayList<>();
        
        //Abre o Arquivo para a leitura usando a String Caminho como um direcionamento do Arquivo
        try(FileInputStream fis = new FileInputStream(Caminho)){
            Workbook wbk = new XSSFWorkbook(fis);
            
            Sheet sheet = wbk.getSheetAt(0);
            //Recebe a Quantia de Linhas que tem no Excel
            int Linhas = sheet.getPhysicalNumberOfRows();
            
            //Percorre todo o Excel Recebendo o Valor em variaveis para adicionar em uma Lista
            for(int i=1; i < Linhas; i++){
                Row row = sheet.getRow(i);
                
                int IDVeiculo = (int) row.getCell(0).getNumericCellValue();
                String Placa = row.getCell(1).getStringCellValue();
                String Marca = row.getCell(2).getStringCellValue();
                String Modelo = row.getCell(3).getStringCellValue();
                int AnoFab = (int) row.getCell(4).getNumericCellValue();
                String EstadoDoVeicTxt = row.getCell(5).getStringCellValue();
                
                //Caso o EstadoDoVeicTxt receber "Ativo" ele envia "true" para o EstadoDoVeicB 
                //e caso seja "Inativo" receberá "false"
                boolean EstadoDoVeicB = EstadoDoVeicTxt.equalsIgnoreCase("Ativo"); 
                
                Veiculo veiculo = new Veiculo(IDVeiculo,Placa,Marca,Modelo,AnoFab,EstadoDoVeicB);
                Lista.add(veiculo);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao Ler Excel: "+e.getMessage());
        }
        
        return Lista;
    }
}