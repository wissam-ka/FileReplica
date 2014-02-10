/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class MultiThread  implements Runnable
{

    
    Thread t;
    String search_type,string_msg;
    Path p1,p2;
    int[] select_c;
    boolean arch_ty;
    List<String> lor=new ArrayList<String>();
    
    MultiThread(String search_type, Path p1,Path p2,int[] bsel)
    {
        this.search_type=search_type;
        this.p1=p1;
        this.p2=p2;
        select_c=bsel;
        t=new Thread(this);
     }

    public MultiThread(String search_type,String string_msg,boolean arch_ty) 
    {
        this.search_type=search_type;
        this.arch_ty=arch_ty;
        this.string_msg=string_msg;
         t=new Thread(this);
    }
   

    @Override
    public void run() 
    {
        if(search_type.equals("arch"))
        {
          FindRepFile f=new FindRepFile(string_msg,arch_ty);
          f.doSearch();    
         }
        else if(search_type.equals("normal"))
        {
            
            FileSearch fs=new FileSearch (p1,p2,select_c);
            fs.doSearch();
            //lor=fs.searchResults();
        }
    }
    
    
}
