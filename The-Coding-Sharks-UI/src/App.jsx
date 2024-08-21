import React from "react";
import { useState } from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";

import { Login } from "./components/Login";
import { Register } from "./components/Register";
import Packlist from "./components/packList";
import RandomDestination from "./components/randomDestination";
import Home from "./components/home";
import Trip from "./components/trip";
import Test from "./components/test";
// <<<<<<< reverting-and-debugging
// import TripSummary  from "./components/tripSummary";
// =======

// import AllTrips from "./components/AllTrips";

// >>>>>>> main

function App() {
  const [currentForm, setCurrentForm] = useState('login');

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  }

  return (
    <div className="App">
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/test" element={<Test />} />
        <Route path="/randomDestination" element={<RandomDestination />} />
        <Route path="/packList" element={<Packlist />} />
// <<<<<<< reverting-and-debugging
//         <Route path="/trip" element={<Trip />} />
//         <Route path="/test" element={<Test />} />
//         <Route path="/TripSummary" element={<TripSummary />} />
// =======
//         <Route path="/trip/:tripId" element={<Trip />} /> {/* Assuming you pass tripId as a parameter */}
//         <Route path="/allTrips" element={<AllTrips />} />
//         <Route path="/login" element={<Login onFormSwitch={toggleForm} />} />
//         <Route path="/register" element={<Register onFormSwitch={toggleForm} />} />
// >>>>>>> main
      </Routes>
      <div>
        {/* {currentForm === "login" ? <Login onFormSwitch={toggleForm} /> : <Register onFormSwitch={toggleForm} />} */}
      </div>
    </div>
  );
}
export default App;
