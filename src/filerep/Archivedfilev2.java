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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Archivedfilev2 extends FileComp
{
    private HashMap<Long,ArrayList<String>> filetable1 = new HashMap<Long,ArrayList<String>>(); 
    private HashMap<Long,ArrayList<String>> filetable2 = new HashMap<Long,ArrayList<String>>(); 
    Properties pr=new Properties();
    String data_file_name;
    
  // ik long key size
    // se list of paths
    FileRep fr;
    int file_cont=0;

    public Archivedfilev2(FileRep fr, String data_file_name)
    {
        this.fr=fr;
        this.data_file_name=data_file_name;
    }
    
    
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
               // System.out.println(i);
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
                dataFileCreate();
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
                            if(g)
                            {
                               
                                flist.add("-------"+ftemp1.getName()+"-----------");
                                fr.ta.append("-------"+ftemp1.getName()+"-----------\n");
                                pr.put(file_cont+1,"-------"+ftemp1.getName()+"-----------");
                                file_cont++;
                                flist.add(stemp1);
                                
                                 fr.ta.append("*"+file_cont+"--"+stemp1+"\n");
                                  pr.put(file_cont+1,stemp1);
                                 file_cont++;
                                g=false;
                            }
                            
                            
                                
                            flist.add(stemp2);
                            pr.put(file_cont+1,stemp2);
                            fr.ta.append("*"+file_cont+"--"+stemp2+"\n");
                            file_cont++;
                            filetable2.get(intKey).remove(jit);
                            jit--;
                            
                        }   
                    }   
                }
        return flist;
    }
    private void dataFileCreate() 
    {
        FileOutputStream fos = null;
        try {
            
            
            fos = new FileOutputStream(data_file_name);
            try {
                pr.store(fos,"data save");
            } catch (IOException ex) {
                Logger.getLogger(FindRepFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindRepFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(FindRepFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
             
    }
}