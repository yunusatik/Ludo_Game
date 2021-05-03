/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import ludoclient.Client;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Yunus Emre Atik (yunusemre.atik@stu.fsm.edu.tr)
 */
public class Game extends javax.swing.JFrame {

    public static Game ThisGame;

    public Thread tmr_slider;
    public Thread sira_slider;
    public boolean thread_Kontrol = true;
    public int my_Sira = 0;
    public int rival_Sira = 0;

    Random rand;
    public int zar_Sonucu = 0;
    public int rival_Zar_Sonucu = 0;

    public boolean zar_Durumu = true;
    public boolean rival_Zar_Durumu = true;

    public int puan = 0;
    public int rival_puan = 0;

    public boolean btn1_Bitirdi = false;
    public boolean btn2_Bitirdi = false;
    public boolean btn3_Bitirdi = false;
    public boolean finish = false;

    public boolean oynama_Durumu = false;
    public boolean rival_oynama_Durumu = false;

    public ArrayList<JTextField> my_Way = new ArrayList<>();
    public ArrayList<JTextField> rival_Way = new ArrayList<>();
    public ArrayList<JButton> red_Buttons = new ArrayList<>();
    public ArrayList<JButton> blue_Buttons = new ArrayList<>();

    String[] searching = {"Searching Rival", "Searching Rival.", "Searching Rival..", "Searching Rival..."};

    public boolean isConnected = false;

    public static int oynama_Adeti = 0;
    public static int RivalSelection = 0;
    public static int MySelection = 0;
    public static int isRivalPlay = 0;

    @SuppressWarnings("empty-statement")
    public Game() {

        initComponents();
        ThisGame = this;

        this.btn_Blue1.setLocation(120, 240);
        this.btn_Blue2.setLocation(120, 240);
        this.btn_Blue3.setLocation(120, 240);

        this.txt_Send.setEditable(false);
        this.txt_Receive.setEditable(false);

        rand = new Random();

        red_Buttons.add(btn_Red1);
        red_Buttons.add(btn_Red2);
        red_Buttons.add(btn_Red3);

        blue_Buttons.add(btn_Blue1);
        blue_Buttons.add(btn_Blue2);
        blue_Buttons.add(btn_Blue3);

        my_Way.add(txtField1);
        my_Way.add(txtField2);
        my_Way.add(txtField3);
        my_Way.add(txtField4);
        my_Way.add(txtField5);
        my_Way.add(txtField6);
        my_Way.add(txtField7);
        my_Way.add(txtField8);
        my_Way.add(txtField9);
        my_Way.add(txtField10);
        my_Way.add(txtField11);
        my_Way.add(txtField12);
        my_Way.add(txtField13);
        my_Way.add(txtField14);
        my_Way.add(txtField15);
        my_Way.add(txtField16);
        my_Way.add(txtField17);
        my_Way.add(txtField18);
        my_Way.add(txtField19);
        my_Way.add(txtField20);
        my_Way.add(txtField21);
        my_Way.add(txtField22);
        my_Way.add(txtField23);
        my_Way.add(txtField24);
        my_Way.add(txtField25);
        my_Way.add(txtField26);
        my_Way.add(txtField27);
        my_Way.add(txtField28);
        my_Way.add(txtField29);
        my_Way.add(txtField30);
        my_Way.add(txtField31);
        my_Way.add(txtField32);
        my_Way.add(txtField33);
        my_Way.add(txtField34);
        my_Way.add(txtField35);
        my_Way.add(txtField37);
        my_Way.add(txtField38);
        my_Way.add(txtField39);

        rival_Way.add(txtField19);
        rival_Way.add(txtField20);
        rival_Way.add(txtField21);
        rival_Way.add(txtField22);
        rival_Way.add(txtField23);
        rival_Way.add(txtField24);
        rival_Way.add(txtField25);
        rival_Way.add(txtField26);
        rival_Way.add(txtField27);
        rival_Way.add(txtField28);
        rival_Way.add(txtField29);
        rival_Way.add(txtField30);
        rival_Way.add(txtField31);
        rival_Way.add(txtField32);
        rival_Way.add(txtField33);
        rival_Way.add(txtField34);
        rival_Way.add(txtField35);
        rival_Way.add(txtField36);
        rival_Way.add(txtField1);
        rival_Way.add(txtField2);
        rival_Way.add(txtField3);
        rival_Way.add(txtField4);
        rival_Way.add(txtField5);
        rival_Way.add(txtField6);
        rival_Way.add(txtField7);
        rival_Way.add(txtField8);
        rival_Way.add(txtField9);
        rival_Way.add(txtField10);
        rival_Way.add(txtField11);
        rival_Way.add(txtField12);
        rival_Way.add(txtField13);
        rival_Way.add(txtField14);
        rival_Way.add(txtField15);
        rival_Way.add(txtField16);
        rival_Way.add(txtField17);
        rival_Way.add(txtField40);
        rival_Way.add(txtField41);
        rival_Way.add(txtField42);

        this.btn_Blue1.setEnabled(false);
        this.btn_Blue2.setEnabled(false);
        this.btn_Blue3.setEnabled(false);

        this.btn_Red1.setEnabled(false);
        this.btn_Red2.setEnabled(false);
        this.btn_Red3.setEnabled(false);

        this.btn_Dice.setEnabled(false);
        this.btn_Send_Message.setEnabled(false);
        this.txt_Dice_Sonuc.setEditable(false);
        sira_slider = new Thread(new Runnable() {
            @Override
            public void run() {
                while (thread_Kontrol == true) {
                    try {
                        Thread.sleep(100);
                        my_Sira++;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        });
        tmr_slider = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        if (thread_Kontrol == false) {
                            thread_Kontrol = true;
                            Message msg = new Message(Message.Message_Type.Sira);
                            msg.content = my_Sira;
                            Client.Send(msg);
                        }
                        if (txt_Rival_Name.getText().equals("Rival") && ThisGame.isConnected == true) {
                            lbl_Searching.setVisible(true);
                            lbl_Searching.setText("Searching Rival.");
                            Thread.sleep(500);
                            lbl_Searching.setText("Searching Rival..");
                            Thread.sleep(500);
                            lbl_Searching.setText("Searching Rival...");
                            Thread.sleep(500);
                            lbl_Searching.setText("Searching Rival....");
                            Thread.sleep(500);
                            lbl_Searching.setText("Searching Rival.....");
                        } else if (finish == false) {
                            lbl_Searching.setVisible(false);
                        }
                        if (puan == 3) {
                            JOptionPane.showMessageDialog(ThisGame, "Tebrikler Kazandınız.");
                            Thread.sleep(3000);
                            reset();
                        }
                        if (rival_puan == 3) {
                            JOptionPane.showMessageDialog(ThisGame, "Rakip Kazandı.");
                            Thread.sleep(1000);
                            reset();
                        }

                    } catch (InterruptedException ex) {
                        System.out.println("Game Treadd'inde Hata");
                    }
                }
            }

        });
    }

    public void reset() {
        try {
            puan = 0;
            rival_puan = 0;
            btn_Join_Game.setEnabled(true);
            txt_Name_Girdi.setEditable(true);
            btn_Dice.setEnabled(false);
            finish = true;
            lbl_Searching.setText("Yeni Oyun Icin Yeniden Baglan!");
            txt_Competitors.setText("Name VS Rival");
            txt_Name.setText("Name");
            txt_Rival_Name.setText("Name");
            txt_Send_Name.setText("Name");
            txt_Receive_Name.setText("Rival");
            txt_Name_Girdi.setText("Name");
            
            txt_Send.setEditable(false);
            txt_Send.setText("");
            txt_Receive.setText("");
            lbl_Searching.setVisible(true);
            Thread.sleep(1000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Yesil60 = new javax.swing.JPanel();
        pnl_Yesil71 = new javax.swing.JPanel();
        pnl_Yesil73 = new javax.swing.JPanel();
        pnl_Yesil59 = new javax.swing.JPanel();
        pnl_Yesil67 = new javax.swing.JPanel();
        pnl_Yesil72 = new javax.swing.JPanel();
        pnl_Yesil57 = new javax.swing.JPanel();
        pnl_Yesil66 = new javax.swing.JPanel();
        pnl_Yesil68 = new javax.swing.JPanel();
        pnl_Yesil62 = new javax.swing.JPanel();
        pnl_Yesil61 = new javax.swing.JPanel();
        pnl_Yesil69 = new javax.swing.JPanel();
        pnl_Yesil58 = new javax.swing.JPanel();
        pnl_Yesil70 = new javax.swing.JPanel();
        pnl_Yesil56 = new javax.swing.JPanel();
        pnl_Yesil64 = new javax.swing.JPanel();
        pnl_Yesil63 = new javax.swing.JPanel();
        pnl_Yesil65 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTextField2 = new javax.swing.JTextField();
        panel_Yellow = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panel_Green = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        panel_Red = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        txt_Rival_Name = new javax.swing.JLabel();
        btn_Join_Game = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_Send = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_Receive = new javax.swing.JTextArea();
        btn_Send_Message = new javax.swing.JButton();
        btn_Dice = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txt_Name_Girdi = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txt_Dice_Sonuc = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        panel_Blue = new javax.swing.JPanel();
        txt_Name = new javax.swing.JLabel();
        txt_Competitors = new javax.swing.JLabel();
        btn_Blue2 = new javax.swing.JButton();
        btn_Blue3 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btn_Blue1 = new javax.swing.JButton();
        btn_Red1 = new javax.swing.JButton();
        btn_Red2 = new javax.swing.JButton();
        btn_Red3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        txt_Receive_Name = new javax.swing.JLabel();
        txt_Send_Name = new javax.swing.JLabel();
        txtField3 = new javax.swing.JTextField();
        txtField36 = new javax.swing.JTextField();
        txtField1 = new javax.swing.JTextField();
        txtField2 = new javax.swing.JTextField();
        txtField39 = new javax.swing.JTextField();
        txtField35 = new javax.swing.JTextField();
        txtField37 = new javax.swing.JTextField();
        txtField38 = new javax.swing.JTextField();
        txtField31 = new javax.swing.JTextField();
        txtField34 = new javax.swing.JTextField();
        txtField33 = new javax.swing.JTextField();
        txtField32 = new javax.swing.JTextField();
        txtField16 = new javax.swing.JTextField();
        txtField13 = new javax.swing.JTextField();
        txtField14 = new javax.swing.JTextField();
        txtField15 = new javax.swing.JTextField();
        txtField17 = new javax.swing.JTextField();
        txtField42 = new javax.swing.JTextField();
        txtField41 = new javax.swing.JTextField();
        txtField40 = new javax.swing.JTextField();
        txtField18 = new javax.swing.JTextField();
        txtField21 = new javax.swing.JTextField();
        txtField20 = new javax.swing.JTextField();
        txtField19 = new javax.swing.JTextField();
        panel55 = new javax.swing.JTextField();
        panel57 = new javax.swing.JTextField();
        txtField10 = new javax.swing.JTextField();
        txtField11 = new javax.swing.JTextField();
        txtField12 = new javax.swing.JTextField();
        txtField9 = new javax.swing.JTextField();
        panel62 = new javax.swing.JTextField();
        panel63 = new javax.swing.JTextField();
        panel64 = new javax.swing.JTextField();
        panel65 = new javax.swing.JTextField();
        txtField8 = new javax.swing.JTextField();
        panel67 = new javax.swing.JTextField();
        txtField6 = new javax.swing.JTextField();
        txtField5 = new javax.swing.JTextField();
        txtField4 = new javax.swing.JTextField();
        txtField7 = new javax.swing.JTextField();
        panel72 = new javax.swing.JTextField();
        txtField23 = new javax.swing.JTextField();
        txtField24 = new javax.swing.JTextField();
        txtField25 = new javax.swing.JTextField();
        txtField22 = new javax.swing.JTextField();
        panel77 = new javax.swing.JTextField();
        panel78 = new javax.swing.JTextField();
        panel79 = new javax.swing.JTextField();
        txtField26 = new javax.swing.JTextField();
        panel81 = new javax.swing.JTextField();
        txtField29 = new javax.swing.JTextField();
        txtField28 = new javax.swing.JTextField();
        txtField27 = new javax.swing.JTextField();
        txtField30 = new javax.swing.JTextField();
        panel87 = new javax.swing.JTextField();
        blue_Son = new javax.swing.JTextField();
        red_Son = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        lbl_Searching = new javax.swing.JLabel();

        pnl_Yesil60.setBackground(new java.awt.Color(102, 255, 102));
        pnl_Yesil60.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil60Layout = new javax.swing.GroupLayout(pnl_Yesil60);
        pnl_Yesil60.setLayout(pnl_Yesil60Layout);
        pnl_Yesil60Layout.setHorizontalGroup(
            pnl_Yesil60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil60Layout.setVerticalGroup(
            pnl_Yesil60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil71.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil71.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil71Layout = new javax.swing.GroupLayout(pnl_Yesil71);
        pnl_Yesil71.setLayout(pnl_Yesil71Layout);
        pnl_Yesil71Layout.setHorizontalGroup(
            pnl_Yesil71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil71Layout.setVerticalGroup(
            pnl_Yesil71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil73.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil73.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil73Layout = new javax.swing.GroupLayout(pnl_Yesil73);
        pnl_Yesil73.setLayout(pnl_Yesil73Layout);
        pnl_Yesil73Layout.setHorizontalGroup(
            pnl_Yesil73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil73Layout.setVerticalGroup(
            pnl_Yesil73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil59.setBackground(new java.awt.Color(102, 255, 102));
        pnl_Yesil59.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil59Layout = new javax.swing.GroupLayout(pnl_Yesil59);
        pnl_Yesil59.setLayout(pnl_Yesil59Layout);
        pnl_Yesil59Layout.setHorizontalGroup(
            pnl_Yesil59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil59Layout.setVerticalGroup(
            pnl_Yesil59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil67.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil67.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil67Layout = new javax.swing.GroupLayout(pnl_Yesil67);
        pnl_Yesil67.setLayout(pnl_Yesil67Layout);
        pnl_Yesil67Layout.setHorizontalGroup(
            pnl_Yesil67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil67Layout.setVerticalGroup(
            pnl_Yesil67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil72.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil72.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil72Layout = new javax.swing.GroupLayout(pnl_Yesil72);
        pnl_Yesil72.setLayout(pnl_Yesil72Layout);
        pnl_Yesil72Layout.setHorizontalGroup(
            pnl_Yesil72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil72Layout.setVerticalGroup(
            pnl_Yesil72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil57.setBackground(new java.awt.Color(102, 255, 102));
        pnl_Yesil57.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil57Layout = new javax.swing.GroupLayout(pnl_Yesil57);
        pnl_Yesil57.setLayout(pnl_Yesil57Layout);
        pnl_Yesil57Layout.setHorizontalGroup(
            pnl_Yesil57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil57Layout.setVerticalGroup(
            pnl_Yesil57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil66.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil66.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil66Layout = new javax.swing.GroupLayout(pnl_Yesil66);
        pnl_Yesil66.setLayout(pnl_Yesil66Layout);
        pnl_Yesil66Layout.setHorizontalGroup(
            pnl_Yesil66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil66Layout.setVerticalGroup(
            pnl_Yesil66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil68.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil68.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil68Layout = new javax.swing.GroupLayout(pnl_Yesil68);
        pnl_Yesil68.setLayout(pnl_Yesil68Layout);
        pnl_Yesil68Layout.setHorizontalGroup(
            pnl_Yesil68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil68Layout.setVerticalGroup(
            pnl_Yesil68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil62.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil62.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil62Layout = new javax.swing.GroupLayout(pnl_Yesil62);
        pnl_Yesil62.setLayout(pnl_Yesil62Layout);
        pnl_Yesil62Layout.setHorizontalGroup(
            pnl_Yesil62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil62Layout.setVerticalGroup(
            pnl_Yesil62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil61.setBackground(new java.awt.Color(102, 255, 102));
        pnl_Yesil61.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil61Layout = new javax.swing.GroupLayout(pnl_Yesil61);
        pnl_Yesil61.setLayout(pnl_Yesil61Layout);
        pnl_Yesil61Layout.setHorizontalGroup(
            pnl_Yesil61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil61Layout.setVerticalGroup(
            pnl_Yesil61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil69.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil69.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil69Layout = new javax.swing.GroupLayout(pnl_Yesil69);
        pnl_Yesil69.setLayout(pnl_Yesil69Layout);
        pnl_Yesil69Layout.setHorizontalGroup(
            pnl_Yesil69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil69Layout.setVerticalGroup(
            pnl_Yesil69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil58.setBackground(new java.awt.Color(102, 255, 102));
        pnl_Yesil58.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil58Layout = new javax.swing.GroupLayout(pnl_Yesil58);
        pnl_Yesil58.setLayout(pnl_Yesil58Layout);
        pnl_Yesil58Layout.setHorizontalGroup(
            pnl_Yesil58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil58Layout.setVerticalGroup(
            pnl_Yesil58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil70.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil70.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil70Layout = new javax.swing.GroupLayout(pnl_Yesil70);
        pnl_Yesil70.setLayout(pnl_Yesil70Layout);
        pnl_Yesil70Layout.setHorizontalGroup(
            pnl_Yesil70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil70Layout.setVerticalGroup(
            pnl_Yesil70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil56.setBackground(new java.awt.Color(102, 255, 102));
        pnl_Yesil56.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil56Layout = new javax.swing.GroupLayout(pnl_Yesil56);
        pnl_Yesil56.setLayout(pnl_Yesil56Layout);
        pnl_Yesil56Layout.setHorizontalGroup(
            pnl_Yesil56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil56Layout.setVerticalGroup(
            pnl_Yesil56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil64.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil64.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil64Layout = new javax.swing.GroupLayout(pnl_Yesil64);
        pnl_Yesil64.setLayout(pnl_Yesil64Layout);
        pnl_Yesil64Layout.setHorizontalGroup(
            pnl_Yesil64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil64Layout.setVerticalGroup(
            pnl_Yesil64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil63.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil63.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil63Layout = new javax.swing.GroupLayout(pnl_Yesil63);
        pnl_Yesil63.setLayout(pnl_Yesil63Layout);
        pnl_Yesil63Layout.setHorizontalGroup(
            pnl_Yesil63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil63Layout.setVerticalGroup(
            pnl_Yesil63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        pnl_Yesil65.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Yesil65.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnl_Yesil65Layout = new javax.swing.GroupLayout(pnl_Yesil65);
        pnl_Yesil65.setLayout(pnl_Yesil65Layout);
        pnl_Yesil65Layout.setHorizontalGroup(
            pnl_Yesil65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        pnl_Yesil65Layout.setVerticalGroup(
            pnl_Yesil65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ludo Game");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/game/images/dice.png")));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_Yellow.setBackground(new java.awt.Color(204, 255, 51));
        panel_Yellow.setBorder(new javax.swing.border.MatteBorder(null));
        panel_Yellow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_Yellow.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 87));

        getContentPane().add(panel_Yellow, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 180, 140));

        panel_Green.setBackground(new java.awt.Color(0, 255, 51));
        panel_Green.setBorder(new javax.swing.border.MatteBorder(null));
        panel_Green.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_Green.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        getContentPane().add(panel_Green, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 180, 140));

        panel_Red.setBackground(new java.awt.Color(255, 0, 0));
        panel_Red.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_Red.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_Red.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 48, -1, -1));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_Red.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_Red.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        txt_Rival_Name.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        txt_Rival_Name.setForeground(new java.awt.Color(255, 255, 255));
        txt_Rival_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_Rival_Name.setText("Rival");
        panel_Red.add(txt_Rival_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 140, -1));

        getContentPane().add(panel_Red, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 180, 50));

        btn_Join_Game.setBackground(new java.awt.Color(51, 255, 0));
        btn_Join_Game.setText("Join Game");
        btn_Join_Game.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Join_GameActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Join_Game, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 100, 30));

        txt_Send.setColumns(20);
        txt_Send.setRows(5);
        txt_Send.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Send.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_SendKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txt_Send);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 180, 90));

        txt_Receive.setColumns(20);
        txt_Receive.setRows(5);
        txt_Receive.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_Receive.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(txt_Receive);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 540, 180, 90));

        btn_Send_Message.setText("Send Message");
        btn_Send_Message.setToolTipText("");
        btn_Send_Message.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Send_MessageActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Send_Message, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 570, 130, -1));

        btn_Dice.setBackground(new java.awt.Color(51, 255, 255));
        btn_Dice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Dice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/dice.png"))); // NOI18N
        btn_Dice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DiceActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Dice, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 80, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 280, -1, -1));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txt_Name_Girdi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_Name_Girdi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_Name_Girdi.setText("Name");
        txt_Name_Girdi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_Name_GirdiKeyPressed(evt);
            }
        });
        getContentPane().add(txt_Name_Girdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 70, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        txt_Dice_Sonuc.setEditable(false);
        txt_Dice_Sonuc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txt_Dice_Sonuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 90, 30));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, -1));

        panel_Blue.setBackground(new java.awt.Color(0, 0, 255));
        panel_Blue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panel_Blue.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_Name.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        txt_Name.setForeground(new java.awt.Color(255, 255, 255));
        txt_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_Name.setText("Name");
        panel_Blue.add(txt_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, -1));

        getContentPane().add(panel_Blue, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 180, 50));

        txt_Competitors.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        txt_Competitors.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_Competitors.setText("Name VS Rival");
        getContentPane().add(txt_Competitors, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 290, -1));

        btn_Blue2.setBackground(new java.awt.Color(153, 153, 153));
        btn_Blue2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/blue_piyon.png"))); // NOI18N
        btn_Blue2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Blue2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Blue2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, 30, 20));

        btn_Blue3.setBackground(new java.awt.Color(153, 153, 153));
        btn_Blue3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/blue_piyon.png"))); // NOI18N
        btn_Blue3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Blue3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Blue3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 30, 20));

        jPanel10.setBackground(new java.awt.Color(0, 0, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 180, 50));

        jPanel12.setBackground(new java.awt.Color(0, 0, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 30, 70));

        jPanel13.setBackground(new java.awt.Color(0, 0, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 20, -1));

        btn_Blue1.setBackground(new java.awt.Color(153, 153, 153));
        btn_Blue1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/blue_piyon.png"))); // NOI18N
        btn_Blue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Blue1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Blue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 30, 20));

        btn_Red1.setBackground(new java.awt.Color(153, 153, 153));
        btn_Red1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/red_piyon.png"))); // NOI18N
        btn_Red1.setSelected(true);
        btn_Red1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Red1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Red1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 420, 30, 20));

        btn_Red2.setBackground(new java.awt.Color(153, 153, 153));
        btn_Red2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/red_piyon.png"))); // NOI18N
        btn_Red2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Red2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Red2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, 30, 20));

        btn_Red3.setBackground(new java.awt.Color(153, 153, 153));
        btn_Red3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/game/images/red_piyon.png"))); // NOI18N
        btn_Red3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Red3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Red3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 30, 20));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 178, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 180, 50));

        jPanel14.setBackground(new java.awt.Color(255, 0, 0));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 20, -1));

        jPanel18.setBackground(new java.awt.Color(255, 0, 0));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 30, -1));

        txt_Receive_Name.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        txt_Receive_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_Receive_Name.setText("Rival");
        getContentPane().add(txt_Receive_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 510, 100, -1));

        txt_Send_Name.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 18)); // NOI18N
        txt_Send_Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_Send_Name.setText("Name");
        getContentPane().add(txt_Send_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 100, -1));

        txtField3.setEditable(false);
        txtField3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 30, 20));

        txtField36.setEditable(false);
        txtField36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 30, 20));

        txtField1.setEditable(false);
        txtField1.setBackground(new java.awt.Color(0, 0, 255));
        txtField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 30, 20));

        txtField2.setEditable(false);
        txtField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 30, 20));

        txtField39.setEditable(false);
        txtField39.setBackground(new java.awt.Color(0, 0, 255));
        txtField39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField39, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 30, 20));

        txtField35.setEditable(false);
        txtField35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField35, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 30, 20));

        txtField37.setEditable(false);
        txtField37.setBackground(new java.awt.Color(0, 0, 255));
        txtField37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField37, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 30, 20));

        txtField38.setEditable(false);
        txtField38.setBackground(new java.awt.Color(0, 0, 255));
        txtField38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField38, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 30, 20));

        txtField31.setEditable(false);
        txtField31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField31, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 30, 20));

        txtField34.setEditable(false);
        txtField34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField34, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 30, 20));

        txtField33.setEditable(false);
        txtField33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 30, 20));

        txtField32.setEditable(false);
        txtField32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField32, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 30, 20));

        txtField16.setEditable(false);
        txtField16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 30, 20));

        txtField13.setEditable(false);
        txtField13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 30, 20));

        txtField14.setEditable(false);
        txtField14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 240, 30, 20));

        txtField15.setEditable(false);
        txtField15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 30, 20));

        txtField17.setEditable(false);
        txtField17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 30, 20));

        txtField42.setEditable(false);
        txtField42.setBackground(new java.awt.Color(255, 0, 0));
        txtField42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField42, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 280, 30, 20));

        txtField41.setEditable(false);
        txtField41.setBackground(new java.awt.Color(255, 0, 0));
        txtField41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField41, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 30, 20));

        txtField40.setEditable(false);
        txtField40.setBackground(new java.awt.Color(255, 0, 0));
        txtField40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField40, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 30, 20));

        txtField18.setEditable(false);
        txtField18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 30, 20));

        txtField21.setEditable(false);
        txtField21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 30, 20));

        txtField20.setEditable(false);
        txtField20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 30, 20));

        txtField19.setEditable(false);
        txtField19.setBackground(new java.awt.Color(255, 0, 0));
        txtField19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 30, 20));

        panel55.setEditable(false);
        panel55.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 30, 20));

        panel57.setEditable(false);
        panel57.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 30, 20));

        txtField10.setEditable(false);
        txtField10.setBackground(new java.awt.Color(204, 255, 51));
        txtField10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 120, 30, 20));

        txtField11.setEditable(false);
        txtField11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 30, 20));

        txtField12.setEditable(false);
        txtField12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 30, 20));

        txtField9.setEditable(false);
        txtField9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 30, 20));

        panel62.setEditable(false);
        panel62.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 30, 20));

        panel63.setEditable(false);
        panel63.setBackground(new java.awt.Color(204, 255, 51));
        panel63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 30, 20));

        panel64.setEditable(false);
        panel64.setBackground(new java.awt.Color(204, 255, 51));
        panel64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 30, 20));

        panel65.setEditable(false);
        panel65.setBackground(new java.awt.Color(204, 255, 51));
        panel65.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 30, 20));

        txtField8.setEditable(false);
        txtField8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 30, 20));

        panel67.setEditable(false);
        panel67.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 30, 20));

        txtField6.setEditable(false);
        txtField6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 30, 20));

        txtField5.setEditable(false);
        txtField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 30, 20));

        txtField4.setEditable(false);
        txtField4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 30, 20));

        txtField7.setEditable(false);
        txtField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 30, 20));

        panel72.setEditable(false);
        panel72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 30, 20));

        txtField23.setEditable(false);
        txtField23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 400, 30, 20));

        txtField24.setEditable(false);
        txtField24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 440, 30, 20));

        txtField25.setEditable(false);
        txtField25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 480, 30, 20));

        txtField22.setEditable(false);
        txtField22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 30, 20));

        panel77.setEditable(false);
        panel77.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 30, 20));

        panel78.setEditable(false);
        panel78.setBackground(new java.awt.Color(0, 255, 51));
        panel78.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 30, 20));

        panel79.setEditable(false);
        panel79.setBackground(new java.awt.Color(0, 255, 51));
        panel79.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 30, 20));

        txtField26.setEditable(false);
        txtField26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 480, 30, 20));

        panel81.setEditable(false);
        panel81.setBackground(new java.awt.Color(0, 255, 51));
        panel81.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 360, 30, 20));

        txtField29.setEditable(false);
        txtField29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField29, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 400, 30, 20));

        txtField28.setEditable(false);
        txtField28.setBackground(new java.awt.Color(0, 255, 51));
        txtField28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 30, 20));

        txtField27.setEditable(false);
        txtField27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, 30, 20));

        txtField30.setEditable(false);
        txtField30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(txtField30, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 30, 20));

        panel87.setEditable(false);
        panel87.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(panel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 30, 20));

        blue_Son.setEditable(false);
        blue_Son.setBackground(new java.awt.Color(0, 0, 255));
        getContentPane().add(blue_Son, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 70, 100));

        red_Son.setEditable(false);
        red_Son.setBackground(new java.awt.Color(255, 0, 0));
        getContentPane().add(red_Son, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 70, 100));

        jPanel15.setBackground(new java.awt.Color(0, 0, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 20, -1));

        jPanel16.setBackground(new java.awt.Color(255, 0, 0));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, 20, -1));

        jPanel17.setBackground(new java.awt.Color(255, 0, 0));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 30, -1));

        lbl_Searching.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 12)); // NOI18N
        lbl_Searching.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        getContentPane().add(lbl_Searching, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Blue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Blue1ActionPerformed
        boolean a = false;
        boolean yeme_kontrol = false;
        int kontrol = 0;
        JTextField gecici = null;
        Iterator<JTextField> panel = my_Way.iterator();
        if (this.btn_Blue1.getX() == 50 && this.btn_Blue1.getY() == 140) {
            gecici = panel.next();
        } else {
            while (panel.hasNext()) {
                gecici = panel.next();
                System.out.println(gecici.getX() + "," + gecici.getY());
                if (gecici.getX() == this.btn_Blue1.getX() && gecici.getY() == this.btn_Blue1.getY()) {
                    for (int j = 0; j < zar_Sonucu; j++) {
                        System.out.println("Zarici: " + gecici.getX() + "," + gecici.getY());
                        if (panel.hasNext()) {
                            gecici = panel.next();
                        } else {
                            kontrol = zar_Sonucu - j - 1;
                            a = true;
                            break;
                        }
                    }
                    break;
                }
            }

        }
        if (!((btn_Blue2.getX() == gecici.getX() && btn_Blue2.getY() == gecici.getY()) || (btn_Blue3.getX() == gecici.getX() && btn_Blue3.getY() == gecici.getY())) && kontrol == 0) {
            if (btn_Red1.getX() == gecici.getX() && btn_Red1.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red1.setLocation(410, 420);
                Message msg3 = new Message(Message.Message_Type.RivalBtn1);
                msg3.content = 1;
                Client.Send(msg3);
            }
            if (btn_Red2.getX() == gecici.getX() && btn_Red2.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red2.setLocation(450, 420);
                Message msg3 = new Message(Message.Message_Type.RivalBtn2);
                msg3.content = 1;
                Client.Send(msg3);
            }
            if (btn_Red3.getX() == gecici.getX() && btn_Red3.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red3.setLocation(490, 420);
                Message msg3 = new Message(Message.Message_Type.RivalBtn3);
                msg3.content = 1;
                Client.Send(msg3);
            }
            oynama_Durumu = false;
            if (a == true) {
                puan++;
                btn1_Bitirdi = true;
                this.btn_Blue1.setLocation(240, 250);
            } else {

                this.btn_Blue1.setLocation(gecici.getX(), gecici.getY());
            }

            Message msg = new Message(Message.Message_Type.Btn1);
            msg.content = 1;
            Client.Send(msg);

            Message msg2 = new Message(Message.Message_Type.RivalPlay);
            msg2.content = MySelection;
            Client.Send(msg2);

            this.btn_Blue1.setEnabled(false);
            this.btn_Blue2.setEnabled(false);
            this.btn_Blue3.setEnabled(false);

            if (yeme_kontrol == true) {
                this.btn_Dice.setEnabled(true);
            }
            if (zar_Durumu == true) {
                this.btn_Dice.setEnabled(true);
            } else if (yeme_kontrol == false) {
                Message msg1 = new Message(Message.Message_Type.DiceDurum);
                Client.Send(msg1);
            }

        } else {
            oynama_Durumu = true;

            this.btn_Blue1.setEnabled(false);
            if (!this.btn_Blue1.isEnabled() && !this.btn_Blue2.isEnabled() && !this.btn_Blue3.isEnabled()) {
                Message msg1 = new Message(Message.Message_Type.DiceDurum);
                Client.Send(msg1);
            } else {
                JOptionPane.showMessageDialog(this, "Baska bir piyonunu oynamayı dene");
            }

        }
        Message msg4 = new Message(Message.Message_Type.OynamaDurum);
        msg4.content = oynama_Durumu;
        Client.Send(msg4);


    }//GEN-LAST:event_btn_Blue1ActionPerformed

    private void btn_Send_MessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Send_MessageActionPerformed
        Message msg = new Message(Message.Message_Type.Text);
        msg.content = txt_Send.getText();
        Client.Send(msg);
        txt_Send.setText("");
    }//GEN-LAST:event_btn_Send_MessageActionPerformed

    private void btn_Join_GameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Join_GameActionPerformed

        Client.Start("127.0.0.1", 2000);

        this.btn1_Bitirdi = false;
        this.btn2_Bitirdi = false;
        this.btn3_Bitirdi = false;
        finish = false;

        if (this.isConnected == true) {
            this.txt_Competitors.setText(txt_Name_Girdi.getText() + " VS .......");
            this.txt_Name.setText(txt_Name_Girdi.getText());
            this.txt_Send_Name.setText(txt_Name_Girdi.getText());
        }


    }//GEN-LAST:event_btn_Join_GameActionPerformed

    private void btn_DiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DiceActionPerformed
        zar_Sonucu = rand.nextInt(6) + 1;
        //zar_Sonucu = 6;
        this.btn_Dice.setEnabled(false);
        if (zar_Sonucu == 6) {
            //this.btn_Dice.setEnabled(true);
            zar_Durumu = true;
            if (btn1_Bitirdi == false) {
                this.btn_Blue1.setEnabled(true);
            }
            if (btn2_Bitirdi == false) {
                this.btn_Blue2.setEnabled(true);
            }
            if (btn3_Bitirdi == false) {
                this.btn_Blue3.setEnabled(true);
            }

        } else {
            zar_Durumu = false;
            if (btn_Blue1.getY() != 140 && btn1_Bitirdi == false) {
                this.btn_Blue1.setEnabled(true);
            }
            if (btn_Blue2.getY() != 140 && btn2_Bitirdi == false) {
                this.btn_Blue2.setEnabled(true);
            }
            if (btn_Blue3.getY() != 140 && btn3_Bitirdi == false) {
                this.btn_Blue3.setEnabled(true);
            }
            if (!this.btn_Blue1.isEnabled() && !this.btn_Blue2.isEnabled() && !this.btn_Blue3.isEnabled()) {
                Message msg1 = new Message(Message.Message_Type.DiceDurum);
                Client.Send(msg1);
            }

        }

        Message msg = new Message(Message.Message_Type.Dice);
        msg.content = zar_Sonucu;
        Client.Send(msg);

        String dice_Sonuc = String.valueOf(zar_Sonucu);
        txt_Dice_Sonuc.setText(dice_Sonuc);

    }//GEN-LAST:event_btn_DiceActionPerformed

    private void btn_Blue3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Blue3ActionPerformed
        boolean a = false;
        int kontrol = 0;
        boolean yeme_kontrol = false;
        JTextField gecici = null;
        Iterator<JTextField> panel = my_Way.iterator();
        if (this.btn_Blue3.getX() == 130 && this.btn_Blue3.getY() == 140) {
            gecici = panel.next();
        } else {
            while (panel.hasNext()) {
                gecici = panel.next();
                System.out.println(gecici.getX() + "," + gecici.getY());
                if (gecici.getX() == this.btn_Blue3.getX() && gecici.getY() == this.btn_Blue3.getY()) {
                    for (int j = 0; j < zar_Sonucu; j++) {
                        System.out.println("Zarici: " + gecici.getX() + "," + gecici.getY());
                        if (panel.hasNext()) {
                            gecici = panel.next();
                        } else {
                            kontrol = zar_Sonucu - j - 1;
                            a = true;
                            break;
                        }
                    }
                    break;
                }
            }

            Game.MySelection = 1;
        }
        if (!((btn_Blue1.getX() == gecici.getX() && btn_Blue1.getY() == gecici.getY()) || (btn_Blue2.getX() == gecici.getX() && btn_Blue2.getY() == gecici.getY())) && kontrol == 0) {
            if (btn_Red1.getX() == gecici.getX() && btn_Red1.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red1.setLocation(410, 420);
                Message msg3 = new Message(Message.Message_Type.RivalBtn1);
                msg3.content = 1;
                Client.Send(msg3);
            }
            if (btn_Red2.getX() == gecici.getX() && btn_Red2.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red2.setLocation(450, 420);
                Message msg3 = new Message(Message.Message_Type.RivalBtn2);
                msg3.content = 1;
                Client.Send(msg3);
            }
            if (btn_Red3.getX() == gecici.getX() && btn_Red3.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red3.setLocation(490, 420);

                Message msg3 = new Message(Message.Message_Type.RivalBtn3);
                msg3.content = 1;
                Client.Send(msg3);
            }
            if (a == true) {
                puan++;
                btn3_Bitirdi = true;
                this.btn_Blue3.setLocation(240, 310);
            } else {

                this.btn_Blue3.setLocation(gecici.getX(), gecici.getY());
            }
            Message msg = new Message(Message.Message_Type.Btn3);
            msg.content = 1;
            Client.Send(msg);

            Message msg2 = new Message(Message.Message_Type.RivalPlay);
            msg2.content = MySelection;
            Client.Send(msg2);

            this.btn_Blue1.setEnabled(false);
            this.btn_Blue2.setEnabled(false);
            this.btn_Blue3.setEnabled(false);
            if (yeme_kontrol == true) {
                this.btn_Dice.setEnabled(true);
            }
            if (zar_Durumu == true) {
                this.btn_Dice.setEnabled(true);
            } else if (yeme_kontrol == false) {
                Message msg1 = new Message(Message.Message_Type.DiceDurum);
                Client.Send(msg1);
            }
        } else {
            oynama_Durumu = true;

            this.btn_Blue3.setEnabled(false);
            if (!this.btn_Blue1.isEnabled() && !this.btn_Blue2.isEnabled() && !this.btn_Blue3.isEnabled()) {
                Message msg1 = new Message(Message.Message_Type.DiceDurum);
                Client.Send(msg1);
            } else {
                JOptionPane.showMessageDialog(this, "Baska bir piyonunu oynamayı dene");
            }
        }
    }//GEN-LAST:event_btn_Blue3ActionPerformed

    private void btn_Blue2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Blue2ActionPerformed
        boolean a = false;
        int kontrol = 0;
        boolean yeme_kontrol = false;
        JTextField gecici = null;
        Iterator<JTextField> panel = my_Way.iterator();
        if (this.btn_Blue2.getX() == 90 && this.btn_Blue2.getY() == 140) {
            gecici = panel.next();

        } else {
            while (panel.hasNext()) {
                gecici = panel.next();
                System.out.println(gecici.getX() + "," + gecici.getY());
                if (gecici.getX() == this.btn_Blue2.getX() && gecici.getY() == this.btn_Blue2.getY()) {
                    for (int j = 0; j < zar_Sonucu; j++) {
                        System.out.println("Zarici: " + gecici.getX() + "," + gecici.getY());
                        if (panel.hasNext()) {
                            gecici = panel.next();
                        } else {
                            kontrol = zar_Sonucu - j - 1;
                            a = true;
                            break;
                        }
                    }
                    break;
                }
            }

            Game.MySelection = 1;
        }
        if (!((btn_Blue1.getX() == gecici.getX() && btn_Blue1.getY() == gecici.getY()) || (btn_Blue3.getX() == gecici.getX() && btn_Blue3.getY() == gecici.getY())) && kontrol == 0) {
            if (btn_Red1.getX() == gecici.getX() && btn_Red1.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red1.setLocation(410, 420);
                Message msg3 = new Message(Message.Message_Type.RivalBtn1);
                msg3.content = 1;
                Client.Send(msg3);
            }
            if (btn_Red2.getX() == gecici.getX() && btn_Red2.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red2.setLocation(450, 420);
                Message msg3 = new Message(Message.Message_Type.RivalBtn2);
                msg3.content = 1;
                Client.Send(msg3);
            }
            if (btn_Red3.getX() == gecici.getX() && btn_Red3.getY() == gecici.getY()) {
                yeme_kontrol = true;
                this.btn_Red3.setLocation(490, 420);
                Message msg3 = new Message(Message.Message_Type.RivalBtn3);
                msg3.content = 1;
                Client.Send(msg3);
            }
            if (a == true) {
                puan++;
                btn2_Bitirdi = true;
                this.btn_Blue2.setLocation(240, 280);
            } else {

                this.btn_Blue2.setLocation(gecici.getX(), gecici.getY());
            }
            Message msg = new Message(Message.Message_Type.Btn2);
            msg.content = 1;
            Client.Send(msg);

            Message msg2 = new Message(Message.Message_Type.RivalPlay);
            msg2.content = MySelection;
            Client.Send(msg2);

            this.btn_Blue1.setEnabled(false);
            this.btn_Blue2.setEnabled(false);
            this.btn_Blue3.setEnabled(false);
            if (yeme_kontrol == true) {
                this.btn_Dice.setEnabled(true);
            }
            if (zar_Durumu == true) {
                this.btn_Dice.setEnabled(true);
            } else if (yeme_kontrol == false) {
                Message msg1 = new Message(Message.Message_Type.DiceDurum);
                Client.Send(msg1);
            }
        } else {
            oynama_Durumu = true;

            this.btn_Blue2.setEnabled(false);
            if (!this.btn_Blue1.isEnabled() && !this.btn_Blue2.isEnabled() && !this.btn_Blue3.isEnabled()) {
                Message msg1 = new Message(Message.Message_Type.DiceDurum);
                Client.Send(msg1);
            } else {
                JOptionPane.showMessageDialog(this, "Baska bir piyonunu oynamayı dene");
            }

        }
    }//GEN-LAST:event_btn_Blue2ActionPerformed
    public void btn_Red1_Click(java.awt.event.ActionEvent evt) {
        btn_Red1ActionPerformed(evt);
    }
    private void btn_Red1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Red1ActionPerformed
        JTextField gecici = null;
        boolean kontrol = false;
        Iterator<JTextField> panel = rival_Way.iterator();
        if (this.btn_Red1.getX() == 410 && this.btn_Red1.getY() == 420) {
            gecici = panel.next();
        } else {
            while (panel.hasNext()) {
                gecici = panel.next();
                System.out.println(gecici.getX() + "," + gecici.getY());
                if (gecici.getX() == this.btn_Red1.getX() && gecici.getY() == this.btn_Red1.getY()) {
                    for (int j = 0; j < rival_Zar_Sonucu; j++) {
                        System.out.println("Zar ici: " + gecici.getX() + "," + gecici.getY());
                        if (panel.hasNext()) {
                            gecici = panel.next();
                        } else {
                            kontrol = true;
                            break;
                        }
                    }
                    break;
                }
            }

        }
        if (kontrol == true) {
            rival_puan++;
            this.btn_Red1.setLocation(310, 250);
        } else {
            this.btn_Red1.setLocation(gecici.getX(), gecici.getY());
        }


    }//GEN-LAST:event_btn_Red1ActionPerformed
    public void btn_Red2_Click(java.awt.event.ActionEvent evt) {
        btn_Red2ActionPerformed(evt);
    }
    private void btn_Red2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Red2ActionPerformed
        JTextField gecici = null;
        boolean kontrol = false;
        Iterator<JTextField> panel = rival_Way.iterator();
        if (this.btn_Red2.getX() == 450 && this.btn_Red2.getY() == 420) {
            gecici = panel.next();

        } else {
            while (panel.hasNext()) {
                gecici = panel.next();
                System.out.println(gecici.getX() + "," + gecici.getY());
                if (gecici.getX() == this.btn_Red2.getX() && gecici.getY() == this.btn_Red2.getY()) {
                    for (int j = 0; j < rival_Zar_Sonucu; j++) {
                        System.out.println("Zar ici: " + gecici.getX() + "," + gecici.getY());
                        if (panel.hasNext()) {
                            gecici = panel.next();
                        } else {
                            kontrol = true;
                            break;
                        }
                    }
                    break;
                }
            }
        }
        if (kontrol == true) {
            rival_puan++;
            this.btn_Red2.setLocation(310, 280);
        } else {
            this.btn_Red2.setLocation(gecici.getX(), gecici.getY());
        }

    }//GEN-LAST:event_btn_Red2ActionPerformed
    public void btn_Red3_Click(java.awt.event.ActionEvent evt) {
        btn_Red3ActionPerformed(evt);
    }
    private void btn_Red3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Red3ActionPerformed
        JTextField gecici = null;
        boolean kontrol = false;
        Iterator<JTextField> panel = rival_Way.iterator();
        if (this.btn_Red3.getX() == 490 && this.btn_Red3.getY() == 420) {
            gecici = panel.next();
        } else {
            while (panel.hasNext()) {
                gecici = panel.next();
                System.out.println(gecici.getX() + "," + gecici.getY());
                if (gecici.getX() == this.btn_Red3.getX() && gecici.getY() == this.btn_Red3.getY()) {
                    for (int j = 0; j < rival_Zar_Sonucu; j++) {
                        System.out.println("Zar ici: " + gecici.getX() + "," + gecici.getY());
                        if (panel.hasNext()) {
                            gecici = panel.next();
                        } else {
                            kontrol = true;
                            break;
                        }
                    }
                    break;
                }
            }

        }
        if (kontrol == true) {
            rival_puan++;
            this.btn_Red3.setLocation(310, 310);
        } else {
            this.btn_Red3.setLocation(gecici.getX(), gecici.getY());
        }
    }//GEN-LAST:event_btn_Red3ActionPerformed

    private void txt_Name_GirdiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_Name_GirdiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ActionEvent evt1 = null;
            btn_Join_GameActionPerformed(evt1);
        }
    }//GEN-LAST:event_txt_Name_GirdiKeyPressed

    private void txt_SendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SendKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ActionEvent evt1 = null;
            btn_Send_MessageActionPerformed(evt1);
        }
    }//GEN-LAST:event_txt_SendKeyPressed

    /**
     * @param args the command line argumlbl_Sari
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game().setVisible(true);
                Game.ThisGame.tmr_slider.start();
                Game.ThisGame.sira_slider.start();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField blue_Son;
    public javax.swing.JButton btn_Blue1;
    public javax.swing.JButton btn_Blue2;
    public javax.swing.JButton btn_Blue3;
    public javax.swing.JButton btn_Dice;
    public javax.swing.JButton btn_Join_Game;
    public javax.swing.JButton btn_Red1;
    public javax.swing.JButton btn_Red2;
    public javax.swing.JButton btn_Red3;
    public javax.swing.JButton btn_Send_Message;
    private javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    public javax.swing.JPanel jPanel12;
    public javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbl_Searching;
    private javax.swing.JTextField panel55;
    private javax.swing.JTextField panel57;
    private javax.swing.JTextField panel62;
    private javax.swing.JTextField panel63;
    private javax.swing.JTextField panel64;
    private javax.swing.JTextField panel65;
    private javax.swing.JTextField panel67;
    private javax.swing.JTextField panel72;
    private javax.swing.JTextField panel77;
    private javax.swing.JTextField panel78;
    private javax.swing.JTextField panel79;
    private javax.swing.JTextField panel81;
    private javax.swing.JTextField panel87;
    public javax.swing.JPanel panel_Blue;
    public javax.swing.JPanel panel_Green;
    public javax.swing.JPanel panel_Red;
    public javax.swing.JPanel panel_Yellow;
    private javax.swing.JPanel pnl_Yesil56;
    private javax.swing.JPanel pnl_Yesil57;
    private javax.swing.JPanel pnl_Yesil58;
    private javax.swing.JPanel pnl_Yesil59;
    private javax.swing.JPanel pnl_Yesil60;
    private javax.swing.JPanel pnl_Yesil61;
    private javax.swing.JPanel pnl_Yesil62;
    private javax.swing.JPanel pnl_Yesil63;
    private javax.swing.JPanel pnl_Yesil64;
    private javax.swing.JPanel pnl_Yesil65;
    private javax.swing.JPanel pnl_Yesil66;
    private javax.swing.JPanel pnl_Yesil67;
    private javax.swing.JPanel pnl_Yesil68;
    private javax.swing.JPanel pnl_Yesil69;
    private javax.swing.JPanel pnl_Yesil70;
    private javax.swing.JPanel pnl_Yesil71;
    private javax.swing.JPanel pnl_Yesil72;
    private javax.swing.JPanel pnl_Yesil73;
    private javax.swing.JTextField red_Son;
    private javax.swing.JTextField txtField1;
    private javax.swing.JTextField txtField10;
    private javax.swing.JTextField txtField11;
    private javax.swing.JTextField txtField12;
    private javax.swing.JTextField txtField13;
    private javax.swing.JTextField txtField14;
    private javax.swing.JTextField txtField15;
    private javax.swing.JTextField txtField16;
    private javax.swing.JTextField txtField17;
    private javax.swing.JTextField txtField18;
    private javax.swing.JTextField txtField19;
    private javax.swing.JTextField txtField2;
    private javax.swing.JTextField txtField20;
    private javax.swing.JTextField txtField21;
    private javax.swing.JTextField txtField22;
    private javax.swing.JTextField txtField23;
    private javax.swing.JTextField txtField24;
    private javax.swing.JTextField txtField25;
    private javax.swing.JTextField txtField26;
    private javax.swing.JTextField txtField27;
    private javax.swing.JTextField txtField28;
    private javax.swing.JTextField txtField29;
    private javax.swing.JTextField txtField3;
    private javax.swing.JTextField txtField30;
    private javax.swing.JTextField txtField31;
    private javax.swing.JTextField txtField32;
    private javax.swing.JTextField txtField33;
    private javax.swing.JTextField txtField34;
    private javax.swing.JTextField txtField35;
    private javax.swing.JTextField txtField36;
    private javax.swing.JTextField txtField37;
    private javax.swing.JTextField txtField38;
    private javax.swing.JTextField txtField39;
    private javax.swing.JTextField txtField4;
    private javax.swing.JTextField txtField40;
    private javax.swing.JTextField txtField41;
    private javax.swing.JTextField txtField42;
    private javax.swing.JTextField txtField5;
    private javax.swing.JTextField txtField6;
    private javax.swing.JTextField txtField7;
    private javax.swing.JTextField txtField8;
    private javax.swing.JTextField txtField9;
    public javax.swing.JLabel txt_Competitors;
    public javax.swing.JTextField txt_Dice_Sonuc;
    public javax.swing.JLabel txt_Name;
    public javax.swing.JTextField txt_Name_Girdi;
    public javax.swing.JTextArea txt_Receive;
    public javax.swing.JLabel txt_Receive_Name;
    public javax.swing.JLabel txt_Rival_Name;
    public javax.swing.JTextArea txt_Send;
    public javax.swing.JLabel txt_Send_Name;
    // End of variables declaration//GEN-END:variables
}
