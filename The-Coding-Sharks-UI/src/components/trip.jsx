import React, { useEffect, useState } from "react";
import axios from "axios";
import LeafletMap from "./leafletMap";
import { useParams } from "react-router-dom";

const Trip = () => {
    const { tripId } = useParams();
  const [destinations, setDestinations] = useState([]);
  const [destinationName, setDestinationName] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    axios.get(`http://localhost:8080/api/destinations/trips/${tripId}`, { withCredentials: true })
      .then(response => {
        if (Array.isArray(response.data)) {
          setDestinations(response.data);
        } else {
          console.error('Unexpected response format:', response.data);
        }
      })
      .catch(error => {
        console.error('Error fetching destinations:', error);
      });
  }, [tripId]);

  const handleAddDestination = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        `http://localhost:8080/api/destinations/addDestination/${tripId}`,
        null,
        {
          params: { destinationName },
          withCredentials: true,
        }
      );

      if (response.status === 200) {
        setMessage("Destination added successfully.");
        // Refresh the destinations list after adding a new one
        axios.get(`http://localhost:8080/api/destinations/trips/${tripId}`, { withCredentials: true })
          .then(response => {
            if (Array.isArray(response.data)) {
              setDestinations(response.data);
            } else {
              console.error('Unexpected response format:', response.data);
            }
          })
          .catch(error => {
            console.error('Error fetching destinations:', error);
          });
      } else {
        setMessage("Failed to add destination.");
      }
    } catch (error) {
      console.error('Error adding destination:', error);
      setMessage("Error adding destination.");
    }
  };

  const handleDeleteDestination = async (id) => {
    console.log("Destination ID: " + id);
    try {
      const response = await axios.delete(`http://localhost:8080/api/destinations/delete/${id}`, { withCredentials: true });

      if (response.status === 204) {  
        // Update the destinations state to remove the deleted destination
        setDestinations(destinations.filter(destination => destination.id !== id));
        setMessage("Destination deleted successfully");
      } else {
        setMessage("Failed to delete destination");
      }
    } catch (error) {
      console.error('Error deleting destination:', error);
      setMessage("Error deleting destination");
    }
  };
  

  return (
    <div>
      <h2>Trip Details for trip id: {tripId}</h2>
      <form onSubmit={handleAddDestination}>
        <input
          type="text"
          value={destinationName}
          onChange={(e) => setDestinationName(e.target.value)}
          placeholder="Enter city name"
        />
        <button type="submit">Add Destination</button>
      </form>
      {message && <p>{message}</p>}
      <ul>
        {destinations.map(destination => (
          <li key={destination.id}>
            {destination.name}
            <button onClick={() => handleDeleteDestination(destination.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <LeafletMap destinations={destinations} />
    </div>
  );
};

export default Trip;
