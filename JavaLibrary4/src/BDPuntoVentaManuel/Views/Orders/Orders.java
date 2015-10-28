/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Orders;

import BDPuntoVentaManuel.ViewsProcess.Process_CatCategoria;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author manuel
 */
public class Orders {
    
    public Orders()
    {
        this.start_tools();
    }
    private void start_tools()
    {
        CategoriasProcess=new Process_CatCategoria();
    }
    
    public DefaultComboBoxModel getModelCategorias()
    {
        return CategoriasProcess.GetComoBoxModelCategoria();
    }
    
    
    
    //Variables
    Process_CatCategoria CategoriasProcess;
}
