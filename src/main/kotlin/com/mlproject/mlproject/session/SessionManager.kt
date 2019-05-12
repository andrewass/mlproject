package com.mlproject.mlproject.session

class SessionManager {

    companion object {
        val sessionMap = mutableMapOf<Long, Session>()

        fun getSession(sessionId : Long) : Session {
            var session = sessionMap[sessionId]
            if (session == null) {
                var newSessionId: Long
                val multiplier = 1000000000L
                do {
                    newSessionId = (Math.random() * multiplier).toLong() + 1L
                } while (sessionMap.containsKey(newSessionId))
                session = Session(newSessionId)
                sessionMap[newSessionId] = session
            }
            return session
        }
    }
}