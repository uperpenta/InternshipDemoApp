import  { useState } from 'react';
import axios from 'axios';

function DeleteIssuer() {
  const [id, setId] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.delete(`http://localhost:8080/api/issuers/${id}`);
      if (response.status === 200) {
        setMessage('Issuer deleted successfully');
      } else {
        setMessage('Error deleting issuer');
      }
    } catch (error) {
      console.error('Error deleting issuer:', error);
      setMessage('Error deleting issuer');
    }
  };

  return (
    <div>
      <h2>Delete Issuer</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Issuer ID:
          <input
            type="text"
            value={id}
            onChange={(event) => setId(event.target.value)}
          />
        </label>
        <br />
        <button type="submit">Delete Issuer</button>
      </form>
      <div>{message}</div>
    </div>
  );
}

export default DeleteIssuer;
