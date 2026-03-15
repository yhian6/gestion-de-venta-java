
package view;

import Dao.ProductoDao;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Producto;


public class Productosfrm extends javax.swing.JDialog {

    Producto pr = new Producto();
    ProductoDao productoDao = new ProductoDao();
    String nombre;
    String codigo;
    Double precio;
    int stock;
    
    
    DefaultTableModel modeloProducto = new DefaultTableModel();
    
    public void listarProductos() {

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            DecimalFormat df = new DecimalFormat("0.00");

            @Override
            public void setValue(Object value) {
                if (value instanceof Number) {
                    setText(df.format(value));
                } else {
                    super.setValue(value);
                }
            }
        };

        tblProducto.getColumnModel().getColumn(6).setCellRenderer(renderer);
        modeloProducto.setRowCount(0);
        modeloProducto = (DefaultTableModel) tblProducto.getModel();
        DecimalFormat df = new DecimalFormat("0.00");
        List<Producto> listar = new ArrayList();
        listar = productoDao.listarProductos(true);
        for (Producto p : listar) {
            Object[] ob = new Object[11];
            ob[0] = p.getId();
            ob[1] = p.getCodigo();
            ob[2] = p.getNombre();
            ob[3] = p.getDescripcion();
            ob[4] = p.getCategoria().getNombre_categoria();
            ob[5] = p.getUm().getNombre();
            ob[6] = df.format(p.getPrecioVenta());
            ob[7] = p.getStock();
            ob[8] = p.getUbicacion();
            ob[9] = p.getEstado();
            modeloProducto.addRow(ob);
        }
        tblProducto.setModel(modeloProducto);

    }
    
    
    public Productosfrm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        DefaultTableCellRenderer izquierda = new DefaultTableCellRenderer();
        izquierda.setHorizontalAlignment(SwingConstants.CENTER);
        tblProducto.getColumnModel().getColumn(0).setCellRenderer(izquierda);
        tblProducto.getColumnModel().getColumn(7).setCellRenderer(izquierda);
        listarProductos();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtBuscarProducto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblProducto.setBackground(new java.awt.Color(255, 204, 102));
        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Código", "Nombre", "Descripción", "Categoria", "U. de Medida", "Precio", "Stock", "Ubicacion", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProducto.setRequestFocusEnabled(false);
        tblProducto.setRowHeight(21);
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblProducto);

        jLabel30.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel30.setText("Buscar");

        txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked

        int fila=tblProducto.getSelectedRow();
       
        codigo= tblProducto.getValueAt(fila, 1).toString();
        nombre= tblProducto.getValueAt(fila, 2).toString();
        precio= Double.parseDouble(tblProducto.getValueAt(fila, 6).toString());
        stock= Integer.parseInt(tblProducto.getValueAt(fila, 7).toString());
         dispose();
        
        
        
        
    }//GEN-LAST:event_tblProductoMouseClicked

    private void txtBuscarProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyReleased
        String nombre = txtBuscarProducto.getText().trim();
        String descripcion = txtBuscarProducto.getText().trim();
        String categoria = txtBuscarProducto.getText().trim();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            DecimalFormat df = new DecimalFormat("0.00");

            @Override
            public void setValue(Object value) {
                if (value instanceof Number) {
                    setText(df.format(value));
                } else {
                    super.setValue(value);
                }
            }
        };

        tblProducto.getColumnModel().getColumn(6).setCellRenderer(renderer);
        
        modeloProducto.setRowCount(0);
        modeloProducto = (DefaultTableModel) tblProducto.getModel();
        DecimalFormat df = new DecimalFormat("0.00");
        List<Producto> buscar = new ArrayList();
        buscar = productoDao.buscarProducto(nombre, descripcion, categoria);
        for (Producto p : buscar) {
            Object[] ob = new Object[11];
            ob[0] = p.getId();
            ob[1] = p.getCodigo();
            ob[2] = p.getNombre();
            ob[3] = p.getDescripcion();
            ob[4] = p.getCategoria().getNombre_categoria();
            ob[5] = p.getUm().getNombre();
            ob[6] = df.format(p.getPrecioVenta());
            ob[7] = p.getStock();
            ob[8] = p.getUbicacion();
            ob[9] = p.getEstado();
            modeloProducto.addRow(ob);
        }
        tblProducto.setModel(modeloProducto);
    }//GEN-LAST:event_txtBuscarProductoKeyReleased

    public String getCodigo(){
    return codigo;
    }
    
    public String getNombre(){
    return nombre;
    }
    
    public Double getPrecio(){
    return precio;
    }
    
    public int getStock(){
    return stock;
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Productosfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productosfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productosfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productosfrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Productosfrm dialog = new Productosfrm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBuscarProducto;
    // End of variables declaration//GEN-END:variables
}
