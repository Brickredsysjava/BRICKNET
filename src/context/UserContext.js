import React, { useState, createContext, useContext } from 'react'

const AuthContext = createContext();

export const UserContext = ({ children }) => {
    const auth = useState(JSON.parse(sessionStorage.getItem('userData')));
    
    return (
        <AuthContext.Provider value={auth}>
            {
                children
            }
        </AuthContext.Provider>
    )
}

export const UserDetailsProvider = () => useContext(AuthContext)