package com.example.chronomaster.game.core

import androidx.room.ColumnInfo
import com.example.chronomaster.game.core.AnimalDao
import javax.inject.Inject

class AnimalRepository {
    interface ReadList {
        suspend fun animals(): List<Animal>
    }

    interface Read {
        suspend fun animal(categoryId: Long): Animal
    }

    interface All : Read, ReadList

    class Base @Inject constructor(
        private val dao: AnimalDao,
    ) : All {

        override suspend fun animal(animalId: Long): Animal =
            dao.animal(animalId).let { Animal(it.animalId, it.animalName, it.price, it.isPurchased, it.icon) }

        override suspend fun animals(): List<Animal> =
            dao.animals().map { Animal(it.animalId, it.animalName, it.price, it.isPurchased, it.icon) }

    }
}

data class Animal(
    private val animalId: Long,
    private val animalName: String,
    private val price: Int,
    private val isPurchased: Boolean,
    private val icon: ByteArray?
) {
    //fun toUi(): CategoryUi =
    // CategoryUi.Base(id, name)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Animal

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
    }
}