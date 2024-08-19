import React, { useState, useEffect } from "react";

const tripId = new URLSearchParams(window.location.search);

const Test = () => {

  const [destinations, setDestinations] = useState([]);
  const [currentForm, setCurrentForm] = useState('login');
  const [trip, setTrip] = useState([]);

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(`Trip #${trip}`);

}


  useEffect(() => {

    fetch(`http://localhost:8080/api/trip/${trip}/destinations`)


  }, [])


  return (
    <div>
              <h2>Testing - trip #{trip}</h2>
              {/* changed from register to Log In */}
              <form className="login-form" onSubmit={handleSubmit}>
                  <label htmlFor="tripNumber">Trip Number:</label>
                  {/* <input value={email} onChange={(e) => setEmail(e.target.value)}type="email" placeholder="youremail@gmail.com" id="email" name="email" /> */}
                  <input value={trip} onChange={(e) => setTrip(e.target.value)} />
                  {/* <label htmlFor="password">password</label> */}
                  {/* <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="*************" id="password" name="password" /> */}
                  <button type="submit">Submit</button>
              </form>
          <button className="link-btn" onClick={() => props.onFormSwitch('register')}>No account? Register Here.</button>
      </div>
  );

};


export default Test;
