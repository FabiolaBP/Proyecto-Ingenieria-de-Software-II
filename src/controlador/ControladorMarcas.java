package controlador;

import modelo.Marcas;
import modelo.MarcasDao;
import modelo.Tablas;
import vista.PanelAdmin;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ControladorMarcas implements ActionListener, MouseListener, KeyListener{
    private final Marcas med;
    private final MarcasDao medDao;
    private final PanelAdmin views;
    DefaultTableModel modelo = new DefaultTableModel();
    public ControladorMarcas(Marcas med, MarcasDao medDao, PanelAdmin views) {
        this.med = med;
        this.medDao = medDao;
        this.views = views;
        this.views.btnRegitrarMed.addActionListener(this);
        this.views.btnNuevoMed.addActionListener(this);
        this.views.btnModificarMed.addActionListener(this);
        this.views.Tablemarca.addMouseListener(this);
        this.views.JLabelmarca.addMouseListener(this);
        this.views.JMenuEliminarMed.addActionListener(this);
        this.views.JMenuReingresarMed.addActionListener(this);
        this.views.txtBuscarMed.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btnRegitrarMed) {
            if (views.txtNombreMed.getText().equals("") || views.txtNombreCortoMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios"); 
            }else{
                med.setNombre(views.txtNombreMed.getText());
                med.setNombre_corto(views.txtNombreCortoMed.getText());
                if (medDao.registrar(med)) {
                    Nuevo();
                    limpiarTable();
                    listarmarca();
                    JOptionPane.showMessageDialog(null, "marca Registrado"); 
                }else{
                    JOptionPane.showMessageDialog(null, "La marca ya existe");
                }
            }
        }else if (e.getSource() == views.btnModificarMed){
            if (views.txtNombreMed.getText().equals("") || views.txtNombreCortoMed.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Todo los campos son obligatorios"); 
            }else{
                med.setNombre(views.txtNombreMed.getText());
                med.setNombre_corto(views.txtNombreCortoMed.getText());
                med.setId(Integer.parseInt(views.txtIdMed.getText()));
                if (medDao.modificar(med)) {
                    Nuevo();
                    limpiarTable();
                    listarmarca();
                    JOptionPane.showMessageDialog(null, "marca Modificado"); 
                }
            }
        }else if (e.getSource() == views.JMenuEliminarMed){
            if (views.txtIdMed.getText().equals("") || views.txtIdMed.getText() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar"); 
            }else{
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdMed.getText());
                    if (medDao.accion("Inactivo", id)) {
                        Nuevo();
                        limpiarTable();
                        listarmarca();
                        JOptionPane.showMessageDialog(null, "marca Eliminado"); 
                    }
                }
            }
        }else if (e.getSource() == views.JMenuReingresarMed){
            if (views.txtIdMed.getText().equals("") || views.txtIdMed.getText() == null) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para reingresar"); 
            }else{
                int pregunta = JOptionPane.showConfirmDialog(null, "Estas seguro de reingresar", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (pregunta == 0) {
                    int id = Integer.parseInt(views.txtIdMed.getText());
                    if (medDao.accion("Activo", id)) {
                        Nuevo();
                        limpiarTable();
                        listarmarca();
                        JOptionPane.showMessageDialog(null, "marca Reingresado"); 
                    }
                }
            }
        }else if(e.getSource() == views.btnNuevoMed){
            Nuevo();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == views.Tablemarca) {
            int fila = views.Tablemarca.rowAtPoint(e.getPoint());
            views.txtIdMed.setText(views.Tablemarca.getValueAt(fila, 0).toString());
            views.txtNombreMed.setText(views.Tablemarca.getValueAt(fila, 1).toString());
            views.txtNombreCortoMed.setText(views.Tablemarca.getValueAt(fila, 2).toString());
        }else if(e.getSource() == views.JLabelmarca){
            views.jTabbedPane1.setSelectedIndex(5);
            limpiarTable();
            listarmarca();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    private void Nuevo() {
        views.txtIdMed.setText("");
        views.txtNombreMed.setText("");
        views.txtNombreCortoMed.setText("");
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txtBuscarMed) {
            limpiarTable();
            listarmarca();
        }
    }

    public void listarmarca() {
        Tablas color = new Tablas();
        views.Tablemarca.setDefaultRenderer(views.Tablemarca.getColumnClass(0), color);
        List<Marcas> lista = medDao.ListarMed(views.txtBuscarMed.getText());
        modelo = (DefaultTableModel) views.Tablemarca.getModel();
        Object[] ob = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            ob[0] = lista.get(i).getId();
            ob[1] = lista.get(i).getNombre();
            ob[2] = lista.get(i).getNombre_corto();
            ob[3] = lista.get(i).getEstado();
            modelo.addRow(ob);
        }
        views.Tablemarca.setModel(modelo);
        JTableHeader header = views.Tablemarca.getTableHeader();
        header.setOpaque(false);
        header.setBackground(Color.blue);
        header.setForeground(Color.white);
    }

    public void limpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

}
