/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package plantdealer;
import java.util.Random;
/**
 *
 * @author Nishi
 */
public class PricingModel {
    Random rand;
    PricingModel(){
        rand=new Random();
    }
    
    int getPrice(){
        return rand.nextInt(500000-50000)+50000;
    }
    
}
