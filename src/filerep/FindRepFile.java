/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    Archivedfile archf=new Archivedfile();
    Archivedfilev2 archf2=new Archivedfilev2();
    FindRepFile(String p,boolean w_woutExt)
    {
        lof.add(p);
        this.w_woutExt=w_woutExt;
    }
    
    
    
    public ArrayList doSearch()
    {
        int coun=0;
        while(coun<lof.size())
        {
            fileName1(lof.get(coun));
            coun++;
        }
        if(w_woutExt)
        {
            return archf2.getHashTable(); 
        }
        else
        {
            return archf.getHashTable();
        }
       
    }
    public void fileName1(String sp)
    {
        File filar= new File(sp);
        String s[]=filar.list();
        try
        {
        for(int i =0;i<s.length;i++)
        {
            pPath=sp+"\\"+s[i];
           
            File ff=new File(pPath);
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
        catch(NullPointerException ne)
        {
            System.out.println(ne.getMessage());
        }
        
    }
   
    
    
}
