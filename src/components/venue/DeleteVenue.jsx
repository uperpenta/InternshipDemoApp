import  { useState } from 'react';
import axios from 'axios';

function DeleteVenue() {
  const [venueId, setVenueId] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await axios.delete(`http://localhost:8080/api/venues/${venueId}`);
      if (res.status === 200) {
        setMessage(`Venue with ID ${venueId} deleted successfully`);
        setVenueId('');
      } else {
        setMessage('Error occurred while deleting venue');
      }
    } catch (err) {
      setMessage(`Error deleting venue: ${err.message}`);
    }
  };

  return (
    <div>
      <h2>Delete Venue</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Venue ID:
          <input
            type="text"
            value={venueId}
            onChange={(e) => setVenueId(e.target.value)}
          />
        </label>
        <button type="submit">Delete Venue</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default DeleteVenue;
