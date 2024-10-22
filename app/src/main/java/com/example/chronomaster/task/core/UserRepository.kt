package com.example.chronomaster.task.core
import javax.inject.Inject
class UserRepository {
    interface ReadList {
        suspend fun users(): List<User>
    }

    interface Read {
        suspend fun user(categoryId: Long): User
    }
    interface Edit {
        suspend fun updateUser(id: Long, balance: Int)
    }
    interface All : Read, ReadList, Edit

    class Base @Inject constructor(
        private val dao: UserDao,
    ) : All {

        override suspend fun user(categoryId: Long): User =
            dao.user(categoryId).let { User(it.id, it.balance) }

        override suspend fun users(): List<User> =
            dao.users().map { User(it.id, it.balance) }

        override suspend fun updateUser(id: Long, balance: Int) {
            val user = dao.user(id)
            dao.insert(user.copy(balance = balance))
        }
    }
}

data class User(
    private val id: Long,
    private val balance: Int
) {
    //fun toUi(): CategoryUi =
        //CategoryUi.Base(id, name)
}