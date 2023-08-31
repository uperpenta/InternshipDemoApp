import  { useState } from 'react';
import axios from 'axios';

function ListInstruments() {
  const [instruments, setInstruments] = useState([]);
  const [fetching, setFetching] = useState(false);

  const fetchInstruments = async () => {
    try {
      setFetching(true); 
      const response = await axios.get('http://localhost:8080/api/instruments');
      setInstruments(response.data);
    } catch (error) {
      console.error('Error fetching instruments:', error);
    } finally {
      setFetching(false);
    }
  };

  return (
    <div>
      <h2>List of Instruments</h2>
      <button onClick={fetchInstruments} disabled={fetching}>
        {fetching ? 'Fetching...' : 'Fetch Instruments'}
      </button>
      {instruments.length > 0 && (
        <ul>
          {instruments.map((instrument) => (
            <li key={instrument.id}>
              <strong>ID:</strong> {instrument.id}<br />
              <strong>ISIN:</strong> {instrument.isin}<br />
              <strong>Currency:</strong> {instrument.currency}<br />
              <strong>Type:</strong> {instrument.type}<br />
              <strong>Description:</strong> {instrument.description}<br />
              <strong>Effective Date:</strong> {instrument.effectiveDate}<br />
              <strong>Status:</strong> {instrument.status}<br />
              <hr />
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default ListInstruments;
