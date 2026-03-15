
package grafico;

import Dao.CompraDao;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import Dao.ProductoDao;
import java.util.List;
import javax.swing.JOptionPane;

public class GraficoProductos {

    public void mostrarGraficoEnPanel(JPanel panelDestino) {
        
        ProductoDao dao = new ProductoDao();
        Map<String, Integer> datos = dao.obtenerProductosBajoStock();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            dataset.addValue(entry.getValue(), "Stock", entry.getKey());
        }

        JFreeChart grafico = ChartFactory.createBarChart(
                "Productos por Agotarse",
                "Producto",
                "Stock",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        ChartPanel chartPanel = new ChartPanel(grafico);
        chartPanel.setPreferredSize(panelDestino.getSize());

        panelDestino.removeAll();
        panelDestino.setLayout(new BorderLayout());
        panelDestino.add(chartPanel, BorderLayout.CENTER);
        panelDestino.validate();
    }
    
     public void mostrarGraficoProductosPorVencer(JPanel panelDestino) {
    CompraDao dao = new CompraDao();
    List<String[]> productos = dao.obtenerProductosPorVencer();

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // Recorrer la lista y sumar cantidades manualmente sin Map
    for (String[] fila : productos) {
        String producto = fila[0];
        int cantidad = Integer.parseInt(fila[2]);

        boolean encontrado = false;

        // Recorremos el dataset para ver si ya existe el producto
        for (int i = 0; i < dataset.getColumnCount(); i++) {
            if (producto.equals(dataset.getColumnKey(i))) {
                int cantidadExistente = dataset.getValue("Cantidad", producto).intValue();
                dataset.setValue(cantidadExistente + cantidad, "Cantidad", producto);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            dataset.setValue(cantidad, "Cantidad", producto);
        }
    }

    JFreeChart grafico = ChartFactory.createBarChart(
            "Productos por Vencer en los Próximos 30 Días",
            "Producto",
            "Cantidad",
            dataset,
            PlotOrientation.VERTICAL,
            false, true, false
    );

    ChartPanel chartPanel = new ChartPanel(grafico);
    chartPanel.setPreferredSize(panelDestino.getSize());

    panelDestino.removeAll();
    panelDestino.setLayout(new BorderLayout());
    panelDestino.add(chartPanel, BorderLayout.CENTER);
    panelDestino.validate();
    panelDestino.repaint();
}

    
}

