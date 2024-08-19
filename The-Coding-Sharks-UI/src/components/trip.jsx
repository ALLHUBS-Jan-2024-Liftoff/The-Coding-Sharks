import React, {useEffect, useState } from "react";
import axios from "axios";
import LeafletMap from "./leafletMap";

const tripId = new URLSearchParams(window.location.search);

// const Trip = ({tripId}) => {

const Trip = () => {
    const [destinations, setDestinations] = useState([]);
    const [users, setUsers] = useState([]);

    useEffect(()=> {
        axios.get(`http://localhost:8080/api/trip/${tripId}/destinations`)
        .then(response => {
            if (Array.isArray(response.data)) {
                console.log(response.data);
                setDestinations(response.data);
                setUsers(response.data); 
            } else {
                console.error('Unexpected response format:', response.data);
            }
        })
        .catch(error => {
            console.error('Error fetching destinations:', error);
        });
    }, []);
    
    return (
        <div>
            <h2> Trip Details for trip id: {tripId} </h2>
            <ul>
                {destinations.map(destination => (
                    <li key = {destination.id}> {destination.name} </li>
                ))}
            </ul>
            <LeafletMap destinations={destinations} />
            <h2> Users on trip id: {tripId} </h2>
            <ul>
                {users.map(user => (
                    <li key = {user.id}>{user.name}</li>
                ))}
            </ul>
        </div>
    )
};
export default Trip;
