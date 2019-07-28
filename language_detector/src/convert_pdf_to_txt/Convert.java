/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convert_pdf_to_txt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

/**
 *
 * @author haicm
 */
public class Convert {
    public static String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    
    public static String readPDFfile(String fileName) throws IOException{
        try (PDDocument document = PDDocument.load(new File(fileName))) {

            document.getClass();

            if (!document.isEncrypted()) {
			
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);
                
                return pdfFileInText;
            }
        }
        return "";
    }
    
    public static String getAbstractPath(String full_path, String file_extension){
        int index = 0;
        for (int i = full_path.length() - 1; i > 0; i --){
            if (full_path.charAt(i) == '.'){
                index = i;
                break;
            }
        }
        String path = "";
        for (int i = 0; i < index + 1; i ++){
            path += full_path.charAt(i);
        }
        path += file_extension;
        return path;
    }
    
    public void convertPdfToTxt(String path_file) throws IOException{
        String extension = Convert.getFileExtension(path_file).toLowerCase();
        if (extension.equals("pdf")){
            String path_txt = Convert.getAbstractPath(path_file, "txt");

            String txt = Convert.readPDFfile(path_file);
            String newTxt = Convert.processText(txt);

            try {
                PrintWriter out = new PrintWriter(path_txt);
                out.println(newTxt);     
                out.close();
                
                System.out.println("Convert success!");
                System.out.println("Saved Path: " + path_txt);
            } catch (Exception e) {
                System.out.println("Convert error!!");
            }
        }else{
            System.out.println("wrong extension!!");
        }
    }
    
    public static String processText(String text){

        return text.replace("-\r\n", "");
    }
    
    public static void main(String[] args) throws IOException {
        String fileName = "C:\\Users\\DucPC\\Documents\\NetBeansProjects\\Sort\\test2.pdf";
        Convert convert = new Convert();
        
        convert.convertPdfToTxt(fileName);
    }
}
