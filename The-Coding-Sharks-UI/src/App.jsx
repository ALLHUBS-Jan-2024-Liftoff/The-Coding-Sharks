import React from "react";
import { useState } from "react";
import "./App.css";
import { Route, Routes } from "react-router-dom";

import { Login } from "./components/Login";
import { Register } from "./components/Register";
import Packlist from "./components/packList";
import RandomDestination from "./components/randomDestination";
import Home from "./components/home";
import Trip from ".components/trip";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/randomDestination" element={<RandomDestination />} />
        <Route path="/packList" element={<Packlist />} />
        <Route path="/trip" element={<Trip />} />
      </Routes>
    </div>
  );
}

//I have shelly's home page commented out to set up routing.

// function App() {
//   const [currentForm, setCurrentForm] = useState('login');

//   const toggleForm = (formName) => {
//     setCurrentForm(formName);
//   }

//   return (
//     <div className="App">
//         {
//           currentForm === "login" ? <Login onFormSwitch={toggleForm} /> : <Register onFormSwitch={toggleForm} />
//         }
//       </div>
//   )
// }
export default App;
