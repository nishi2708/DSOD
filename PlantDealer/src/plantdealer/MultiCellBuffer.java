/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plantdealer;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
/**
 *
 * @author Nishi
 */
public class MultiCellBuffer {
    private static MultiCellBuffer instance=null;
    protected MultiCellBuffer(){
        
    }
    public static MultiCellBuffer getInstance(){
        if(instance==null){
            instance=new MultiCellBuffer();
        }
        return instance;
    }
    static String[] inputValue=new String[2];
    static int counter=0;
    Semaphore dealerWrite=new Semaphore(2);
    Semaphore plantReader=new Semaphore(0);
    
    public void dealerToCell(String st) throws InterruptedException{
        dealerWrite.acquire();
        
        synchronized(inputValue){
             
           
            inputValue[this.counter]=st;
            this.counter++;
            plantReader.release();
        }
        
        /*
        dealerWrite.acquire();
        this.counter++;
        synchronized(inputValue){
            inputValue[this.counter-1]=st;
            plantReader.release();
        }*/
    }
    
    public String cellToPlant() throws InterruptedException{
        
        plantReader.acquire();
        
        //System.out.println("Did read value : "+(this.counter-1));
        synchronized(inputValue[this.counter-1]){
            
            String val=inputValue[this.counter-1];
            
            this.counter--;
            dealerWrite.release();
            //System.out.println("READ "+val);
            return val;
        }
        
    }
    
}
