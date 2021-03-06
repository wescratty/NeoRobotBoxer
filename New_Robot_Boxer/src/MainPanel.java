
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;


public class MainPanel extends JPanel {

    public MainPanel() {


        JLabel  fatigueLblP1, strengthLblP1, agilityLblP1, fatigueLblP2, strengthLblP2, agilityLblP2, time, timer, splash;
        JPanel b1LabelPanel = new JPanel();
        JPanel b2LabelPanel = new JPanel();
        JPanel gameLabelPanel = new JPanel();
        JPanel subPanel = new JPanel();


        time = new JLabel("Time left in bought: ");
        timer = new JLabel("5:00");
        splash = new JLabel("client message");
        fatigueLblP1 = new JLabel(" P1 Fatigue: 0   ");
        strengthLblP1 = new JLabel(" P1 Strength: 0  ");
        agilityLblP1 = new JLabel("  P1 Agility: 0   ");
        fatigueLblP2 = new JLabel("  P2 Fatigue: 0   ");
        strengthLblP2 = new JLabel(" P2 Strength: 0  ");
        agilityLblP2 = new JLabel("  P2 Agility: 0   ");

        b1LabelPanel.add(fatigueLblP1);
        b1LabelPanel.add(strengthLblP1);
        b1LabelPanel.add(agilityLblP1);
        b1LabelPanel.setLayout(new BoxLayout(b1LabelPanel, BoxLayout.PAGE_AXIS));
        b1LabelPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        b2LabelPanel.add(fatigueLblP2);
        b2LabelPanel.add(strengthLblP2);
        b2LabelPanel.add(agilityLblP2);
        b2LabelPanel.setLayout(new BoxLayout(b2LabelPanel, BoxLayout.PAGE_AXIS));
        b2LabelPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        gameLabelPanel.add(time);
        gameLabelPanel.add(timer);
        gameLabelPanel.add(splash);
        gameLabelPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        subPanel.setLayout(new BorderLayout());
        subPanel.add(b1LabelPanel, BorderLayout.EAST);
        subPanel.add(b2LabelPanel, BorderLayout.WEST);
        subPanel.add(gameLabelPanel, BorderLayout.CENTER);
        subPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLoweredBevelBorder());
        setBackground(Color.LIGHT_GRAY);

        add(subPanel, BorderLayout.SOUTH);

        create();

    }

    public void create(){
        Dialog boxer1Stats = Dialog.getInstance();
        String b1Sts = boxer1Stats.getBoxerStats();


        Boxer _boxer1 = new Boxer();
        Boxer _boxer2 = new Boxer();

        ObservaBoxing obs1 = new ObservaBoxing(_boxer1);
//        ObservaBoxing obs2 = new ObservaBoxing(_boxer2);

        _boxer1.register(obs1);
        _boxer2.register(obs1);
        Runnable game = new Game(_boxer1,_boxer2);

        Thread boxer1Thread = new Thread(game);
        int b1Identifier = System.identityHashCode(boxer1Thread);
        Thread boxer2Thread = new Thread(game);
        int b2Identifier = System.identityHashCode(boxer2Thread);


        _boxer1.setid(b1Identifier,1);
        _boxer2.setid(b2Identifier, 2);
        System.out.print(_boxer1.getid());


        boxer1Thread.start();
        boxer2Thread.start();
//        try {
//            boxer1Thread.join();
//            boxer2Thread.join();
//        }catch(Exception e){}


        //TODO +create() pushImageState(String state)<<no idea on formatting yet>>

    }




    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int top = 100;
        int width = 800;
        int poleDiag = 80;
        Point nw= new Point(100,100);


        //create ring
        g.drawLine(nw.X(),nw.Y(),top-poleDiag,top-poleDiag);
        g.drawLine(width,top,width+poleDiag,top-poleDiag);
        g.drawLine(top,width,top-poleDiag,width+poleDiag);
        g.drawLine(width, width, width + poleDiag, width + poleDiag);


        g.drawLine(top,top,top,width);
        g.drawLine(top,top,width,top);
        g.drawLine(width,top,width,width);
        g.drawLine(top, width, width, width);


        g.drawArc(20,20,20,20,20,360);
        g.drawArc(20,860,20,20,20,360);
        g.drawArc(860,860,20,20,20,360);
        g.drawArc(860,20,20,20,20,360);


        g.drawArc(30,5,840,50,180,180);  //top rope
        g.drawArc(5,30,50,840,270,180);  // left rope

        g.drawArc(845,30,50,840,90,180);  //right rope
        g.drawArc(30, 845, 840, 50, 0, 180);


        // Boxer 1
        g.setColor(Color.BLUE);
        g.fillArc(200, 400, 50, 50, 0, 360);
        g.fillArc(200+30, 400+50, 30, 30, 0, 360);
        g.fillArc(200+30, 400-30, 30, 30, 0, 360);

        // Boxer 1
        g.setColor(Color.RED);
        g.fillArc(600, 400, 50, 50, 0, 360);
        g.fillArc(600, 400+50, 30, 30, 0, 360);
        g.fillArc(600, 400-30, 30, 30, 0, 360);

    }

}
