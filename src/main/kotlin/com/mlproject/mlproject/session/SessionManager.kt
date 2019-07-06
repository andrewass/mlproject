package com.mlproject.mlproject.session

class SessionManager {

    companion object {
        val sessionMap = mutableMapOf<Long, Session>()

        private const val MULTIPLIER = 1000000000L

        fun getSession(sessionId : Long) : Session {
            var session = sessionMap[sessionId]
            if (session == null) {
                var newSessionId: Long
                do {
                    newSessionId = (Math.random() * MULTIPLIER).toLong() + 1L
                } while (sessionMap.containsKey(newSessionId))
                session = Session(newSessionId)
                sessionMap[newSessionId] = session
            }
            return session
        }
    }
}