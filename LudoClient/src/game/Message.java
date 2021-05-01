/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Yunus Emre Atik (yunusemre.atik@stu.fsm.edu.tr)
 */
public class Message implements java.io.Serializable {

    public static enum Message_Type {
        Name, Dice, RivalConnected, Text, RivalPlay, Btn1, Btn2, Btn3,RivalBtn1, RivalBtn2, RivalBtn3
        , DiceDurum, OynamaDurum
    }

    public Message_Type type;
    public Object content;

    public Message(Message_Type t) {
        this.type = t;
    }

}
