/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import datatype.Fee;
import datatype.Resident;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.*;

/**
 *
 * @author s145633
 */
public class LoadCSVdata {
    public static final String PATH = "C://";
    public static final String RESIDENTS_FILE = "residents.csv";
    public static final String FEE_FILE = "fee.csv";
    public File fee_file = new File(PATH+"fee_tmp.csv"); 
    
    CSVParser parser = null;
    File file;
    // private ArrayList<Team> teams;
    
    public LoadCSVdata(File path){
        //this.file = path;
    }

    public LoadCSVdata() {
        
    }
    
    public void LoadFeeDataToJTable(JTable t, String path){
        try{
            file = new File(path);
            parser = CSVParser.parse(file, Charset.forName("UTF-8"), CSVFormat.DEFAULT);
            DefaultTableModel model = (DefaultTableModel) t.getModel();
            model.setRowCount(0);
            for(CSVRecord c:parser){
                if(c.getRecordNumber() == 1)
                    continue;
                model.addRow(new Object[]{c.get(datatype.GlobalVariable.TYPE), c.get(datatype.GlobalVariable.AMOUNT), c.get(datatype.GlobalVariable.PAID_BY), c.get(datatype.GlobalVariable.PAYER)});
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void LoadData(){
        // ArrayList<Resident> residents = new ArrayList<>();
        try{
            // READ FEE TABLE
            file = new File(PATH + FEE_FILE);
            System.out.println(file.toPath());
            parser = CSVParser.parse(file, Charset.forName("UTF-8"), CSVFormat.DEFAULT);
            Fee new_fee;
            Resident new_res;
            for (CSVRecord c : parser) {
                
                // skip first record
                if(c.getRecordNumber() == 1)
                    continue;
                
                System.out.println(c.get(datatype.GlobalVariable.TYPE)+", "+c.get(datatype.GlobalVariable.AMOUNT)+", "+c.get(datatype.GlobalVariable.PAID_BY)+", "+c.get(datatype.GlobalVariable.PAYER));
                String tmp =c.get(datatype.GlobalVariable.PAYER);
                 //String payers;
                
                String[] payers = tmp.split(";");
                
                System.out.println(payers.length);
                new_fee = new Fee();
                new_fee.name = c.get(datatype.GlobalVariable.TYPE);
                new_fee.amount = Double.valueOf(c.get(datatype.GlobalVariable.AMOUNT));
                new_fee.number_of_payer = payers.length;
                
                System.out.println("new fee: "+ new_fee.name);
                
                //datatype.GlobalVariable.FEES
                
                int res_index;
                // add payer
                
                for (int i=0;i<payers.length;i++) {
                    res_index = -1;
                    for (Resident r : datatype.GlobalVariable.RESIDENTS) {
                        if (r.name.equals(payers[i])) {
                            res_index = datatype.GlobalVariable.RESIDENTS.indexOf(r);
                            break;
                        }
                    }
                    
                    System.out.println(res_index);
                    
                    
                    // new resident found
                    if(res_index == -1){
                        new_res = new Resident();
                        
                        // System.out.println(payers[i]);
                        new_res.name = payers[i];
                        datatype.GlobalVariable.RESIDENTS.add(new_res);
                        res_index = datatype.GlobalVariable.RESIDENTS.indexOf(new_res);
                    }
                    
                    // insert payer's fee
//                    if(datatype.GlobalVariable.RESIDENTS.get(res_index).extra_fee.size()>=1)
//                        System.out.println(datatype.GlobalVariable.RESIDENTS.get(res_index).extra_fee.get(datatype.GlobalVariable.RESIDENTS.get(res_index).extra_fee.size()-1).name);
                    datatype.GlobalVariable.RESIDENTS.get(res_index).extra_fee.add(new_fee);
//                    System.out.println(datatype.GlobalVariable.RESIDENTS.get(res_index).extra_fee.get(datatype.GlobalVariable.RESIDENTS.get(res_index).extra_fee.size()-1).name);
                }
                
                // add paid by
                
                res_index = -1;
                for (Resident r : datatype.GlobalVariable.RESIDENTS) {
                    if (r.name.equals(c.get(datatype.GlobalVariable.PAID_BY))) {
                        res_index = datatype.GlobalVariable.RESIDENTS.indexOf(r);
                        break;
                    }
                }

                // new resident found
                if (res_index == -1) {
                    new_res = new Resident();

                    new_res.name = c.get(datatype.GlobalVariable.PAID_BY);
                    datatype.GlobalVariable.RESIDENTS.add(new_res);
                    res_index = datatype.GlobalVariable.RESIDENTS.indexOf(new_res);
                }

                // insert paid
                datatype.GlobalVariable.RESIDENTS.get(res_index).paid.add(new_fee);

            }
            
            file = new File(PATH + RESIDENTS_FILE);
            // READ RESIDENT TABLE
            parser = CSVParser.parse(file, Charset.forName("UTF-8"), CSVFormat.DEFAULT);
            for (CSVRecord c : parser) {
                
                // skip first record
                if(c.getRecordNumber() == 1)
                    continue;
                
                System.out.println(c.get(datatype.GlobalVariable.NAME)+", "+c.get(datatype.GlobalVariable.RENT));
                int res_index = -1;
                for (Resident r : datatype.GlobalVariable.RESIDENTS) {
                    if (r.name.equals(c.get(datatype.GlobalVariable.NAME))) {
                        res_index = datatype.GlobalVariable.RESIDENTS.indexOf(r);
                        break;
                    }
                }
                datatype.GlobalVariable.RESIDENTS.get(res_index).basic_rent = Integer.parseInt(c.get(datatype.GlobalVariable.RENT));
                
            }
            
//            for(int i=0;i<datatype.GlobalVariable.RESIDENTS.size();i++){
//                System.out.println(datatype.GlobalVariable.RESIDENTS.get(i).name);
//                for(Fee f:datatype.GlobalVariable.RESIDENTS.get(i).extra_fee){
//                    System.out.println(f.name);
//                }
//            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        //return residents;
    }
}

