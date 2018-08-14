/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsplatsearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tosha
 */
public class Data {
    private List<File> data;
    
    private static Data instance = new Data();
    
    private Data(){
        data = new ArrayList<>();
    
    }
    
    public static Data getInstance(){
        return instance;
    }
    
    public void add(File f){
        data.add(f);
    }
    
    public File get(int i){
        return data.get(i);
    }
    
    public void clear(){
        data.clear();
    }
    public int size(){
        return data.size();
    }
    public boolean isEmpty(){
        return data.isEmpty();
    }
    
}
