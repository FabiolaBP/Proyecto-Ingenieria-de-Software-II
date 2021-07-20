
package controlador;

import vista.PanelAdmin;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorConfiguracion implements MouseListener{

    private PanelAdmin views;

    public ControladorConfiguracion(PanelAdmin views) {
        this.views = views;
        this.views.JLabelCat.addMouseListener(this);
        this.views.JLabelClientes.addMouseListener(this);
        this.views.JLabelConfig.addMouseListener(this);
        this.views.JLabelmarca.addMouseListener(this);
        
        this.views.JLabelNuevaVenta.addMouseListener(this);
        this.views.JLabelProveedor.addMouseListener(this);
        this.views.JLabelUsers.addMouseListener(this);
        this.views.JlabelProductos.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == views.JLabelCat) {
            views.JPanelCategorias.setBackground(new Color(255,51,51));
        }else if(e.getSource() == views.JLabelClientes) {
            views.JPanelClientes.setBackground(new Color(255,51,51));
        }else if(e.getSource() == views.JLabelConfig) {
            views.JPanelConfig.setBackground(new Color(255,51,51));
        }else if(e.getSource() == views.JLabelmarca) {
            views.JPanelmarca.setBackground(new Color(255,51,51));
        }else if(e.getSource() == views.JLabelProveedor) {
            views.JPanelProveedor.setBackground(new Color(255,51,51));
        }else if(e.getSource() == views.JLabelUsers) {
            views.JPanelUsers.setBackground(new Color(255,51,51));
        }else{
            views.JPanelProducto.setBackground(new Color(255,51,51));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == views.JLabelCat) {
            views.JPanelCategorias.setBackground(new Color(51,51,51));
        }else if(e.getSource() == views.JLabelClientes) {
            views.JPanelClientes.setBackground(new Color(51,51,51));
        }else if(e.getSource() == views.JLabelConfig) {
            views.JPanelConfig.setBackground(new Color(51,51,51));
        }else if(e.getSource() == views.JLabelmarca) {
            views.JPanelmarca.setBackground(new Color(51,51,51));
        }else  if(e.getSource() == views.JLabelNuevaVenta) {
            views.JPanelNuevaVenta.setBackground(new Color(51,51,51));
        }else if(e.getSource() == views.JLabelProveedor) {
            views.JPanelProveedor.setBackground(new Color(51,51,51));
        }else if(e.getSource() == views.JLabelUsers) {
            views.JPanelUsers.setBackground(new Color(51,51,51));
        }else{
            views.JPanelProducto.setBackground(new Color(51,51,51));
        }
    }
    
}
