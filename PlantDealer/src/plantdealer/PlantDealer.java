/*
 * Nishi Shah
 * Assignment 2
 */
package plantdealer;

import java.util.Scanner;

/**
 *
 * @author Nishi
 */
public class PlantDealer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Plant p1=new Plant(1);
        System.out.println("Please enter p value after which you want to kill the plant");
        Scanner in=new Scanner(System.in);
        int num=in.nextInt();
        p1.killAfterPriceCut=num;
        
         for(int i=0;i<5;i++){
            Dealer dealer=new Dealer(i);
            Thread thread = new Thread(dealer);
            thread.start();
            p1.addDealer(dealer);
        }
        
        Thread plantThread=new Thread(p1);
        plantThread.start();
        
       
    }
    
    
    
}
