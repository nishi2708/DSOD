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
public class OrderClass {

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getCardNo() {
        return cardNo;
    }

    public int getAmount() {
        return amount;
    }

    public int getUnit_price() {
        return unit_price;
    }
    int senderId; //identity of sender: thread name or thread id
    int cardNo;
    int amount;
    int unit_price;
    
}
