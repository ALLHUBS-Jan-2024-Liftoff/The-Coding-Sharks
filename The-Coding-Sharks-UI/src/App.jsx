// <<<<<<< 25-front-end-randomize-destination
// import React from 'react';
// import LeafletMap from './components/leafletMap';
// import RandomDestination from './components/randomDestination';
// =======
import { useState } from "react";
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';

// >>>>>>> main
import './App.css'
import { Login } from './Login';
import { Register } from './Register';

function App() {
  const [currentForm, setCurrentForm] = useState('login');

  const toggleForm = (formName) => {
    setCurrentForm(formName);
  }

  return (
// <<<<<<< 25-front-end-randomize-destination
//     <div>
//       <h1>Trial Run Home Page</h1>
//       <LeafletMap />
//       <RandomDestination />
//     </div>
//   );
// =======
      <div className="App">
        {
          currentForm === "login" ? <Login onFormSwitch={toggleForm} /> : <Register onFormSwitch={toggleForm} />
        }
      </div>
  )
// >>>>>>> main
}
export default App;
