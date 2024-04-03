package com.example.chronomaster.task.core

import javax.inject.Inject

class CategoryRepository {
    interface ReadList {
        suspend fun categories(): List<Category>
    }

    interface Read {
        suspend fun category(categoryId: Long): Category
    }

    interface All : Read, ReadList

    class Base  @Inject constructor(
        private val dao: CategoryDao,
    ) : All {

        override suspend fun category(categoryId: Long): Category =
            dao.category(categoryId).let { Category(it.id, it.name, it.userId) }

        override suspend fun categories(): List<Category> =
            dao.categories().map { Category(it.id, it.name, it.userId) }

    }
}

data class Category(
    private val id: Long,
    private val name: String,
    private  val userId: Long
) {
    //fun toUi(): CategoryUi =
       // CategoryUi.Base(id, name)
}