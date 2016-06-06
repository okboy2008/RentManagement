/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author s145633
 */
public class GlobalVariable {
    public static ArrayList<Resident> RESIDENTS = new ArrayList<>();
    public static ArrayList<Fee> FEES = new ArrayList<>();
    public static int ROW;
    public static int COLUMN;
    // public static int NEW_FEE_COUNTER = 0;
    
    // file path
    public static String FEE_FILE_PATH = "C://fee.csv";
    public static String RESIDENT_FILE_PATH = "C://residents.csv";
    public static String[] REPORT_FILES = {"fee_report.csv", "rent_report.csv"};
    public static JTable[] REPORT_TABLES;
    
    // fee table
    public static final int TYPE = 0;
    public static final int AMOUNT = 1;
    public static final int PAID_BY = 2;
    public static final int PAYER = 3;
    
    // residents table
    public static final int NAME = 0;
    public static final int RENT = 1;
}
