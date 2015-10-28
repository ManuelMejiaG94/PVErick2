/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.ViewsProcess;

import BDPuntoVentaManuel.ABSTRACT.ICatCategoria;
import BDPuntoVentaManuel.FACTORY.FactoryCatCategoria;
import BDPuntoVentaManuel.MODEL.*;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author manuel
 */
public class Process_CatCategoria {
 
    public Process_CatCategoria()
    {
        this.Start_tools();
    }
    
    private void Start_tools()
    {
        ctrlCategoriaDefault=new FactoryCatCategoria().getInstanceAbstract();
        
    }
    
    public DefaultComboBoxModel GetComoBoxModelCategoria()
    {
        Vector<Catcategoria> dataModel=new Vector<Catcategoria>();
        List<Catcategoria> listCategorias=this.ctrlCategoriaDefault.findCatcategoriaEntities();
        
        Catcategoria defaultModel=new Catcategoria();
        defaultModel.setId(-1);
        defaultModel.setStrValor("Seleccionar");
        
        dataModel.add(defaultModel);
        for ( Catcategoria item : listCategorias ) {
            dataModel.add(item);
       }
        
        DefaultComboBoxModel model=new DefaultComboBoxModel(dataModel);
        return model;
    
    }
    
    
    
    
    //Controladoras
    ICatCategoria ctrlCategoriaDefault;
    
}
