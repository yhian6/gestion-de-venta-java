
package view;

import Dao.DetalleVentaDao;
import Dao.ProductoDao;
import Dao.VentaDao;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.DetalleVenta;
import model.Venta;


public class PanelVenta extends javax.swing.JPanel {
DefaultTableModel modeloProducto = new DefaultTableModel();
Double total; 
int cantidad;
ProductoDao prDao = new ProductoDao();
Venta venta = new Venta();
VentaDao ventaDao = new VentaDao();
DetalleVentaDao dvd = new DetalleVentaDao();
DetalleVenta dv = new DetalleVenta();

    public PanelVenta(String nombre, String usuario, String rol ) {
        initComponents();
        
        txtCodigoVenta.setEditable(false);
        txtNombreVenta.setEditable(false);
        txtPrecioVenta.setEditable(false);
        txtStockVenta.setEditable(false);
        lblUsuario.setText(usuario);
        lblRol.setText(rol);
        txtMontoTotal.setEditable(false);
        txtNombreCliente.setEditable(false);
        txtApellidosCliente.setEditable(false);
        txtDniCliente.setEditable(false);
        
    }
    
   public void sumaTotal(){
   
       double suma=0;
       for (int i = 0; i < tblVenta.getRowCount(); i++) {
           suma+= Double.parseDouble(tblVenta.getValueAt(i, 4).toString());
       }
       txtMontoTotal.setText(String.format("%.2f", suma));
   }
   
    public void vuelto() {
        if(!txtMontoTotal.getText().equals("")){
        double mt = Double.parseDouble(txtMontoTotal.getText());
        double pg = Double.parseDouble(txtPagoVenta.getText());
        double vuelto = pg - mt;
        txtVueltoVenta.setText(String.format("%.2f",vuelto));
        }else{
        JOptionPane.showMessageDialog(null, "No hay monto total");
        txtPagoVenta.setText("");
        }
    }
    
    
    
    
    
    

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellidosCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        btnClienteVenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVenta = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtCodigoVenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNombreVenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCantidadVenta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtStockVenta = new javax.swing.JTextField();
        btnProductosVenta = new javax.swing.JButton();
        lblIdVenta = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtPagoVenta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtVueltoVenta = new javax.swing.JTextField();
        btnNuevoVenta1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnEliminarVenta = new javax.swing.JButton();
        btnNuevoVenta = new javax.swing.JButton();
        btnGuardarVenta = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel1.setText("Nombre");

        jLabel2.setText("Apellidos");

        jLabel3.setText("Dni");

        btnClienteVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        btnClienteVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtApellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(478, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnClienteVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        tblVenta.setBackground(new java.awt.Color(255, 204, 102));
        tblVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Precio u.", "Cantidad", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVenta.setRequestFocusEnabled(false);
        tblVenta.setRowHeight(21);
        tblVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVenta);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel6.setText("Código");

        jLabel7.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel7.setText("Nombre");

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel8.setText("Precio");

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel9.setText("Stock");

        txtCantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadVentaKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel10.setText("Cantidad");

        btnProductosVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        btnProductosVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosVentaActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Usuario:");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblUsuario.setText("jLabel18");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Rol:");

        lblRol.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblRol.setText("jLabel18");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnProductosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(183, 183, 183)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(lblIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(lblIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(lblUsuario)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(lblRol))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnProductosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel11.setText("Monto Total");

        txtPagoVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPagoVentaKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel12.setText("Paga con:");

        jLabel13.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel13.setText("Vuelto:");

        btnNuevoVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevoVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoVenta1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel14.setText("$/.");

        jLabel15.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel15.setText("$/.");

        jLabel16.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel16.setText("$/.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVueltoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPagoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnNuevoVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPagoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVueltoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnNuevoVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        btnEliminarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btnEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVentaActionPerformed(evt);
            }
        });

        btnNuevoVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoVentaActionPerformed(evt);
            }
        });

        btnGuardarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        btnGuardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(430, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(btnEliminarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnNuevoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnGuardarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(478, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1430, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1760, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1760, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1156, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1826, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1156, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentaMouseClicked

        int fila= tblVenta.getSelectedRow();
        String codigo=tblVenta.getValueAt(fila, 0).toString();
        txtCodigoVenta.setText(tblVenta.getValueAt(fila, 0).toString());
        txtNombreVenta.setText(tblVenta.getValueAt(fila, 1).toString());
        txtPrecioVenta.setText(tblVenta.getValueAt(fila, 2).toString());
        int stock=prDao.getStock(codigo);
        txtStockVenta.setText(stock + "");
        txtCantidadVenta.setText(tblVenta.getValueAt(fila, 3).toString());
        
        
        
        
    }//GEN-LAST:event_tblVentaMouseClicked

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed
        int fila=tblVenta.getSelectedRow();
        if(fila>=0){
        modeloProducto.setNumRows(fila);
        limpiarProducto();
        sumaTotal();
        txtPagoVenta.setText("");
        txtVueltoVenta.setText("");
        }else{
        JOptionPane.showMessageDialog(null, "Seleccione una fila a eliminar");
        }
    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void btnNuevoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoVentaActionPerformed
        limpiarProducto();
        modeloProducto.setRowCount(0);
        txtMontoTotal.setText("");
        txtPagoVenta.setText("");
        txtVueltoVenta.setText("");
    }//GEN-LAST:event_btnNuevoVentaActionPerformed

    private void btnProductosVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosVentaActionPerformed
         JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);  // Obtener la ventana principal
        Productosfrm productosDialog = new Productosfrm(parentFrame, true);
        productosDialog.setLocationRelativeTo(parentFrame);
        productosDialog.setVisible(true);  // Mostrar el JDialog

        String codigo=productosDialog.getCodigo();
        String nombre = productosDialog.getNombre();
        Double precio = productosDialog.getPrecio();
        int stock = productosDialog.getStock();
        
        if (codigo != null && !nombre.isEmpty() && precio!=null && stock!=0) {
            txtCodigoVenta.setText(codigo);
            txtNombreVenta.setText(nombre);
            txtPrecioVenta.setText(String.format("%.2f", precio));
            txtStockVenta.setText(stock+"");
            txtCantidadVenta.requestFocus();
            
       }
    }//GEN-LAST:event_btnProductosVentaActionPerformed

    private void txtCantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
    if (!txtCodigoVenta.getText().equals("") && !txtNombreVenta.getText().equals("") && !txtPrecioVenta.getText().equals("")) {
        if (!txtCantidadVenta.getText().equals("")) {

            String codigo = txtCodigoVenta.getText();
            String nombre = txtNombreVenta.getText();
            double precio = Double.parseDouble(txtPrecioVenta.getText());
            int stock = Integer.parseInt(txtStockVenta.getText());
            int cantidadIngresada = Integer.parseInt(txtCantidadVenta.getText());

            if (cantidadIngresada <= stock && cantidadIngresada > 0) {
                total = precio * cantidadIngresada;
                modeloProducto = (DefaultTableModel) tblVenta.getModel();
                boolean existe = false;

                for (int i = 0; i < modeloProducto.getRowCount(); i++) {
                    String nombreTabla = tblVenta.getValueAt(i, 1).toString();

                    if (nombreTabla.equals(nombre)) {
                        int cantidadExistente = Integer.parseInt(tblVenta.getValueAt(i, 3).toString());

                        // Aquí evaluamos si actualizar o advertir
                        if (cantidadIngresada > stock) {
                            JOptionPane.showMessageDialog(null, "No hay stock suficiente para esa cantidad.");
                            return;
                        }

                        double nuevoTotal = precio * cantidadIngresada;

                        // Actualizamos con la nueva cantidad (sea menor o mayor)
                        tblVenta.setValueAt(cantidadIngresada, i, 3);
                        tblVenta.setValueAt(nuevoTotal, i, 4);
                        existe = true;
                        break;
                    }
                }

                // Si el producto no está en la tabla, lo agregamos como nuevo
                
                
                if (!existe) {
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

        tblVenta.getColumnModel().getColumn(2).setCellRenderer(renderer);
        tblVenta.getColumnModel().getColumn(4).setCellRenderer(renderer);
                    
                    Object[] fila = new Object[5];
                    fila[0] = codigo;
                    fila[1] = nombre;
                    fila[2] = precio;
                    fila[3] = cantidadIngresada;
                    fila[4] = total;
                    modeloProducto.addRow(fila);
                }

                tblVenta.setModel(modeloProducto);
                limpiarProducto();
                sumaTotal();
                txtPagoVenta.setText("");
                txtVueltoVenta.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "Cantidad inválida o superior al stock.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese la cantidad");
            txtCantidadVenta.setText("");
            txtCantidadVenta.requestFocus();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Debe ingresar el producto");
    }
}

    }//GEN-LAST:event_txtCantidadVentaKeyPressed

    private void txtPagoVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagoVentaKeyReleased

        if(!txtPagoVenta.getText().equals("")){
        vuelto();
        }else{
        txtVueltoVenta.setText("");
        }
        
    }//GEN-LAST:event_txtPagoVentaKeyReleased

    private void btnNuevoVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoVenta1ActionPerformed
        txtPagoVenta.setText("");
        txtVueltoVenta.setText("");
        txtPagoVenta.requestFocus();
    }//GEN-LAST:event_btnNuevoVenta1ActionPerformed

    private void btnGuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVentaActionPerformed

      String dni = txtDniCliente.getText();
      String usuario=  lblUsuario.getText();
      
      
        if (tblVenta.getRowCount() > 0) {
            Double total = Double.parseDouble(txtMontoTotal.getText());
            
            

            // Mostrar confirmación
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea registrar la venta?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
           

            if (ventaDao.guardarVenta(usuario, dni, total)) {
                JOptionPane.showMessageDialog(null, "Venta registrada con éxito");
                Long id_venta = ventaDao.idVenta(usuario);
                
                // Actualizar stock
                for (int i = 0; i < tblVenta.getRowCount(); i++) {
                    String codigo = tblVenta.getValueAt(i, 0).toString();
                    int cantidad = Integer.parseInt(tblVenta.getValueAt(i, 3).toString());
                    ventaDao.actualizarStock(codigo, cantidad);
                }

                // Guardar detalle
                for (int i = 0; i < tblVenta.getRowCount(); i++) {
                    String codigo = tblVenta.getValueAt(i, 0).toString();
                    int cantidad = Integer.parseInt(tblVenta.getValueAt(i, 3).toString());
                    double precio_unitario = Double.parseDouble(tblVenta.getValueAt(i, 2).toString());

                    Long id_producto = ventaDao.idProducto(codigo);
                    dvd.guardarDetalle(id_venta, id_producto, cantidad, precio_unitario);
                }
                
                modeloProducto.setRowCount(0);
                txtNombreCliente.setText("");
                txtApellidosCliente.setText("");
                txtDniCliente.setText("");

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar la venta");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Registro cancelado");
        }

        } else {
            JOptionPane.showMessageDialog(null, "La venta está vacía ");
        }

    }//GEN-LAST:event_btnGuardarVentaActionPerformed

    private void btnClienteVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteVentaActionPerformed

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);  // Obtener la ventana principal
        Clientefrm clienteDialog = new Clientefrm(parentFrame, true);
        clienteDialog.setLocationRelativeTo(parentFrame);
        clienteDialog.setVisible(true); 
        
        txtNombreCliente.setText(clienteDialog.nombre);
        txtApellidosCliente.setText(clienteDialog.apellidos);
        txtDniCliente.setText(clienteDialog.dni);
        
    }//GEN-LAST:event_btnClienteVentaActionPerformed

    private void txtCantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadVentaKeyTyped

         Character c=evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }

        
    }//GEN-LAST:event_txtCantidadVentaKeyTyped


    public void limpiarProducto(){
    
        txtCodigoVenta.setText("");
        txtNombreVenta.setText("");
        txtPrecioVenta.setText("");
        txtStockVenta.setText("");
        txtCantidadVenta.setText("");
        txtNombreCliente.setText("");
        txtApellidosCliente.setText("");
        txtDniCliente.setText("");
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClienteVenta;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnGuardarVenta;
    private javax.swing.JButton btnNuevoVenta;
    private javax.swing.JButton btnNuevoVenta1;
    private javax.swing.JButton btnProductosVenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIdVenta;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTable tblVenta;
    private javax.swing.JTextField txtApellidosCliente;
    private javax.swing.JTextField txtCantidadVenta;
    private javax.swing.JTextField txtCodigoVenta;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreVenta;
    private javax.swing.JTextField txtPagoVenta;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStockVenta;
    private javax.swing.JTextField txtVueltoVenta;
    // End of variables declaration//GEN-END:variables
}
