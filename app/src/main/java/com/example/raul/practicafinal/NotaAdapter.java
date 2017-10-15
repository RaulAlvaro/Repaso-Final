package com.example.raul.practicafinal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.raul.practicafinal.models.Nota;

import java.util.List;

/**
 * Created by RAUL on 14/10/2017.
 */

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaViewHolder> {

    private List<Nota> notas;

    //2 paso crear una variable global de la interface
    private onClickItemListener onClickItemListener;

    //3 recibir el listener por el constructor
    public NotaAdapter(onClickItemListener onClickItemListener){
        this.onClickItemListener = onClickItemListener;
    }

    public void addList (List<Nota> notas){
        this.notas=notas;
        notifyDataSetChanged(); // si el adapter recibe una lista, el recyclerview se actualiza
    }


    @Override
    public NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item,
                parent,false);

        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotaViewHolder holder, int position) {
        final Nota nota = notas.get(position);
        holder.tvtitulo.setText(nota.getNota());

        //paso 5, integrar el metodo de la interfaz con el onclick del view
        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickItemListener != null){
                    onClickItemListener.onEditarNotaClick(nota);
                }
            }
        });

        //paso 5, integrar el metodo de la interfaz con el onclick del view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onClickItemListener != null){
                    onClickItemListener.onItemClick(nota);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(notas == null){
            return 0;
        }
        else{
            return notas.size();
        }
    }

    public class NotaViewHolder extends RecyclerView.ViewHolder {
        TextView tvtitulo;
        Button btnEditar;

        public NotaViewHolder(View itemView) {
            super(itemView);
            tvtitulo = (TextView) itemView.findViewById(R.id.tv_tituloNotaLista);
            btnEditar = (Button) itemView.findViewById(R.id.btn_editar);

        }
    }

    //1 paso crear la interface listener
    public interface onClickItemListener{
        void onItemClick(Nota nota); //metodo para eliminar un item
        void onEditarNotaClick(Nota nota); //metodo para el click del boton editar
    }

}
