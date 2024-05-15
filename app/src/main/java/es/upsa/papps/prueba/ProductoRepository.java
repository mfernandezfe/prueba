package es.upsa.papps.prueba;

import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto("Plátanos", 0.50));
        productos.add(new Producto("Manzanas", 0.30));
        productos.add(new Producto("Cereales", 2.00));
        productos.add(new Producto("Leche", 1.50));
        productos.add(new Producto("Huevos", 3.00));
        productos.add(new Producto("Aceite", 5.00));
        return productos;
    }
}

