/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

/**
 *
 * @author s145633
 */
public class Fee {
    public String name;
    public double amount;
    public int number_of_payer;
    
    public Fee(){
        name = "";
        amount = 0.0;
        number_of_payer = 0;
    }
    
    public String amount_toString(){
        return String.format("%.2f", amount);
    }
}
