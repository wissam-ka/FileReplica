/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class LargeSizeFile 
{
    File f2c1,f2c2;
    Long filsize;
    final int x=10000000;
    byte[] bf1,bf2;

    public LargeSizeFile(File f2c1,File f2c2) 
    {
        this.f2c1=f2c1;
        this.f2c2=f2c2;
        filsize=f2c1.length();
        
    }
     public int[] NumOfCom()
     {
         int[] resl={0,0};
         if(filsize%x !=0)
         {
             resl[0]=(int)(filsize%x);
         }
         resl[1]=(int)((filsize-resl[0])/x);
         return resl;
     }
     public boolean byteLevelCompare() throws FileNotFoundException, IOException
    {
        int[] resl=NumOfCom();
        FileInputStream fis1=new FileInputStream(f2c1);
        FileInputStream fis2=new FileInputStream(f2c2);
        boolean compre=true;
         byte[] b,b2;
            
            
              b=new byte[x];
               b2= new byte[x];
            
     
        for(int i=0;i<=resl[1];i++)
        {
             System.out.println(i);
            if((i*x-filsize)==resl[0])
            {
                b= new byte[resl[0]];
             b2= new byte[resl[0]];
            }
           fis1.read(b);
           fis2.read(b2);
          
           compre=(compre&&Arrays.equals(b, b2));
          if(!compre)
          {
              return false;
          }
        }
       return compre; 
    }
    
    
    
    
}
