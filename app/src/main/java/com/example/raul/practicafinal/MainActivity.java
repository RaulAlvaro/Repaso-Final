package com.example.raul.practicafinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.raul.practicafinal.models.DaoSession;
import com.example.raul.practicafinal.models.Nota;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NotaAdapter.onClickItemListener {

    private static final String TAG = "MainActivity";

    private List<Nota> notas;
    private NotaAdapter notaAdapter;
    private DaoSession daoSession;

    @BindView(R.id.btn_agregar)
    Button btnAgregar;
    @BindView(R.id.rv_notas)
    RecyclerView rvNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RepasoApplication repasoApplication = (RepasoApplication) getApplication();
        daoSession = repasoApplication.getDaoSesion();

            /* pasar el  mainactivity que ahora tiene el comportamiento del listener.*/
        notaAdapter = new NotaAdapter(this);

        rvNotas.setLayoutManager(new LinearLayoutManager(this));
        rvNotas.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvNotas.setAdapter(notaAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notas = daoSession.getNotaDao().loadAll();
        notaAdapter.addList(notas);
    }



    @OnClick(R.id.btn_agregar)
    public void onViewClicked() {
        FomularioNotaActivity.start(this, null);
    }

    @Override
    public void onItemClick(Nota nota) {
        daoSession.delete(nota);
        notas = daoSession.getNotaDao().loadAll();
        notaAdapter.addList(notas);
    }

    @Override
    public void onEditarNotaClick(Nota nota) {
        FomularioNotaActivity.start(this, nota);
        Toast.makeText(this, "Boton editar", Toast.LENGTH_SHORT).show();
    }
}
