import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UserHomePage = () => {
  const [user, setUser] = useState(null);
  const [trips, setTrips] = useState([]);
  

  useEffect(() => {
    // Fetch logged-in user's data
    const fetchUserData = async () => {
      
      try {
        const userResponse = await axios.get('http://localhost:8080/api/user/currentUser', {withCredentials: true});
        setUser(userResponse.data);

        const tripsResponse = await axios.get(`http://localhost:8080/api/trip/user/${userResponse.data.id}`, {withCredentials: true});
        setTrips(tripsResponse.data);
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    };

    fetchUserData();
  }, []);

  if (!user) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h1>Welcome, {user.firstName} {user.lastName}</h1>
      <p>Email: {user.email}</p>
      <h2>Your Trips</h2>
      {trips.length === 0 ? (
        <p>You have not created any trips yet.</p>
      ) : (
        <ul>
        {Array.isArray(trips) ? trips.map(trip => (
          <li key={trip.id}>{trip.name}</li>
        )) : <p>Failed to load trips</p>}
      </ul>
      )}
    </div>
  );
};

export default UserHomePage;

