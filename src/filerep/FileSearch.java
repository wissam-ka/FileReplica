/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
   
    
    
    String strp="";
    ////
    private List<String> lof=new ArrayList<String>();
    private List<String> mfl=new ArrayList<String>();
    FileSearch(Path pth1,Path pth2,int[] s_type)
    {
        this.pth1=pth1;
        this.pth2=pth2;
       this.s_type=s_type;
        
        //fnam+=pth1.getFileName();
        strp+=pth2;
        lof.add(strp);
        
       // System.out.println(strp);
    }
    
    public void doSearch()
    {
//        for(String ing1:lof )
//        {
//            fileName1(ing1);
//        }
        int coun=0;
        while(coun<lof.size())
        {
            fileName1(lof.get(coun));
            coun++;
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
            File ff=new File(sp+"\\"+s[i]);
            if(ff.isDirectory())
            {
                lof.add(sp+"\\"+s[i]);
            }
            else
            {
                
                    String sss=""+pth1;
                    File ff1=new File(sss);
                    //FileComp fc=new FileComp(ff,ff1);
                    FileCompDet fc=new FileCompDet(ff,ff1,s_type);
                
                    if(fc.docomp())
                    {
                        //mfl.add(sp+"\\"+s[i]+"       "+ff.length());
                        mfl.add(sp+"\\"+s[i]);
                    }
              
               
            }
            
            
        }
       }
       catch(NullPointerException ne)
       {
           System.out.println(ne.getMessage());
       }
        
        
    }
    
    
    public List<String> searchResults()
    {
        return mfl;
    }
    
}

