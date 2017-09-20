/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantdealer;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nishi
 */
public class Plant implements Runnable {
    int myID;
    int killAfterPriceCut=5;
    int prevPrice;
    OrderClass order;
    MultiCellBuffer buffer;
    ConversionData convert=new ConversionData();
    List<Dealer> dealers=new ArrayList<Dealer>();
    boolean flag;
    PricingModel model=new PricingModel();
	Plant(int id) {	 	// constructor with 1 parameter
		myID = id;
                prevPrice=0;
                flag=true;
                buffer=MultiCellBuffer.getInstance();      
                order=new OrderClass();
	}
        
        public void run() {
            String orderStr="";
            
            //After 5 price cuts the Plant thread will be killed.
                while(killAfterPriceCut>0){
                    int price=model.getPrice();
                System.out.println("Waiting for Price cut");   
                try {
                    sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                    if(prevPrice>price){
                        System.out.println("Price has been cut");
                        flag=false;
                        killAfterPriceCut--;
                        //Calls event handlers in dealers which have subscribed to the event
                        for(Dealer dealer:dealers){
                            dealer.notifyToDealers(price);
                            System.out.println("Notified to Dealer :"+dealer.getD_id());
                        }
                        
                      
                        int i=0;
                        while(i<5){
                            
                            try {
                                orderStr=buffer.cellToPlant();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ConversionData convert1=new ConversionData();
                            //Creating a new thread to process the order
                            OrderProcessing op=new OrderProcessing(convert1.decode(orderStr));
                            Thread processOrder=new Thread(op);
                            processOrder.start();               
                            i++;
                        }
                        try {
                            sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Plant.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println("Plant has finished processing orders for PriceCut "+(5-killAfterPriceCut));
                        
                        
                        
                        
                    }
                    
                    prevPrice=price;
                    
                    
                    
                    
                }
                for(Dealer dealer:dealers)
                    dealer.killDealer();
		
	}
        public void addDealer(Dealer d){
            dealers.add(d);
        }
}