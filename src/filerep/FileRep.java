/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author admin
 */
public class FileRep extends JFrame  implements ActionListener,MouseListener,KeyListener
{

    /**
     * @param args the command line arguments
     */
    private List<String> lor=new ArrayList<String>();
    DataInputStream dr=new DataInputStream(System.in);
    Path path;
    String pastr,sstr;
    JButton check,comp,arch,clearF,archv2;
    Container con;
    JTextField jtx,jtx1;
    FileSearch fs;
    TextArea ta;
    Checkbox ch_n,ch_s,ch_bl,ch_ex;
    Label l1,l2,textareat_note;
    boolean archive_type,control_check;
    
   
    
    FileRep()
    {
        
        con=getContentPane();
        
        con.setBackground(Color.gray);
         con.setLayout(null);
         
        ch_n=new Checkbox("name check",true);
        ch_n.setBounds(350,80,100,20);
        con.add(ch_n);
        ch_s=new Checkbox("size check",true);
        con.add(ch_s);
        ch_s.setBounds(350,105,100,20);
        ch_bl=new Checkbox("container check",true);
        con.add(ch_bl);
        ch_bl.setBounds(350,130,150,20);
         ch_ex=new Checkbox("extention check");
        con.add(ch_ex);
        ch_ex.setBounds(350,155,150,20);
        
      //  ch_n.setBounds(WIDTH, WIDTH, WIDTH, WIDTH);
        check=new JButton("open");
        check.addActionListener(this);
        check.setBounds(90,135,100,30);
        con.add(check);
        
        comp=new JButton("find");
        comp.addActionListener(this);
        comp.setBounds(210,135,100,30);
        con.add(comp);
        //comp.set
        clearF=new JButton("Clear");
        clearF.addActionListener(this);
        clearF.setBounds(150,480,150,30);
        con.add(clearF);
        arch=new JButton(" Find Replica with *.ext");
        arch.addActionListener(this);
        arch.setBounds(90,180,220,30);
        con.add(arch);
        archv2=new JButton("Find Replica without *.ext");
        archv2.addActionListener(this);
        archv2.setBounds(90,220,220,30);
        con.add(archv2);
        
        jtx=new JTextField(15);
        jtx.setBounds(90,10,250,40);
        con.add(jtx);
        jtx1=new JTextField(15);
        con.add(jtx1);
        jtx1.setBounds(90,80,250,40);

        ta=new TextArea(10,30);
        
        ta.setBounds(20,255,400,200);
        con.add(ta);
        ta.addKeyListener(this);
        ta.addMouseListener(this);
        textareat_note=new Label("For more option press  \"Crl + Double click the number next to the file's name\"");
        textareat_note.setBounds(20,445,410,50);
        con.add(textareat_note);
        
        l1=new Label("file name:");
        con.add(l1);
        l1.setBounds(10, 15,61, 30);
        l2=new Label(" Directory:");
        con.add(l2);
        l2.setBounds(10, 85,61, 30);
     
        setSize(500,700);
        
       setVisible(true);
        

    }
    
    public void openFile1()
    {
        JFileChooser jfc=new JFileChooser();
        int result=jfc.showOpenDialog(this);
        if(result==JFileChooser.CANCEL_OPTION)
        {
            System.out.println("Pressed Cancel : "+result);
        }
        else
        {
            path=Paths.get(jfc.getSelectedFile().getPath());
            //File deee=new File(jfc.getSelectedFile().getPath());
            jtx.setText(jfc.getSelectedFile().getPath());
            //path=Paths.get(jtx.getText());
             sstr=""+path.getParent();
          
            
            jtx1.setText(sstr);
        }
    }
    public static void main(String[] args)
    {
        FileRep fr=new FileRep();
        fr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        
        if(ae.getSource()==check)
        {
            openFile1();
        }
        if(ae.getSource()==comp)
        {
            doComp();
        }
        if(ae.getSource()==arch)
        {
            archive_type=false;
            doArchive();
        }
        if(ae.getSource()==clearF)
        {
            cleanAllF();
        }
        if(ae.getSource()==archv2)
        {
            archive_type=true;
            doArchive();
        }
    }
    
    
    public void DispOnta()
    {
        if(lor.isEmpty())
            {
                ta.append("\n No files found");
            }
            else
            {
            for(int j=0 ;j<lor.size();j++)
            {
                if(!lor.get(j).startsWith("------"))
                {
                   
                    ta.append("*"+j+"--");
                   
                }
                ta.append(lor.get(j)+"\n");
               
            }
            ta.append("done\n");
            
            }
    }

    private int[] bgselected() 
    {
        int box_check[]={0,0,0,0};
        if(ch_n.getState())
        {
            box_check[0]=1;
        }
        if(ch_s.getState())
        {
            box_check[1]=1;
        }
        if(ch_bl.getState())
        {
            box_check[2]=1;
        }
        if(ch_ex.getState())
        {
            box_check[3]=1;
        }
       return box_check;
    }

    private void cleanAllF()
    {
        jtx.setText("");
        jtx1.setText("");
        ta.setText("");
        ch_bl.setState(true);
        ch_n.setState(true);
        ch_s.setState(true);
        ch_ex.setState(false);
    }
    private void doComp()
    {
        try
        {               
            ta.setText("searching........\n");
            //System.out.println(jtx1.getText()+"\\"+jtx.getText());
            fs=new FileSearch (Paths.get(jtx.getText()),Paths.get(jtx1.getText()),bgselected());
           
            fs.doSearch();
            lor=fs.searchResults();
            DispOnta();
        }
        catch(Exception e)
        {
            ta.setText("error........\n"+e.getMessage()+"   \n"+e.toString()+"\n"+e.getLocalizedMessage()); 
        }
    }
    private void doArchive()
    {
//        try
//        {
            FindRepFile frf=new FindRepFile(jtx1.getText(),archive_type);
            lor=frf.doSearch();
            DispOnta();
//        }
//        catch(Exception fe)
//        {
//            ta.setText("error........\n"+fe.getMessage()+"   \n"+fe.toString());  
//        }     
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getButton()==MouseEvent.BUTTON1&&control_check)
        {
            openselectedfile();
        }
    
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
        if(e.getSource()==ta)
        {
            control_check=e.isControlDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        //throw new UnsupportedOperationException("Not supported yet.");
         if(e.getSource()==ta)
        {
            control_check=e.isControlDown();
        }  
    }
    public String clearStr(String str2cln)
    {
        String clean_str="";
        for(int i=0;i<str2cln.length();i++)
        {
            if((str2cln.charAt(i) !='*')&&(str2cln.charAt(i) !='-'))
            clean_str+=(str2cln.charAt(i));
        }
        return clean_str;
    }
    
    public void openselectedfile()
    {
        String s;
        s=ta.getSelectedText();
        s=clearStr(s);
        if(s!="")
        {
            int list_itr=Integer.parseInt(clearStr(s));
            FileControlFrame fcf=new FileControlFrame(lor.get(list_itr));
            fcf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
               
         }
        
    }
}
