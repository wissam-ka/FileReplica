/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

/**
 *
 * @author admin
 */
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Archivedfilev2 extends FileComp
{
    private HashMap<Long,ArrayList<String>> filetable1 = new HashMap<Long,ArrayList<String>>(); 
    private HashMap<Long,ArrayList<String>> filetable2 = new HashMap<Long,ArrayList<String>>(); 
    
  // ik long key size
    // se list of paths
    
    public void add2Map(long ik,String se)
    { 
            if(filetable1.containsKey(ik))
            {
                filetable1.get(ik).add(se);
            }
            else
            {
                ArrayList<String> lop3=new ArrayList<String>();
                lop3.add(se);
                filetable1.put(ik, lop3);
            }  
    }

    public void gettable()
    {
            for(Long i:filetable1.keySet())
            {
                System.out.println(i);
                for(int i1=0;i1<filetable1.get(i).size();i1++)
                {
                   System.out.print(filetable1.get(i).get(i1) +"   "); 
                }
                System.out.println("");
            }
            System.out.println();
        }
    
    public ArrayList getHashTable()
    {
        ArrayList<String> frl=new ArrayList<String>();
        filetable2=filetable1;
            for(Long i:filetable2.keySet())
            {
                frl=tableOrganize(i,frl);    
            }     
        return frl;
    }
    
    public ArrayList<String> tableOrganize(long intKey, ArrayList<String> flist)
    {
        while(filetable2.get(intKey).size()>1)
                {
                    boolean g;
                    String stemp1,stemp2;
                    stemp1=filetable2.get(intKey).get(0);
                    filetable2.get(intKey).remove(0);
                    File ftemp1,ftemp2;
                    ftemp1=new File(stemp1);
                    g=true;
                    for(int jit=0;jit<filetable2.get(intKey).size();jit++)
                    {
                        stemp2=filetable2.get(intKey).get(jit);
                       
                        ftemp2=new File(stemp2);
                        if(byteLevelCompare(ftemp1,ftemp2))
                        {
                            if(g)
                            {
                                flist.add("-------"+ftemp1.getName()+"-----------");
                                flist.add(stemp1);
                                g=false;
                            }
                            flist.add(stemp2);
                            filetable2.get(intKey).remove(jit);
                            jit--;
                        }   
                    }   
                }
        return flist;
    }
}