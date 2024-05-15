package es.upsa.papps.prueba;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductoViewModel extends ViewModel {
    private final ProductoRepository repository;
    private final MutableLiveData<List<Producto>> productos;

    public ProductoViewModel() {
        repository = new ProductoRepository();
        productos = new MutableLiveData<>(repository.obtenerProductos());
    }

    public LiveData<List<Producto>> getProductos() {
        return productos;
    }

    public void actualizarCantidad(String nombre, int cantidad) {
        List<Producto> lista = new ArrayList<>(productos.getValue());
        for (Producto producto : lista) {
            if (producto.getNombre().equals(nombre)) {
                producto.setCantidad(cantidad);
                break;
            }
        }
        productos.setValue(lista);
    }
}
