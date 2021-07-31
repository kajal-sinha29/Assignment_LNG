
package assignment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.*;



public class Assignment {

   
    public static void main(String[] args) throws Exception {
        
        Scanner scf= new Scanner(System.in);
        System.out.println("Enter the path of source file with file name..");  
        String file_s= scf.next(); 
        
        System.out.println("Enter the path of Destination path with file name");
        String file_d = scf.next();
        
        System.out.println("Enter the path for log file with file name");
        String file_l = scf.next();
        
        File src_file = new File(file_s);
        File dest_file = new File(file_d);
        FileReader fin = new FileReader(src_file);  
        FileWriter fout = new FileWriter(dest_file, true);  
        BufferedWriter buffer = new BufferedWriter(fout);
          
        Logger logger = Logger.getLogger(Assignment.class.getName()); 
         Pattern regex = Pattern.compile("[A-Za-z0-9|]");
        
        String str ="";
        int lengthArr, lengthSubArr;
        
        
        
        try{
            
             FileHandler fh = new FileHandler(file_l);  
             logger.addHandler(fh);
             SimpleFormatter formatter = new SimpleFormatter();  
             fh.setFormatter(formatter); 
             logger.setUseParentHandlers(false);
            
            
            
            Scanner sc = new Scanner(src_file);
            
             while(sc.hasNextLine()){
                 String fileData = sc.nextLine();
                
             }
                 
                 if(dest_file.exists()){
                     
                     logger.info("File already exist!!!");
                 }
                 else{
                     
                     dest_file.createNewFile();
                     System.out.println(dest_file.getName()+" created");
                 }
                 
                 int i;
                while ((i = fin.read()) != -1) {    
                     str += (char)i;
                     
                  }
                
                String[] arrOfStr = str.split("\\r?\\n");
               
                
                 lengthArr = arrOfStr.length;    
                for(int s=0; s<lengthArr; s++){
                    
                    
                    boolean result = Pattern.matches("[A-Za-z0-9|]*", arrOfStr[s]);
                    
                    if(result==true){

                    
                    String[] subArrOfStr = arrOfStr[s].split("\\|");
                    
                    
                    lengthSubArr = subArrOfStr.length;
                    if(lengthSubArr!=6){
                        logger.info("The data elements are not same on LINE NO : "+s);  
                        continue;
                    }
                    else{
                        
                    String[] subArrOfStrNew = new String[lengthSubArr];
                   
                    subArrOfStrNew[0]=subArrOfStr[5];
                    subArrOfStrNew[1]=subArrOfStr[0];
                    subArrOfStrNew[2]=subArrOfStr[1];
                    subArrOfStrNew[3]=subArrOfStr[2];
                    subArrOfStrNew[4]=subArrOfStr[4];
                    subArrOfStrNew[5]=subArrOfStr[3];
                    
                    float pr=Float.parseFloat(subArrOfStrNew[5]);
                    double profit = pr/74.25;
                    
                    String country = subArrOfStrNew[1].toUpperCase();
                    String profitS = Double.toString(profit);
                    
                    subArrOfStrNew[5] = profitS;
                    subArrOfStrNew[1] = country;
                    
                  
                  String joinedString = String.join("| ", subArrOfStrNew);
                  
                  arrOfStr[s]=joinedString;
                  buffer.write(arrOfStr[s]);
                    buffer.write("\n");
                     
                }
                 }
                    else{
                        logger.info("It contains other delimeter on LINE NO :"+s);
                    }  
                }  
                buffer.close();
                                
                
        }//try ending
        catch(FileNotFoundException e){
            
            logger.info("File not found ");
            e.printStackTrace();
        }
        
        catch(IOException e){
            logger.info("Can not create a file");
            e.printStackTrace();
        }
       
     
}
    
   
}