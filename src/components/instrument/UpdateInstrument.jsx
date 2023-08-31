import  { useState } from 'react';
import axios from 'axios';

function UpdateInstrument() {
  const [instrumentId, setInstrumentId] = useState('');
  const [currency, setCurrency] = useState('');
  const [type, setType] = useState('');
  const [description, setDescription] = useState('');
  const [effectiveDate, setEffectiveDate] = useState('');
  const [status, setStatus] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    const updatedInstrument = {
      currency,
      type,
      description,
      effectiveDate,
      status,
    };

    try {
      const response = await axios.put(`http://localhost:8080/api/instruments/${instrumentId}`, updatedInstrument);
      if (response.status === 200) {
        setMessage('Instrument updated successfully');
      } else {
        setMessage('Error updating instrument');
      }
    } catch (error) {
      console.error('Error updating instrument:', error);
      setMessage('Error updating instrument');
    }
  };

  return (
    <div>
      <h2>Update Instrument</h2>
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
          Currency:
          <input
            type="text"
            value={currency}
            onChange={(event) => setCurrency(event.target.value)}
          />
        </label>
        <br />
        <label>
          Type:
          <input
            type="text"
            value={type}
            onChange={(event) => setType(event.target.value)}
          />
        </label>
        <br />
        <label>
          Description:
          <input
            type="text"
            value={description}
            onChange={(event) => setDescription(event.target.value)}
          />
        </label>
        <br />
        <label>
          Effective Date:
          <input
            type="text"
            value={effectiveDate}
            onChange={(event) => setEffectiveDate(event.target.value)}
          />
        </label>
        <br />
        <label>
          Status:
          <input
            type="text"
            value={status}
            onChange={(event) => setStatus(event.target.value)}
          />
        </label>
        <br />
        <button type="submit">Update Instrument</button>
      </form>
      <div>{message}</div>
    </div>
  );
}

export default UpdateInstrument;
