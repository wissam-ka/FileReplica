/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;


import java.awt.Container;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author admin
 */
public class FileControlFrame extends JFrame implements ActionListener
{

    
    Container con1;
    JButton del_file,open_file,bro_file;
    JTextField path_show;
    JCheckBox del_agree;
    
    FileControlFrame(String file_path)
    {
        con1=getContentPane();

        con1.setLayout(null);
        con1.setLocation(new Point(1000,1000));
        
        del_agree=new JCheckBox("I want to delete this file",false);
        del_agree.addActionListener(this);
        del_agree.setBounds(10, 80, 250, 15);
        con1.add(del_agree);
        
        path_show=new JTextField();
        path_show.addActionListener(this);
        path_show.setBounds(5,5,270,25);
        con1.add(path_show);
        path_show.setText(file_path);
        
        del_file=new JButton("Delete");
        del_file.addActionListener(this);
        del_file.setEnabled(false);
        del_file.setBounds(10,40,75,25);
        
        con1.add(del_file);
        
       
        
        open_file=new JButton("open");
        open_file.addActionListener(this);
        open_file.setBounds(95,40,75,25);
        con1.add(open_file);
        
        bro_file=new JButton("browser");
        bro_file.addActionListener(this);
        bro_file.setBounds(180,40,75,25);
        con1.add(bro_file);
        
        
        con1.setBounds(300, 120, 300, 120);
         setSize(300,140);
        setVisible(true);
        
       
        
    }

    public void fileBrowth()
    {
           if (java.awt.Desktop.isDesktopSupported ())
           {
               //String ss=""+Paths.get(path_show.getText()).getParent();
               java.awt.Desktop desktop = java.awt.Desktop.getDesktop ();
               if (desktop.isSupported (java.awt.Desktop.Action.BROWSE))
               {
                   try
                   {
                       //desktop.browse (new java.net.URI (ss));
                      
                        desktop.browse (Paths.get(path_show.getText()).getParent().toUri());
                        
                   }
                   catch (java.io.IOException e)
                   {
                       e.printStackTrace ();
                   }
               }
            }         
    }
    public void fileopen()
    {
        try 
        {
            Process pc = Runtime.getRuntime().exec("cmd.exe /c start "+Paths.get(path_show.getText()));
        }
        catch (IOException ex) 
        {
            Logger.getLogger(FileControlFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void fileDelete()
    {
         File ff=new File(path_show.getText());
         ff.delete();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       if(e.getSource()==del_agree)
       {
           del_file.setEnabled(del_agree.isSelected());
       }
       if(e.getSource()==del_file)
       {
           fileDelete();
       }
       if(e.getSource()==open_file)
       {
           fileopen();
       }
       if(e.getSource()==bro_file)
       {
           fileBrowth();
       }
    }
}
