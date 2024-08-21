import React, { useEffect, useState } from "react";
import axios from "axios";
import Trip from "./trip"; 


const AllTrips = () => {
    const [trips, setTrips] = useState([]);

    useEffect(() => {
        axios.get("http://localhost:8080/api/trips")
            .then(response => {
                if (Array.isArray(response.data)) {
                    console.log(response.data);
                    setTrips(response.data);
                } else {
                    console.error('Unexpected response format:', response.data);
                }
            })
            .catch(error => {
                console.error('Error fetching trips:', error);
            });
    }, []);

    return (
        <div>
            <h2>All Trips</h2>
            <ul>
                {trips.map(trip => (
                    <li key={trip.id}>
                        <Trip tripId={trip.id} />
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AllTrips;