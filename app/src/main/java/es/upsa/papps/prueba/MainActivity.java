package es.upsa.papps.prueba;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import es.upsa.papps.listacompra.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ProductoViewModel viewModel;
    private ProductoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getProductos().observe(this, productos -> {
            adapter = new ProductoAdapter(productos, viewModel);
            recyclerView.setAdapter(adapter);
        });

        // Corregir la referencia del botón a Button en lugar de MaterialButton
        Button buttonVerLista = findViewById(R.id.buttonVerLista);
        buttonVerLista.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaCompraActivity.class);
            startActivity(intent);
        });
    }
}
