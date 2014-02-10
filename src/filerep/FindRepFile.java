/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FindRepFile extends FileComp
{
    private List<String> lof=new ArrayList<String>();
    String pPath;
    String exPath;
    long sizePath;
    boolean w_woutExt;
    Archivedfile archf;
    Archivedfilev2 archf2;
    String data_file_name="datafile.dat";
   
    FindRepFile(String p,boolean w_woutExt)
    {
        lof.add(p);
        this.w_woutExt=w_woutExt;
        archf=new Archivedfile();
        archf2=new Archivedfilev2(data_file_name);
    }
    
    
    
    public void doSearch()
    {
        System.out.println(lof.size());
        
        int coun=0;
        while(coun<lof.size())
        {
            fileName1(lof.get(coun));
            
            coun++;
        }
        
        if(w_woutExt)
        {
           
            archf2.getHashTable(); 
        }
        else
        {
            
           archf.getHashTable();
        }
       
    }
    public void fileName1(String sp)
   {
       
        File filar= new File(sp);
        
        String s[]=filar.list();
        int i=-1;
        while(true)
        {
            i++;
            try
            {
                try
                {
                   String mm=s[i];
                }
                catch(NullPointerException npe)
                {
                    continue;
                }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                break;
            }
            pPath=sp+"\\"+s[i];
            File ff=new File(pPath);
            if(!ff.isHidden())
            {
                if(ff.isDirectory())
            {
                    lof.add(pPath); 
            }
            else
            {
                sizePath=ff.length();
                if(w_woutExt)
                {
                    archf2.add2Map(sizePath,pPath);
                }
                else
                {
                    exPath=getexten(ff);
                    archf.add2Map(exPath, sizePath,pPath);
                }
                 
            }
        }
        }
    }


}
