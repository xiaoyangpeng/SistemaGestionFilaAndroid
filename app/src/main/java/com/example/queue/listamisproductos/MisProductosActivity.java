package com.example.queue.listamisproductos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.queue.R;
import com.example.queue.valorFijo.Ids;

public class MisProductosActivity extends AppCompatActivity {


    private TextView totalPrecio;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_productos);


        ActionBar actionBar=this.getSupportActionBar();
        actionBar.setTitle("Mis productos");

        // añadir flecha vuelve atras y su accione esta en  onSupportNavigateUp()
        actionBar.setDisplayHomeAsUpEnabled(true);



        totalPrecio = (TextView) findViewById(R.id.totalMisproductos);

        if(Ids.yaestaEncola) {

                    PeticionListaMisProductos peticionListaMisProductos = new PeticionListaMisProductos();

                    peticionListaMisProductos.start();

                    try {
                        peticionListaMisProductos.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // crear view de lista
                    RecyclerView lista = (RecyclerView) findViewById(R.id.viewListasProductos);

                    lista.setNestedScrollingEnabled(false);
                    lista.setHasFixedSize(true);

                    lista.setFocusable(false);

                    lista.setHasFixedSize(true);

                    RecyclerView.LayoutManager rl = new LinearLayoutManager(this);

                    lista.setLayoutManager(rl);

                    // coger el objeto PetecionPorductos de Listaproducto Activity
                    RecyclerView.Adapter adaptador = new AdaptadorMisLista(peticionListaMisProductos.getListaProducto());

                    lista.setAdapter(adaptador);


                    // cuando vuvele array vacion signifaca aun no hay producto
                    if(peticionListaMisProductos.getListaProducto()==null){

                        totalPrecio.setText("Aún no has añadir ningún producto");

                    }else {
                            // suma los precios total
                        double total = 0;

                        for (ListaProducto i : peticionListaMisProductos.getListaProducto()) {

                            total += i.getPrecioTotal();

                        }

                        totalPrecio.setText(String.valueOf(total));

                    }



        }else{

            totalPrecio.setText("Aún no estas en ninguna fila");
        }

    }



    // accion de flecha vuelve atras
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}