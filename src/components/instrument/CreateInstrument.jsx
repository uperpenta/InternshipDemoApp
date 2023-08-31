import  { useState } from 'react';
import axios from 'axios';

function CreateInstrument() {
  const [instrumentData, setInstrumentData] = useState({
    isin: '',
    currency: '',
    type: '',
    description: '',
    effectiveDate: '',
    status: '',
  });
  const [message, setMessage] = useState('');
  const [submitting, setSubmitting] = useState(false);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setInstrumentData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setSubmitting(true);

    try {
      const response = await axios.post('http://localhost:8080/api/instruments', instrumentData);
      if (response.status === 200 || response.status===201) {
        setMessage('Instrument created successfully');
        setInstrumentData({
          isin: '',
          currency: '',
          type: '',
          description: '',
          effectiveDate: '',
          status: '',
        });
      } else {
        setMessage('Error creating instrument');
      }
    } catch (error) {
      console.error('Error creating instrument:', error);
      setMessage('Error creating instrument');
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div>
      <h2>Create Instrument</h2>
      <form onSubmit={handleSubmit}>
        <label>
          ISIN:
          <input
            type="text"
            name="isin"
            value={instrumentData.isin}
            onChange={handleInputChange}
            required
          />
        </label>
        <label>
          Currency:
          <input
            type="text"
            name="currency"
            value={instrumentData.currency}
            onChange={handleInputChange}
            required
          />
        </label>
        <label>
          Type:
          <input
            type="text"
            name="type"
            value={instrumentData.type}
            onChange={handleInputChange}
            required
          />
        </label>
        <label>
          Description:
          <input
            type="text"
            name="description"
            value={instrumentData.description}
            onChange={handleInputChange}
            required
          />
        </label>
        <label>
          Effective Date:
          <input
            type="text"
            name="effectiveDate"
            value={instrumentData.effectiveDate}
            onChange={handleInputChange}
            required
          />
        </label>
        <label>
          Status:
          <input
            type="text"
            name="status"
            value={instrumentData.status}
            onChange={handleInputChange}
            required
          />
        </label>
        <button type="submit" disabled={submitting}>
          {submitting ? 'Creating...' : 'Create Instrument'}
        </button>
      </form>
      <div className="message">{message}</div>
    </div>
  );
}

export default CreateInstrument;


