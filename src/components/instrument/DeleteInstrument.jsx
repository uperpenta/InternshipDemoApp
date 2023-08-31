import  { useState } from 'react';
import axios from 'axios';

function DeleteInstrument() {
  const [instrumentId, setInstrumentId] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.delete(`http://localhost:8080/api/instruments/${instrumentId}`);
      if (response.status === 200) {
        setMessage('Instrument deleted successfully');
      } else {
        setMessage('Error deleting instrument');
      }
    } catch (error) {
      console.error('Error deleting instrument:', error);
      setMessage('Error deleting instrument');
    }
  };

  return (
    <div>
      <h2>Delete Instrument</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Instrument ID:
          <input
            type="text"
            value={instrumentId}
            onChange={(event) => setInstrumentId(event.target.value)}
          />
        </label>
        <br />
        <button type="submit">Delete Instrument</button>
      </form>
      <div>{message}</div>
    </div>
  );
}

export default DeleteInstrument;
