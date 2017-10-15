package com.example.raul.practicafinal.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by RAUL on 14/10/2017.
 */
@Entity
public class Nota  implements Parcelable{

    @Id(autoincrement = true)
    private Long Id;

    private String nota;
    private String descripcion;

    protected Nota(Parcel in) {
        this.Id = (Long) in.readValue(Long.class.getClassLoader());
        this.nota = in.readString();
        this.descripcion = in.readString();
    }

    @Generated(hash = 1678098700)
    public Nota(Long Id, String nota, String descripcion) {
        this.Id = Id;
        this.nota = nota;
        this.descripcion = descripcion;
    }

    public Nota() {
    }

    public static final Parcelable.Creator<Nota> CREATOR = new Parcelable.Creator<Nota>() {
        @Override
        public Nota createFromParcel(Parcel in) {
            return new Nota(in);
        }

        @Override
        public Nota[] newArray(int size) {
            return new Nota[size];
        }
    };

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.Id);
        parcel.writeString(nota);
        parcel.writeString(descripcion);
    }


}
