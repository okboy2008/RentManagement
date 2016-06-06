/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatype;

import java.util.ArrayList;

/**
 *
 * @author s145633
 */
public class Resident {
    public String name;
    public int basic_rent;
    public ArrayList<Fee> extra_fee;
    public ArrayList<Fee> paid;
    
    public Resident(){
        name = "";
        basic_rent = 0;
        extra_fee = new ArrayList<>();
        paid = new ArrayList<>();
    }
    
    public void removeExtraFee(String name){
        int index = -1;
        for(int i = 0;i<this.extra_fee.size();i++){
            if(this.extra_fee.get(i).name.equals(name)){
                index = i;
                break;
            }
        }
        if(index != -1){
            this.extra_fee.remove(index);
        }
    }
    
    public void removePaid(String name){
        int index = -1;
        for(int i = 0;i<this.paid.size();i++){
            if(this.paid.get(i).name.equals(name)){
                index = i;
                break;
            }
        }
        if(index != -1){
            this.paid.remove(index);
        }
    }
    
    public boolean hasFeeToPay(String name){
        int index = -1;
        for(int i = 0;i<this.extra_fee.size();i++){
            if(this.extra_fee.get(i).name.equals(name)){
                index = i;
                break;
            }
        }
        if(index == -1){
            return false;
        }
        return true;
    }
    
    public boolean hasFeePaid(String name){
        int index = -1;
        for(int i = 0;i<this.paid.size();i++){
            if(this.paid.get(i).name.equals(name)){
                index = i;
                break;
            }
        }
        if(index == -1){
            return false;
        }
        return true;
    }
}
