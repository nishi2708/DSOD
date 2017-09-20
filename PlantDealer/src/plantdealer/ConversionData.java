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
public class ConversionData {
  OrderClass order=new OrderClass();
  String strOrder;
  public String encode(OrderClass order){
      StringBuilder sb=new StringBuilder();
      sb.append(order.getCardNo()+":"+order.getSenderId()+":"+order.getUnit_price()+":"+order.getAmount());
      return sb.toString();
  }
  public OrderClass decode(String st){
      String[] attr=st.split(":");
    
      order.setCardNo(Integer.valueOf(attr[0]));
      order.setSenderId(Integer.valueOf(attr[1]));
      order.setUnit_price(Integer.valueOf(attr[2]));
      order.setAmount(Integer.valueOf(attr[3]));
      return order;
      
  }
}
