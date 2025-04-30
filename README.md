<p align="center">
  <img src="banner(2).png" alt="LockForce Banner" width="100%">
</p>

# 📱 Aplikasi Android AquaTrack 
![Made with Kotlin](https://img.shields.io/badge/Made%20with-Kotlin-7F52FF.svg?style=for-the-badge&logo=kotlin)
![Built in Android Studio](https://img.shields.io/badge/Built%20in-Android%20Studio-3DDC84?style=for-the-badge&logo=androidstudio)

---

# 💧 AquaTrack

🎯 **AquaTrack** adalah aplikasi mobile berbasis Android yang dirancang untuk membantu pengguna mengingatkan konsumsi air harian mereka agar tetap terhidrasi dengan baik.

---

## 📱 Fitur Unggulan

- 🎨 **Tampilan Antarmuka Menarik**: Desain UI yang simpel, intuitif, dan nyaman digunakan.
- 🔔 **Notifikasi Pengingat Minum**: Notifikasi otomatis yang akan mengingatkan kamu untuk minum air secara rutin.
- ⏰ **Pemilihan Waktu Pengingat**: Atur sendiri kapan kamu ingin diingatkan untuk minum air sesuai kebutuhanmu.

---

## 🚀 Teknologi yang Digunakan

- Kotlin
- Android SDK
- Notifikasi Lokal Android

---  

## 📷 Screenshot

| Splash Screen | Welcome Screen | Home Screen |
|---------------|----------------|-------------|
| ![splash]( https://github.com/hdrzk/Aplikasi-AquaTrack/blob/main/SS%20APK/LOAD.png?raw=true) | ![Welcome](https://github.com/hdrzk/Aplikasi-AquaTrack/blob/main/SS%20APK/WELCOME.png?raw=true) | ![Home](https://github.com/hdrzk/Aplikasi-AquaTrack/blob/main/SS%20APK/MAIN.png?raw=true) |

## Code Breakdown (*example Main of app)
📱 Tampilan Utama (`activity_main.xml`)
``` xml
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#E3F2FD"
    tools:context=".MainActivity">


    <!-- Wave kiri atas -->
    <ImageView
        android:id="@+id/waveTopLeft"
        android:layout_width="400dp"
        android:layout_height="650dp"
        android:src="@drawable/wave_top_left"
        android:scaleType="fitXY"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <!-- Wave kanan bawah -->
    <ImageView
        android:id="@+id/waveBottomRight"
        android:layout_width="400dp"
        android:layout_height="650dp"
        android:src="@drawable/wave_bottom_right"
        android:scaleType="fitXY"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Kotak tengah -->
    <LinearLayout
        android:id="@+id/centerCard"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:background="@drawable/center_card_bg"
        android:padding="24dp"
        android:layout_margin="32dp"
        android:elevation="4dp"
        android:gravity="center"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <TextView
            android:id="@+id/tvTitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="AquaTrack - Drink Your Water"
            android:textSize="20sp"
            android:textStyle="bold"
            android:textColor="#FFFFFF"
            android:layout_marginBottom="20dp" />

        <EditText
            android:id="@+id/etName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Masukkan Nama Anda"
            android:textColorHint="#B0BEC5"
            android:inputType="textPersonName"
            android:textColor="#FFF"
            android:backgroundTint="#0D47A1"
            android:padding="12dp"
            android:layout_marginBottom="16dp" />

        <TextView
            android:id="@+id/tvSelectedTime"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Waktu Belum Dipilih"
            android:textColor="#FFF"
            android:layout_marginBottom="16dp" />

        <Button
            android:id="@+id/btnPickTime"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Pilih Waktu"
            android:textAllCaps="false"
            android:layout_marginBottom="16dp"
            android:backgroundTint="#0D47A1"
            android:textColor="#FFF" />

        <Button
            android:id="@+id/btnSave"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Simpan Pengingat"
            android:textAllCaps="false"
            android:backgroundTint="#1976D2"
            android:textColor="#FFF" />
    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

``` xml
🔹 Penjelasan:
Menggunakan ConstraintLayout sebagai root layout.

ImageView untuk dekorasi wave di kiri atas dan kanan bawah.

LinearLayout di tengah untuk konten utama: input nama, pemilihan waktu, dan tombol aksi.

Warna dominan: biru muda dan putih dengan kesan segar.
```

🧠 Logika Aplikasi (MainActivity.kt)
``` kotlin
package com.cnsb.reminderme

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelectedTime: TextView
    private lateinit var etName: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPickTime = findViewById<Button>(R.id.btnPickTime)
        val btnSave = findViewById<Button>(R.id.btnSave)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)
        etName = findViewById(R.id.etName)

        btnPickTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this, { _, h, m ->
                val time = String.format("%02d:%02d", h, m)
                tvSelectedTime.text = "Waktu untuk Minum: $time"
            }, hour, minute, true)

            timePicker.show()
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val time = tvSelectedTime.text.toString()

            if (name.isEmpty() || time == "Waktu Belum Dipilih") {
                Toast.makeText(this, "Ayo lengkapi namamu terlebih dahulu!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Halo $name, jangan lupa minum air ya!\n$time", Toast.LENGTH_LONG).show()
            }
        }
    }

}
```

🕒 Pemilihan Waktu:
``` kotlin
btnPickTime.setOnClickListener {
    val timePicker = TimePickerDialog(this, { _, h, m ->
        val time = String.format("%02d:%02d", h, m)
        tvSelectedTime.text = "Waktu untuk Minum: $time"
    }, hour, minute, true)

    timePicker.show()
}
```
Membuka dialog waktu (TimePickerDialog).
Menyimpan dan menampilkan waktu yang dipilih.

💾 Menyimpan Pengingat:
``` kotlin
btnSave.setOnClickListener {
    val name = etName.text.toString()
    val time = tvSelectedTime.text.toString()

    if (name.isEmpty() || time == "Waktu Belum Dipilih") {
        Toast.makeText(this, "Ayo lengkapi namamu terlebih dahulu!", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(this, "Halo $name, jangan lupa minum air ya!\n$time", Toast.LENGTH_LONG).show()
    }
}
```
Validasi input nama dan waktu.
Tampilkan pesan motivasi melalui Toast.

---

## ✨ Tentang Developer
Aplikasi ini dibuat dengan ❤ oleh HaidirZackyy sebagai latihan dan pengembangan aplikasi kesehatan berbasis Android.

---

## 🔧 Try it!

Clone repository:

```bash
git clone https://github.com/username/AquaTrack.git
