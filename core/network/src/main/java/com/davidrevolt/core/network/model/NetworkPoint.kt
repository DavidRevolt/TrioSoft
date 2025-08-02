package com.davidrevolt.core.network.model


import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date


// Firestore require default constructor
data class NetworkPoint(
    @DocumentId
    val id: String = "", // Firebase automatically populated with the document's ID
    val humidity: Int = 0,
    val temperature: Int = 0,
    val date: Date = Timestamp.now().toDate(),
    val userId: String = "",
    @ServerTimestamp
    val createdAt: Date = Timestamp.now()
        .toDate()  // @ServerTimestamp-annotated field, it will be replaced with a server-generated timestamp
)
