import React from 'react';
import { Route, Routes } from 'react-router-dom';

// component
import Login from './pages/login/Login'
import DashboardLayout from './pages/dashboard/DashboardLayout';
import ForgetPassword from './pages/forgetPasword/ForgetPassword';
import Home from './pages/dashboardHome/Home';
import Suggestions from './pages/suggestions/Suggestions';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route element={<DashboardLayout />}>
        <Route path="hello" element={<Home />} />
      </Route>
      <Route path="suggestions" element={<Suggestions />} />
    </Routes>
  )
}

export default App;