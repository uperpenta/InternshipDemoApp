import  { useState } from 'react';
import axios from 'axios';

function AddIssuerToInstrument() {
  const [instrumentId, setInstrumentId] = useState('');
  const [issuerId, setIssuerId] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.put(`http://localhost:8080/api/instruments/${instrumentId}/issuer/${issuerId}`);
      if (response.status === 200) {
        setMessage('Issuer added to instrument successfully');
      } else {
        setMessage('Error adding issuer to instrument');
      }
    } catch (error) {
      console.error('Error adding issuer to instrument:', error);
      setMessage('Error adding issuer to instrument');
    }
  };

  return (
    <div>
      <h2>Add Issuer to Instrument</h2>
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
        <label>
          Issuer ID:
          <input
            type="text"
            value={issuerId}
            onChange={(event) => setIssuerId(event.target.value)}
          />
        </label>
        <br />
        <button type="submit">Add Issuer</button>
      </form>
      <div>{message}</div>
    </div>
  );
}

export default AddIssuerToInstrument;
