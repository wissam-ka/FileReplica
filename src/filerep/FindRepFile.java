/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.File;
import java.nio.file.Path;
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
    Archivedfile archf=new Archivedfile();
    FindRepFile(String p)
    {
        lof.add(p);
    }
    
    
    
    public ArrayList doSearch()
    {
        int coun=0;
        while(coun<lof.size())
        {
            fileName1(lof.get(coun));
            coun++;
        }
        //archf.gettable();
       
        return archf.getHashTable();
    }
    public void fileName1(String sp)
    {
        File filar= new File(sp);
        String s[]=filar.list();
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
                exPath=getexten(ff);
                sizePath=ff.length();
                archf.add2Map(exPath, sizePath,pPath);
            } 
        }
        
    }
   
    
    
}
