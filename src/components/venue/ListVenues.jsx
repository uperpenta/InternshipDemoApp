import  { useState, } from 'react';
import axios from 'axios';


export default function ListVenues() {
    const [data, setData] = useState([]);


    async function fetchData() {
        try {
            const response = await axios.get('http://localhost:8080/api/venues');
            setData(response.data);

        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }


    return (
        <div>
            <h2>Venue List</h2>
            <button onClick={fetchData}>List Venues</button>
            
            <ul>
                {data.map(venue => (
                    <li key={venue.id}>{venue.id}, {venue.name}, {venue.city}, {venue.country}</li>
                ))}
            </ul>
        </div>);
}