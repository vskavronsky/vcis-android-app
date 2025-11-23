package com.automotive.bootcamp.userprofiles.data.datasource

import com.automotive.bootcamp.userprofiles.domain.model.User
import kotlinx.coroutines.delay

class UserNetworkMockDataSource : UserDataSource {
    override suspend fun getUsers(): List<User> {
        // Simulate a network request delay
        delay(1000) // 1 second delay

        // Simulate fetching user data from a network API
        return listOf(
            User("Alice", 1),
            User("Bob", 2),
            User("Charlie", 3),
            User("David", 4)
        )
    }
}
