import React from 'react';
import { useState } from "react";
import './App.css'

import { Login } from './Login';
import { Register } from './Register';
import Packlist from "./components/packList";

function App() {
  const [currentForm, setCurrentForm] = useState('login');

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  }

  return (
  //   <div>
  //     <h1>Cole's testing.</h1> //leaving this commented out to test components
  //     <Packlist />
      
  //   </div>
  // );

      <div className="App">
        {
          currentForm === "login" ? <Login onFormSwitch={toggleForm} /> : <Register onFormSwitch={toggleForm} />
        }
      </div>
  )
}
export default App;
