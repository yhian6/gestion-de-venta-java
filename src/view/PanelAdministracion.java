package view;

import Dao.CategoriaDao;
import Dao.CompraDao;
import Dao.DetalleCompraDao;
import Dao.EmpleadoDao;
import Dao.ProductoDao;
import Dao.UnidadMedidaDao;
import Dao.UsuarioDao;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Categoria;
import model.Compra;
import model.DetalleCompra;
import model.Empleado;
import model.Producto;
import model.UnidadMedida;
import model.Usuario;
import model.Venta;

public class PanelAdministracion extends javax.swing.JPanel {

    Empleado empleado = new Empleado();
    EmpleadoDao empleadoDao = new EmpleadoDao();
    Categoria categoria = new Categoria();
    CategoriaDao categoriaDao = new CategoriaDao();
    Usuario usuario = new Usuario();
    UsuarioDao usuarioDao = new UsuarioDao();
    UnidadMedida um = new UnidadMedida();
    UnidadMedidaDao umd = new UnidadMedidaDao();
    Producto pr = new Producto();
    ProductoDao productoDao = new ProductoDao();
    Compra compra = new Compra();
    CompraDao compraDao = new CompraDao();
    DetalleCompra dc = new DetalleCompra();
    DetalleCompraDao dcd = new DetalleCompraDao();

    DefaultTableModel modelo = new DefaultTableModel();
    DefaultTableModel modeloCategoria = new DefaultTableModel();
    DefaultTableModel modeloUsuario = new DefaultTableModel();
    DefaultTableModel modeloMedida = new DefaultTableModel();
    DefaultTableModel modeloProducto = new DefaultTableModel();
    DefaultComboBoxModel<String> modeloComboCategoria = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<String> modeloComboUm = new DefaultComboBoxModel<>();
    DefaultTableModel modeloCompra = new DefaultTableModel();
    
    Double total; 
    int cantidad;

    public PanelAdministracion(String usuario, String rol) {
        initComponents();
        DefaultTableCellRenderer izquierda = new DefaultTableCellRenderer();
        izquierda.setHorizontalAlignment(SwingConstants.CENTER);
        tblEmpleado.getColumnModel().getColumn(0).setCellRenderer(izquierda);
        tblCategoria.getColumnModel().getColumn(0).setCellRenderer(izquierda);
        tblUsuario.getColumnModel().getColumn(0).setCellRenderer(izquierda);
        tblMedida.getColumnModel().getColumn(0).setCellRenderer(izquierda);
        tblProducto.getColumnModel().getColumn(0).setCellRenderer(izquierda);
        listarEmpleados();
        listarCategoria();
        listarUsuario();
        listarMedida();
        listarProductos();
        listarJcbCategoria();
        listarJcbUnidadMedida();
        listarCompras();
                
        lblIdEmpleado.setVisible(false);
        btnActualizarEmpleado.setEnabled(false);
        btnEliminarEmpleado.setEnabled(false);
        btnActualizarUsuario.setEnabled(false);
        btnEliminarUsuario.setEnabled(false);
        lblIdCategoria.setVisible(false);
        lblIdUsuario.setVisible(false);
        btnActualizarCategoria.setEnabled(false);
        btnEliminarCategoria.setEnabled(false);
        lblIdMedida.setVisible(false);
        btnActualizarMedida.setEnabled(false);
        btnEliminarMedida.setEnabled(false);
        txtNombreUsuario.setEditable(false);
        txtApellidosUsuario.setEditable(false);
        lblIdProducto.setVisible(false);
        btnActualizarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        lblUsuario.setText(usuario);
        lblRol.setText(rol);
        lblUsuario1.setText(usuario);
        lblRol1.setText(rol);
        lblUsuario2.setText(usuario);
        lblRol2.setText(rol);
        lblUsuario3.setText(usuario);
        lblRol3.setText(rol);
        lblUsuario4.setText(usuario);
        lblRol4.setText(rol);
        lblUsuarioCompra.setText(usuario);
        lblRol5.setText(rol);
        lblUsuario5.setText(usuario);
        lblRol6.setText(rol);
        
        txtCodigoCompra.setEditable(false);
        txtNombreCompra.setEditable(false);
        txtPrecioCompra.setEditable(false);
        txtNombrePro.setEditable(false);
        agregarMenuContextual();
        
    }

    public void listarEmpleados() {
        modelo.setRowCount(0);
        modelo = (DefaultTableModel) tblEmpleado.getModel();
        List<Empleado> listar = new ArrayList();
        listar = empleadoDao.listarEmpleado();
        for (Empleado e : listar) {
            Object[] ob = new Object[7];
            ob[0] = e.getId();
            ob[1] = e.getNombre();
            ob[2] = e.getApellidos();
            ob[3] = e.getTelefono();
            ob[4] = e.getDireccion();
            ob[5] = e.getDni();
            ob[6] = e.getRol().getNombre_rol();
            modelo.addRow(ob);
        }

        tblEmpleado.setModel(modelo);
    }

    public void listarCategoria() {
        modeloCategoria.setRowCount(0);
        modeloCategoria = (DefaultTableModel) tblCategoria.getModel();
        List<Categoria> listar = new ArrayList();
        listar = categoriaDao.listarCategoria();
        for (Categoria ca : listar) {
            Object[] ob = new Object[2];
            ob[0] = ca.getId();
            ob[1] = ca.getNombre_categoria();

            modeloCategoria.addRow(ob);
        }

        tblCategoria.setModel(modeloCategoria);
    }

    public void listarUsuario() {
        modeloUsuario.setRowCount(0);
        modeloUsuario = (DefaultTableModel) tblUsuario.getModel();
        List<Usuario> listar = new ArrayList();
        listar = usuarioDao.listarUsuario();
        for (Usuario u : listar) {
            Object[] ob = new Object[6];
            ob[0] = u.getId();
            ob[1] = u.getEmpleado().getNombre();
            ob[2] = u.getEmpleado().getApellidos();
            ob[3] = u.getUsuario();
            ob[4] = u.getContraseña();
            ob[5] = u.getEstado();

            modeloUsuario.addRow(ob);
        }

        tblUsuario.setModel(modeloUsuario);
    }

    public void listarMedida() {
        modeloMedida.setRowCount(0);
        modeloMedida = (DefaultTableModel) tblMedida.getModel();
        List<UnidadMedida> listar = new ArrayList();
        listar = umd.listarMedida();
        for (UnidadMedida um : listar) {
            Object[] ob = new Object[3];
            ob[0] = um.getId();
            ob[1] = um.getNombre();
            ob[2] = um.getAbreviacion();

            modeloMedida.addRow(ob);
        }

        tblMedida.setModel(modeloMedida);
    }

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
        tblProducto.getColumnModel().getColumn(7).setCellRenderer(renderer);
        modeloProducto.setRowCount(0);
        modeloProducto = (DefaultTableModel) tblProducto.getModel();
        DecimalFormat df = new DecimalFormat("0.00");
        List<Producto> listar = new ArrayList();
        listar = productoDao.listarProductos(false);
        for (Producto p : listar) {
            Object[] ob = new Object[11];
            ob[0] = p.getId();
            ob[1] = p.getCodigo();
            ob[2] = p.getNombre();
            ob[3] = p.getDescripcion();
            ob[4] = p.getCategoria().getNombre_categoria();
            ob[5] = p.getUm().getNombre();
            ob[6] = df.format(p.getPrecioCompra());
            ob[7] = df.format(p.getPrecioVenta());
            ob[8] = p.getStock();
            ob[9] = p.getUbicacion();
            ob[10] = p.getEstado();
            modeloProducto.addRow(ob);
        }
        tblProducto.setModel(modeloProducto);

    }

    public void listarJcbCategoria() {
        modeloComboCategoria.removeAllElements();
        modeloComboCategoria = (DefaultComboBoxModel<String>) cboCategoriaProducto.getModel();
        List<String> listarCa = new ArrayList();
        listarCa = productoDao.listarCategoria();
        modeloComboCategoria.addAll(listarCa);
        cboCategoriaProducto.setModel(modeloComboCategoria);
        cboCategoriaProducto.setSelectedIndex(-1);

        cboCategoriaProducto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == -1 && value == null) {
                    setText("Seleccione una categoría");
                }
                return this;
            }
        });

    }

    public void listarJcbUnidadMedida() {
        modeloComboUm.removeAllElements();
        modeloComboUm = (DefaultComboBoxModel<String>) cboUmProducto.getModel();
        List<String> listarUm = new ArrayList();
        listarUm = productoDao.listarUm();
        modeloComboUm.addAll(listarUm);
        cboUmProducto.setModel(modeloComboUm);
        cboUmProducto.setSelectedIndex(-1);
        cboUmProducto.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == -1 && value == null) {
                    setText("Seleccione una categoría");
                }
                return this;
            }
        });
    }
    
    public void listarCompras() {
        modeloCompra.setRowCount(0);
        modeloCompra = (DefaultTableModel) tblListaCompra.getModel();
        List<Compra> listar = compraDao.listarCompras();
        for(Compra c : listar){
            Object[] ob = new Object[5];
            ob[0]=c.getId();
            ob[1]=c.getFecha();
            ob[2]=c.getProveedor().getNombre();
            ob[3]=c.getUsuario().getUsuario();
            ob[4]=c.getTotal();
            modeloCompra.addRow(ob);
        }
        
        tblListaCompra.setModel(modeloCompra);

    }
    
    private void agregarMenuContextual() {
        // Crear menú y opción
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem itemDetalle = new JMenuItem("Ver Detalle de Compra");
        popupMenu.add(itemDetalle);
        tblListaCompra.setComponentPopupMenu(popupMenu);

        // Seleccionar la fila con clic derecho
        tblListaCompra.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int fila = tblListaCompra.rowAtPoint(e.getPoint());
                if (fila >= 0) {
                    tblListaCompra.setRowSelectionInterval(fila, fila);
                }
            }
        });

        // Acción al hacer clic en "Ver Detalle de Venta"
        itemDetalle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int fila = tblListaCompra.getSelectedRow();
                if (fila >= 0) {
                    Long idCompra = Long.parseLong(tblListaCompra.getValueAt(fila, 0).toString());

                    // 👇 Este es el nuevo código que reemplaza al anterior
                    DetalleCompraForm detalle = new DetalleCompraForm(
                            (Frame) SwingUtilities.getWindowAncestor(PanelAdministracion.this),
                            true,
                            idCompra
                    );
                    detalle.setLocationRelativeTo(null);
                    detalle.setVisible(true);
                }
            }
        });

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mytp = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JTextField();
        txtTelefonoEmpleado = new javax.swing.JTextField();
        txtApellidosEmpleado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnNuevoEmpleado = new javax.swing.JButton();
        btnGuardarEmpleado = new javax.swing.JButton();
        btnActualizarEmpleado = new javax.swing.JButton();
        btnEliminarEmpleado = new javax.swing.JButton();
        lblIdEmpleado = new javax.swing.JLabel();
        txtDireccionEmpleado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDniEmpleado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cboRolEmpleado = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtBuscarEmpleado = new javax.swing.JTextField();
        lblRol = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtNombreCategoria = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btnNuevoCategoria = new javax.swing.JButton();
        btnGuardarCategoria = new javax.swing.JButton();
        btnActualizarCategoria = new javax.swing.JButton();
        btnEliminarCategoria = new javax.swing.JButton();
        lblIdCategoria = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtBuscarCategoria = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        lblUsuario1 = new javax.swing.JLabel();
        lblRol1 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCategoria = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        txtContraseniaUsuario = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnNuevoUsuario = new javax.swing.JButton();
        btnGuardarUsuario = new javax.swing.JButton();
        btnActualizarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        lblIdUsuario = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cboEstadoUsuario = new javax.swing.JComboBox<>();
        btnEmpleados = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtApellidosUsuario = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtBuscarUsuario = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        lblUsuario2 = new javax.swing.JLabel();
        lblRol2 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtNombreMedida = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnNuevoMedida = new javax.swing.JButton();
        btnGuardarMedida = new javax.swing.JButton();
        btnActualizarMedida = new javax.swing.JButton();
        btnEliminarMedida = new javax.swing.JButton();
        lblIdMedida = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtAbreviacionMedida = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtBuscarMedida = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        lblUsuario3 = new javax.swing.JLabel();
        lblRol3 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMedida = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        txtDescripcionProducto = new javax.swing.JTextField();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        btnNuevoProducto = new javax.swing.JButton();
        btnGuardarProducto = new javax.swing.JButton();
        btnActualizarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        lblIdProducto = new javax.swing.JLabel();
        txtPrecioCompraProducto = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cboCategoriaProducto = new javax.swing.JComboBox<>();
        txtPrecioVentaProducto = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        cboEstadoProducto = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        cboUmProducto = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtUbicacionProducto = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtBuscarProducto = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        lblUsuario4 = new javax.swing.JLabel();
        lblRol4 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        txtCodigoCompra = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtNombreCompra = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        btnProductosVenta = new javax.swing.JButton();
        lblIdVenta = new javax.swing.JLabel();
        txtCantidadCompra = new javax.swing.JTextField();
        txtFv = new com.toedter.calendar.JDateChooser();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblCompra = new javax.swing.JTable();
        btnEliminarVenta = new javax.swing.JButton();
        btnNuevoVenta = new javax.swing.JButton();
        btnGuardarVenta = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        lblUsuarioCompra = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        lblRol5 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txtNombrePro = new javax.swing.JTextField();
        btnProductosVenta1 = new javax.swing.JButton();
        lblId = new javax.swing.JLabel();
        lblId1 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        txtBuscarCompra = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        lblUsuario5 = new javax.swing.JLabel();
        lblRol6 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblListaCompra = new javax.swing.JTable();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel2.setText("Apellidos");

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel3.setText("Teléfono");

        txtNombreEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreEmpleadoActionPerformed(evt);
            }
        });

        txtTelefonoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoEmpleadoActionPerformed(evt);
            }
        });
        txtTelefonoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoEmpleadoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel5.setText("EMPLEADO");

        btnNuevoEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevoEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoEmpleadoActionPerformed(evt);
            }
        });

        btnGuardarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        btnGuardarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarEmpleadoActionPerformed(evt);
            }
        });

        btnActualizarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actualizar (2).png"))); // NOI18N
        btnActualizarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarEmpleadoActionPerformed(evt);
            }
        });

        btnEliminarEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btnEliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarEmpleadoActionPerformed(evt);
            }
        });

        txtDireccionEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionEmpleadoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel6.setText("Dirección");

        jLabel7.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel7.setText("Dni");

        txtDniEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDniEmpleadoActionPerformed(evt);
            }
        });
        txtDniEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniEmpleadoKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel8.setText("Rol");

        cboRolEmpleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Vendedor" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtApellidosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(txtTelefonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDireccionEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDniEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboRolEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(164, 164, 164)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnGuardarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnActualizarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnEliminarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(txtApellidosEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDireccionEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDniEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboRolEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnNuevoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel4.setText("Buscar");

        txtBuscarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarEmpleadoActionPerformed(evt);
            }
        });
        txtBuscarEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarEmpleadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarEmpleadoKeyReleased(evt);
            }
        });

        lblRol.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblRol.setText("jLabel18");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblUsuario.setText("jLabel18");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Rol:");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("Usuario:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 845, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario)
                            .addComponent(jLabel35))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(lblRol)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtBuscarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        tblEmpleado.setBackground(new java.awt.Color(255, 204, 102));
        tblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellidos", "Teléfono", "Dirección", "Dni", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmpleado.setRequestFocusEnabled(false);
        tblEmpleado.setRowHeight(21);
        tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(455, 455, 455))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(777, Short.MAX_VALUE))
        );

        mytp.addTab("Empleados", jPanel1);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel17.setText("Nombre");

        txtNombreCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCategoriaActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel20.setText("CATEGORIA");

        btnNuevoCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCategoriaActionPerformed(evt);
            }
        });

        btnGuardarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        btnGuardarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCategoriaActionPerformed(evt);
            }
        });

        btnActualizarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actualizar (2).png"))); // NOI18N
        btnActualizarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCategoriaActionPerformed(evt);
            }
        });

        btnEliminarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btnEliminarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel12Layout.createSequentialGroup()
                            .addGap(164, 164, 164)
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnGuardarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btnActualizarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel20))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblIdCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel17)
                .addGap(5, 5, 5)
                .addComponent(txtNombreCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnNuevoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel24.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel24.setText("Buscar");

        txtBuscarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCategoriaActionPerformed(evt);
            }
        });
        txtBuscarCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarCategoriaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarCategoriaKeyReleased(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Usuario:");

        lblUsuario1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblUsuario1.setText("jLabel18");

        lblRol1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblRol1.setText("jLabel18");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("Rol:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 843, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel38)
                    .addComponent(jLabel37))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario1)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(lblRol1)))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(txtBuscarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        tblCategoria.setBackground(new java.awt.Color(255, 204, 102));
        tblCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCategoria.setRequestFocusEnabled(false);
        tblCategoria.setRowHeight(21);
        tblCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCategoriaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblCategoria);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(455, 455, 455))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(816, Short.MAX_VALUE))
        );

        mytp.addTab("Categoria", jPanel2);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel9.setText("Nombre Empleado");

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel10.setText("Usuario");

        jLabel11.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel11.setText("Contraseña");

        txtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUsuarioActionPerformed(evt);
            }
        });

        txtContraseniaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseniaUsuarioActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel12.setText("Usuario");

        btnNuevoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoUsuarioActionPerformed(evt);
            }
        });

        btnGuardarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        btnActualizarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actualizar (2).png"))); // NOI18N
        btnActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarUsuarioActionPerformed(evt);
            }
        });

        btnEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel13.setText("Estado");

        cboEstadoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        btnEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/office-worker (1).png"))); // NOI18N
        btnEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadosActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel14.setText("Apellidos Empleado");

        txtApellidosUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(txtContraseniaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtApellidosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGap(164, 164, 164)
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnActualizarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel12))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(txtApellidosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContraseniaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboEstadoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel16.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel16.setText("Buscar");

        txtBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarUsuarioActionPerformed(evt);
            }
        });
        txtBuscarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarUsuarioKeyReleased(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Usuario:");

        lblUsuario2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblUsuario2.setText("jLabel18");

        lblRol2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblRol2.setText("jLabel18");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("Rol:");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 846, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40)
                    .addComponent(jLabel39))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario2)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(lblRol2)))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(txtBuscarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        tblUsuario.setBackground(new java.awt.Color(255, 204, 102));
        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellidos", "Usuario", "Contraseña", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Byte.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuario.setRequestFocusEnabled(false);
        tblUsuario.setRowHeight(21);
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblUsuario);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(455, 455, 455))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(785, Short.MAX_VALUE))
        );

        mytp.addTab("Usuarios", jPanel7);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel18.setText("Nombre");

        txtNombreMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreMedidaActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel21.setText("Unidad de Medida");

        btnNuevoMedida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevoMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoMedidaActionPerformed(evt);
            }
        });

        btnGuardarMedida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        btnGuardarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMedidaActionPerformed(evt);
            }
        });

        btnActualizarMedida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actualizar (2).png"))); // NOI18N
        btnActualizarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarMedidaActionPerformed(evt);
            }
        });

        btnEliminarMedida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btnEliminarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMedidaActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel19.setText("Abreviación");

        txtAbreviacionMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbreviacionMedidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel18Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNombreMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAbreviacionMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel21)
                            .addGap(68, 68, 68)
                            .addComponent(lblIdMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnGuardarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(btnActualizarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnEliminarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel21))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblIdMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel18)
                .addGap(5, 5, 5)
                .addComponent(txtNombreMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addGap(5, 5, 5)
                .addComponent(txtAbreviacionMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnNuevoMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel25.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel25.setText("Buscar");

        txtBuscarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarMedidaActionPerformed(evt);
            }
        });
        txtBuscarMedida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarMedidaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarMedidaKeyReleased(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setText("Usuario:");

        lblUsuario3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblUsuario3.setText("jLabel18");

        lblRol3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblRol3.setText("jLabel18");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setText("Rol:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 852, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario3)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(lblRol3)))
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25)
                        .addComponent(txtBuscarMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        tblMedida.setBackground(new java.awt.Color(255, 204, 102));
        tblMedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Abreviación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Byte.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMedida.setRequestFocusEnabled(false);
        tblMedida.setRowHeight(21);
        tblMedida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMedidaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMedida);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(455, 455, 455))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(805, Short.MAX_VALUE))
        );

        mytp.addTab("U. de medida", jPanel16);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel15.setText("Código");

        jLabel22.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel22.setText("Nombre");

        jLabel23.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel23.setText("Descripción");

        txtCodigoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProductoActionPerformed(evt);
            }
        });

        txtDescripcionProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionProductoActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel26.setText("Producto");

        btnNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        btnNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProductoActionPerformed(evt);
            }
        });

        btnGuardarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/GuardarTodo.png"))); // NOI18N
        btnGuardarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarProductoActionPerformed(evt);
            }
        });

        btnActualizarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Actualizar (2).png"))); // NOI18N
        btnActualizarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProductoActionPerformed(evt);
            }
        });

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        txtPrecioCompraProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCompraProductoActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel27.setText("Precio compra");

        jLabel28.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel28.setText("Precio venta");

        jLabel29.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel29.setText("Categoria");

        cboCategoriaProducto.setToolTipText("");
        cboCategoriaProducto.setName(""); // NOI18N
        cboCategoriaProducto.setVerifyInputWhenFocusTarget(false);

        txtPrecioVentaProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioVentaProductoActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel32.setText("Estado");

        cboEstadoProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        jLabel33.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel33.setText("U. de medida");

        jLabel34.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel34.setText("Ubicación");

        txtUbicacionProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUbicacionProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPrecioCompraProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(38, 38, 38)
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPrecioVentaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cboCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(51, 51, 51)
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel33)
                                        .addComponent(cboUmProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22)
                                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel25Layout.createSequentialGroup()
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel25Layout.createSequentialGroup()
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(56, 56, 56))
                                        .addGroup(jPanel25Layout.createSequentialGroup()
                                            .addComponent(txtUbicacionProducto)
                                            .addGap(38, 38, 38)))
                                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cboEstadoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(48, 48, 48))))
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGap(164, 164, 164)
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnGuardarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(btnActualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel26))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel15)
                .addGap(5, 5, 5)
                .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(3, 3, 3)
                .addComponent(txtNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboUmProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioCompraProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtPrecioVentaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUbicacionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboEstadoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnNuevoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel30.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel30.setText("Buscar");

        txtBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarProductoActionPerformed(evt);
            }
        });
        txtBuscarProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProductoKeyReleased(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setText("Usuario:");

        lblUsuario4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblUsuario4.setText("jLabel18");

        lblRol4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblRol4.setText("jLabel18");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setText("Rol:");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 849, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel43))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol4, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario4)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44)
                            .addComponent(lblRol4)))
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(txtBuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        tblProducto.setBackground(new java.awt.Color(255, 204, 102));
        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Código", "Nombre", "Descripción", "Categoria", "U. de Medida", "Precio Compra", "Precio Venta", "Stock", "Ubicacion", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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
        if (tblProducto.getColumnModel().getColumnCount() > 0) {
            tblProducto.getColumnModel().getColumn(5).setHeaderValue("U. de Medida");
            tblProducto.getColumnModel().getColumn(6).setHeaderValue("Precio Compra");
            tblProducto.getColumnModel().getColumn(7).setHeaderValue("Precio Venta");
            tblProducto.getColumnModel().getColumn(8).setHeaderValue("Stock");
            tblProducto.getColumnModel().getColumn(9).setHeaderValue("Ubicacion");
            tblProducto.getColumnModel().getColumn(10).setHeaderValue("Estado");
        }

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(401, 401, 401))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(779, Short.MAX_VALUE))
        );

        mytp.addTab("Productos", jPanel21);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        jLabel31.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel31.setText("Código");

        jLabel45.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel45.setText("Nombre");

        jLabel46.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel46.setText("Precio");

        jLabel47.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel47.setText("Cantidad");

        btnProductosVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        btnProductosVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosVentaActionPerformed(evt);
            }
        });

        txtCantidadCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadCompraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadCompraKeyTyped(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel51.setText("Fecha de vencimiento");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(lblIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(txtCodigoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(txtNombreCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnProductosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFv, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCantidadCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGap(56, 56, 56))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(lblIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(8, 8, 8)
                                .addComponent(txtCodigoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnProductosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(8, 8, 8)
                                .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel29Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblCompra.setBackground(new java.awt.Color(255, 204, 102));
        tblCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Precio u.", "Cantidad", "Fecha de vencimiento", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCompra.setRequestFocusEnabled(false);
        tblCompra.setRowHeight(21);
        tblCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCompraMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblCompra);

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

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel49.setText("Usuario:");

        lblUsuarioCompra.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblUsuarioCompra.setText("jLabel18");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setText("Rol:");

        lblRol5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblRol5.setText("jLabel18");

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel50)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuarioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuarioCompra)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(lblRol5))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor"));

        jLabel48.setFont(new java.awt.Font("Lucida Fax", 0, 12)); // NOI18N
        jLabel48.setText("Nombre");

        btnProductosVenta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        btnProductosVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosVenta1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(txtNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnProductosVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblId)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblId1)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombrePro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProductosVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(0, 58, Short.MAX_VALUE)
                    .addComponent(lblId)
                    .addGap(0, 57, Short.MAX_VALUE)))
            .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel35Layout.createSequentialGroup()
                    .addGap(0, 58, Short.MAX_VALUE)
                    .addComponent(lblId1)
                    .addGap(0, 57, Short.MAX_VALUE)))
        );

        jPanel22.setBackground(new java.awt.Color(204, 255, 204));

        jLabel53.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel53.setText("Monto Total");

        jLabel54.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        jLabel54.setText("$/.");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(114, 114, 114)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(114, Short.MAX_VALUE)))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1044, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminarVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGuardarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(79, 79, 79))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(btnEliminarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btnNuevoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnGuardarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1282, Short.MAX_VALUE))
        );

        mytp.addTab("Compras", jPanel28);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1442, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1229, Short.MAX_VALUE)
        );

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));
        jPanel36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setFont(new java.awt.Font("Lucida Fax", 1, 18)); // NOI18N
        jLabel65.setText("Buscar");

        txtBuscarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarCompraActionPerformed(evt);
            }
        });
        txtBuscarCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarCompraKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarCompraKeyReleased(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel66.setText("Usuario:");

        lblUsuario5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblUsuario5.setText("jLabel18");

        lblRol6.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lblRol6.setText("jLabel18");

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel67.setText("Rol:");

        jDateChooser1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione una fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 8))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 496, Short.MAX_VALUE)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel67)
                    .addComponent(jLabel66))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRol6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(7, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel36Layout.createSequentialGroup()
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblUsuario5)
                                .addComponent(jLabel66))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel67)
                                .addComponent(lblRol6)))
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65)
                            .addComponent(txtBuscarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        tblListaCompra.setBackground(new java.awt.Color(255, 204, 102));
        tblListaCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Fecha", "Proveedor", "Usuario", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListaCompra.setRequestFocusEnabled(false);
        tblListaCompra.setRowHeight(21);
        tblListaCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListaCompraMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblListaCompra);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(701, 701, 701))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 1104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(763, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        mytp.addTab("Listar Compras", jPanel23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mytp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mytp)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
        int fila = tblEmpleado.getSelectedRow();
        lblIdEmpleado.setText(tblEmpleado.getValueAt(fila, 0).toString());
        txtNombreEmpleado.setText(tblEmpleado.getValueAt(fila, 1).toString());
        txtApellidosEmpleado.setText(tblEmpleado.getValueAt(fila, 2).toString());
        txtTelefonoEmpleado.setText(tblEmpleado.getValueAt(fila, 3).toString());
        txtDireccionEmpleado.setText(tblEmpleado.getValueAt(fila, 4).toString());
        txtDniEmpleado.setText(tblEmpleado.getValueAt(fila, 5).toString());
        cboRolEmpleado.setSelectedItem(tblEmpleado.getValueAt(fila, 6).toString());

        btnActualizarEmpleado.setEnabled(true);
        btnEliminarEmpleado.setEnabled(true);
        btnGuardarEmpleado.setEnabled(false);


    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void txtBuscarEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoKeyReleased
        String nombre = txtBuscarEmpleado.getText().trim();

        modelo.setRowCount(0);
        List<Empleado> buscar = new ArrayList();
        buscar = empleadoDao.buscarEmpleado(nombre);
        for (Empleado e : buscar) {
            Object[] ob = new Object[7];
            ob[0] = e.getId();
            ob[1] = e.getNombre();
            ob[2] = e.getApellidos();
            ob[3] = e.getTelefono();
            ob[4] = e.getDireccion();
            ob[5] = e.getDni();
            ob[6] = e.getRol().getNombre_rol();
            modelo.addRow(ob);
        }
        tblEmpleado.setModel(modelo);
    }//GEN-LAST:event_txtBuscarEmpleadoKeyReleased

    private void txtBuscarEmpleadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoKeyPressed

    }//GEN-LAST:event_txtBuscarEmpleadoKeyPressed

    private void txtBuscarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarEmpleadoActionPerformed

    private void txtDniEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDniEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniEmpleadoActionPerformed

    private void txtDireccionEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionEmpleadoActionPerformed

    private void btnEliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarEmpleadoActionPerformed

        Long id = Long.parseLong(lblIdEmpleado.getText());
        if (empleadoDao.EliminarEmpleado(id) == true) {
            JOptionPane.showMessageDialog(null, "Empleado eliminado");
            listarEmpleados();
            limpiar();
            btnGuardarEmpleado.setEnabled(true);
            btnActualizarEmpleado.setEnabled(false);
            btnEliminarEmpleado.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }
    }//GEN-LAST:event_btnEliminarEmpleadoActionPerformed

    private void btnActualizarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarEmpleadoActionPerformed

        Long id = Long.parseLong(lblIdEmpleado.getText());
        String nombre = txtNombreEmpleado.getText();
        String apellidos = txtApellidosEmpleado.getText();
        String telefono = txtTelefonoEmpleado.getText();
        String direccion = txtDireccionEmpleado.getText();
        String dni = txtDniEmpleado.getText();
        String rol = cboRolEmpleado.getSelectedItem().toString();
        Byte idRol = empleadoDao.obtenerIdRol(rol);

        empleado.setId(id);
        empleado.setNombre(nombre);
        empleado.setApellidos(apellidos);
        empleado.setTelefono(telefono);
        empleado.setDireccion(direccion);
        empleado.setDni(dni);

        if (empleadoDao.actualizarEmpleado(idRol, empleado) == true) {
            JOptionPane.showMessageDialog(null, "Empleado modificado");
            listarEmpleados();
            limpiar();
            btnGuardarEmpleado.setEnabled(true);
            btnActualizarEmpleado.setEnabled(false);
            btnEliminarEmpleado.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar");
        }
    }//GEN-LAST:event_btnActualizarEmpleadoActionPerformed

    private void btnGuardarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarEmpleadoActionPerformed

        if (!txtNombreEmpleado.getText().equals("") && !txtApellidosEmpleado.getText().equals("") && !txtDniEmpleado.getText().equals("")) {
            String nombre = txtNombreEmpleado.getText();
            String apellidos = txtApellidosEmpleado.getText();
            String telefono = txtTelefonoEmpleado.getText();
            String direccion = txtDireccionEmpleado.getText();
            String dni = txtDniEmpleado.getText();
            String rol = cboRolEmpleado.getSelectedItem().toString();
            Byte idRol = empleadoDao.obtenerIdRol(rol);

            empleado.setNombre(nombre);
            empleado.setApellidos(apellidos);
            empleado.setTelefono(telefono);
            empleado.setDireccion(direccion);
            empleado.setDni(dni);

            if (empleadoDao.guardarEmpleado(idRol, empleado) == true) {
                JOptionPane.showMessageDialog(null, "Empleado registrado");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar");
            }

            listarEmpleados();
            limpiar();

        } else {
            JOptionPane.showMessageDialog(null, "Llene los campos importantes");
        }
    }//GEN-LAST:event_btnGuardarEmpleadoActionPerformed

    private void btnNuevoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoEmpleadoActionPerformed
        limpiar();
        btnActualizarEmpleado.setEnabled(false);
        btnEliminarEmpleado.setEnabled(false);
        btnGuardarEmpleado.setEnabled(true);
    }//GEN-LAST:event_btnNuevoEmpleadoActionPerformed

    private void txtTelefonoEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoEmpleadoActionPerformed

    }//GEN-LAST:event_txtTelefonoEmpleadoActionPerformed

    private void txtNombreEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreEmpleadoActionPerformed

    private void txtNombreCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCategoriaActionPerformed

    private void btnNuevoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoCategoriaActionPerformed
        limpiarCategoria();
        btnActualizarCategoria.setEnabled(false);
        btnEliminarCategoria.setEnabled(false);
        btnGuardarCategoria.setEnabled(true);
    }//GEN-LAST:event_btnNuevoCategoriaActionPerformed

    private void btnGuardarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCategoriaActionPerformed

        if (!txtNombreCategoria.getText().equals("")) {
            String nombre = txtNombreCategoria.getText();

            categoria.setNombre_categoria(nombre);

            if (categoriaDao.guardarCategoria(categoria) == true) {
                JOptionPane.showMessageDialog(null, "Categoria registrado");
                listarJcbCategoria();

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar");
            }

            listarCategoria();
            limpiarCategoria();

        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar nombre de categoria");
        }
    }//GEN-LAST:event_btnGuardarCategoriaActionPerformed

    private void btnActualizarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCategoriaActionPerformed

        int id = Integer.parseInt(lblIdCategoria.getText());
        String nombre = txtNombreCategoria.getText();

        categoria.setId(id);
        categoria.setNombre_categoria(nombre);

        if (categoriaDao.actualizarCategoria(categoria) == true) {
            JOptionPane.showMessageDialog(null, "Categoria modificado");
            listarCategoria();
            limpiarCategoria();
            btnGuardarCategoria.setEnabled(true);
            btnActualizarCategoria.setEnabled(false);
            btnEliminarCategoria.setEnabled(false);
            

        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar");
        }
        listarCategoria();
        listarJcbCategoria();

        
    }//GEN-LAST:event_btnActualizarCategoriaActionPerformed

    private void btnEliminarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCategoriaActionPerformed

        int id = Integer.parseInt(lblIdCategoria.getText());
        if (categoriaDao.eliminarCategoria(id) == true) {
            JOptionPane.showMessageDialog(null, "Categoria eliminado");
            listarCategoria();
            limpiarCategoria();
            btnGuardarCategoria.setEnabled(true);
            btnActualizarCategoria.setEnabled(false);
            btnEliminarCategoria.setEnabled(false);
            listarCategoria();
            listarJcbCategoria();

        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }
    }//GEN-LAST:event_btnEliminarCategoriaActionPerformed

    private void txtBuscarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCategoriaActionPerformed

    private void txtBuscarCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCategoriaKeyPressed

    }//GEN-LAST:event_txtBuscarCategoriaKeyPressed

    private void txtBuscarCategoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCategoriaKeyReleased
        String nombre = txtBuscarCategoria.getText().trim();
        modeloCategoria.setRowCount(0);
        List<Categoria> buscar = new ArrayList();
        buscar = categoriaDao.buscarCategoria(nombre);
        for (Categoria ca : buscar) {
            Object[] ob = new Object[2];
            ob[0] = ca.getId();
            ob[1] = ca.getNombre_categoria();
            modeloCategoria.addRow(ob);
        }
        tblCategoria.setModel(modeloCategoria);
    }//GEN-LAST:event_txtBuscarCategoriaKeyReleased

    private void tblCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCategoriaMouseClicked
        int fila = tblCategoria.getSelectedRow();
        lblIdCategoria.setText(tblCategoria.getValueAt(fila, 0).toString());
        txtNombreCategoria.setText(tblCategoria.getValueAt(fila, 1).toString());

        btnActualizarCategoria.setEnabled(true);
        btnEliminarCategoria.setEnabled(true);
        btnGuardarCategoria.setEnabled(false);
    }//GEN-LAST:event_tblCategoriaMouseClicked

    private void txtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreUsuarioActionPerformed

    private void txtContraseniaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseniaUsuarioActionPerformed

    }//GEN-LAST:event_txtContraseniaUsuarioActionPerformed

    private void btnNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoUsuarioActionPerformed
        limpiarUsuario();
        btnActualizarUsuario.setEnabled(false);
        btnEliminarUsuario.setEnabled(false);
        btnGuardarUsuario.setEnabled(true);
    }//GEN-LAST:event_btnNuevoUsuarioActionPerformed

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed

        if (!txtUsuario.getText().equals("") && !txtContraseniaUsuario.getText().equals("") && !txtNombreUsuario.getText().equals("")) {

            String nombre_u = txtNombreUsuario.getText();
            String apellidos = txtApellidosUsuario.getText();
            String usuari = txtUsuario.getText();
            String contraseña = txtContraseniaUsuario.getText();
            String estado = cboEstadoUsuario.getSelectedItem().toString();
            Long id = usuarioDao.obtenerIdEmpleado(nombre_u, apellidos);

            usuario.setUsuario(usuari);
            usuario.setContrasenia(contraseña);
            usuario.setEstado(estado);

            if (usuarioDao.guardarUsuario(id, usuario) == true) {
                JOptionPane.showMessageDialog(null, "Usuario registrado");

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar");
            }

            listarUsuario();
        } else {
            JOptionPane.showMessageDialog(null, "Llene los campos");
        }


    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void btnActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarUsuarioActionPerformed

        Byte id = Byte.parseByte(lblIdUsuario.getText());
        String nombre_u = txtNombreUsuario.getText();
        String apellidos = txtApellidosUsuario.getText();
        String usuari = txtUsuario.getText();
        String contraseña = txtContraseniaUsuario.getText();
        String estado = cboEstadoUsuario.getSelectedItem().toString();
        Long idU = usuarioDao.obtenerIdEmpleado(nombre_u, apellidos);

        usuario.setUsuario(usuari);
        usuario.setContrasenia(contraseña);
        usuario.setEstado(estado);
        usuario.setId(id);

        if (usuarioDao.actualizarUsuario(idU, usuario) == true) {
            JOptionPane.showMessageDialog(null, "Empleado modificado");
            listarUsuario();
            limpiarUsuario();
            btnGuardarUsuario.setEnabled(true);
            btnActualizarUsuario.setEnabled(false);
            btnEliminarUsuario.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar");
        }
    }//GEN-LAST:event_btnActualizarUsuarioActionPerformed

    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed

        Byte id = Byte.parseByte(lblIdUsuario.getText());
        if (usuarioDao.eliminarUsuario(id) == true) {
            JOptionPane.showMessageDialog(null, "Usuario eliminado");
            listarUsuario();
            limpiarUsuario();
            btnGuardarUsuario.setEnabled(true);
            btnActualizarUsuario.setEnabled(false);
            btnEliminarUsuario.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed

    private void txtBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarUsuarioActionPerformed

    private void txtBuscarUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUsuarioKeyPressed

    }//GEN-LAST:event_txtBuscarUsuarioKeyPressed

    private void txtBuscarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarUsuarioKeyReleased
        String nombre = txtBuscarUsuario.getText().trim();
        String apellidos = txtBuscarUsuario.getText().trim();
        String usuario = txtBuscarUsuario.getText().trim();

        modeloUsuario.setRowCount(0);
        List<Usuario> buscar = new ArrayList();
        buscar = usuarioDao.buscarUsuario(nombre, apellidos, usuario);
        for (Usuario u : buscar) {
            Object[] ob = new Object[7];
            ob[0] = u.getId();
            ob[1] = u.getEmpleado().getNombre();
            ob[2] = u.getEmpleado().getApellidos();
            ob[3] = u.getUsuario();
            ob[4] = u.getContraseña();
            ob[5] = u.getEstado();

            modeloUsuario.addRow(ob);
        }
        tblUsuario.setModel(modeloUsuario);
    }//GEN-LAST:event_txtBuscarUsuarioKeyReleased

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        int fila = tblUsuario.getSelectedRow();
        lblIdUsuario.setText(tblUsuario.getValueAt(fila, 0).toString());
        txtNombreUsuario.setText(tblUsuario.getValueAt(fila, 1).toString());
        txtApellidosUsuario.setText(tblUsuario.getValueAt(fila, 2).toString());
        txtUsuario.setText(tblUsuario.getValueAt(fila, 3).toString());
        txtContraseniaUsuario.setText(tblUsuario.getValueAt(fila, 4).toString());
        cboEstadoUsuario.setSelectedItem(tblUsuario.getValueAt(fila, 5).toString());

        btnActualizarUsuario.setEnabled(true);
        btnEliminarUsuario.setEnabled(true);
        btnGuardarUsuario.setEnabled(false);


    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void btnEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadosActionPerformed

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);  // Obtener la ventana principal
        Empleadosfrm empleadosDialog = new Empleadosfrm(parentFrame, true);
        empleadosDialog.setLocationRelativeTo(parentFrame);
        empleadosDialog.setVisible(true);  // Mostrar el JDialog

        String nombre = empleadosDialog.nombreSeleccionado();
        String apellidos = empleadosDialog.apellidoSeleccionado();
        if (nombre != null && !nombre.isEmpty()) {
            txtNombreUsuario.setText(nombre);
            txtApellidosUsuario.setText(apellidos);
        }


    }//GEN-LAST:event_btnEmpleadosActionPerformed

    private void txtApellidosUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosUsuarioActionPerformed

    private void txtNombreMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreMedidaActionPerformed

    private void btnNuevoMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoMedidaActionPerformed
        limpiarMedida();
    }//GEN-LAST:event_btnNuevoMedidaActionPerformed

    private void btnGuardarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarMedidaActionPerformed
        if (!txtNombreMedida.getText().equals("") && !txtAbreviacionMedida.getText().equals("")) {
            String nombre = txtNombreMedida.getText();
            String abreviacion = txtAbreviacionMedida.getText();
            um.setNombre(nombre);
            um.setAbreviacion(abreviacion);

            if (umd.guardarMedida(um) == true) {
                JOptionPane.showMessageDialog(null, "U. de medida registrado");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar");
            }

            listarMedida();
            listarJcbUnidadMedida();
            limpiarMedida();

        } else {
            JOptionPane.showMessageDialog(null, "Llene los campos");
        }
    }//GEN-LAST:event_btnGuardarMedidaActionPerformed

    private void btnActualizarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarMedidaActionPerformed

        Byte id = Byte.parseByte(lblIdMedida.getText());

        String nombre = txtNombreMedida.getText();
        String abreviacion = txtAbreviacionMedida.getText();
        um.setNombre(nombre);
        um.setAbreviacion(abreviacion);
        um.setId(id);

        if (umd.actualizarMedida(um) == true) {
            JOptionPane.showMessageDialog(null, "U. de medida modificado");

            listarMedida();
            limpiarMedida();
            btnGuardarMedida.setEnabled(true);
            btnActualizarMedida.setEnabled(false);
            btnEliminarMedida.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar");
        }

    }//GEN-LAST:event_btnActualizarMedidaActionPerformed

    private void btnEliminarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMedidaActionPerformed

        Byte id = Byte.parseByte(lblIdMedida.getText());

        if (umd.eliminarMedida(id) == true) {
            JOptionPane.showMessageDialog(null, "U. de medida eliminado");
            btnGuardarMedida.setEnabled(true);
            btnActualizarMedida.setEnabled(false);
            btnEliminarMedida.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }

    }//GEN-LAST:event_btnEliminarMedidaActionPerformed

    private void txtBuscarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMedidaActionPerformed

    private void txtBuscarMedidaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedidaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarMedidaKeyPressed

    private void txtBuscarMedidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarMedidaKeyReleased

        String nombre = txtBuscarMedida.getText().trim();
        String abreviacion = txtBuscarMedida.getText().trim();
        modeloMedida.setRowCount(0);
        List<UnidadMedida> buscar = new ArrayList();
        buscar = umd.buscarMedida(nombre, abreviacion);
        for (UnidadMedida um : buscar) {
            Object[] ob = new Object[3];
            ob[0] = um.getId();
            ob[1] = um.getNombre();
            ob[2] = um.getAbreviacion();
            modeloMedida.addRow(ob);
        }
        tblMedida.setModel(modeloMedida);
    }//GEN-LAST:event_txtBuscarMedidaKeyReleased

    private void tblMedidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMedidaMouseClicked
        int fila = tblMedida.getSelectedRow();
        lblIdMedida.setText(tblMedida.getValueAt(fila, 0).toString());
        txtNombreMedida.setText(tblMedida.getValueAt(fila, 1).toString());
        txtAbreviacionMedida.setText(tblMedida.getValueAt(fila, 2).toString());

        btnActualizarMedida.setEnabled(true);
        btnEliminarMedida.setEnabled(true);
        btnGuardarMedida.setEnabled(false);

    }//GEN-LAST:event_tblMedidaMouseClicked

    private void txtAbreviacionMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbreviacionMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAbreviacionMedidaActionPerformed

    private void txtCodigoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoProductoActionPerformed

    private void txtBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProductoActionPerformed

    private void txtBuscarProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProductoKeyPressed

    }//GEN-LAST:event_txtBuscarProductoKeyPressed

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
        tblProducto.getColumnModel().getColumn(7).setCellRenderer(renderer);
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
            ob[6] = df.format(p.getPrecioCompra());
            ob[7] = df.format(p.getPrecioVenta());
            ob[8] = p.getStock();
            ob[9] = p.getUbicacion();
            ob[10] = p.getEstado();
            modeloProducto.addRow(ob);
        }
        tblProducto.setModel(modeloProducto);
    }//GEN-LAST:event_txtBuscarProductoKeyReleased

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        int fila = tblProducto.getSelectedRow();
        lblIdProducto.setText(tblProducto.getValueAt(fila, 0).toString());
        txtCodigoProducto.setText(tblProducto.getValueAt(fila, 1).toString());
        txtNombreProducto.setText(tblProducto.getValueAt(fila, 2).toString());
        txtDescripcionProducto.setText(tblProducto.getValueAt(fila, 3).toString());
        cboCategoriaProducto.setSelectedItem(tblProducto.getValueAt(fila, 4).toString());
        cboUmProducto.setSelectedItem(tblProducto.getValueAt(fila, 5).toString());
        txtPrecioCompraProducto.setText(tblProducto.getValueAt(fila, 6).toString());
        txtPrecioVentaProducto.setText(tblProducto.getValueAt(fila, 7).toString());
        txtUbicacionProducto.setText(tblProducto.getValueAt(fila, 9).toString());
        cboEstadoProducto.setSelectedItem(tblProducto.getValueAt(fila, 10).toString());
        
        btnActualizarProducto.setEnabled(true);
        btnEliminarProducto.setEnabled(true);
        btnGuardarProducto.setEnabled(false);
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProductoActionPerformed
        limpiarProducto();
        btnActualizarProducto.setEnabled(false);
        btnEliminarProducto.setEnabled(false);
        btnGuardarProducto.setEnabled(true);
    }//GEN-LAST:event_btnNuevoProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed

        Long id = Long.parseLong(lblIdProducto.getText());
        if (productoDao.eliminarProducto(id) == true) {
            JOptionPane.showMessageDialog(null, "Producto eliminado");
            listarProductos();
            limpiarProducto();
            btnGuardarProducto.setEnabled(true);
            btnActualizarProducto.setEnabled(false);
            btnEliminarProducto.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar");
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnActualizarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProductoActionPerformed

        Long id=Long.parseLong(lblIdProducto.getText());
        String codigo = txtCodigoProducto.getText();
        String nombre = txtNombreProducto.getText();
        String descripcion = txtDescripcionProducto.getText();
        String categoria = cboCategoriaProducto.getSelectedItem().toString();
        String um = cboUmProducto.getSelectedItem().toString();
        Double precioCompra = Double.parseDouble(txtPrecioCompraProducto.getText());
        Double precioVenta = Double.parseDouble(txtPrecioVentaProducto.getText());
        String ubicacion = txtUbicacionProducto.getText();
        String estado = cboEstadoProducto.getSelectedItem().toString();

        pr.setId(id);
        pr.setCodigo(codigo);
        pr.setNombre(nombre);
        pr.setDescripcion(descripcion);
        pr.setPrecioCompra(precioCompra);
        pr.setPrecioVenta(precioVenta);
        pr.setUbicacion(ubicacion);
        pr.setEstado(estado);

        if (productoDao.actualizarProducto(categoria, um, pr) == true) {
            JOptionPane.showMessageDialog(null, "Producto modificado");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo modificar");
        }

        listarProductos();
        limpiarProducto();
    }//GEN-LAST:event_btnActualizarProductoActionPerformed

    private void btnGuardarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarProductoActionPerformed

        String codigo = txtCodigoProducto.getText();
        String nombre = txtNombreProducto.getText();
        String descripcion = txtDescripcionProducto.getText();
        String categoria = cboCategoriaProducto.getSelectedItem().toString();
        String um = cboUmProducto.getSelectedItem().toString();
        Double precioCompra = Double.parseDouble(txtPrecioCompraProducto.getText());
        Double precioVenta = Double.parseDouble(txtPrecioVentaProducto.getText());
       
        String ubicacion = txtUbicacionProducto.getText();
        String estado = cboEstadoProducto.getSelectedItem().toString();

        pr.setCodigo(codigo);
        pr.setNombre(nombre);
        pr.setDescripcion(descripcion);
        pr.setPrecioCompra(precioCompra);
        pr.setPrecioVenta(precioVenta);
        pr.setUbicacion(ubicacion);
        pr.setEstado(estado);

        if (productoDao.guardarProducto(categoria, um, pr) == true) {
            JOptionPane.showMessageDialog(null, "Producto registrado");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo registrar");
        }

        listarProductos();
        limpiarProducto();


    }//GEN-LAST:event_btnGuardarProductoActionPerformed

    private void txtDescripcionProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionProductoActionPerformed

    }//GEN-LAST:event_txtDescripcionProductoActionPerformed

    private void txtPrecioCompraProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCompraProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioCompraProductoActionPerformed

    private void txtPrecioVentaProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioVentaProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioVentaProductoActionPerformed

    private void txtUbicacionProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUbicacionProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionProductoActionPerformed

    private void txtTelefonoEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoEmpleadoKeyTyped
          Character c=evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
        if(txtTelefonoEmpleado.getText().length()>=9){
            JOptionPane.showMessageDialog(null, "Numero invalido");
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            txtTelefonoEmpleado.setText("");
        }
    }//GEN-LAST:event_txtTelefonoEmpleadoKeyTyped

    private void txtDniEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniEmpleadoKeyTyped
          Character c=evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }
        if(txtDniEmpleado.getText().length()>=8){
            JOptionPane.showMessageDialog(null, "Numero invalido");
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            txtDniEmpleado.setText("");
        }
    }//GEN-LAST:event_txtDniEmpleadoKeyTyped

    private void btnProductosVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosVentaActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);  // Obtener la ventana principal
        Productosfrm productosDialog = new Productosfrm(parentFrame, true);
        productosDialog.setLocationRelativeTo(parentFrame);
        productosDialog.setVisible(true);  // Mostrar el JDialog

        String codigo=productosDialog.getCodigo();
        String nombre = productosDialog.getNombre();
        Double precio = productosDialog.getPrecio();
    //    int stock = productosDialog.getStock();

        if (codigo != null && !nombre.isEmpty() && precio!=null) {
            txtCodigoCompra.setText(codigo);
            txtNombreCompra.setText(nombre);
            txtPrecioCompra.setText(String.format("%.2f", precio));
          //  txtCantidadCompra.setText(stock+"");
            txtCantidadCompra.requestFocus();

        }
    }//GEN-LAST:event_btnProductosVentaActionPerformed

    private void tblCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCompraMouseClicked

        int fila= tblCompra.getSelectedRow();
        String codigo=tblCompra.getValueAt(fila, 0).toString();
        txtCodigoCompra.setText(tblCompra.getValueAt(fila, 0).toString());
        txtNombreCompra.setText(tblCompra.getValueAt(fila, 1).toString());
        txtPrecioCompra.setText(tblCompra.getValueAt(fila, 2).toString());
        txtCantidadCompra.setText(tblCompra.getValueAt(fila, 3).toString());

    }//GEN-LAST:event_tblCompraMouseClicked

    private void btnEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVentaActionPerformed
        int fila=tblCompra.getSelectedRow();
        if(fila>=0){
            modeloProducto.setNumRows(fila);
            limpiarProductoCompra();
            sumaTotal();
            
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione una fila a eliminar");
        }
    }//GEN-LAST:event_btnEliminarVentaActionPerformed

    private void btnNuevoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoVentaActionPerformed
        limpiarProductoCompra();
        modeloProducto.setRowCount(0);
        txtMontoTotal.setText("");
        
    }//GEN-LAST:event_btnNuevoVentaActionPerformed

    private void btnGuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVentaActionPerformed

        if (tblCompra.getRowCount() > 0) {
            Double total = Double.parseDouble(txtMontoTotal.getText());

            // Mostrar confirmación
            int opcion = JOptionPane.showConfirmDialog(this, "¿Desea registrar la compra?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {

                String usuario = lblUsuarioCompra.getText();
                String nombreProveedor = txtNombrePro.getText();
                if (compraDao.guardarCompra(nombreProveedor, usuario, total)) {
                    JOptionPane.showMessageDialog(null, "Compra registrada con éxito");
                    Long id_compra = compraDao.idCompra(usuario);

                    // Actualizar stock
                    for (int i = 0; i < tblCompra.getRowCount(); i++) {
                        String codigo = tblCompra.getValueAt(i, 0).toString();
                        int cantidad = Integer.parseInt(tblCompra.getValueAt(i, 3).toString());
                        compraDao.actualizarStock(codigo, cantidad);
                    }

                    // Guardar detalle
                    for (int i = 0; i < tblCompra.getRowCount(); i++) {
                        String codigo = tblCompra.getValueAt(i, 0).toString();
                        int cantidad = Integer.parseInt(tblCompra.getValueAt(i, 3).toString());
                        double precio_unitario = Double.parseDouble(tblCompra.getValueAt(i, 2).toString());

                        // Obtener la fecha de vencimiento como String
                        String fechaVencimientoStr = tblCompra.getValueAt(i, 4).toString();

                        // Convertir la fecha String a java.util.Date usando SimpleDateFormat
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); // Ajusta el formato al tipo de fecha que estás recibiendo
                        Date fechaVencimiento = null;
                        try {
                            fechaVencimiento = formatoFecha.parse(fechaVencimientoStr);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        // Convertir java.util.Date a java.sql.Date
                        java.sql.Date fechaVencimientoSql = new java.sql.Date(fechaVencimiento.getTime());
                        // Obtener el ID del producto
                        Long id_producto = compraDao.idProducto(codigo);

                        // Guardar el detalle de la compra con la fecha de vencimiento
                        dcd.guardarDetalle(id_compra, id_producto, cantidad, precio_unitario, fechaVencimientoSql);
                    }

                    // Limpiar la tabla y listar las compras
                    modeloProducto.setRowCount(0);
                    listarCompras();

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo registrar la compra");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Registro cancelado");
            }

        } else {
            JOptionPane.showMessageDialog(null, "La compra está vacía ");
        }

    }//GEN-LAST:event_btnGuardarVentaActionPerformed

    private void btnProductosVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosVenta1ActionPerformed
       
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);  // Obtener la ventana principal
        Proveedorfrm proDialog = new Proveedorfrm(parentFrame, true);
        proDialog.setLocationRelativeTo(parentFrame);
        proDialog.setVisible(true);  // Mostrar el JDialog

        Long id = proDialog.id;
        String nombre=proDialog.nombre;

        if (!nombre.isEmpty()) {
            
            txtNombrePro.setText(nombre);
            
            
        }
            
    }//GEN-LAST:event_btnProductosVenta1ActionPerformed

    private void txtCantidadCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadCompraKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtCodigoCompra.getText().equals("") && !txtNombreCompra.getText().equals("") && !txtPrecioCompra.getText().equals("")) {
                if (!txtCantidadCompra.getText().equals("")) {

                    String codigo = txtCodigoCompra.getText();
                    String nombre = txtNombreCompra.getText();
                    double precio = Double.parseDouble(txtPrecioCompra.getText());
                    int cantidadIngresada = Integer.parseInt(txtCantidadCompra.getText());
                    Date fechaVencimiento = txtFv.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaFormateada = sdf.format(fechaVencimiento);
                    

                    if (cantidadIngresada > 0) {
                        total = precio * cantidadIngresada;
                        modeloProducto = (DefaultTableModel) tblCompra.getModel();
                        boolean existe = false;

                        for (int i = 0; i < modeloProducto.getRowCount(); i++) {
                            String nombreTabla = tblCompra.getValueAt(i, 1).toString();

                            if (nombreTabla.equals(nombre)) {
                                int cantidadExistente = Integer.parseInt(tblCompra.getValueAt(i, 3).toString());

                                // Aquí evaluamos si actualizar o advertir
                                if (cantidadIngresada <0) {
                                    JOptionPane.showMessageDialog(null, "Numero invalido");
                                    return;
                                }

                                double nuevoTotal = precio * cantidadIngresada;

                                // Actualizamos con la nueva cantidad (sea menor o mayor)
                                tblCompra.setValueAt(cantidadIngresada, i, 3);
                                tblCompra.setValueAt(nuevoTotal, i, 4);
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

                            tblCompra.getColumnModel().getColumn(2).setCellRenderer(renderer);
                            tblCompra.getColumnModel().getColumn(4).setCellRenderer(renderer);

                            Object[] fila = new Object[6];
                            fila[0] = codigo;
                            fila[1] = nombre;
                            fila[2] = precio;
                            fila[3] = cantidadIngresada;
                            fila[4] = fechaFormateada;
                            fila[5] = total;
                           
                            modeloProducto.addRow(fila);
                        }

                        tblCompra.setModel(modeloProducto);
                        limpiarProductoCompra();
                        sumaTotal();
               

                    } else {
                        JOptionPane.showMessageDialog(null, "Cantidad inválida");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingrese la cantidad");
                    
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe ingresar el producto");
            }
        }
    }//GEN-LAST:event_txtCantidadCompraKeyPressed

    private void txtCantidadCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadCompraKeyTyped

        Character c=evt.getKeyChar();
        if(!Character.isDigit(c)){
            evt.consume();
        }

    }//GEN-LAST:event_txtCantidadCompraKeyTyped

    private void txtBuscarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCompraActionPerformed

    private void txtBuscarCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCompraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarCompraKeyPressed

    private void txtBuscarCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarCompraKeyReleased
        
        String nombre = txtBuscarCompra.getText();
        modeloCompra.setRowCount(0);
        List<Compra> buscar = compraDao.buscarCompra(nombre);
        for (Compra c : buscar) {
            Object[] ob = new Object[5];
            ob[0] = c.getId();
            ob[1] = c.getFecha();
            ob[2] = c.getProveedor().getNombre();
            ob[3] = c.getUsuario().getUsuario();
            ob[4] = c.getTotal();
            modeloCompra.addRow(ob);
        }
        
        tblCompra.setModel(modeloCompra);
        
    }//GEN-LAST:event_txtBuscarCompraKeyReleased

    private void tblListaCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListaCompraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblListaCompraMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(jDateChooser1.getDate()!=null){
        Date fechaUtil = jDateChooser1.getDate();
        Date fecha = null;
        if(fechaUtil != null){
            fecha = new java.sql.Date(fechaUtil.getTime());
        }
        modeloCompra.setRowCount(0); // Limpia la tabla

        List<Compra> compra;

        
         compra = compraDao.buscarCompraFecha( (java.sql.Date) fecha);
        

        for (Compra co : compra) {
            Object[] ob = new Object[5];
            ob[0] = co.getId();
            ob[1] = co.getFecha();
            ob[2] = co.getProveedor().getNombre();
            ob[3] = co.getUsuario().getUsuario();
            ob[4] = co.getTotal();
            modeloCompra.addRow(ob);
        }

        tblListaCompra.setModel(modeloCompra);
        }else{
        listarCompras();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void limpiar() {
        txtNombreEmpleado.setText("");
        txtApellidosEmpleado.setText("");
        txtTelefonoEmpleado.setText("");
        txtDireccionEmpleado.setText("");
        txtDniEmpleado.setText("");
        cboRolEmpleado.setSelectedIndex(0);
        txtNombreEmpleado.requestFocus();
    }

    public void limpiarCategoria() {
        txtNombreCategoria.setText("");
        txtNombreCategoria.requestFocus();
    }

    public void limpiarUsuario() {
        lblIdUsuario.setText("");
        txtNombreUsuario.setText("");
        txtApellidosUsuario.setText("");
        txtUsuario.setText("");
        txtContraseniaUsuario.setText("");
        cboEstadoUsuario.setSelectedItem(0);

    }

    public void limpiarMedida() {

        txtNombreMedida.setText("");
        txtAbreviacionMedida.setText("");
        txtNombreMedida.requestFocus();
    }
    
    public void limpiarProducto(){
    
        lblIdProducto.setText("");
        txtCodigoProducto.setText("");
        txtNombreProducto.setText("");
        txtDescripcionProducto.setText("");
        cboCategoriaProducto.setSelectedItem("");
        cboUmProducto.setSelectedItem("");
        txtPrecioCompraProducto.setText("");
        txtPrecioVentaProducto.setText("");
        txtUbicacionProducto.setText("");
        cboEstadoProducto.setSelectedItem("");
        txtCodigoProducto.requestFocus();
    }
    
     public void sumaTotal(){
       double suma=0;
       for (int i = 0; i < tblCompra.getRowCount(); i++) {
           suma+= Double.parseDouble(tblCompra.getValueAt(i, 5).toString());
       }
       txtMontoTotal.setText(String.format("%.2f", suma));
   }
     
       public void limpiarProductoCompra(){
    
        txtCodigoCompra.setText("");
        txtNombreCompra.setText("");
        txtPrecioCompra.setText("");
        txtCantidadCompra.setText("");
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCategoria;
    private javax.swing.JButton btnActualizarEmpleado;
    private javax.swing.JButton btnActualizarMedida;
    private javax.swing.JButton btnActualizarProducto;
    private javax.swing.JButton btnActualizarUsuario;
    private javax.swing.JButton btnEliminarCategoria;
    private javax.swing.JButton btnEliminarEmpleado;
    private javax.swing.JButton btnEliminarMedida;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnEliminarVenta;
    private javax.swing.JButton btnEmpleados;
    private javax.swing.JButton btnGuardarCategoria;
    private javax.swing.JButton btnGuardarEmpleado;
    private javax.swing.JButton btnGuardarMedida;
    private javax.swing.JButton btnGuardarProducto;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnGuardarVenta;
    private javax.swing.JButton btnNuevoCategoria;
    private javax.swing.JButton btnNuevoEmpleado;
    private javax.swing.JButton btnNuevoMedida;
    private javax.swing.JButton btnNuevoProducto;
    private javax.swing.JButton btnNuevoUsuario;
    private javax.swing.JButton btnNuevoVenta;
    private javax.swing.JButton btnProductosVenta;
    private javax.swing.JButton btnProductosVenta1;
    private javax.swing.JComboBox<String> cboCategoriaProducto;
    private javax.swing.JComboBox<String> cboEstadoProducto;
    private javax.swing.JComboBox<String> cboEstadoUsuario;
    private javax.swing.JComboBox<String> cboRolEmpleado;
    private javax.swing.JComboBox<String> cboUmProducto;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblId1;
    private javax.swing.JLabel lblIdCategoria;
    private javax.swing.JLabel lblIdEmpleado;
    private javax.swing.JLabel lblIdMedida;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblIdVenta;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblRol1;
    private javax.swing.JLabel lblRol2;
    private javax.swing.JLabel lblRol3;
    private javax.swing.JLabel lblRol4;
    private javax.swing.JLabel lblRol5;
    private javax.swing.JLabel lblRol6;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuario1;
    private javax.swing.JLabel lblUsuario2;
    private javax.swing.JLabel lblUsuario3;
    private javax.swing.JLabel lblUsuario4;
    private javax.swing.JLabel lblUsuario5;
    private javax.swing.JLabel lblUsuarioCompra;
    private javax.swing.JTabbedPane mytp;
    private javax.swing.JTable tblCategoria;
    private javax.swing.JTable tblCompra;
    private javax.swing.JTable tblEmpleado;
    private javax.swing.JTable tblListaCompra;
    private javax.swing.JTable tblMedida;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtAbreviacionMedida;
    private javax.swing.JTextField txtApellidosEmpleado;
    private javax.swing.JTextField txtApellidosUsuario;
    private javax.swing.JTextField txtBuscarCategoria;
    private javax.swing.JTextField txtBuscarCompra;
    private javax.swing.JTextField txtBuscarEmpleado;
    private javax.swing.JTextField txtBuscarMedida;
    private javax.swing.JTextField txtBuscarProducto;
    private javax.swing.JTextField txtBuscarUsuario;
    private javax.swing.JTextField txtCantidadCompra;
    private javax.swing.JTextField txtCodigoCompra;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtContraseniaUsuario;
    private javax.swing.JTextField txtDescripcionProducto;
    private javax.swing.JTextField txtDireccionEmpleado;
    private javax.swing.JTextField txtDniEmpleado;
    private com.toedter.calendar.JDateChooser txtFv;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNombreCategoria;
    private javax.swing.JTextField txtNombreCompra;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtNombreMedida;
    private javax.swing.JTextField txtNombrePro;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioCompraProducto;
    private javax.swing.JTextField txtPrecioVentaProducto;
    private javax.swing.JTextField txtTelefonoEmpleado;
    private javax.swing.JTextField txtUbicacionProducto;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
