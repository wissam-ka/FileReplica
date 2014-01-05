/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Archivedfile extends FileComp
{
  
    
    
    private HashMap<String,HashMap<Long,ArrayList<String>>> filetable1 = new HashMap<String,HashMap<Long,ArrayList<String>>>(); 
      private HashMap<String,HashMap<Long,ArrayList<String>>> filetable2= new HashMap<String,HashMap<Long,ArrayList<String>>>(); 
    //sk  extention
    // size
    // se list of paths

    public void add2Map(String sk,long ik,String se)
    {
        if(filetable1.containsKey(sk))
        {
            if(filetable1.get(sk).containsKey(ik))
            {
                filetable1.get(sk).get(ik).add(se);
            }
            else
            {
                ArrayList<String> lop3=new ArrayList<String>();
                lop3.add(se);
                filetable1.get(sk).put(ik, lop3);
            }
        }
        else
        {
            ArrayList <String> lop1=new ArrayList<String>();
           
            lop1.add(se);
            HashMap<Long,ArrayList<String>> lop2=new HashMap<Long,ArrayList<String>>();
            lop2.put(ik, lop1);
            filetable1.put(sk,lop2);
            
        }
        
    }

    public void gettable()
    {
        for(String si:filetable1.keySet())
        {
            System.out.println(si);
            for(Long i:filetable1.get(si).keySet())
            {
                System.out.println(i);
                //System.out.println(i);
                
                for(int i1=0;i1<filetable1.get(si).get(i).size();i1++)
                {
                   System.out.print(filetable1.get(si).get(i).get(i1) +"   "); 
                }
                System.out.println("");

            }

            System.out.println();
        }
    }
    public ArrayList getHashTable()
    {
        boolean g;
        ArrayList<String> frl=new ArrayList<String>();
        filetable2=filetable1;
         for(String si:filetable2.keySet())
        {
           // System.out.println(si);
            for(Long i:filetable2.get(si).keySet())
            {
                
                while(filetable2.get(si).get(i).size()>1)
                {
                    String stemp1,stemp2;
                    stemp1=filetable2.get(si).get(i).get(0);
                    filetable2.get(si).get(i).remove(0);
                    File ftemp1,ftemp2;
                    ftemp1=new File(stemp1);
                    g=true;
                    for(int jit=0;jit<filetable2.get(si).get(i).size();jit++)
                    {
                        stemp2=filetable2.get(si).get(i).get(jit);
                       
                        ftemp2=new File(stemp2);
                        if(byteLevelCompare(ftemp1,ftemp2))
                        {
                            if(g)
                            {
                                frl.add("-------"+ftemp1.getName()+"-----------");
                                frl.add(stemp1);
                                g=false;
                            }
                            frl.add(stemp2);
                            filetable2.get(si).get(i).remove(jit);
                        }   
                    }   
                }
            }     
        } 
        return frl;
    }
       
}
