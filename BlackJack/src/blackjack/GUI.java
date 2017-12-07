 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;


import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author lenovo
 */
public class GUI extends JFrame implements MouseMotionListener {
    ArrayList <PlayingCard> Cards=new ArrayList<PlayingCard>();
    String DirectoryPath="C:\\Users\\lenovo\\Documents\\NetBeansProjects\\BlackJack\\Cards";
    JLabel XCor=new JLabel("XCor");
    JLabel YCor=new JLabel("YCor");
    ArrayList <PlayingCard> player1 =new ArrayList<PlayingCard>();
    ArrayList <PlayingCard> dealer =new ArrayList<PlayingCard>();
    ArrayList<PlayingCard> target= new ArrayList<PlayingCard>();
    String[] options = new String[] {"Play Again!", "Close"};
     String[] ace = new String[] {"one ","eleven"};
    JButton hit=new JButton ("HIT");
    JButton stand=new JButton ("STAND");
    JLabel Pscore=new JLabel();
    JLabel dscore=new JLabel();
    JLabel dealerScore=new JLabel("dealer score:");
    JLabel playerScore=new JLabel("player score:");
    JLabel backCard =new JLabel();
    boolean turn=true;
   
    int r=2;
//    int x2=500;
//    int y2=30;
//    int x=500;
//    int y=600;
    public GUI()
    {
        setTitle("");
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setSize(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
        setLayout(null);
        try {
            setContentPane (new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\BlackJack\\Cards\\green-screen.png")))));
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
         backCard.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\lenovo\\Documents\\NetBeansProjects\\BlackJack\\Cards\\back.png").getImage().getScaledInstance(110, 160, Image.SCALE_DEFAULT)));
        
       XCor.setBounds(10,840,120,30);
       YCor.setBounds(130,840,120,30);
//        add(XCor);
//        add(YCor);
        playerScore.setBounds(200,750,120,30);
        Pscore.setBounds(300,750,120,30);
        dealerScore.setBounds(200,200,120,30);
        dscore.setBounds(300,200,120,30);
        hit.setBounds(1400, 450, 180, 70);
        stand.setBounds(1600, 450, 180, 70);
        backCard.setBounds(600,26,110,160);
        add(hit);
        add(stand);
        add(Pscore);
        add(playerScore);
        add(dscore);
        add(dealerScore);
        add(backCard);
       // setLayout(new FlowLayout());
     
        int x=0;
        int y=10;
        
        int elementsinrow=0;
        for(int m =0 ;m<4;m++){
              System.out.println(m);
           
        for (int i=1;i<=13;i++)
        { 
           
             PlayingCard p=new PlayingCard();
             if(i>=10)
                 p.Value=10;
             else
                 p.Value=i;
             p.CardName=i+p.shapes[m];
             p.Shape=PlayingCard.ShapeTypes.hearts;
             p.ImageName=DirectoryPath+"\\"+i+"_of_"+p.shapes[m]+".png";
             Cards.add(p);
           
             JLabel j=new JLabel();
             j.setIcon(new ImageIcon(new ImageIcon(Cards.get(r-2).ImageName).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
//             add(j);
//             j.addMouseMotionListener(this); 
//             j.setBounds(x,y, 100, 150);
          
            
            p.Holder=j;
            x+=100;
             
            add(p.Holder);
           
            elementsinrow++;
            if (elementsinrow>13)
            {
                x=0;
                y+=150;
                elementsinrow=0;
            }
              r++;
        }
      System.out.println(Cards.size());
        
        
         
            
            
        }   
       distrbute();
       showFirstcard();
       showpCards();
       
       
      

//        int x2=800;
//        int y2=30;
//        for(int i =0 ;i<3;i++){
//            JLabel l=new JLabel();
//            l.setIcon(new ImageIcon(new ImageIcon(player1.get(i).ImageName).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
//            add(l);
//            l.addMouseMotionListener(this); 
//            l.setBounds(x,y, 100, 150);
//            x+=100;
//            JLabel t=new JLabel();
//            t.setIcon(new ImageIcon(new ImageIcon(player2.get(i).ImageName).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
//            add(t);
//            t.addMouseMotionListener(this); 
//            t.setBounds(x2,y2, 100, 150);
//            x2+=100;
//        }
           
           myHandler handler =new myHandler();
           hit.addActionListener(handler);
           stand.addActionListener(handler);
        
        //private class myHandler implements ActionListener{

      //  @Override
      //  public void actionPerformed(ActionEvent ae) {
  //          Hit(player1);
//            showcards();
      //  }
         
    // } 
//            private class myHandler implements ActionListener{
//              
//               
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//        }
//                
//            }
//       
       
       
        }
    
    
    
    private class myHandler implements ActionListener{
        int response;
        @Override
        public void actionPerformed(ActionEvent ae) {
          Object buttonPressed =ae.getSource();
          
          target=player1;
            if(buttonPressed.equals(hit)&&turn==true){
               // target= player1;
                Hit(player1);
                showpCards();
                //calculate(player1);
                checkAce(player1);
                Pscore.setText(Integer.toString(calculate(player1)));
                
                if(calculate(player1)>21)
                     checkWinner();
                

            }
            if(buttonPressed.equals(hit)&&turn==false&&calculate(dealer)<17){
                
              
                
                    Hit(dealer);
                    showdcard();
                    checkAce(dealer);
                    dscore.setText(Integer.toString(calculate(dealer)));
                    if(calculate(dealer)>=17)
                       checkWinner();
                    
             
            }
           
      
        
             if(buttonPressed.equals(stand)){
                 turn=false; 
                 showdcard();
                 
                  backCard.hide();
                 
                checkAce(dealer);
                dscore.setText(Integer.toString(calculate(dealer)));
                if(calculate(dealer)>=17)
                    checkWinner();
                
                 // show 2nd card and calculate score
               } 
//           if(response==0){
//               dealer.clear();
//               player1.clear();
//           }

           
    }}

    @Override
    public void mouseDragged(MouseEvent e) {
        XCor.setText(""+e.getX());
        YCor.setText(""+e.getXOnScreen());
        for (PlayingCard p:Cards)
        {
        if (p.Holder.equals(e.getSource()))
        {
            //JOptionPane.showMessageDialog(null, "Label 0");
           // int i=Cards.indexOf(e.getSource());
            p.Holder.setBounds(e.getXOnScreen()-10,e.getYOnScreen()-20, 100, 150);
           // XCor.setText(""+p.Value);
            
        }
        }
        
      
        //System.out.println("E"+e.getSource().toString());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    

   
     public void distrbute (){
         
         Random no1=new Random();
         Random no2=new Random();
         int num1;
         int num2; 
         int response;
           
        for(int i=0;i<2;i++){
            num1=no1.nextInt(Cards.size());
            player1.add(Cards.get(num1));
           Cards.remove(num1);
           
            
             num2=no2.nextInt(Cards.size());
            dealer.add(Cards.get(num2));
            Cards.remove(num2);
          
        }
         checkAce(player1);
         Pscore.setText(Integer.toString(calculate(player1)));
     }
     
 
     public int  calculate(ArrayList<PlayingCard> player){
       int sum=0;
         for(int i =0;i<player.size();i++){
             sum+=player.get(i).Value;
             
         }
     
                return sum;
     }
     public void Hit (ArrayList<PlayingCard> player){
         
         Random no1=new Random();
         int num1;
        
           
       
        num1=no1.nextInt(Cards.size());
        player.add(Cards.get(num1));
       Cards.remove(num1);
        }
     public void showpCards(){
        
         int x=500;
         int y=600;
        for(int i =0 ;i<player1.size();i++){
           
            JLabel l=new JLabel();
            l.setIcon(new ImageIcon(new ImageIcon(player1.get(i).ImageName).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
            add(l);
            l.addMouseMotionListener(this); 
            l.setBounds(x,y, 100, 150);
            x+=100;
        }
       checkAce(player1);
        

        }
     public void showFirstcard(){
        int x2=500;
        int y2=30;
          for(int i =0 ;i<1;i++){

            JLabel t=new JLabel();
            t.setIcon(new ImageIcon(new ImageIcon(dealer.get(i).ImageName).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
            add(t);
            t.addMouseMotionListener(this); 
            t.setBounds(x2,y2, 100, 150);
            x2+=100;
       }}
     public void showdcard(){
        int x2=500;
        int y2=30;
          for(int i =0 ;i<dealer.size();i++){

            JLabel t=new JLabel();
            t.setIcon(new ImageIcon(new ImageIcon(dealer.get(i).ImageName).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT)));
            add(t);
            t.addMouseMotionListener(this); 
            t.setBounds(x2,y2, 100, 150);
            x2+=100;
       }}
 
     private void checkWinner() {
        int response;
        
        if      (calculate(player1) >  21)  {
            //JOptionPane.showMessageDialog(null,"player busts-dealer wins");
           response = JOptionPane.showOptionDialog(null, "player busts-dealer wins", "Winner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
            
        }
        else if (calculate(dealer)  >  21)   {        
           // JOptionPane.showMessageDialog(null,"Dealer busts-player wins");
            response = JOptionPane.showOptionDialog(null, "Dealer busts-player wins", "Winner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        }
        else if (calculate(player1) == calculate(dealer)){
            //JOptionPane.showMessageDialog(null,"draw");
            response = JOptionPane.showOptionDialog(null, "draw", "Winner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        }
        else if (calculate(player1) >  calculate(dealer))  {
           //JOptionPane.showMessageDialog(null,"player wins");
            response = JOptionPane.showOptionDialog(null, "player wins", "Winner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
            
        }
        else     {
            //JOptionPane.showMessageDialog(null,"Dealer wins");
            response = JOptionPane.showOptionDialog(null, "Dealer wins", "Winner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);
        }
        if(response==0){
          
            GUI o=new GUI ();
            o.setVisible(true);
            o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();
        }
        else{
            System.exit(0);
        }

    }
     void checkAce(ArrayList<PlayingCard>user){
         for(int i =0;i<user.size();i++){
             if (user.get(i).Value==1&&calculate(user)+10<=21){
                 
                 user.get(i).Value=11;
                 System.out.println("haga"+user.get(i).Value);
                   System.out.println("haga2"+calculate(user)); 
             }
         }
     }
         
}
