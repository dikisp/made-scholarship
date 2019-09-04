package com.diki.myprofile.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tFriend")
public class FriendData implements Serializable {

    @PrimaryKey
    @NonNull
    public String nim;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "kelas")
    public String kelas;

    @ColumnInfo(name = "telepon")
    public String telepon;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "medsos")
    public String medsos;

    @ColumnInfo(name = "alamat")
    public String alamat;

    @NonNull
    public String getNim() {
        return nim;
    }

    public void setNim(@NonNull String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMedsos() {
        return medsos;
    }

    public void setMedsos(String medsos) {
        this.medsos = medsos;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}
