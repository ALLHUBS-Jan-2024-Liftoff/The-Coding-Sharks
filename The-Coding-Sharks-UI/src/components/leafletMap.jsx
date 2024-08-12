import React, { useEffect, useState } from "react";
import axios from "axios";
import { MapContainer, TileLayer, Marker, Popup, useMap } from "react-leaflet";

const FitBoundsToMarkers = ({ markers }) => {
  const map = useMap();
  useEffect(() => {
      if (markers.length > 0) {
          const bounds = L.latLngBounds(markers.map(marker => marker.geocode));
          map.fitBounds(bounds);
      }
  }, [markers, map]);

  return null;
};

const LeafletMap = () => {
    const [markers, setMarkers] = useState([]);

  useEffect(() => {
      axios.get('http://localhost:8080/api/destinations')
          .then(response => {
              if (Array.isArray(response.data)) {
                  setMarkers(response.data.map(destination => ({
                      geocode: [destination.latitude, destination.longitude],
                      popUp: destination.name
                  })));
              } else {
                  console.error('Unexpected response format:', response.data);
              }
          })
          .catch(error => {
              console.error('Error fetching data:', error);
          });
  }, []);
  
    return (
        <MapContainer center={[38.6270, -90.1994]} zoom={10} style={{ height: "70vh", width: "70vw" }}>
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

