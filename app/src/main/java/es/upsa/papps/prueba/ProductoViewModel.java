package es.upsa.papps.prueba;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewModel extends ViewModel {
    private final ProductoRepository repository;
    private final MutableLiveData<List<Producto>> productos;
    private final MutableLiveData<Double> totalCompra;

    public ProductoViewModel() {
        repository = new ProductoRepository();
        productos = new MutableLiveData<>(repository.obtenerProductos());
        totalCompra = new MutableLiveData<>(0.0); // Inicializar el total de la compra en 0.0
    }

    public LiveData<List<Producto>> getProductos() {
        return productos;
    }

    public LiveData<Double> getTotalCompra() {
        return totalCompra;
    }

    public void actualizarCantidad(String nombre, int cantidad) {
        List<Producto> lista = new ArrayList<>(productos.getValue());
        double total = 0.0;
        for (Producto producto : lista) {
            if (producto.getNombre().equals(nombre)) {
                producto.setCantidad(cantidad);
            }
            total += producto.getPrecio() * producto.getCantidad();
        }
        totalCompra.setValue(total); // Actualizar el valor del total de la compra
        productos.setValue(lista);
    }
}
