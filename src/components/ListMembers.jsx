import  { useState, } from 'react';
import axios from 'axios';


export default function ListMembers() {
    const [data, setData] = useState([]);


    async function fetchData() {
        try {
            const response = await axios.get('http://localhost:8080/api/members');
            setData(response.data);

        } catch (error) {
            console.error('Error fetching data:', error);
        }


    }


    return (
        <div>
            <h2>Member List</h2>
            <button onClick={fetchData}>List Members</button>
            
            <ul>
                {data.map(member => (
                    <li key={member.id}>{member.id}, {member.legalName}, {member.lei}, {member.description}, {member.address}</li>
                ))}
            </ul>
        </div>);
}