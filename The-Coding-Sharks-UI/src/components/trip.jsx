import React, { useEffect, useState } from "react";
import axios from "axios";
import LeafletMap from "./leafletMap";
import { useParams } from "react-router-dom";

const Trip = () => {
  const { tripId } = useParams();
  const [destinations, setDestinations] = useState([]);
  const [destinationName, setDestinationName] = useState("");
  const [editingDestinationId, setEditingDestinationId] = useState(null);
  const [updatedName, setUpdatedName] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/destinations/trips/${tripId}`, {
        withCredentials: true,
      })
      .then((response) => {
        if (Array.isArray(response.data)) {
          setDestinations(response.data);
        } else {
          console.error("Unexpected response format:", response.data);
        }
      })
      .catch((error) => {
        console.error("Error fetching destinations:", error);
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
        fetchDestinations();
      } else {
        setMessage("Failed to add destination.");
      }
    } catch (error) {
      console.error("Error adding destination:", error);
      setMessage("Error adding destination.");
    }
  };

  const handleDeleteDestination = async (id) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/destinations/delete/${id}`,
        { withCredentials: true }
      );

      if (response.status === 204) {
        // Update the destinations state to remove the deleted destination
        setDestinations(
          destinations.filter((destination) => destination.id !== id)
        );
        setMessage("Destination deleted successfully");
      } else {
        setMessage("Failed to delete destination");
      }
    } catch (error) {
      console.error("Error deleting destination:", error);
      setMessage("Error deleting destination");
    }
  };
  const handleEditDestination = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(
        `http://localhost:8080/api/destinations/${editingDestinationId}`,
        null,
        {
          params: { newName: updatedName },
          withCredentials: true,
        }
      );

      if (response.status === 200) {
        setMessage("Destination updated successfully.");
        setEditingDestinationId(null); // Clear the edit mode
        setUpdatedName("");
        // Refresh the destinations list after updating
        fetchDestinations();
      } else {
        setMessage("Failed to update destination.");
      }
    } catch (error) {
      console.error("Error updating destination:", error);
      setMessage("Error updating destination.");
    }
  };

  const fetchDestinations = () => {
    axios
      .get(`http://localhost:8080/api/destinations/trips/${tripId}`, {
        withCredentials: true,
      })
      .then((response) => {
        if (Array.isArray(response.data)) {
          setDestinations(response.data);
        } else {
          console.error("Unexpected response format:", response.data);
        }
      })
      .catch((error) => {
        console.error("Error fetching destinations:", error);
      });
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
      {editingDestinationId && (
        <form onSubmit={handleEditDestination}>
          <input
            type="text"
            value={updatedName}
            onChange={(e) => setUpdatedName(e.target.value)}
            placeholder="New destination name"
          />
          <button type="submit">Update Destination</button>
        </form>
      )}
      <ul>
        {destinations.map((destination) => (
          <li key={destination.id}>
            {destination.name}
            <button
              onClick={() => {
                setEditingDestinationId(destination.id);
                setUpdatedName(destination.name);
              }}
            >
              Edit
            </button>
            <button onClick={() => handleDeleteDestination(destination.id)}>
              Delete
            </button>
          </li>
        ))}
      </ul>
      <LeafletMap destinations={destinations} />
    </div>
  );
};

export default Trip;
