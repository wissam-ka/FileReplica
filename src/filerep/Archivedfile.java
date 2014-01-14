/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Archivedfile extends FileComp
{
  
    long filmatc_coun=0;
    
    private HashMap<String,HashMap<Long,ArrayList<String>>> filetable1 = new HashMap<String,HashMap<Long,ArrayList<String>>>(); 
      private HashMap<String,HashMap<Long,ArrayList<String>>> filetable2= new HashMap<String,HashMap<Long,ArrayList<String>>>(); 
    //sk String key  extention
    // ik long key size
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
        ArrayList<String> frl=new ArrayList<String>();
        filetable2=filetable1;
         for(String si:filetable2.keySet())
        {
            for(Long i:filetable2.get(si).keySet())
            {       
                frl=tableOrganize(si,i,frl);    
            }     
        } 
         System.out.println("find allllllll");
        return frl;
    }
    
    public ArrayList<String> tableOrganize(String strKey, long intKey, ArrayList<String> flist)
    {
        
        while(filetable2.get(strKey).get(intKey).size()>1)
                {
                    boolean g;
                    String stemp1,stemp2;
                    stemp1=filetable2.get(strKey).get(intKey).get(0);
                    filetable2.get(strKey).get(intKey).remove(0);
                    File ftemp1,ftemp2;
                    ftemp1=new File(stemp1);
                      // System.out.println("ftemp1.length "+ftemp1.length());
                    g=true;
                    for(int jit=0;jit<filetable2.get(strKey).get(intKey).size();jit++)
                    {
                        stemp2=filetable2.get(strKey).get(intKey).get(jit);
                       
                        ftemp2=new File(stemp2);
                        boolean choose_byte_com=false;
                        if(ftemp2.length()<30000000)
                        {
                            choose_byte_com=byteLevelCompare(ftemp1,ftemp2);
                        }
                        else
                        {
                            try {
                                LargeSizeFile lsg=new LargeSizeFile(ftemp1, ftemp2);
                                choose_byte_com=lsg.byteLevelCompare();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Archivedfile.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Archivedfile.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if(choose_byte_com)
                        {
                          //  filmatc_coun++;
                          //  System.out.println("filmatc_coun   "+filmatc_coun);
                            if(g)
                            {
                             
                                flist.add("-------"+ftemp1.getName()+"-----------");
                                flist.add(stemp1);
                                g=false;
                            }
                            
                            flist.add(stemp2);
                            filetable2.get(strKey).get(intKey).remove(jit);
                            jit--;
                        }   
                    }   
                }
        return flist;
    }
}