import React from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AppBar from "../components/AppBar";
import LandingPage from '../pages/LandingPage';
import RoomPage from "../pages/RoomPage";
import DevicesPage from "../pages/DevicesPage";
import FunctionalityPage from "../pages/FunctionalityPage";

export default function MainRoute({ darkMode, setDarkMode }) {
    return (
        <Router>
            <AppBar check={darkMode} change={() => setDarkMode(!darkMode)} />
            <Routes>
                <Route path="/" element={<LandingPage />} />
                <Route path="/rooms" element={<RoomPage />} />
                <Route path="/rooms/:roomId/devices" element={<DevicesPage />} />
                <Route path="/rooms/:roomId/devices/:deviceId" element={<FunctionalityPage />} />
            </Routes>
        </Router>
    );
}
