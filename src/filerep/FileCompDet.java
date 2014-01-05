/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filerep;

import java.io.File;

/**
 *
 * @author admin
 */
public class FileCompDet extends FileComp
{
    private int[]  se;
    boolean resul;
    FileCompDet(File f1,File f2,int[] se)
    {
        super(f1,f2);
        this.se=se;
    }
    /////
    public boolean docomp()
    {  
        //resul=true;
        if((se[0]==0)&&(se[1]==0)&&(se[2]==0)&&(se[3]==0))
        {  
            return false;
           // resul=false;
        }
        if((se[0]==1)&&(!namecomp()))
        {
            return false;
        }
        if((se[1]==1)&&(!sizecomp()))
        {
            return false; 
        }
        if((se[2]==1)&&(!byteLevelCompare()))
        {
             return false;
        }
        if((se[3]==1)&&(!extencomp()))
        {
             return false;
        }
        return true;
    }
    
}
