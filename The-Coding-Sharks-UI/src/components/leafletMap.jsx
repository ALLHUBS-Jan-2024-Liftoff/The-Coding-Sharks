import React, { useEffect, useState } from "react";
import axios from "axios";
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";
import L from 'leaflet';

// FitBoundsToMarkers component for adjusting map view to markers
const FitBoundsToMarkers = ({ markers }) => {
    const map = useMap();
    useEffect(() => {
        if (markers.length > 0) {
            const bounds = L.latLngBounds(markers.map(marker => marker.geocode));
            map.fitBounds(bounds, { padding: [50, 50] }); 
        }
    }, [markers, map]);

    return null;
};

const LeafletMap = ({ destinations = [], selectedCity }) => {
    const [markers, setMarkers] = useState([]);

    useEffect(() => {
        let updatedMarkers = [];

        if (selectedCity) {
          console.log(selectedCity)
          console.log("testing")
            updatedMarkers = [{
                geocode: [selectedCity.latitude, selectedCity.longitude],
                popUp: selectedCity.name
            }];
            console.log(updatedMarkers + "1")

        } else if (destinations.length > 0) {
            updatedMarkers = destinations.map(destination => ({
                geocode: [destination.latitude, destination.longitude],
                popUp: destination.name
            }));
        } else {
            axios.get('http://localhost:8080/api/destinations')
                .then(response => {
                    if (Array.isArray(response.data)) {
                        updatedMarkers = response.data.map(destination => ({
                            geocode: [destination.latitude, destination.longitude],
                            popUp: destination.name
                        }));
                        setMarkers(updatedMarkers);
                    } else {
                        console.error('Unexpected response format:', response.data);
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        }
        if (JSON.stringify(markers) !== JSON.stringify(updatedMarkers)) {
          setMarkers(updatedMarkers);
      }
    }, [destinations, selectedCity]);

    return (
        <MapContainer center={[38.6270, -90.1994]} zoom={12} style={{ height: "100vh", width: "100vw" }}>
            <TileLayer
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />

            {markers.map((marker, index) => (
                <Marker key={index} position={marker.geocode}>
                    <Popup>{marker.popUp}</Popup>
                </Marker>
            ))}
            <FitBoundsToMarkers markers={markers} />
        </MapContainer>
    );
};

export default LeafletMap;
