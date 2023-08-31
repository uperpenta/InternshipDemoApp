import  { useState } from 'react';
import axios from 'axios';

function UpdateIssuer() {
  const [id, setId] = useState('');
  const [legalName, setLegalName] = useState('');
  const [description, setDescription] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = {
      legalName,
      description,
    };

    try {
      const response = await axios.put(`http://localhost:8080/api/issuers/${id}`, formData);
      if (response.status === 200) {
        setMessage('Issuer updated successfully');
      } else {
        setMessage('Error updating issuer');
      }
    } catch (error) {
      console.error('Error updating issuer:', error);
      setMessage('Error updating issuer');
    }
  };

  return (
    <div>
      <h2>Update Issuer</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Issuer ID:
          <input type="text" value={id} onChange={(e) => setId(e.target.value)} />
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
        <button type="submit">Update Issuer</button>
      </form>
      <div>{message}</div>
    </div>
  );
}

export default UpdateIssuer;
