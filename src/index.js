import React from 'react';
import ReactDOM from 'react-dom/client';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import App from './App.js';
import { UserContext } from './context/UserContext';
import './index.css';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <BrowserRouter>
        <UserContext>
            <App />
        </UserContext>
    </BrowserRouter>
);

reportWebVitals();