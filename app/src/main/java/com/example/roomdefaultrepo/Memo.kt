package com.example.roomdefaultrepo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Memo(
    var content : String
){
    @PrimaryKey(autoGenerate = true)
    var id :Long = 0
}
