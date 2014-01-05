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


/**
 *
 * @author admin
 */
public class FileRep extends JFrame  implements ActionListener
{

    /**
     * @param args the command line arguments
     */
    private List<String> lor=new ArrayList<String>();
    DataInputStream dr=new DataInputStream(System.in);
    Path path;
    String pastr,sstr;
    JButton check,comp,arch,clearF;
    Container con;
    JTextField jtx,jtx1;
    FileSearch fs;
    TextArea ta;
    Checkbox ch_n,ch_s,ch_bl,ch_ex;
    Label l1,l2;
   
    
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
        arch=new JButton(" Find Replica");
        arch.addActionListener(this);
        arch.setBounds(90,180,220,30);
        con.add(arch);
        comp=new JButton("find");
        comp.addActionListener(this);
        comp.setBounds(210,135,100,30);
        con.add(comp);
        clearF=new JButton("Clear");
        clearF.addActionListener(this);
        clearF.setBounds(150,450,150,30);
        con.add(clearF);
        
        jtx=new JTextField(15);
        jtx.setBounds(90,10,250,40);
        con.add(jtx);
        jtx1=new JTextField(15);
        con.add(jtx1);
        jtx1.setBounds(90,80,250,40);

        ta=new TextArea(10,30);
        ta.setBounds(20,235,400,200);
        con.add(ta);
        
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
            File deee=new File(jfc.getSelectedFile().getPath());
            jtx.setText(deee.getName());
            //path=Paths.get(jtx.getText());
             sstr=""+path.getParent();
          
            
            jtx1.setText(sstr);
        }
    }
    public static void main(String[] args)
    {
        FileRep fr=new FileRep();
        fr.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        // TODO code application logic here
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==check)
        {
            //pastr=jtx.getText();
            //jtx1.setText(pastr);
            openFile1();
        }
        if(ae.getSource()==comp)
        {
            
            
            try
           {
                  
            //File fd=new File(jtx1.getText()+"\\"+jtx.getText());
             ta.setText("searching........\n");
            System.out.println(jtx1.getText()+"\\"+jtx.getText());
            fs=new FileSearch (Paths.get(jtx1.getText()+"\\"+jtx.getText()),Paths.get(jtx1.getText()),bgselected());
            fs.doSearch();
            lor=fs.searchResults();
            DispOnta();
            
           }
           catch(Exception e)
           {
              ta.setText("error........\n"+e.getMessage()+"   \n"+e.toString()+"\n"+e.getLocalizedMessage()); 
           }
            
               
          
        }
        if(ae.getSource()==arch)
        {
            try
            {
                FindRepFile frf=new FindRepFile(jtx1.getText());
                lor=frf.doSearch();
                DispOnta();
            }
            catch(Exception fe)
            {
              ta.setText("error........\n"+fe.getMessage()+"   \n"+fe.toString());  
            }
            
        }
        if(ae.getSource()==clearF)
        {
            cleanAllF();
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
               ta.append(lor.get(j)+"\n");
               
            }
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
}
