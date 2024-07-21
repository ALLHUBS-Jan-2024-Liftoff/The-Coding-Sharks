import React, { useRef } from "react";
import { MapContainer, TileLayer, Marker} from "react-leaflet";
import "leaflet/dist/leaflet.css";
import { Icon, marker, popup } from "leaflet";

const LeafletMap = () => {
    const mapRef = useRef(null);
    
    //using placeholder locations which will actually come from the Database later
    const markers = [
        {
            geocode: [38.6516758442689, -90.25925641100909],
            popUp: "LaunchCode HQ"
        },
        {
            geocode: [38.6377976058171, -90.28211059392594], 
            popUp: "STL Zoo"
        },
        {
            geocode: [38.75801729531715, -90.35330027799324],
            popUp: "STL International Airport"
        }
    ];
  
    return ( 
        <MapContainer center={[38.6270, -90.1994]} zoom={13} ref={mapRef} style={{height: "70vh", width: "70vw"}}>
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          
          {markers.map(marker => (
            <Marker position ={marker.geocode}>
            </Marker>
          ))}

        </MapContainer>
    );
  };
  
  export default LeafletMap;
