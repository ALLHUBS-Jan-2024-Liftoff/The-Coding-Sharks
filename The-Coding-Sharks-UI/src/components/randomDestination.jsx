import React, { useState } from "react";
import axios from "axios";
import LeafletMap from "./leafletMap";

const RandomDestination = () => {
  const [selectedCity, setSelectedCity] = useState(null);

  const cities = [
    "New York City", "Los Angeles", "Chicago", "Houston", "Phoenix", 
    "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose",
    "Austin", "Jacksonville", "Fort Worth", "Columbus", "Charlotte", 
    "San Francisco", "Indianapolis", "Seattle", "Denver", "Washington D.C."
  ];

  const getRandomCity = () => {
    const randomIndex = Math.floor(Math.random() * cities.length);
    return cities[randomIndex];
  };

  const fetchCityCoordinates = async (cityName) => {
    const apiUrl = `http://localhost:8080/api/destinations/geocode?text=${encodeURIComponent(cityName)}`;
    console.log("Encoded City Name:", encodeURIComponent(cityName)); 

    try {
      const response = await axios.get(apiUrl);
      const data = response.data;

      if (data && data.latitude !== undefined && data.longitude !== undefined) {
        return {
          name: data.name,
          latitude: data.latitude,
          longitude: data.longitude,
        };
      } else {
        throw new Error("Location not found in response data");
      }
    } catch (error) {
      console.error("Error fetching coordinates:", error.message);
      throw new Error("Error fetching coordinates");
    }
  };

  const handleClick = async () => {
    try {
      const randomCityName = getRandomCity();
      const cityData = await fetchCityCoordinates(randomCityName);
      setSelectedCity(cityData);
    } catch (error) {
      console.error("Error fetching coordinates:", error);
    }
  };

  return (
    <div>
      <h2>You're going to {selectedCity ? selectedCity.name : "..."}</h2>
      <button onClick={handleClick}>Pick a Random US Destination!</button>
      {selectedCity && (
        <LeafletMap selectedCity={selectedCity} />
      )}
    </div>
  );
};

export default RandomDestination;

