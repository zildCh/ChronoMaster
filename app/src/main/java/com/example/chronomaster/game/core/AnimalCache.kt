package com.example.chronomaster.game.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "animals"
)
data class AnimalCache(
    @PrimaryKey
    @ColumnInfo(name = "animal_id")
    val animalId: Long,
    @ColumnInfo(name = "animal_name")
    val animalName: String,
    @ColumnInfo(name = "animal_price")
    val price: Int,
    @ColumnInfo(name = "is_purchased")
    val isPurchased: Boolean,
    @ColumnInfo(name = "animal_icon", typeAffinity = ColumnInfo.BLOB)
    val icon: ByteArray? // Поле для хранения изображения в виде массива байтов
) /*{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AnimalCache

        if (animalId != other.animalId) return false
        if (animalName != other.animalName) return false
        if (price != other.price) return false
        if (isPurchased != other.isPurchased) return false
        if (icon != null) {
            if (other.icon == null) return false
            if (!icon.contentEquals(other.icon)) return false
        } else if (other.icon != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = animalId.hashCode()
        result = 31 * result + animalName.hashCode()
        result = 31 * result + price
        result = 31 * result + isPurchased.hashCode()
        result = 31 * result + (icon?.contentHashCode() ?: 0)
        return result
    }*/
//}