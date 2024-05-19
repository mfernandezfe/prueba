package es.upsa.papps.prueba;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import es.upsa.papps.listacompra.R;
public class ListaCompraActivity extends AppCompatActivity {

    private ProductoViewModel compraViewModel;
    private TextView textViewLista;
    private TextView textViewTotal; // TextView para mostrar el total de la compra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        compraViewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        textViewLista = findViewById(R.id.textViewLista);
        textViewTotal = findViewById(R.id.textViewTotal); // Inicializar el TextView del total

        // Recibir el precio total actualizado de MainActivity
        double totalCompra = getIntent().getDoubleExtra("TOTAL_COMPRA", 0.0);

        // Mostrar el precio total actualizado en el textViewTotal
        textViewTotal.setText(String.format("Total: %.2f €", totalCompra));

        /*compraViewModel.getProductos().observe(this, new Observer<List<Producto>>() {
            @Override
            public void onChanged(List<Producto> productos) {
                StringBuilder listaCompra = new StringBuilder();
                double totalPrecio = 0;

                for (Producto producto : productos) {
                    if (producto.isChecked()) { // Solo mostrar productos con isChecked = true
                        listaCompra.append(producto.getNombre()).append("\n"); // Mostrar solo el nombre del producto
                        totalPrecio += producto.getPrecio(); // Solo se suma el precio una vez por producto
                    }
                }

                listaCompra.append("\nTotal: ").append(totalPrecio).append(" €");
                textViewLista.setText(listaCompra.toString());
            }
        });*/



            /*StringBuilder listaCompra = new StringBuilder();
            double totalPrecio = 0;

            for (Producto producto : productos) {
                if (producto.getCantidad() == 0) {
                    listaCompra.append(producto.getCantidad())
                            .append(" x ")
                            .append(producto.getNombre())
                            .append("\n");
                    totalPrecio += producto.getCantidad() * producto.getPrecio();
                }
            }
            listaCompra.append("\nTotal: ").append(totalPrecio).append(" €");
            textViewLista.setText(listaCompra.toString());
        });*/

        Button buttonVolverProductos = findViewById(R.id.buttonVolverProductos);
        buttonVolverProductos.setOnClickListener(v -> {
            // Crear un intent para volver a la actividad principal
            Intent intent = new Intent(ListaCompraActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Opcional: terminar la actividad actual para evitar que quede en la pila de actividades
        });
    }
}
