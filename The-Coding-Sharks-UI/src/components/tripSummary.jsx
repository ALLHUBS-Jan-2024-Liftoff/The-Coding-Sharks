import React, {useEffect, useState } from "react";
import axios from "axios";

/*
Need to take in trip ID from URL parameter

Trip Summary

Users on trip:
[User A], [User B], [User C], . . . 

Trip destinations:

-Destination 1
-Destination 2
-Destination 3
. . . . 
*/
// const {tripId} = useParams();

const primUser = "";
const secUsers = [];
const urlTripId = new URLSearchParams(window.location.search);

const tripSummary = () => {
    const [destinations, setDestinations] = React.useState([]);
    const [users, setUsers] = React.useState([]);
    
// Currently set up to pull all destinations
// Need to rework to pull only destinations associated with a given trip
// Pull items from trip identified with queryParameters
    useEffect(() => {
        axios.get(`http://localhost:8080/api/trip/${urlTripId}`)
            .then(response => {
                
                // Need to check what form the response comes in before working on it
                console.log(response);

                //Pull in array of destinations from trip (?)
                //Need to specify what the trip id is - use urlTripId

                // if (Array.isArray(response.data)) {
                //     setDestinations(response.data.map(trip => ({

                //         destinations: trip.destinations
                //     })))

                // } else {
                //     console.error('Unexpected response format:', response.data);
                // }
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);




    return (
        <div>
            <h1>Trip Summary</h1>
            <h3>Users on trip:</h3>
            {users.map((user) => (
                <h4>{user}</h4>
            ))}
            <h3>Trip destinations:</h3>
            {/* {destinations} */}
            {destinations.map((destination) => (
                <h4>{destination}</h4>
            ))}
            {/* Testing - checking to see the query parameter get passed */}
            <ul>Query Parameters
            <li>{urlTripId}</li>
            </ul>
        </div>
    );
};

export default tripSummary;
