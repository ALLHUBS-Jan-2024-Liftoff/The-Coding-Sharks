import React, { useState } from "react";
import axios from "axios";

const AddTrip = () => {
    const [name, setName] = useState("");
    const [message, setMessage] = useState("");

const submitTrip = async (e) => {
    e.preventDefault()
    try {
      const response = await axios.post(
          `http://localhost:8080/api/trip`,
          null,
          {
              params: { name },
              withCredentials: true,
          }
      );

      // Check if the response status is 201 and handle the success message
      if (response.status === 201) {
          // You can either set the success message here directly
          setMessage("Trip added successfully!");
      } else {
          // Handle other unexpected statuses if needed
          setMessage("Unexpected response status.");
      }
  } catch (error) {
      console.log(`Name: ${name}`);
      setMessage(error.response?.data?.message || "Failed to add trip.");
  }
};



  return (

    <div>
      <h2>Create a New Trip</h2>
      <form onSubmit={submitTrip}>
      <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="e.g. My Trip to NYC"
        />
        <button type="submit">Add to My Profile</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default AddTrip;