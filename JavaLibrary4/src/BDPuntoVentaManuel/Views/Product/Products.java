/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDPuntoVentaManuel.Views.Product;


import BDPuntoVentaManuel.ABSTRACT.IProducto;
import BDPuntoVentaManuel.CONCREATE.ExtendsAbstracts.IProductExtends;
import BDPuntoVentaManuel.FACTORY.FactoryProducto;
import BDPuntoVentaManuel.MODEL.Catcategoria;
import BDPuntoVentaManuel.MODEL.Producto;
import BDPuntoVentaManuel.ViewsProcess.Process_CatCategoria;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class Products {
    
    
     public Products()
    {
        this.start_tools();
    }
    private void start_tools()
    {
        CategoriasProcess=new Process_CatCategoria();
        ctrlProducto=new FactoryProducto().getInstanceAbstractExtends();
        ctrlProductoDefault=new FactoryProducto().getInstanceAbstract();
    }
    public DefaultComboBoxModel getModelCategorias()
    {
        return CategoriasProcess.GetComoBoxModelCategoria();
    }
    public void ChargeDataDefault(DefaultTableModel model)
    {
        List<Producto> listProducts=ctrlProducto.findDatadefault();
        Object []data=new Object[5];
        
        
        for(Producto item : listProducts)
        {
            data[0]=item.getStrClave();
            data[1]=item.getStrNombre();
            data[2]=item.getDobPrecioCompra();
            data[3]=item.getDobPrecioVenta();
            data[4]=item.getDobCantidad();
            
            model.addRow(data);
        }
        
    }
    public void SaveProduct(Producto product)
    {
        ctrlProductoDefault.create(product);
    }
     public void ChargeDataByCategoriaId(DefaultTableModel model,Catcategoria categoria)
    {
        List<Producto> listProducts=ctrlProducto.FindDataByCategoriaId(categoria);
        Object []data=new Object[5];
        
        for(Producto item : listProducts)
        {
            data[0]=item.getStrClave();
            data[1]=item.getStrNombre();
            data[2]=item.getDobPrecioCompra();
            data[3]=item.getDobPrecioVenta();
            data[4]=item.getDobCantidad();
            
            model.addRow(data);
        }
        
    }
    
    
     public Producto GetProductByCode(String code)
     {
         Producto item=ctrlProducto.findProductByCode(code);
         return item;
     }
     
    //Variables de procesos
    Process_CatCategoria CategoriasProcess;
    
    //Controlers
    IProducto ctrlProductoDefault;
    IProductExtends ctrlProducto;
    
}
