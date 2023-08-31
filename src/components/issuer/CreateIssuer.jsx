import  { useState } from 'react';
import axios from 'axios';

function CreateIssuer() {
  const [LEI, setLEI] = useState('');
  const [legalName, setLegalName] = useState('');
  const [description, setDescription] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = {
      LEI,
      legalName,
      description,
    };

    try {
      const response = await axios.post('http://localhost:8080/api/issuers', formData);
      if (response.status === 201) {
        setMessage('Issuer created successfully');
      } else {
        setMessage('Error creating issuer');
      }
    } catch (error) {
      console.error('Error creating issuer:', error);
      setMessage('Error creating issuer');
    }
  };

  return (
    <div>
      <h2>Create Issuer</h2>
      <form onSubmit={handleSubmit}>
        <label>
          LEI:
          <input type="text" value={LEI} onChange={(e) => setLEI(e.target.value)} />
        </label>
        <br />
        <label>
          Legal Name:
          <input type="text" value={legalName} onChange={(e) => setLegalName(e.target.value)} />
        </label>
        <br />
        <label>
          Description:
          <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} />
        </label>
        <br />
        <button type="submit">Create Issuer</button>
      </form>
      <div>{message}</div>
    </div>
  );
}

export default CreateIssuer;
