/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;
import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
        
/**
 *
 * @author admin
 */
public class FileComp 
{
    private File f1,f2;
    private byte[] bf1,bf2;
    FileComp()
    {
    }
    
    FileComp(File f1,File f2)
    {
        this.f1=f1;
        this.f2=f2;        
    }

    // compare two files at byte level
    public boolean byteLevelCompare(File ff1,File ff2)
    {
        this.f1=ff1;
        this.f2=ff2; 
        return byteLevelCompare();
    }
    public boolean byteLevelCompare()
    {
        bf1=F2B(f1);
        bf2=F2B(f2);
       return (Arrays.equals(bf1,bf2)); 
    }
    
    // convert file to byte array
    public byte[] F2B(File f)
    {
        FileInputStream fis=null;
        byte[] b= new byte[(int)f.length()];
        try
        {
         fis=new FileInputStream(f)   ;
         fis.read(b);
         
        }
        catch(FileNotFoundException e)
        {
           System.out.println("File Not Found."); 
        }
        catch(IOException e)
        {
             System.out.println("Error Reading The File.");
        }
        finally
        {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileComp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       return b; 
    }
  
    // copare the size of two files 
    public boolean sizecomp()
    {
        return (f1.length()==f2.length());
    }
    //
    public boolean namecomp()
    {
        return (f1.getName().equals(f2.getName()));
    }
    /// extra act  extention
    public String getexten(File f)
    {
       String s=f.getName();
       return s.substring(s.lastIndexOf("."));
    }
    // compair two file extentions
    public boolean extencomp()
    {
        return((getexten(f1)).equals(getexten(f2))) ;
    }
    
}
