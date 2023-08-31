import { useState } from 'react';
import axios from 'axios';

function GetInstrumentsByVenue() {
  const [venueId, setVenueId] = useState('');
  const [instruments, setInstruments] = useState([]);

  const fetchInstrumentsByVenue = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/venues/${venueId}/instruments`);
      setInstruments(response.data);
    } catch (error) {
      console.error('Error fetching instruments:', error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    fetchInstrumentsByVenue();
  };

  return (
    <div>
      <h2>Get Instruments by Venue</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Venue ID:
          <input
            type="text"
            value={venueId}
            onChange={(event) => setVenueId(event.target.value)}
          />
        </label>
        <button type="submit">Fetch Instruments</button>
      </form>
      <ul>
        {instruments.map((instrument) => (
          <li key={instrument.id}>{instrument.id},{instrument.currency}, {instrument.type}, {instrument.description}, {instrument.effectiveDate}</li>
        ))}
      </ul>
    </div>
  );
}

export default GetInstrumentsByVenue;
