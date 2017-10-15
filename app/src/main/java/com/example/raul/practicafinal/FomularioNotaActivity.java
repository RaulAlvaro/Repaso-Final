package com.example.raul.practicafinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.raul.practicafinal.models.DaoSession;
import com.example.raul.practicafinal.models.Nota;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FomularioNotaActivity extends AppCompatActivity {

    @BindView(R.id.et_agregartitulo)
    EditText etAgregartitulo;

    @BindView(R.id.btn_action)
    Button btnAction;
    @BindView(R.id.et_agregardescripcion)
    EditText etAgregardescripcion;

    private DaoSession daoSession;
    private Nota nota;
    private boolean esActualizable = false;

    public static void start(Context context, Nota nota) {
        Intent starter = new Intent(context, FomularioNotaActivity.class);
        starter.putExtra("nota", nota);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fomulario_nota);
        ButterKnife.bind(this);

        nota = getIntent().getParcelableExtra("nota");

        RepasoApplication repasoApplication = (RepasoApplication) getApplication();
        daoSession = repasoApplication.getDaoSesion();

        if (nota != null) {
            etAgregartitulo.setText(nota.getNota());
            etAgregardescripcion.setText(nota.getDescripcion());
            btnAction.setText("EDITAR");
            esActualizable = true;
        } else {
            btnAction.setText("AGREGAR");
        }
    }

    @OnClick(R.id.btn_action)
    public void onViewClicked() {
        if (esActualizable == true) {
            actualizarNota();
        } else {
            agregarNota();
        }
    }

    public void actualizarNota() {
        nota.setNota(etAgregartitulo.getText().toString());
        nota.setDescripcion(etAgregardescripcion.getText().toString());

        daoSession.getNotaDao().update(nota);
        Toast.makeText(this, "La nota se actualizo", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void agregarNota() {
        Nota nota = new Nota();
        nota.setNota(etAgregartitulo.getText().toString());
        nota.setDescripcion(etAgregardescripcion.getText().toString());

        daoSession.getNotaDao().insert(nota);
        Toast.makeText(this, "La nota se creo correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }


}
