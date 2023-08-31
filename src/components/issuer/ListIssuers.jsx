import  { useState } from 'react';
import axios from 'axios';

function ListIssuers() {
  const [issuers, setIssuers] = useState([]);

  const fetchIssuers = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/issuers');
      setIssuers(response.data);
    } catch (error) {
      console.error('Error fetching issuers:', error);
    }
  };

  return (
    <div>
      <h2>List Issuers</h2>
      <button onClick={fetchIssuers}>Fetch Issuers</button>
      <ul>
        {issuers.map((issuer) => (
          <li key={issuer.id}>
            <p>ID: {issuer.id}</p>
            <p>Legal Name: {issuer.legalName}</p>
            <p>LEI: {issuer.lei}</p>
            <p>Description : {issuer.description}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ListIssuers;
