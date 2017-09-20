/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantdealer;

/**
 *
 * @author Nishi
 */
public class OrderProcessing implements Runnable{
    OrderClass order;
    int locationCharge;
    float tax;
    
    OrderProcessing(OrderClass order){
        this.order=order;
        this.locationCharge=500;
        this.tax=(float) 1.8;
    }
    public void run(){
        int creditNumber=this.order.getCardNo();
        //ConversionData c=new ConversionData();
       
       if(creditNumber>=5000 && creditNumber<=7000){
            int finalPrice=this.order.getAmount()*order.getUnit_price()+this.locationCharge;
            finalPrice+=finalPrice*this.tax;
            System.out.println("final price for dealer "+this.order.getSenderId()+" is:"+finalPrice+ " Number of cars: "+ this.order.getAmount());
        }
       else{
           System.out.println("Credit card is not valid");
       }
    }
}
