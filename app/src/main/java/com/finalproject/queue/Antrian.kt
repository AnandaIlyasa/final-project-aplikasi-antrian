package com.finalproject.queue

import java.io.Serializable

data class Antrian(var nama: String, var deskripsi: String, var nomor: Int? = 0, var jumlah: Int? = 0) : Serializable
