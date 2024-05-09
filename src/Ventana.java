import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Ventana {
    private JPanel Ventana;
    private JTextField textCedula;
    private JTextField textNombre;
    private JTextField textEdad;
    private JComboBox cBoxGenero1;
    private JComboBox cBoxRegion2;
    private JButton añadirButton;
    private JList list1;
    private JButton buscarGeneroEdadButton;
    private JButton buscarEdadRegionButton;
    private JList list2;

    private Cola empleados= new Cola();
    DefaultListModel dlm = new DefaultListModel();
    DefaultListModel dlm1 = new DefaultListModel();

    public Ventana(){

        añadirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    empleados.encolar(textCedula.getText(),textNombre.getText(),
                            Integer.parseInt(textEdad.getText()),cBoxGenero1.getSelectedItem().toString(),
                                    cBoxRegion2.getSelectedItem().toString());
                    List<Persona> lista = empleados.listarPersonas();
                    llenarJList(lista,list1,dlm);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                setearCampos();
            }
        });
        buscarGeneroEdadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Persona> listaGE = empleados.listaEdadSexo(Integer.parseInt(textEdad.getText()), cBoxGenero1.getSelectedItem().toString());
                        if(listaGE.isEmpty())
                            JOptionPane.showMessageDialog(null, "busqueda no coincide");
                        else
                            llenarJList(listaGE,list2,dlm1);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        buscarEdadRegionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    List<Persona> listaER = empleados.listaEdadRegion(Integer.parseInt(textEdad.getText()), cBoxRegion2.getSelectedItem().toString());

                    if(listaER.isEmpty())
                        JOptionPane.showMessageDialog(null, "busqueda no coincide");
                    else
                        llenarJList(listaER,list2,dlm1);
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
        });
    }

    public void setearCampos(){
        textCedula.setText("");
        textNombre.setText("");
        textEdad.setText("");
        cBoxGenero1.setSelectedIndex(0);
        cBoxRegion2.setSelectedIndex(0);
    }

    public void llenarJList(List<Persona> lista, JList listMostrar, DefaultListModel dl){
        dl.removeAllElements();
        Collections.sort(lista);
        for(Persona p: lista)
            dl.addElement(p);
        listMostrar.setModel(dl);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
