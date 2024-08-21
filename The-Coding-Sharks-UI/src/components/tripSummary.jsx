import React, { useState, useEffect } from "react";
import axios from "axios";
import LeafletMap from "./leafletMap";

const queryString = new URLSearchParams(window.location.search);
const tripId = queryString.get('tripid');

const TripSummary = () => {
    const [destList, setDestList] = useState([]);
    let latList = [];
    let longList = [];

    // Using useEffect to control when the axios call is made
    useEffect(() => {
        console.log(tripId);
        axios.get(`http://localhost:8080/api/trip/${tripId}`)
            .then(response => {
                setDestList(response.data.destinationList);
                console.log(response.data);

                response.data.destinationList.map(item => {
                    latList.push(item.latitude);
                    longList.push(item.longitude);
                });

                console.log(latList);
                console.log(longList);
            })
            .catch(error => {
                console.error('Error fetching trip data:', error);
            });
    }, [tripId]); // The effect depends on tripId

    return (
        <div>
            <h1>Trip Summary</h1>
            <LeafletMap destinations={destList} />
            <h2>Destinations</h2>
            <ul>
                {destList.map(item => (
                    <li key={item.id}>
                        {item.name}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TripSummary;
