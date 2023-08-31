import  { useState } from 'react';
import axios from 'axios';

function GetIssuerByInstrument() {
  const [instrumentId, setInstrumentId] = useState('');
  const [issuer, setIssuer] = useState({});
  const [errorMessage, setErrorMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.get(`http://localhost:8080/api/instruments/${instrumentId}/issuer`);
      setIssuer(response.data);
      setErrorMessage('');
    } catch (error) {
      console.error('Error fetching issuer:', error);
      setIssuer({});
      setErrorMessage('Error fetching issuer');
    }
  };

  return (
    <div>
      <h2>Get Issuer by Instrument</h2>
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
        <button type="submit">Fetch Issuer</button>
      </form>
      <div>
        <h3>Issuer Information</h3>
        <p>ID: {issuer.id}</p>
        <p>LEI: {issuer.lei}</p>
        <p>Legal Name: {issuer.legalName}</p>
        <p>Description: {issuer.description}</p>
        <p>{errorMessage}</p>
      </div>
    </div>
  );
}

export default GetIssuerByInstrument;
