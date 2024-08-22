import React, { useState } from "react";
import "./App.css";
import Login from "./components/Login";
import Register from "./components/Register";
import RandomDestination from "./components/randomDestination";
import Trip from "./components/trip";
import TripSummary from "./components/tripSummary";
import AllTrips from "./components/AllTrips";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
  Link,
} from "react-router-dom";
import Logout from "./components/Logout";

function App() {
  const [authenticated, setAuthenticated] = useState(false);

  return (
    <Router>
      <nav>
        {!authenticated ? (
          <>
            <Link to="/login">Login</Link>
            <Link to="/register">Register</Link>
          </>
        ) : (
          <>
            <Link to="/randomDestination">Randomize Destination</Link>
            <Link to="/tripSummary">Trip Summary</Link>
            <Link to="/logout">Logout</Link>
          </>
        )}
      </nav>
      <div className="App">
        <header className="App-header">
          <Routes>
            {/* Public Routes */}
            <Route
              path="/login"
              element={<Login setAuthenticated={setAuthenticated} />}
            />
            <Route path="/register" element={<Register />} />

            {/* Private Routes */}
            {authenticated ? (
              <>
                <Route path="/randomDestination" element={<RandomDestination />} />
                <Route path="/trip" element={<Trip />} />
                <Route path="/tripSummary" element={<TripSummary />} />
                <Route
                  path="/logout"
                  element={
                    <Logout setAuthenticated={setAuthenticated} />
                  }
                />
              </>
            ) : (
              <Route path="*" element={<Navigate to="/login" replace />} />
            )}
          </Routes>
        </header>
      </div>
    </Router>
  );
}

export default App;
