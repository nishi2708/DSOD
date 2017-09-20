/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantdealer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nishi
 */
public class Dealer implements PriceCutInterface,Runnable{
    int D_id;
    MultiCellBuffer buffer;
    int unitPrice;
    ConversionData convert=new ConversionData();
    OrderClass order=new OrderClass();
    boolean flag=false;
    boolean flag2 = true;
    @Override
    public void notifyToDealers(int price){
        
        unitPrice= price;
        flag=true;
        //System.out.println("In dealer....setting flag true");
       
    }

    public void setD_id(int D_id) {
        this.D_id = D_id;
    }

    public int getD_id() {
        return D_id;
    }
    Dealer(int id){
       buffer=MultiCellBuffer.getInstance();
       D_id=id;  
      // order=new OrderClass();
    }
    public void killDealer(){
        flag2=false;
    }
    public void run(){
       
        while(flag2){
            try {
                //System.out.println("flag value "+flag);
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Dealer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(flag){
               
                order.setSenderId(D_id);
                //Calculate number of cars
                Random rand=new Random(); 
                order.setAmount(rand.nextInt(10)+1);
                order.setCardNo(5000+D_id);
                order.setUnit_price(unitPrice);
                String encoded=convert.encode(order);
                
                try {
                    buffer.dealerToCell(encoded);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Dealer.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               flag=false;
            }
          
        }
        
    }
}
