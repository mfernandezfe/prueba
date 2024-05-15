package es.upsa.papps.prueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.Resources;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {
    private List<Producto> productos;
    private ProductoViewModel viewModel;

    public ProductoAdapter(List<Producto> productos, ProductoViewModel viewModel) {
        this.productos = productos;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productos.get(position);
        holder.checkBox.setText(producto.getNombre());
        holder.textViewCantidad.setText("" + producto.getCantidad());
        holder.checkBox.setChecked(producto.getCantidad() > 0);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked) {
                viewModel.actualizarCantidad(producto.getNombre(), 0);
            }
        });

        holder.buttonIncrease.setOnClickListener(v -> viewModel.actualizarCantidad(producto.getNombre(), producto.getCantidad() + 1));

        holder.buttonDecrease.setOnClickListener(v -> {
            if (producto.getCantidad() > 0) {
                viewModel.actualizarCantidad(producto.getNombre(), producto.getCantidad() - 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textViewCantidad;
        Button buttonIncrease;
        Button buttonDecrease;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            textViewCantidad = itemView.findViewById(R.id.textViewCantidad);
            buttonIncrease = itemView.findViewById(R.id.buttonIncrease);
            buttonDecrease = itemView.findViewById(R.id.buttonDecrease);
        }
    }
}

