package es.upsa.papps.prueba;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import es.upsa.papps.listacompra.R;
public class ListaCompraActivity extends AppCompatActivity {

    private ProductoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        viewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        TextView textViewLista = findViewById(R.id.textViewLista);

        viewModel.getProductos().observe(this, productos -> {
            StringBuilder listaCompra = new StringBuilder();
            double totalPrecio = 0;

            for (Producto producto : productos) {
                if (producto.getCantidad() > 0) {
                    listaCompra.append(producto.getCantidad())
                            .append(" x ")
                            .append(producto.getNombre())
                            .append("\n");
                    totalPrecio += producto.getCantidad() * producto.getPrecio();
                }
            }
            listaCompra.append("\nTotal: ").append(totalPrecio).append(" €");
            textViewLista.setText(listaCompra.toString());
        });

        /*Button buttonVolverProductos = findViewById(R.id.buttonVolverProductos);
        buttonVolverProductos.setOnClickListener(v -> {
            // Crear un intent para volver a la actividad principal
            Intent intent = new Intent(ListaCompraActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Opcional: terminar la actividad actual para evitar que quede en la pila de actividades
        });*/
    }
}
