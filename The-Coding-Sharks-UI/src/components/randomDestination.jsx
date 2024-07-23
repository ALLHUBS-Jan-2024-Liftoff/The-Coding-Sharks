import React, { useState } from "react";

const RandomDestination = () => {
    const [city, setCity] = useState("");

    const cities = [
        "New York City",
        "Los Angeles",
        "Chicago",
        "Houston",
        "Phoenix",
        "Philadelphia",
        "San Antonio",
        "San Diego",
        "Dallas",
        "San Jose",
        "Austin",
        "Jacksonville",
        "Fort Worth",
        "Columbus",
        "Charlotte",
        "San Francisco",
        "Indianapolis",
        "Seattle",
        "Denver",
        "Washington D.C."
    ];

    const getRandomCity = () => {
        const randomIndex = Math.floor(Math.random() * cities.length);
        return cities[randomIndex];
    };

    const handleClick = () => {
        setCity(getRandomCity());
    };

    return (
        <div>
            <h2>You're going to {city}!</h2>
            <button onClick={handleClick}>
                Pick a Random US Destination!
            </button>
        </div>
    );
};

export default RandomDestination;
