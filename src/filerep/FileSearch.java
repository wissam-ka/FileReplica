/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class FileSearch 
{
    // file path
    Path pth1;
//     serch path
    Path pth2;
    // customized the search
    int[] s_type;
   FileOutputStream fos = null;
   Properties pr=new Properties();
   int file_counter=0;
    
    
    String strp="";
    ////
    private List<String> lof=new ArrayList<String>();
    private List<String> mfl=new ArrayList<String>();
    FileSearch(Path pth1,Path pth2,int[] s_type)
    {
        this.pth1=pth1;
        this.pth2=pth2;
       this.s_type=s_type;

        strp+=pth2;
        lof.add(strp);
    }
    
    public void doSearch()
    {
         pr.put("end","n");
         dataFileCreate();
        int coun=0;
        while(coun<lof.size())
        {
            fileName1(lof.get(coun));
            coun++;
            dataFileCreate();
            
        }   
        pr.put("end","Done");
        pr.put("end1","Done");
        dataFileCreate();
    }
    public void fileName1(String sp)
    {
        File filar= new File(sp);
        String s[]=filar.list();
       try
       {
        for(int i =0;i<s.length;i++)
        {
            File ff=new File(sp+"\\"+s[i]);
            if(ff.isDirectory())
            {
                lof.add(sp+"\\"+s[i]);  
            }
            else
            {
                    String sss=""+pth1;
                    File ff1=new File(sss);
                    FileCompDet fc=new FileCompDet(ff,ff1,s_type);
                    if(fc.docomp())
                    {
                        mfl.add(sp+"\\"+s[i]);
                        pr.put(String.valueOf(file_counter),sp+"\\"+s[i]);
                        file_counter++;
                    }
            } 
        }
       }
       catch(NullPointerException ne)
       {
           System.out.println(ne.getMessage()+"  "+sp);
       }
    }
    
    
    public List<String> searchResults()
    {
        return mfl;
    }
     private void dataFileCreate() 
     {
       // FileOutputStream fos = null;
        try {
          fos = new FileOutputStream("datafile.dat");            
            pr.store(fos,"data save");
           
        } catch (IOException ex) {
            Logger.getLogger(Archivedfile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Archivedfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    }
}

