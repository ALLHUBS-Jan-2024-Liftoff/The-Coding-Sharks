import React, { useState } from "react";
import axios from "axios";

const AddTrip = () => {
    const [name, setName] = useState("");
    const [message, setMessage] = useState("");

const submitTrip = async (e) => {
    e.preventDefault()
    try {
      //I think the 400 error is from the syntax for this axios post request
        const response = await axios.post(
            `http://localhost:8080/api/trip`, 
            name
    // Comment this out when user auth is added
      ,{
          withCredentials: true,
      }

    );
    setMessage(response.data.message);
    }

    catch (error) {
        console.log(`Name: ${name}`);

        setMessage(error.response?.data?.message || "Failed to add trip.");
    }



}



  return (

    <div>
      <h2>Add New Trip</h2>
      <form onSubmit={submitTrip}>
      <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="e.g. My Trip to NYC"
        />
        <button type="submit">Submit</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default AddTrip;