package es.upsa.papps.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.upsa.papps.listacompra.R;

public class MainActivity extends AppCompatActivity {

    private ProductoViewModel viewModel;
    private ProductoAdapter adapter;
    private Button buttonStartShopping;
    private Button buttonVerLista;
    private TextView totalCompraTextView;
    private double totalCompra; // Variable para almacenar el total de la compra

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(ProductoViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        totalCompraTextView = findViewById(R.id.totalCompraTextView);
        totalCompraTextView.setVisibility(View.INVISIBLE);

        buttonStartShopping = findViewById(R.id.buttonStartShopping);
        buttonStartShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProductList();
            }
        });

        buttonVerLista = findViewById(R.id.buttonVerLista);
        buttonVerLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pasar el total de la compra a ListaCompraActivity
                Intent intent = new Intent(MainActivity.this, ListaCompraActivity.class);
                intent.putExtra("TOTAL_COMPRA", totalCompra);
                startActivity(intent);
            }
        });

        recyclerView.setVisibility(View.INVISIBLE);
        totalCompraTextView.setVisibility(View.INVISIBLE);

        viewModel.getProductos().observe(this, productos -> {
            if (adapter == null) {
                adapter = new ProductoAdapter(productos, viewModel);
                recyclerView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getTotalCompra().observe(this, total -> {
            totalCompraTextView.setText(String.format("Total de la compra: $%.2f", total));
            // Guardar el total de la compra en la variable
            totalCompra = total;
        });
    }

    private void showProductList() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        buttonVerLista.setVisibility(View.VISIBLE);
        findViewById(R.id.textViewTitulo).setVisibility(View.VISIBLE);
        findViewById(R.id.textViewStartShopping).setVisibility(View.GONE);
        buttonStartShopping.setVisibility(View.GONE);
    }
}
