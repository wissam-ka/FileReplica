/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DisplayThread implements Runnable
{
    Thread t;
    FileRep fr;
    FileInputStream fis;
    Properties pr=new Properties();
    long oldfilelength;
    int oldlistlength=0;
    boolean quit=false;
            //=new FileInputStream("datafile.dat");

    public DisplayThread(FileRep fr) 
    {
        this.fr=fr;    
        t=new Thread(this);
        
    }

    @Override
    public void run() 
    {
        
        while(!quit)
        {
          
           // System.out.println("inside while");
            File f=new File("datafile.dat");
            if(f.length()!=oldfilelength)
            {
            try 
            {
                fis=new FileInputStream("datafile.dat");
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(DisplayThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try 
            {
                pr.load(fis);
                
                oldfilelength=f.length();
            }
            catch (IOException ex) 
            {
                Logger.getLogger(DisplayThread.class.getName()).log(Level.SEVERE, null, ex);
            }
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(DisplayThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DispOnta();
                if(((String)pr.get("end")).equals("Done"))
                {
                quit=true;
                
                }
           
                            
        }
    }
    public void DispOnta()
    {
        
        if((pr.size()==1)&&((String)pr.get("end")).equals("Done"))
            {
                fr.ta.append("\n No files found");
            }
            else
            {
            for(int j=oldlistlength ;j<pr.size();j++)
            {
                
                   
                    fr.ta.append("*"+j+"--");
                   
                   fr.ta.append(pr.get(String.valueOf(j))+"\n");
               
            }
           
            oldlistlength=pr.size();
            
            }
    }
    
    
}
