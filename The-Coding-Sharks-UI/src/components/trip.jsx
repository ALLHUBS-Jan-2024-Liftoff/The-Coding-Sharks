import React, {useEffect, useState } from "react";
import axios from "axios";
import LeafletMap from "./leafletMap";

const Trip = ({tripId}) => {
    const [destinations, setDestinations] = useState([]);

    useEffect(()=> {
        axios.get(`http://localhost:8080/api/trip/${tripId}/destinations`)
        .then(response => {
            if (Array.isArray(response.data)) {
                console.log(response.data);
                setDestinations(response.data);
            } else {
                console.error('Unexpected response format:', response.data);
            }
        })
        .catch(error => {
            console.error('Error fetching destinations:', error);
        });
    }, [tripId]);
    
    return (
        <div>
            <h2> Trip Details for trip id: {tripId} </h2>
            <ul>
                {destinations.map(destination => (
                    <li key = {destination.id}> {destination.name} </li>
                ))}
            </ul>
            <LeafletMap destinations={destinations} />
        </div>
    )
};

export default Trip;

