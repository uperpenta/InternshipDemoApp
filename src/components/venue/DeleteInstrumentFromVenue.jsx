import  { useState } from 'react';
import axios from 'axios';

function DeleteInstrumentFromVenue() {
  const [instrumentId, setInstrumentId] = useState('');
  const [venueId, setVenueId] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.delete(`http://localhost:8080/api/venues/${venueId}/instruments/${instrumentId}`);
      if (res.status === 200) {
        setMessage(`Instrument with ID ${instrumentId} deleted from venue with ID ${venueId}`);
        setInstrumentId('');
        setVenueId('');
      } else {
        setMessage('Error occurred while deleting instrument from venue');
      }
    } catch (err) {
      setMessage(`Error deleting instrument: ${err.message}`);
    }
  };

  return (
    <div>
      <h2>Delete Instrument from Venue</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Instrument ID:
          <input
            type="text"
            value={instrumentId}
            onChange={(e) => setInstrumentId(e.target.value)}
          />
        </label>
        <label>
          Venue ID:
          <input
            type="text"
            value={venueId}
            onChange={(e) => setVenueId(e.target.value)}
          />
        </label>
        <button type="submit">Delete Instrument</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default DeleteInstrumentFromVenue;
