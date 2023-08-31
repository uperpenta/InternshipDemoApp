import  { useState } from 'react';
import axios from 'axios';

function UpdateVenue() {
  const [venueId, setVenueId] = useState('');
  const [name, setName] = useState('');
  const [city, setCity] = useState('');
  const [country, setCountry] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    const venueData = {
      name,
      city,
      country,
    };

    try {
      const res = await axios.put(`http://localhost:8080/api/venues/${venueId}`, venueData);
      if (res.status === 200) {
        setMessage(`Venue with ID ${venueId} updated successfully`);
        setName('');
        setCity('');
        setCountry('');
      } else {
        setMessage('Error occurred while updating venue');
      }
    } catch (err) {
      setMessage(`Error updating venue: ${err.message}`);
    }
  };

  return (
    <div>
      <h2>Update Venue</h2>
      <form className="margine" onSubmit={handleSubmit}>
        <label>
          Venue ID:
          <input
            type="text"
            value={venueId}
            onChange={(e) => setVenueId(e.target.value)}
          />
        </label>
        <label>
          Name:
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </label>
        <label>
          City:
          <input
            type="text"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />
        </label>
        <label>
          Country:
          <input
            type="text"
            value={country}
            onChange={(e) => setCountry(e.target.value)}
          />
        </label>
        <button type="submit">Update Venue</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
}

export default UpdateVenue;
